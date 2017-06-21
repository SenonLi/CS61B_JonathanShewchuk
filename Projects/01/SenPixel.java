/**
 * Created by SenonLi on 6/20/2017.
 */
public class SenPixel {
    private short red, green, blue;

    public SenPixel()   { red = 0; green = 0; blue = 0;  }
    public SenPixel(int r, int g, int b) {
        red     = (short) r;
        green   = (short) g;
        blue    = (short) b;
    }
    public String toString () { return red + ", " + green + ", " + blue; }
    public void setRed(int r)   { red = (short) r;}
    public void setGreen(int g) { green = (short) g;}
    public void setBlue(int b)  { blue = (short) b;}

}
