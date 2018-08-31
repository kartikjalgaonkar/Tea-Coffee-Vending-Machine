package com.fwf.tcvm.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fwf.tcvm.dao.ContainerDAO;
import com.fwf.tcvm.dao.MaterialDAO;
import com.fwf.tcvm.pojo.ContainerType;
import com.fwf.tcvm.pojo.Item;
import com.fwf.tcvm.pojo.ItemType;
import com.fwf.tcvm.pojo.Material;

public class ContainerServiceImpl implements ContainerService{
	MaterialDAO materialDAO ;
	public ContainerServiceImpl() {
		this.materialDAO = new MaterialDAO();
	}
	
	
	@Override
	public boolean getOrder(Item item,int quantity){
		List<Material> listOfMaterial = materialDAO.getMaterialList(item.getItemType());
		if(checkAvalabilityInContainer(listOfMaterial, quantity)){
			return true;
		}
		return false;
			
	}

	@Override
	public void resetContainer() {
		ContainerDAO.resetQuantity();
		System.out.println("All the containers have been filled completely!!");
	}
	
	@Override
	public boolean checkAvalabilityInContainer(List<Material> listOfMaterial, int quantity) {
		
		Map<String, Double> mapOfContents = getMapOfContent(listOfMaterial);
		if(teaContainerQuantity(mapOfContents,quantity)>=0 && waterContainerQuantity(mapOfContents,quantity) >= 0 && sugarContainerQuantity(mapOfContents,quantity) >=0 && milkContainerQuantity(mapOfContents,quantity) >= 0 && coffeeContainerQuantity(mapOfContents,quantity) >= 0){
			
			return true;
		}
			
		return false;
	}
	@Override
	public void getContainerStatus() {
		System.out.println("Quantity available in Tea Container is "+ContainerDAO.teaContainerAvailableQuantity);
		System.out.println("Quantity available in Coffee Container is "+ContainerDAO.coffeeContainerAvailableQuantity);
		System.out.println("Quantity available in Sugar Container is "+ContainerDAO.sugarContainerAvailableQuantity);
		System.out.println("Quantity available in Water Container is "+ContainerDAO.waterContainerAvailableQuantity);
		System.out.println("Quantity available in Milk Container is "+ContainerDAO.milkContainerAvailableQuantity);
	}

	@Override
	public void getWasteOfMaterial(List<Material> listOfMaterial, int quantity) {
		Map<String, Double> wastedMaterial = new LinkedHashMap<String, Double>(); 
		for(Material material : listOfMaterial){
			wastedMaterial.put(material.getMaterial(), material.getWasteOfMaterial());
		}
		
		ContainerDAO.waterWasted = ContainerDAO.waterWasted + Optional.ofNullable(wastedMaterial.get("water")).orElse(0.0)*quantity;
		ContainerDAO.teaWasted = ContainerDAO.teaWasted + Optional.ofNullable(wastedMaterial.get("tea")).orElse(0.0)*quantity;
		ContainerDAO.sugarWasted = ContainerDAO.sugarWasted + Optional.ofNullable(wastedMaterial.get("sugar")).orElse(0.0)*quantity;
		ContainerDAO.milkWasted = ContainerDAO.milkWasted + Optional.ofNullable(wastedMaterial.get("milk")).orElse(0.0)*quantity;
		ContainerDAO.coffeeWasted = ContainerDAO.coffeeWasted + Optional.ofNullable(wastedMaterial.get("coffee")).orElse(0.0)*quantity;
	}
	
