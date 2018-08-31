package com.fwf.tcvm.service;

import java.util.Map;

import com.fwf.tcvm.dao.ContainerDAO;
import com.fwf.tcvm.dao.ItemDespenseDAO;

public class ReportServiceImpl implements ReportService {

	@Override
	public void generateTeaCoffeeRportDrinkwise(Map<String, Integer> mapOfItemAndQuantity) {
		ItemDespenseDAO itemDespenseDAO = new ItemDespenseDAO();
		
		Map<String, Double> itemCostMap = itemDespenseDAO.getMapOfItemAndCost();
		System.out.println("***********************************");
		System.out.println("Item        Quantity     Cost");
		if (!mapOfItemAndQuantity.isEmpty()) {
			for (Map.Entry<String, Integer> map : mapOfItemAndQuantity.entrySet()) {
				if(itemCostMap.containsKey(map.getKey())){
					System.out.println(map.getKey()+"        "+map.getValue()+"       "+itemCostMap.get(map.getKey())*map.getValue());
					
				}
			}
			System.out.println("***********************************");
		}
	}

	@Override
	public void generateTotalTeaCoffeeReport(Map<String, Integer> mapOfItemAndQuantity) {
		int cupQuantity = 0;
		double totalSale = 0.0;
		ItemDespenseDAO itemDespenseDAO = new ItemDespenseDAO();
		Map<String, Double> itemCostMap = itemDespenseDAO.getMapOfItemAndCost();
		if (!mapOfItemAndQuantity.isEmpty()) {
			for (Map.Entry<String, Integer> map : mapOfItemAndQuantity.entrySet()) {
				cupQuantity = cupQuantity + map.getValue();
				if(itemCostMap.containsKey(map.getKey())){
					totalSale = totalSale + itemCostMap.get(map.getKey())*map.getValue();
					
				}
			}
			System.out.println("\n********** Total Tea-Coffee Sale **********");
			System.out.println("total cup sold: "+cupQuantity + "\nTotal cost: "+totalSale);
			System.out.println("********************************************");
		
	}
		}

	@Override
	public void containerStatusReport() {
		ContainerService containerService = new ContainerServiceImpl();
		containerService.getContainerStatus();
	}

	@Override
	public void refillingCounterStatus() {
		System.out.println("*************************************");
		System.out.println("Container status for each of the container is: ");
		System.out.println("Tea container Status: "+ContainerDAO.refillCounterForTeaContainer);
		System.out.println("Water container Status: "+ContainerDAO.refillCounterForWaterContainer);
		System.out.println("Sugar container Status: "+ContainerDAO.refillCounterForSugarContainer);
		System.out.println("Coffee container Status: "+ContainerDAO.refillCounterForCoffeeContainer);
		System.out.println("Milk container Status: "+ContainerDAO.refillCounterForMilkContainer);

	}

}
