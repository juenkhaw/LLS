import java.util.Scanner;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Hello World!");
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		System.out.println("Hello World!");
		System.out.println(new Patron("TEST", "TEST", "TEST", "TEST", 99.99).toString());
	}
}
