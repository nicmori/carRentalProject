package controller;


import java.util.function.Predicate;
import model.Car;
import model.Resource;

public class Load {

	public static void Load_CarList() {
		
		Car.Builder cb = new Car.Builder();
		
		/* Sam Dankberg [sdankber - 1204912224] Cars */
		Resource.CAR_LIST.add(cb.carMake("Honda").carModel("Accord").carMPG(26).carCategoryType(Resource.CarCategoryType.COUPE).build());
		Resource.CAR_LIST.add(cb.carMake("Ford").carModel("Escape").carMPG(23).carCategoryType(Resource.CarCategoryType.CROSSOVER).build());
		Resource.CAR_LIST.add(cb.carMake("Toyota").carModel("Tacoma").carMPG(19).carCategoryType(Resource.CarCategoryType.TRUCK).build());				
	
		//Ryan_Fehnel
		Resource.CAR_LIST.add(cb.carMake("Toyota").carModel("Prius").carMPG(58).carCategoryType(Resource.CarCategoryType.HYBRID).build());        
		Resource.CAR_LIST.add(cb.carMake("Nissan").carModel("NV200").carMPG(24).carCategoryType(Resource.CarCategoryType.VAN).build());        
		Resource.CAR_LIST.add(cb.carMake("Hyundai").carModel("Tucson").carMPG(26).carCategoryType(Resource.CarCategoryType.SUV).build());
	
		//Nic Mori
		Resource.CAR_LIST.add(cb.carMake("Kia").carModel("Sorento").carMPG(21).carCategoryType(Resource.CarCategoryType.SUV).build());        
		Resource.CAR_LIST.add(cb.carMake("Subaru").carModel("Outback").carMPG(25).carCategoryType(Resource.CarCategoryType.CROSSOVER).build());        
		Resource.CAR_LIST.add(cb.carMake("Mazda").carModel("Mazda6").carMPG(26).carCategoryType(Resource.CarCategoryType.SEDAN).build());
	
	}
	
	/**
	 * Removes all Cards from Cost and Recall Lists
	 */
	public static void Load_RemoveCars() {
		Load_RemoveCostList();
		Load_RemoveRecallList();
		
		//Testing  BEFORE removal
		if(Main.DEBUG) System.out.println("DEBUG RESOURCE.CAR_LIST before and after recall/cost reduction");
		if(Main.DEBUG) Resource.CAR_LIST.forEach(c -> System.out.println("\t" + c.toString()));
		
		Resource.REMOVE_COST_LIST.forEach(str -> Load_RemoveMake(str));
		Resource.REMOVE_RECALL_LIST.forEach(str -> Load_RemoveMake(str));
		
		//Testing  AFTER removal
		if(Main.DEBUG) Resource.CAR_LIST.forEach(c -> System.out.println("\t\t" + c.toString()));
	}
	
	
	/***
	 * Load_RemoveMake
	 * @param make - string
	 * removes param from car list if exists
	 */
	public static void Load_RemoveMake(String make) {
		
		Predicate<Car> removePredicate = c -> c.getCarMake().toLowerCase().equals(make.toLowerCase());
		Resource.CAR_LIST.removeIf(removePredicate);
	}
	
	/**
	 * Spec Revision - Remove due to Cost
	 */
	private static void Load_RemoveCostList() {
		Resource.REMOVE_COST_LIST.add("Aston Martin");
		Resource.REMOVE_COST_LIST.add("Audi");
		Resource.REMOVE_COST_LIST.add("Bentley");
		Resource.REMOVE_COST_LIST.add("BMW");
		Resource.REMOVE_COST_LIST.add("Ferrari");
		Resource.REMOVE_COST_LIST.add("Freightliner");
		Resource.REMOVE_COST_LIST.add("Genesis");
		Resource.REMOVE_COST_LIST.add("INFINITI");
		Resource.REMOVE_COST_LIST.add("Jeep");
		Resource.REMOVE_COST_LIST.add("Jaguar");
		Resource.REMOVE_COST_LIST.add("Lamborghini");
		Resource.REMOVE_COST_LIST.add("Land Rover");
		Resource.REMOVE_COST_LIST.add("Lexus");
		Resource.REMOVE_COST_LIST.add("Lincoln");
		Resource.REMOVE_COST_LIST.add("Lotus");
		Resource.REMOVE_COST_LIST.add("Maserati");
		Resource.REMOVE_COST_LIST.add("McLaren");
		Resource.REMOVE_COST_LIST.add("MINI");
		Resource.REMOVE_COST_LIST.add("Mercedes-Benz");
		Resource.REMOVE_COST_LIST.add("Porshe");
		Resource.REMOVE_COST_LIST.add("Rolls-Royce");
		Resource.REMOVE_COST_LIST.add("Tesla");
		Resource.REMOVE_COST_LIST.add("Volvo");
	}
	
	
	/**
	 * Spec Revision - Remove due to Recall
	 */
	private static void Load_RemoveRecallList() {
		Resource.REMOVE_RECALL_LIST.add("Acura");
		Resource.REMOVE_RECALL_LIST.add("Buick");
		Resource.REMOVE_RECALL_LIST.add("Scion");
		Resource.REMOVE_RECALL_LIST.add("Cadillac");
		Resource.REMOVE_RECALL_LIST.add("Snart");
		Resource.REMOVE_RECALL_LIST.add("Subaru");
		Resource.REMOVE_RECALL_LIST.add("Toyota");
		Resource.REMOVE_RECALL_LIST.add("Mazda");
	}
} //end class Load
