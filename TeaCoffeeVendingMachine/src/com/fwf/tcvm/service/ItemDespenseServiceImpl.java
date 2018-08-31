package com.fwf.tcvm.service;


import com.fwf.tcvm.dao.ItemDespenseDAO;
import com.fwf.tcvm.pojo.ItemType;

public class ItemDespenseServiceImpl implements ItemDespenseService{
	ContainerService containerService;
	ItemDespenseDAO itemDespenseDAO;
	public ItemDespenseServiceImpl() {
		this.containerService = new ContainerServiceImpl();
		this.itemDespenseDAO = new ItemDespenseDAO();
	}

	@Override
	public boolean checkInputAmount(double enteredAmount, double actualAmount) {
		if(enteredAmount==actualAmount){
			return true;
			
		}
		return false;
	}

	@Override
	public double calculateAmount(ItemType itemType, int quantity) {
		
		return itemDespenseDAO.getItemAndCost(itemType)*quantity;
	}

	@Override
	public void dispenseItem(int quanity, ItemType itemType) {
		
		containerService.updateContainerQuantityAfterProductIsDispensed(itemType,quanity);
		System.out.println("Please have your "+quanity+" cup of "+itemType.getType());
		
	}
	
}
