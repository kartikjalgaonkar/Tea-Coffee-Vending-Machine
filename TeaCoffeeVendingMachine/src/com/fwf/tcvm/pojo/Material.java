package com.fwf.tcvm.pojo;

public class Material {
	
	
	
	private ItemType itemType;
	private String material;
	private double consumptionOfMaterial;
	private double wasteOfMaterial;

	public Material(ItemType itemType, String material, double consumptionOfMaterial, double wasteOfMaterial) {
		super();
		this.itemType = itemType;
		this.material = material;
		this.consumptionOfMaterial = consumptionOfMaterial;
		this.wasteOfMaterial = wasteOfMaterial;
	}

	public ItemType getItemType() {
		return itemType;
	}

/*	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
*/
	public String getMaterial() {
		return material;
	}

/*	public void setMaterial(String material) {
		this.material = material;
	}*/

	public double getConsumptionOfMaterial() {
		return consumptionOfMaterial;
	}

/*	public void setConsumptionOfMaterial(double consumptionOfMaterial) {
		this.consumptionOfMaterial = consumptionOfMaterial;
	}
*/
	public double getWasteOfMaterial() {
		return wasteOfMaterial;
	}

/*	public void setWasteOfMaterial(double wasteOfMaterial) {
		this.wasteOfMaterial = wasteOfMaterial;
	}*/

}
