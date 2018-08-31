package com.fwf.tcvm.service;

import java.util.List;

import com.fwf.tcvm.pojo.ContainerType;
import com.fwf.tcvm.pojo.Item;
import com.fwf.tcvm.pojo.ItemType;
import com.fwf.tcvm.pojo.Material;

public interface ContainerService {
	public boolean getOrder(Item item,int quantity);
	public boolean checkAvalabilityInContainer(List<Material> listOfMaterial,int quantity);
	public void getContainerStatus();
	public void getWasteOfMaterial(List<Material> listOfMaterial,int quantity);
	public void resetContainer();
	public boolean refillContainer(ContainerType containerType, Double refillAmount);
	public void updateContainerQuantityAfterProductIsDispensed(ItemType itemType, int quanity);
}
