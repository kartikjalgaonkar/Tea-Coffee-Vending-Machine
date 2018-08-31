package com.fwf.tcvm.service;

import com.fwf.tcvm.pojo.ItemType;

public interface ItemDespenseService {
	public double calculateAmount(ItemType itemType, int quantity);
	public boolean checkInputAmount(double enteredAmount, double costOfItem);
	public void dispenseItem(int quanity, ItemType itemType);
}
