import java.util.Scanner;
import java.io.IOException;
import java.util.Date;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Hello World!");
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		System.out.println("Hello World!");
		
		//Testing Objects
		Patron patr = new Patron("Stella", "Jln ABC", "11700", "012-3456789", 0);
		Resource resc = new Resource("Finding Dory", "Disney", "2016-6-10");
		
		//Testing Outputs
		System.out.println(patr.toString()+"\n"+resc.toString());
	}
}
