package controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;

import model.Car;
import model.Resource;
import view.View;

public class Controller {

	private static Controller instance = null;
	
	private View view;
	
	//Empty Constructor
	private Controller() {
		this(null);
	}
	
	//Overload Param Constructor to set Instance var View
	private Controller(View v) {
		this.view = v;
	}
	
	//Converts String to Integer, returns -1 if fail
	private int inputToInteger(String input) {
		if(Main.DEBUG) view.print("inputToInt DEBUG: " + input + "\n");
		try {
			return Integer.parseInt(input.trim());
		}
		catch(Exception e) {
			return -1;
		}
	}
	
	//Note, Min/Max can not be -1 [seen as error values]
	private String validateInteger(int input, int possibleMin, int possibleMax) {
		String result = null;
		if(input != -1) {
			if(possibleMin != -1) {
				result = (input < possibleMin) ? "Error - input: "+input+" greater than "+possibleMin : null;
			}
			if(possibleMax != -1) {
				result = (input > possibleMax) ? "Error - input: "+input+" less than "+possibleMax : null;
			}
		}
		else {
			result = "Error - Input must be an integer [can not be -1]";
		}

		return result;
	}
	
	//Aquires a single integer between a min/max. Loops until returns with valid value
	private int fetchInteger(String request, int min, int max) {
		int integer;
		String validate;
		boolean valid = false;
		
		do {
			integer = this.inputToInteger(this.view.in(request));
			validate = this.validateInteger(integer, min, max);
			if(validate == null) {
				valid = true;
			}
			else {
				valid = false;
				this.view.print(validate + "\n");
			}
		} while(!valid);
		return integer;
	}
	
	//Finds all possible cars based on input
	private void calcCar(int pass, int days, int miles) { 
		
		//index 0 -> car
		//index 1 -> rental per day cost
		//index 2 -> cost for mpg
		ArrayList<Object[]> validCarList = new ArrayList<Object[]>();
					
		//GET VALID CARS (fits enough people) [0]
		for(Car c : Resource.CAR_LIST) {
			
			//Sets Car / Cost per  day / cost for mpg
			if(c.getCarCategory().getMaxPassengers() >= pass) {
				Object[] o = new Object[3];
				o[0] = c;
				o[1] = c.getCarCategory().getRentalCostPerDay() * days;
				o[2] = (double)miles / c.getCarMPG() * Resource.GAS_PRICE_PER_GALLON;
				validCarList.add(o);
			}
		}
		
		//APPLY DISCOUNTS
		for(Object[] o : validCarList) {
			Car c = (Car)o[0];
			
			//trip is 6 days or longer
			if(days  >= 6) {
				
				//Category is Intermediate or Standard
				if( c.getCarCategory().getType() == Resource.INTERMEDIATE.getType()
						|| c.getCarCategory().getType() == Resource.STANDARD.getType()) {
					
					//Every 6th day is free. So days/6 finds how many mulitples of 6, * costPerDay and subtract from total trip cost
					o[1] = (double)o[1] - (c.getCarCategory().getRentalCostPerDay() * (days / 6) );
				}
			}	
		}

		
		//APPLY EXTRA CHARGES
		for(Object[] o : validCarList) {
			Car c = (Car)o[0];
			if ( c.getCarMake() == "Honda"){
				if (c.getCarCategoryType().toString() == "SEDAN" 
						|| c.getCarCategoryType().toString() == "HYBRID" 
						|| c.getCarCategoryType().toString() == "SUV") {
					o[1] = (double)o[1] * 1.1;
				}
			}
			else if (c.getCarMake() == "Chevrolet" 
					|| c.getCarMake() == "Chrysler" 
					|| c.getCarMake() == "Ford" 
					|| c.getCarMake() == "Dodge" 
					|| c.getCarMake() == "GMC") {
				o[1] = (double)o[1] * 1.05;
			}
		}
		
		
		//FIND CHEAPEST OPTIONS TODO
		double cheapestPoor = 0;
		double cheapestMedium = 0;
		double cheapestGood = 0;
		
		//Find cheapest of each tier
		for(Object[] o : validCarList) {
			Car c = (Car)o[0];
			double total = (double)o[1] + (double)o[2];
			if( c.getCarCategory().getCarComfort() == Resource.CarComfort.POOR ) {
				if(cheapestPoor == 0 || total < cheapestPoor) {
					cheapestPoor = total;
				}
			}
			else if( c.getCarCategory().getCarComfort() == Resource.CarComfort.MEDIUM ) {
				if( cheapestMedium == 0 || total < cheapestMedium ) {
					cheapestMedium = total;
				}
			}
			else if( c.getCarCategory().getCarComfort() == Resource.CarComfort.GOOD ) {
				if( cheapestGood == 0 || total < cheapestGood ) {
					cheapestGood = total;
				}
			}
		}
		
		//Remove any car more expensive than cheapest from each tier
		//Remove All Poor Quality cars if Medium or Good quality is cheaper
		//Remove All Medium Quality cars if  Good quality is cheaper
		
		Iterator<Object[]> itr = validCarList.iterator();
		while(itr.hasNext()) {
			Object[] o = itr.next();
			Car  c = (Car)o[0];
			double total = (double)o[1] + (double)o[2];
			if( c.getCarCategory().getCarComfort() == Resource.CarComfort.POOR ) {
				if( total > cheapestPoor || cheapestPoor > cheapestMedium || cheapestPoor > cheapestGood) {
					itr.remove();
				}
			}
			else if( c.getCarCategory().getCarComfort() == Resource.CarComfort.MEDIUM ) {
				if( total > cheapestMedium || cheapestMedium > cheapestGood) {
					itr.remove();
				}
			}
			else if( c.getCarCategory().getCarComfort() == Resource.CarComfort.GOOD ) {
				if( total > cheapestGood ) {
					itr.remove();
				}
			}
		}
		
		
		//SEND TO OUTPUT
		this.fetchOutput(validCarList);
	}

	
	//Calls view function to print output of possible cars
	private void fetchOutput(ArrayList<Object[]> possibleOutput) {
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		this.view.print("\nRental Options\n--------------");
		
		for(Object[] o : possibleOutput) {
			Car c = (Car)o[0];
			
			this.view.print("\n\nQuality        : " + c.getCarCategory().getCarComfort().toString()
						  + "\nMake           : " + c.getCarMake()
						  + "\nModel          : " + c.getCarModel()
						  + "\nMax Passengers : " + c.getCarCategory().getMaxPassengers()
						  + "\nCost to Rent   : " + formatter.format((double)o[1])
						  + "\nCost for Gas   : " + formatter.format((double)o[2])
						  + "\nTotal Trip Cost: " + formatter.format(((double)o[1] + (double)o[2])));
		}
	}
	
	/**
	 * Controllers Public function
	 */
	public void fetchInput() {
		int pass;
		int days;
		int miles;
		
		this.view.print("Welcome to Car Rental\n---------------------");
		pass  = this.fetchInteger("Enter a number of passengers   : ", 1, 7);
		days  = this.fetchInteger("Enter a number of rental days  : ", 1, -1);
		miles = this.fetchInteger("Enter a number of approx milage: ", 1, -1);
		this.view.closeScanner();
		
		this.calcCar(pass, days, miles);
	}
	
	

	
	/**
	 * GetInstance -> Singleton
	 * @return
	 */
	public static Controller getInstance() {
		if(instance ==  null) {
			instance = new Controller();
		}
		return instance;
	}
	
	public static Controller getInstance(View v) {
		if(instance ==  null) {
			instance = new Controller(v);
		}
		return instance;
	}
	
} //end class Controller