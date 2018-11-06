package model;


import java.util.ArrayList;

public class Resource {

	/**
	 * Constants
	 */
	
	/* Generic Gas price for each gallon */
	public static final double GAS_PRICE_PER_GALLON = 2.25;
	
	/* Car Category Types with static rental data */
	public static final CarCategory ECONOMY 		= new CarCategory("Economy", 45, 4, Resource.CarComfort.POOR);
	public static final CarCategory INTERMEDIATE 	= new CarCategory("Intermediate", 50, 4, Resource.CarComfort.MEDIUM);
	public static final CarCategory STANDARD 		= new CarCategory("Standard", 55, 5, Resource.CarComfort.GOOD);
	public static final CarCategory VAN 			= new CarCategory("Van", 70, 7, Resource.CarComfort.MEDIUM);
	
	/* Car List*/
	public static final ArrayList<Car> CAR_LIST					= new ArrayList<Car>();
	public static final ArrayList<String> REMOVE_RECALL_LIST 	= new ArrayList<String>();
	public static final ArrayList<String> REMOVE_COST_LIST 		= new ArrayList<String>();
	
	
	/**
	 * Enums
	 */
	
	/* Quality of a car */
	public static enum CarComfort {
		POOR, MEDIUM, GOOD
	}
	
	/* Category that a can can belong to */
	public static enum CarCategoryType {
		SUV, CROSSOVER, SEDAN, TRUCK, COUPE, HYBRID, VAN, MINIVAN
	}
	
} //end class Resource
