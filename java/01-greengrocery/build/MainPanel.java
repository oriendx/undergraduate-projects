import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainPanel extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
    private static final long serialVersionUID = 1L;
    private int level;
    private Container picholder = this.getContentPane();
    private JPanel mainPanel;
    private JMenuItem[] theItem;
    private JMenu[] allMenu;
    private JMenuBar bar;
    private ImageIcon icon;
    private MainPanel.Painter pic;
    private String name;
    private static int WIDTH = 1000;
    private static int HEIGHT = 700;
    public Map<String, Stock> items = new HashMap();
    private Map<String, StockContainer> panels = new HashMap();

    public MainPanel() {
        super("ShopMap");
        this.picholder.setLayout(new BorderLayout(5, 5));
        this.mainPanel = new JPanel(new GridLayout(3, 2, 50, 50));
        this.theItem = new JMenuItem[9];
        this.allMenu = new JMenu[3];
        this.bar = new JMenuBar();
        this.level = 3;
        this.initPanel("in.txt");
        this.initMenu();
        this.picholder.add(this.bar, "North");
        this.picholder.add(this.mainPanel, "Center");
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
    }

    public void loadFile(String filename) {
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                StringTokenizer t = new StringTokenizer(line, " ");
                String name = t.nextToken();
                if (!name.toLowerCase().equals("type")) {
                    int num = Integer.parseInt(t.nextToken());
                    boolean b = Boolean.parseBoolean(t.nextToken());
                    int x = Integer.parseInt(t.nextToken());
                    int y = Integer.parseInt(t.nextToken());
                    this.items.put(name, new Stock(name, num, b, x, y));
                }
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        }

    }

    public void initPanel(String name) {
        this.loadFile(name);
        Iterator var2 = this.items.keySet().iterator();

        while(var2.hasNext()) {
            String n = (String)var2.next();
            Stock sk = (Stock)this.items.get(n);
            int offx = sk.posx;
            int offy = sk.posy;
            String image = "images/" + n + ".png";
            int num = sk.number;
            StockContainer sc = new StockContainer(image, offx, offy, num);
            sc.setSize(sc.getPreferredSize());
            if (sk.display) {
                sc.setVisible(true);
            } else {
                sc.setVisible(false);
            }

            this.panels.put(n, sc);
            this.mainPanel.add(sc);
        }

        this.mainPanel.setSize(WIDTH, HEIGHT);
    }

    public void initMenu() {
        String[] menu = new String[]{"File", "Display", "Items"};
        char[] men = new char[]{'F', 'p', 'S'};
        String[] item = new String[]{"Open File", "Print Stock List", "Save & Exit", "Add Display Case", "Remove Display Case", "Purchase Items", "Restock Items"};

        int i;
        for(i = 0; i < item.length; ++i) {
            this.theItem[i] = new JMenuItem(item[i]);
            this.theItem[i].addActionListener(this);
        }

        for(i = 0; i < menu.length; ++i) {
            this.allMenu[i] = new JMenu(menu[i]);
            this.allMenu[i].setMnemonic(men[i]);
            this.bar.add(this.allMenu[i]);
        }

        for(i = 0; i < item.length; ++i) {
            if (i < 3) {
                this.allMenu[0].add(this.theItem[i]);
            }

            if (i < 5 && i >= 3) {
                this.allMenu[1].add(this.theItem[i]);
            }

            if (i >= 5) {
                this.allMenu[2].add(this.theItem[i]);
            }
        }

    }

    public void open() {
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(0);
        int result = file.showOpenDialog(this);
        if (result != 1) {
            File get = file.getSelectedFile();
            if (get != null && !get.getName().equals("")) {
                this.name = get.getAbsolutePath();
                Iterator var4 = this.panels.keySet().iterator();

                while(var4.hasNext()) {
                    String name = (String)var4.next();
                    this.mainPanel.remove((Component)this.panels.get(name));
                }

                this.initPanel(this.name);
                this.setSize(WIDTH, HEIGHT);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid File Name", "Error", 0);
            }

        }
    }

    private void printStocks() {
        System.out.println("Type       Number Display PositionX PositionY");
        System.out.println("------------------------------------------");
        Iterator var1 = this.items.keySet().iterator();

        while(var1.hasNext()) {
            String name = (String)var1.next();
            System.out.println(this.items.get(name));
        }

    }

    private void saveFile() {
        try {
            File outfile = new File("in.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(outfile));
            writer.write("Type       Number Display PositionX PositionY\n");
            Iterator var3 = this.items.keySet().iterator();

            while(var3.hasNext()) {
                String name = (String)var3.next();
                writer.write(((Stock)this.items.get(name)).toString());
                writer.write("\n");
            }

            writer.flush();
            writer.close();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    private String selectCase() {
        String[] choices = (String[])this.items.keySet().toArray(new String[this.items.size()]);
        String input = (String)JOptionPane.showInputDialog((Component)null, "Choose cases...", "The Choice of a Display case", 3, (Icon)null, choices, choices[1]);
        return input == null ? "" : input;
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("Open File")) {
            this.open();
        } else if (event.getActionCommand().equals("Print Stock List")) {
            this.printStocks();
        } else if (event.getActionCommand().equals("Save & Exit")) {
            this.saveFile();
            System.exit(0);
        } else {
            String input;
            if (event.getActionCommand() == "Add Display Case") {
                input = this.selectCase();
                if (!"".equals(input) && this.panels.containsKey(input)) {
                    ((StockContainer)this.panels.get(input)).setVisible(true);
                    this.repaint();
                }
            } else if (event.getActionCommand() == "Remove Display Case") {
                input = this.selectCase();
                if (!"".equals(input) && this.panels.containsKey(input)) {
                    ((StockContainer)this.panels.get(input)).setVisible(false);
                }
            } else if (event.getActionCommand() == "Purchase Items") {
                input = this.selectCase();
                if (!"".equals(input) && this.panels.containsKey(input)) {
                    String sx = JOptionPane.showInputDialog("Input Number:");

                    try {
                        int x = Integer.parseInt(sx);
                        if (((Stock)this.items.get(input)).number > x) {
                            ((StockContainer)this.panels.get(input)).purchase(x);
                            Stock var10000 = (Stock)this.items.get(input);
                            var10000.number -= x;
                        }
                    } catch (Exception var5) {
                        var5.printStackTrace();
                    }
                }

                this.repaint();
            } else if (event.getActionCommand() == "Restock Items") {
                input = this.selectCase();
                if (!"".equals(input) && this.panels.containsKey(input)) {
                    ((StockContainer)this.panels.get(input)).restock();
                    ((Stock)this.items.get(input)).number = 30;
                }

                this.repaint();
            }
        }

    }

    public void mouseClicked(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();
    }

    public void mousePressed(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();
    }

    public void mouseReleased(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();
    }

    public void mouseEntered(MouseEvent event) {
    }

    public void mouseExited(MouseEvent event) {
    }

    public void mouseDragged(MouseEvent event) {
    }

    public void mouseMoved(MouseEvent event) {
    }

    public static void main(String[] args) {
        new MainPanel();
    }

    public class Painter extends JPanel {
        private static final long serialVersionUID = 1L;

        public Painter() {
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(MainPanel.this.icon.getImage(), 0, 0, 100, 100, this);
        }
    }
}
