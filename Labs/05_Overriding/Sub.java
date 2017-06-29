
public class Sub extends Super {
	
	public int getX() {
		x += 1;
		return x;
	}
	
	public static void main(String[] args)	{
		Sub sub = new Sub();
		Super sup = sub;
		int x = sub.super.getX();
		System.out.println("test x = " + x);
		
	}
}