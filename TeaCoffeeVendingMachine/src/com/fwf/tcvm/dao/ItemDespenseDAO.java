package com.fwf.tcvm.dao;

import java.util.HashMap;
import java.util.Map;

import com.fwf.tcvm.pojo.ItemType;

public class ItemDespenseDAO {

	public double getItemAndCost(ItemType itemType){
		Map<ItemType, Double> itemCostMap = new HashMap<>();
		itemCostMap.put(ItemType.TEA, 10d);
		itemCostMap.put(ItemType.BLACK_TEA, 5d);
		itemCostMap.put(ItemType.COFFE, 15d);
		itemCostMap.put(ItemType.BLACK_COFFEE, 10d);
		
		return itemCostMap.get(itemType);
	}
	
	public Map<String, Double> getMapOfItemAndCost(){
		Map<String, Double> itemCostMap = new HashMap<>();
		itemCostMap.put("tea", 10d);
		itemCostMap.put("black tea", 5d);
		itemCostMap.put("coffee", 15d);
		itemCostMap.put("black coffee", 10d);
		return itemCostMap;
	}
}
