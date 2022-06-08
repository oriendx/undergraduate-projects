public class Stock {
    public String name;
    public int number;
    public boolean display;
    public int posx;
    public int posy;

    public Stock(String na, int nu, boolean d, int x, int y) {
        name = na;
        number = nu;
        display = d;
        posx = x;
        posy = y;
    }

    @Override
    public String toString() {
        String ret = name + " " + String.valueOf(number);
        if (display) ret += " true";
        else ret += " false";

        ret += " " + String.valueOf(posx) + " " + String.valueOf(posy);

        String fmtstr = String.format("%10s %5d %5s %10d %10d",
                name, number, display, posx, posy);

        return fmtstr;
    }
}
