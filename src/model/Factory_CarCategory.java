package model;

import model.CarCategory;
import model.Resource;

public class Factory_CarCategory {

	public static CarCategory getCarCategory(Resource.CarCategoryType type) {
		switch(type) {
			case SUV:
				return Resource.STANDARD;
			case COUPE:
				return Resource.ECONOMY;
			case CROSSOVER:
				return Resource.STANDARD;
			case HYBRID:
				return Resource.INTERMEDIATE;
			case MINIVAN:
				return Resource.VAN;
			case SEDAN:
				return Resource.INTERMEDIATE;
			case TRUCK:
				return Resource.STANDARD;
			case VAN:
				return Resource.VAN;
		}
		return null;
	}
} //end class Factory_CarCategory
