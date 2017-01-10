import java.io.*;

class Nuke2{
	
	public static void main(String[] arg) throws Exception	{
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		
		String s = keyboard.readLine();
		String ss0 = s.substring(0, 1);
		
		//String ss2ToEnd = s.substring(2, s.length());
		String ss2ToEnd = s.substring(2);
		String noSecond = ss0 + ss2ToEnd;
		
		// System.out.println("ss0 = " + ss0);
		// System.out.println("s.length() = " + s.length());
		// System.out.println(ss2ToEnd);
		
		
		System.out.println(noSecond);
		
	}
}