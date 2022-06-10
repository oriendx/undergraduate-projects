import java.awt.*; 
import java.io.*;
import java.util.*;
import java.awt.event.*; 
import javax.swing.*;
import java.applet.Applet;

public class MainPanel 
	extends JFrame 
		implements ActionListener,MouseListener,MouseMotionListener
{
	private static final long serialVersionUID = 1L;

	private int level;

	private Container picholder;
	private JPanel mainPanel;
	private JMenuItem theItem[];
	private JMenu allMenu[];
	private JMenuBar bar;
	private ImageIcon icon;

	private Painter pic;
	private String name;

    private static int WIDTH = 1000;
    private static int HEIGHT = 700;

    public Map<String, Stock> items = new HashMap<String, Stock>();
	private Map<String, StockContainer> panels 
        = new HashMap<String, StockContainer>();

	public MainPanel()
	{
		super("ShopMap");

		picholder = getContentPane();
		picholder.setLayout( new BorderLayout( 5, 5 ) );
		mainPanel = new JPanel(new GridLayout( 3, 2, 50, 50));

		theItem = new JMenuItem[ 9 ];
		allMenu = new JMenu[ 3 ];
		bar = new JMenuBar();

		level = 3;

		initPanel("in.txt");
		initMenu();

		picholder.add( bar, BorderLayout.NORTH );
		picholder.add( mainPanel, BorderLayout.CENTER );

		addMouseListener( this );
		addMouseMotionListener( this );

		setSize(WIDTH, HEIGHT); 
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

    // read file
    public void loadFile(String filename) {
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                StringTokenizer t = new StringTokenizer(line, " ");
                String name = t.nextToken();

                //System.out.println(name);
                if (name.toLowerCase().equals("type")) continue;

                int num = Integer.parseInt(t.nextToken());
                boolean b = Boolean.parseBoolean(t.nextToken());
                int x = Integer.parseInt(t.nextToken());
                int y = Integer.parseInt(t.nextToken());

                items.put(name, new Stock(name, num, b, x, y));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        for (String name : items.keySet()) {
            System.out.println(name);
            System.out.println(items.get(name));
        }
        */
    }

	//initialize main panel
	public void initPanel( String name )
	{
        loadFile(name);

        for (String n : items.keySet()) {
            Stock sk = items.get(n);
            int offx = sk.posx;
            int offy = sk.posy;
            String image = "images/" + n + ".png";
            int num = sk.number;

            StockContainer sc = new StockContainer(image, 
                                                    offx, offy, num);
            sc.setSize(sc.getPreferredSize());

            if (sk.display) {
                sc.setVisible(true);
            } else {
                sc.setVisible(false);
            }

            panels.put(n, sc);
            mainPanel.add(sc);
        }

		mainPanel.setSize(WIDTH, HEIGHT);
	}

	//initialize menu
	public void initMenu()
	{
		String menu[] = { "File", "Display", "Items"};
		char men[] = { 'F', 'p', 'S' };
		String item[] = { "Open File","Print Stock List","Save & Exit",
            "Add Display Case","Remove Display Case",
            "Purchase Items","Restock Items"};

		for( int i=0; i<item.length; i++ )
		{
			theItem[ i ] = new JMenuItem( item[ i ] );
			theItem[ i ].addActionListener( this );
		}

		for( int i=0; i<menu.length; i++ )
		{
			allMenu[ i ] = new JMenu( menu[ i ] );
			allMenu[ i ].setMnemonic( men[ i ] );
			bar.add( allMenu[ i ] );
		}

		for( int i=0; i<item.length; i++ )
		{ 
			if( i<3 )allMenu[ 0 ].add( theItem[ i ] );
			if( i<5 && i>=3 )allMenu[ 1 ].add( theItem[ i ] );
			if( i>=5 )allMenu[ 2 ].add( theItem[ i ] );
		}
	} 

	public void open()
	{
		JFileChooser file = new JFileChooser();
		file.setFileSelectionMode( JFileChooser.FILES_ONLY );

		int result = file.showOpenDialog(this);
		if( result == JFileChooser.CANCEL_OPTION )
			return;
		File get = file.getSelectedFile();

		if( get == null || get.getName().equals("") ) {
			JOptionPane.showMessageDialog( this, "Invalid File Name" ,
                    "Error",JOptionPane.ERROR_MESSAGE );
        } else {
            //load images
			name = get.getAbsolutePath();
			//mainPanel.remove(wm);
			//mainPanel.remove(pic);

            for (String name : panels.keySet()) {
                mainPanel.remove(panels.get(name));
            }

			initPanel(name);
			setSize(WIDTH,HEIGHT);
		}
	}

    private void printStocks() {
        System.out.println("Type       Number Display PositionX PositionY");
        System.out.println("------------------------------------------");

        for (String name : items.keySet()) {
            System.out.println(items.get(name));
        }
    }

    private void saveFile() {
        try {
            File outfile = new File("in.txt");
            BufferedWriter writer = 
                new BufferedWriter(new FileWriter(outfile));
            writer.write("Type       Number Display PositionX PositionY\n");

            for (String name : items.keySet()) {
                writer.write(items.get(name).toString());
                writer.write("\n");
            }

            writer.flush();
            writer.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String selectCase() {
        String[] choices = items.keySet().toArray(new String[items.size()]);
        String input = (String) JOptionPane.showInputDialog(null, 
                "Choose cases...",
                "The Choice of a Display case", 
                JOptionPane.QUESTION_MESSAGE, null, // Use
                // default
                // icon
                choices, // Array of choices
                choices[1]); // Initial choice

        if (input == null) return "";

        return input;
    }

	//cases
	public void actionPerformed( ActionEvent event )
	{
		if ( event.getActionCommand().equals("Open File") ) {
            open();
        }
		else if( event.getActionCommand().equals("Print Stock List") ){
            printStocks();
        }
		else if( event.getActionCommand().equals("Save & Exit") ) {
            saveFile();
            System.exit(0);
        }
		else if( event.getActionCommand() == "Add Display Case" )
		{
            String input = selectCase();
            if (!"".equals(input) && panels.containsKey(input)) {
                panels.get(input).setVisible(true);
                repaint();
            }
		}
		else if( event.getActionCommand() == "Remove Display Case" )
		{
            String input = selectCase();
            //System.out.println(input);

            if (!"".equals(input) && panels.containsKey(input)) {
                panels.get(input).setVisible(false);
            }
		}
		else if( event.getActionCommand() == "Purchase Items" ){
            String input = selectCase();

            if (!"".equals(input) && panels.containsKey(input)) {
                String sx = JOptionPane.showInputDialog("Input Number:" );
                try {
                    int x = Integer.parseInt(sx);

                    if (items.get(input).number > x) {
                        panels.get(input).purchase(x);
                        items.get(input).number -= x;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            repaint();
		}
		else if( event.getActionCommand() == "Restock Items" ){
            String input = selectCase();

            if (!"".equals(input) && panels.containsKey(input)) {
                panels.get(input).restock();
                items.get(input).number = 30;
            }

            repaint();
        }
	}

	//cursor cases
	public void mouseClicked( MouseEvent event )
	{ 
		int x = event.getX();
		int y = event.getY();
	}

	public void mousePressed( MouseEvent event )
	{
		int x = event.getX();
		int y = event.getY();
	}

	public void mouseReleased( MouseEvent event )
	{
		int x = event.getX();
		int y = event.getY();
	}

	public void mouseEntered( MouseEvent event )
	{}
	public void mouseExited( MouseEvent event )
	{}

	public void mouseDragged( MouseEvent event )
	{}

	public void mouseMoved( MouseEvent event )
	{}

	public class Painter extends JPanel
	{
		private static final long serialVersionUID = 1L;
		public void paintComponent( Graphics g )
		{
			super.paintComponent(g);
			g.drawImage( icon.getImage(), 0,0, 100,100, this );
		}
	}

	public static void main( String args[] )
	{
		MainPanel game = new MainPanel();
	}
}
