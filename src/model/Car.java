package model;

import model.Car;
import model.CarCategory;
import model.Factory_CarCategory;
import model.Resource;
import model.Car.Builder;

public class Car {

	
	/**
	 * Builder
	 */
	public static class Builder {
		
		/**
		 * Builder Instance Variables
		 */
		private String 						_carMake;
		private String 						_carModel;
		private Resource.CarCategoryType	_carCategoryType;
		private CarCategory					_carCategory;				//HAS-A relationship
		private int							_carMPG;

		
		/**
		 * Constructor
		 */
		public Builder() {
			this._carMake			= null;
			this._carModel			= null;
			this._carCategoryType	= null;
			this._carCategory		= null;
			this._carMPG			= -1;
		}
		
		/**
		 * Build call
		 */
		public Car build() {
			return new Car(this);
		}
		
		/**
		 * Builder Setters
		 */
		public Builder carMake(String carMake) {
			this._carMake = carMake;
			return this;
		}
		
		public Builder carModel(String carModel) { 
			this._carModel = carModel;
			return this;
		}
		
		public Builder carCategoryType(Resource.CarCategoryType carCategoryType) {
			this._carCategoryType 	= carCategoryType;
			this._carCategory 		= Factory_CarCategory.getCarCategory(this._carCategoryType);
			return this;
		}
		
		public Builder carMPG(int mpg) {
			this._carMPG = mpg;
			return this;
		}
	} //end class Builder
	

	/**
	 * CarProperties constant Instance Variables
	 */
	private final String 					_CAR_MAKE;
	private final String 					_CAR_MODEL;
	private final Resource.CarCategoryType 	_CAR_CATEGORY_TYPE;
	private final CarCategory				_CAR_CATEGORY;			//HAS-A relationship
	private final int						_CAR_MPG;
	
	/**
	 * Constructor
	 */
	private Car(Builder builder) {
		this._CAR_MAKE 			= builder._carMake;
		this._CAR_MODEL 		= builder._carModel;
		this._CAR_CATEGORY_TYPE	= builder._carCategoryType;
		this._CAR_CATEGORY 		= builder._carCategory;
		this._CAR_MPG			= builder._carMPG;
	}
	
	/**
	 * Getters
	 */
	public String getCarMake() 								{ return this._CAR_MAKE; }
	public String getCarModel() 							{ return this._CAR_MODEL; }
	public Resource.CarCategoryType getCarCategoryType() 	{ return this._CAR_CATEGORY_TYPE; }
	public CarCategory getCarCategory() 					{ return this._CAR_CATEGORY; }
	public int getCarMPG()									{ return this._CAR_MPG; }
	
	//DEBUG output. Not formatted for Client
	public String toString() {
		return "| "+this._CAR_MAKE + " " + this._CAR_MODEL + " | mpg: " + this._CAR_MPG + " | type: " + this._CAR_CATEGORY_TYPE.toString() + " | category: " + this._CAR_CATEGORY.getType() + " |";
	}
	
} //end class CarProperties
