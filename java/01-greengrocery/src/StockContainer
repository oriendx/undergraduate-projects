import java.util.Timer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class StockContainer extends JPanel {

	private int picH,picW;
	private int height = 309, width = 98;
	private int rowI = 3,colI = 10;

	private int movX, movY; /* moved place */

	private ImageIcon img;
	private Image optImage;
	private Graphics bg;

    private int total = 30;

    private int startX, startY;

	public StockContainer()
	{
	}

	//construct funtions
	public StockContainer( String name, int offx, int offy, int count )
	{
        total = count;
		init(name,offx,offy);
	}

	//initialize parameters
	public void init( String name, int offx, int offy)
	{
        startX = offx;
        startY = offy;

		img = new ImageIcon( name );

		crowIeSize();

		picH = height/rowI;
		picW = width/colI;

		optImage = new BufferedImage( picW, picH, 1);
		bg = optImage.getGraphics();
		//bg.drawImage( img.getImage(), 0,0,picW, picH, this );
	}

	//image size
	public void crowIeSize()
	{
		double w = (double)img.getImage().getWidth( null );
		double h = (double)img.getImage().getHeight( null );

        width = 309;
        height = 98;
	}

	//draw
	void drawSeg(int x, int y, Graphics g)
	{
		g.drawImage(img.getImage(), 
                x*picW+startX, y*picH+startY, picW, picH, this);
	}

	public void paint( Graphics g )
	{
		/* draw */
		for (int i = 0; i < rowI; i++) {
			for (int j = 0; j < colI; j++) {
                if (i*colI+j < total) {
                    drawSeg(j, i, g);
                }
			}
		}

		g.setColor( Color.WHITE );

		/* draw square */
		for (int i = 0; i < rowI; i++) 
			for (int j = 0; j < colI; j++)
				g.drawRect(j*picW+startX, i*picH+startY, picW,picH);
	}

	//pannel size
	public Dimension getPreferredSize()
	{
		return new Dimension( width,height );
	}

	//pannel width hetght
	public int getSize( int x )
	{
		if(x==1) return width;
		else return height;
	}

	//quantity
	public void setTotal( int x )
	{
		total = x;

		//repaint
		repaint();
	}

    public void restock() {
        total = rowI*colI;
        repaint();
    }

    public void purchase(int x) {
        if (total > 0) {
            total -= x;
            repaint();
        }
    }
} 
