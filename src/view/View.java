package view;

import java.util.Scanner;

import controller.Main;

public class View {

	private static View instance;
	private Scanner scan;

	private View() {
		this.scan = new Scanner(System.in);
	}
	
	
	public String in(String request) {
		//Scanner reader = new Scanner(System.in);
		this.print(request);
		
		String input = "";
		input = scan.nextLine();
		
		if(Main.DEBUG) this.print("view.in DEBUG: " + input + "\n");
		
		//reader.close();
		return input;
		
	}
	
	public void closeScanner() {
		this.scan.close();
	}
	
	public void print(String toPrint) {
		System.out.print(toPrint);
	}
	
	/**
	 * Singleton getInstance
	 * @return
	 */
	public static View getInstance() {
		if(instance == null)
			instance = new View();
		return instance;
	}
}
