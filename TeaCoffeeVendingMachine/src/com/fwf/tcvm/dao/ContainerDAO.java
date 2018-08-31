package com.fwf.tcvm.dao;

import java.util.HashMap;
import java.util.Map;

public class ContainerDAO {
	public static Integer refillCounterForTeaContainer = 0;
	public static Integer refillCounterForCoffeeContainer = 0;
	public static Integer refillCounterForSugarContainer = 0;
	public static Integer refillCounterForWaterContainer = 0;
	public static Integer refillCounterForMilkContainer = 0;
	
	public static final Double teaContainerCapacity = 2000.0;
	public static final Double coffeeContainerCapacity = 2000.0;
	public static final Double sugarContainerCapacity = 8000.0;
	public static final Double waterContainerCapacity = 15000.0;
	public static final Double milkContainerCapacity = 10000.0;
	
	public static Double teaContainerAvailableQuantity = 2000.0;
	public static Double coffeeContainerAvailableQuantity = 2000.0;
	public static Double sugarContainerAvailableQuantity = 8000.0;
	public static Double waterContainerAvailableQuantity = 15000.0;
	public static Double milkContainerAvailableQuantity = 10000.0;
	
	public static Double teaWasted = 0.0;
	public static Double coffeeWasted = 0.0;
	public static Double sugarWasted= 0.0;
	public static Double waterWasted = 0.0;
	public static Double milkWasted = 0.0;
	
	public static void resetQuantity(){
		ContainerDAO.teaContainerAvailableQuantity = teaContainerCapacity;
		ContainerDAO.coffeeContainerAvailableQuantity = coffeeContainerCapacity;
		ContainerDAO.waterContainerAvailableQuantity = waterContainerCapacity;
		ContainerDAO.milkContainerAvailableQuantity=milkContainerCapacity;
		ContainerDAO.sugarContainerAvailableQuantity=sugarContainerCapacity;
	}
	
	public String getMapOfContainers(int inputChoice){
		Map<Integer, String> mapOfContainers = new HashMap<>();
		mapOfContainers.put(1, "Tea Container");
		mapOfContainers.put(2, "Coffee Container");
		mapOfContainers.put(3, "Sugar Container");
		mapOfContainers.put(4, "Water Container");
		mapOfContainers.put(5, "Milk Container");
		
		return mapOfContainers.get(inputChoice);
	}
		
	
	
	
}