	private double teaContainerQuantity(Map<String, Double> mapOfContents, int quantity){
		return ContainerDAO.teaContainerAvailableQuantity-Optional.ofNullable(mapOfContents.get("tea")).orElse(0.0)*quantity;
	}
	private double waterContainerQuantity(Map<String, Double> mapOfContents, int quantity){
		return ContainerDAO.waterContainerAvailableQuantity-Optional.ofNullable(mapOfContents.get("water")).orElse(0.0)*quantity;
	}
	private double sugarContainerQuantity(Map<String, Double> mapOfContents, int quantity){
		return ContainerDAO.sugarContainerAvailableQuantity-Optional.ofNullable(mapOfContents.get("sugar")).orElse(0.0)*quantity;
	}
	private double milkContainerQuantity(Map<String, Double> mapOfContents, int quantity){
		return ContainerDAO.milkContainerAvailableQuantity-Optional.ofNullable(mapOfContents.get("milk")).orElse(0.0)*quantity;
	}
	private double coffeeContainerQuantity(Map<String, Double> mapOfContents, int quantity){
		return ContainerDAO.coffeeContainerAvailableQuantity-Optional.ofNullable(mapOfContents.get("coffee")).orElse(0.0)*quantity;
	}
	
	private Map<String, Double> getMapOfContent(List<Material> listOfMaterial){
		Map<String, Double> mapOfContents = new LinkedHashMap<String, Double>(); 
		
		for(Material material : listOfMaterial){
			mapOfContents.put(material.getMaterial(), material.getConsumptionOfMaterial());
		}
		return mapOfContents;
	}
	@Override
	public boolean refillContainer(ContainerType containerType, Double refillAmount) {
		
		Boolean refillStatus = false;
		
		if(containerType.equals(ContainerType.Milk)){
			if(ContainerDAO.milkContainerCapacity - ContainerDAO.milkContainerAvailableQuantity >= refillAmount){
				ContainerDAO.milkContainerAvailableQuantity += refillAmount;
				ContainerDAO.refillCounterForMilkContainer++;
				refillStatus = true;
			}
				
		} 
		
		if(containerType.equals(ContainerType.Water)){
			if(ContainerDAO.waterContainerCapacity - ContainerDAO.waterContainerAvailableQuantity >= refillAmount){
				ContainerDAO.waterContainerAvailableQuantity += refillAmount; 
				ContainerDAO.refillCounterForWaterContainer++;
				refillStatus = true;
			}
				
		}
		
		if(containerType.equals(ContainerType.Sugar)){
			if(ContainerDAO.sugarContainerCapacity - ContainerDAO.sugarContainerAvailableQuantity >= refillAmount){
				ContainerDAO.sugarContainerAvailableQuantity += refillAmount;
				ContainerDAO.refillCounterForSugarContainer++;
				refillStatus = true;
			}
		}
		
		if(containerType.equals(ContainerType.Coffee)){
			if(ContainerDAO.coffeeContainerCapacity - ContainerDAO.coffeeContainerAvailableQuantity >= refillAmount){
				ContainerDAO.coffeeContainerAvailableQuantity += refillAmount; 
				ContainerDAO.refillCounterForCoffeeContainer++;
				refillStatus = true;
			}
		}
		
		if(containerType.equals(ContainerType.Tea)){
			if(ContainerDAO.teaContainerCapacity - ContainerDAO.teaContainerAvailableQuantity >= refillAmount){
				ContainerDAO.teaContainerAvailableQuantity += refillAmount; 
				ContainerDAO.refillCounterForTeaContainer++;
				refillStatus = true;
			}
		}
		
		return refillStatus;
		
	}

	@Override
	public void updateContainerQuantityAfterProductIsDispensed(ItemType itemType, int quantity) {
		Map<String, Double> mapOfContents = getMapOfContent(materialDAO.getMaterialList(itemType));
		ContainerDAO.teaContainerAvailableQuantity = teaContainerQuantity(mapOfContents,quantity);
		ContainerDAO.waterContainerAvailableQuantity = waterContainerQuantity(mapOfContents,quantity);
		ContainerDAO.sugarContainerAvailableQuantity = sugarContainerQuantity(mapOfContents,quantity);
		ContainerDAO.milkContainerAvailableQuantity = milkContainerQuantity(mapOfContents,quantity);
		ContainerDAO.coffeeContainerAvailableQuantity = coffeeContainerQuantity(mapOfContents,quantity);
		getWasteOfMaterial(materialDAO.getMaterialList(itemType), quantity);
		
	}

}
