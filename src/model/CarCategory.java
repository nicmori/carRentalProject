package model;

import model.Resource;

public class CarCategory {
	
	private String 						_type;
	private double 						_rentalCostPerDay;
	private int 						_maxPassengers;
	private Resource.CarComfort 		_carComfort;

	
	public CarCategory(String type, double rentalCostPerDay, 
			int maxPassengers, Resource.CarComfort carComfort) {
		this._type 				= type;
		this._rentalCostPerDay 	= rentalCostPerDay;
		this._maxPassengers 	= maxPassengers;
		this._carComfort 		= carComfort;
	}
	
	/**
	 * Getters
	 */
	public String getType() 							{ return this._type; }
	public double getRentalCostPerDay() 				{ return this._rentalCostPerDay; }
	public int getMaxPassengers() 						{ return this._maxPassengers; }
	public Resource.CarComfort getCarComfort() 			{ return this._carComfort; }
	
} //end class CarCategory
