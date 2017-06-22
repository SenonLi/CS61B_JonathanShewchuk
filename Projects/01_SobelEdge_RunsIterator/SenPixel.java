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

    public int getSquareSum()
    {
        return red*red + blue*blue + green*green;
    }
    public String toString () { return red + ", " + green + ", " + blue; }
    public boolean equals(SenPixel other)    {
        if (other == null)   return false;
        return red==other.red && blue==other.blue && green==other.green;
    }

    public void setRed(int r)   { red = (short) r;}
    public void setGreen(int g) { green = (short) g;}
    public void setBlue(int b)  { blue = (short) b;}

    public short getRed()   { return red;}
    public short getGreen() { return green;}
    public short getBlue()  { return blue;}

    public void addToRed(short addNum)  { red   += addNum; }
    public void addToGreen(short addNum){ green += addNum; }
    public void addToBlue(short addNum) { blue  += addNum; }

    public static void main(String[] args)  {
        char a = 255, b = 255;
        System.out.println("a = " + a + ", \t b = " + b);
        long c = (a * a + b * b);
        System.out.println("c = " + c);
    }
}
