package controller;

import controller.Controller;
import controller.Load;
import view.View;

public class Main {

	public static final boolean DEBUG = false;
	
	public static void main(String[] args)  {
		
		Load.Load_CarList();
		Load.Load_RemoveCars();
		
		Controller controller = Controller.getInstance(View.getInstance());
		controller.fetchInput();
	}
} //end class Main
