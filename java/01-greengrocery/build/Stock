public class Stock {
    public String name;
    public int number;
    public boolean display;
    public int posx;
    public int posy;

    public Stock(String na, int nu, boolean d, int x, int y) {
        this.name = na;
        this.number = nu;
        this.display = d;
        this.posx = x;
        this.posy = y;
    }

    public String toString() {
        String ret = this.name + " " + this.number;
        if (this.display) {
            ret = ret + " true";
        } else {
            ret = ret + " false";
        }

        (new StringBuilder()).append(ret).append(" ").append(String.valueOf(this.posx)).append(" ").append(String.valueOf(this.posy)).toString();
        String fmtstr = String.format("%10s %5d %5s %10d %10d", this.name, this.number, this.display, this.posx, this.posy);
        return fmtstr;
    }
}
