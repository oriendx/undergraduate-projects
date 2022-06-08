import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class StockContainer extends JPanel {
    private int picH;
    private int picW;
    private int height = 309;
    private int width = 98;
    private int rowI = 3;
    private int colI = 10;
    private int movX;
    private int movY;
    private ImageIcon img;
    private Image optImage;
    private Graphics bg;
    private int total = 30;
    private int startX;
    private int startY;

    public StockContainer() {
    }

    public StockContainer(String name, int offx, int offy, int count) {
        this.total = count;
        this.init(name, offx, offy);
    }

    public void init(String name, int offx, int offy) {
        this.startX = offx;
        this.startY = offy;
        this.img = new ImageIcon(name);
        this.crowIeSize();
        this.picH = this.height / this.rowI;
        this.picW = this.width / this.colI;
        this.optImage = new BufferedImage(this.picW, this.picH, 1);
        this.bg = this.optImage.getGraphics();
    }

    public void crowIeSize() {
        double w = (double)this.img.getImage().getWidth((ImageObserver)null);
        double h = (double)this.img.getImage().getHeight((ImageObserver)null);
        this.width = 309;
        this.height = 98;
    }

    void drawSeg(int x, int y, Graphics g) {
        g.drawImage(this.img.getImage(), x * this.picW + this.startX, y * this.picH + this.startY, this.picW, this.picH, this);
    }

    public void paint(Graphics g) {
        int i;
        int j;
        for(i = 0; i < this.rowI; ++i) {
            for(j = 0; j < this.colI; ++j) {
                if (i * this.colI + j < this.total) {
                    this.drawSeg(j, i, g);
                }
            }
        }

        g.setColor(Color.WHITE);

        for(i = 0; i < this.rowI; ++i) {
            for(j = 0; j < this.colI; ++j) {
                g.drawRect(j * this.picW + this.startX, i * this.picH + this.startY, this.picW, this.picH);
            }
        }

    }

    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }

    public int getSize(int x) {
        return x == 1 ? this.width : this.height;
    }

    public void setTotal(int x) {
        this.total = x;
        this.repaint();
    }

    public void restock() {
        this.total = this.rowI * this.colI;
        this.repaint();
    }

    public void purchase(int x) {
        if (this.total > 0) {
            this.total -= x;
            this.repaint();
        }

    }
}
