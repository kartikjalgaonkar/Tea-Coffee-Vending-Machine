package com.fwf.tcvm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fwf.tcvm.pojo.ItemType;
import com.fwf.tcvm.pojo.Material;

public class MaterialDAO {

	
	List<Material> listOfMaterial = new ArrayList<>();
	
	public void addMaterialToList(){
		listOfMaterial.add(new Material(ItemType.TEA, "tea", 5, 1));
		listOfMaterial.add(new Material(ItemType.TEA, "water", 60, 5));
		listOfMaterial.add(new Material(ItemType.TEA, "milk", 40, 4));
		listOfMaterial.add(new Material(ItemType.TEA, "sugar", 15, 2));
		
		listOfMaterial.add(new Material(ItemType.BLACK_TEA, "tea", 3, 0));
		listOfMaterial.add(new Material(ItemType.BLACK_TEA, "water", 100, 12));
		listOfMaterial.add(new Material(ItemType.BLACK_TEA, "sugar", 15, 2));
		
		listOfMaterial.add(new Material(ItemType.COFFE, "coffee", 4, 1));
		listOfMaterial.add(new Material(ItemType.COFFE, "water", 20, 3));
		listOfMaterial.add(new Material(ItemType.COFFE, "milk", 80, 8));
		listOfMaterial.add(new Material(ItemType.COFFE, "sugar", 15, 2));

		listOfMaterial.add(new Material(ItemType.BLACK_COFFEE, "coffee", 3, 0));
		listOfMaterial.add(new Material(ItemType.BLACK_COFFEE, "water", 100, 12));
		listOfMaterial.add(new Material(ItemType.BLACK_COFFEE, "sugar", 15, 2));
	}
	
	public List<Material> getMaterialList(ItemType itemType){
		addMaterialToList();
		return listOfMaterial.stream().filter(n->n.getItemType().getType().equals(itemType.getType())).collect(Collectors.toList());
	}
	
}
