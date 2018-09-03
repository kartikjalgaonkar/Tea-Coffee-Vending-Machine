package com.fwf.tcvm.controller;

import java.util.HashMap;
import java.util.Map;

import com.fwf.tcvm.pojo.ContainerType;
import com.fwf.tcvm.pojo.Item;
import com.fwf.tcvm.pojo.ItemType;
import com.fwf.tcvm.service.ContainerService;
import com.fwf.tcvm.service.ContainerServiceImpl;
import com.fwf.tcvm.service.ItemDespenseService;
import com.fwf.tcvm.service.ItemDespenseServiceImpl;
import com.fwf.tcvm.service.ReportService;
import com.fwf.tcvm.service.ReportServiceImpl;

public class TCVMController {

	InputScanner scanner;
	ItemDespenseService itemDespenseService;
	ContainerServiceImpl containerServiceImpl;
	Item item;
	ReportService reportService;
	static Map<String, Integer> mapOfItemAndQuantity = new HashMap<>();

	public TCVMController() {
		scanner = new InputScanner();
		itemDespenseService = new ItemDespenseServiceImpl();
		containerServiceImpl = new ContainerServiceImpl();
		item =  new Item();
		reportService = new ReportServiceImpl();
	}

	public TCVMController(InputScanner scanner, ItemDespenseService itemDespenseService,
			ContainerServiceImpl containerServiceImpl, Item item, ReportService reportService) {
		super();
		this.scanner = scanner;
		this.itemDespenseService = itemDespenseService;
		this.containerServiceImpl = containerServiceImpl;
		this.item = item;
		this.reportService = reportService;
	}

	public String viewMenu() {
		System.out.println(
				"1. Tea\n2. Black Tea\n3. Coffee\n4. Black Coffee\n5. Refill Container\n6. Check Total Sale\n7. Container Status\n8. Reset Container\n9. Exit TCVM");
		String inputChoice = scanner.getString();
		return inputChoice;

	}

	public int getDrinksQuantity() {

		System.out.println("Please enter the quantity");
		return Integer.parseInt(scanner.getString());

	}

	public void callOption() {
		System.out.println("Please select option from the following");
		String inputChoice = viewMenu();
		int quantity = 0;
		if (Integer.parseInt(inputChoice) <= 4 && Integer.parseInt(inputChoice) > 0)
			quantity = getDrinksQuantity();

		switch (inputChoice) { 
		case "1":
			item.setItemType(ItemType.TEA);
			checkOrder(item, quantity);
			callMenuAgain();

			break;

		case "2":
			item.setItemType(ItemType.BLACK_TEA);
			checkOrder(item, quantity);
			callMenuAgain();
			break;

		case "3":
			item.setItemType(ItemType.COFFE);
			checkOrder(item, quantity);
			callMenuAgain();
			break;

		case "4":
			item.setItemType(ItemType.BLACK_COFFEE);
			checkOrder(item, quantity);
			callMenuAgain();
			break;

		case "5":
			Integer containerType = selectRefillContainer();
			refillContainer(ContainerType.getById(containerType));
			callMenuAgain();
			break;
			//refillContainer();
			//break;

		case "6":
			getReport();
			callMenuAgain();
			break;
			
		case "7":
			containerServiceImpl.getContainerStatus();
			callMenuAgain();
			break;

		case "8":
			containerServiceImpl.resetContainer();
			callMenuAgain();
			break;
			
		case "9":
			System.out.println("You have exited from the system");
			break;

		default: 
			System.out.println("Please select valid choice");
			callMenuAgain();
			break;

		}

	}

	public void callMenuAgain() {
		System.out.println("Please press 0 to go back to main menu else press 1");
		int a = scanner.nextInt();
		if (a == 0) {
			callOption();
		}
	}

	public void checkOrder(Item item, int quantity) {

		double costOfItem = 0;
		try {
			if (containerServiceImpl.getOrder(item, quantity)) {
				costOfItem = itemDespenseService.calculateAmount(item.getItemType(), quantity);
				System.out.println("Please enter Rs. " + costOfItem);
				double enteredAmount = scanner.nextDouble();
				if (itemDespenseService.checkInputAmount(enteredAmount, costOfItem)) {
					getItemAndItsQuantity(item.getItemType(), quantity);
					itemDespenseService.dispenseItem(quantity, item.getItemType());

				} else {
					throw new RuntimeException("Please enter correct amount");
				}
			} else {
				throw new RuntimeException("Not Enough Material available!! Please try again!!");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public Map<String, Integer> getItemAndItsQuantity(ItemType itemType, int quanity) {
		if (mapOfItemAndQuantity.containsKey(itemType.getType()))
			mapOfItemAndQuantity.put(itemType.getType(), mapOfItemAndQuantity.get(itemType.getType()) + quanity);
		else
			mapOfItemAndQuantity.put(itemType.getType(), quanity);
		return mapOfItemAndQuantity;
	}

	public void refillContainer(ContainerType containerType) {
		
		
		System.out.println("Please enter refill quantity");
		Double getRefillAmount = scanner.nextDouble();
		
		Boolean refillStatus = containerServiceImpl.refillContainer(containerType, getRefillAmount);
		try{
		if(refillStatus) {
			System.out.println("Container Refilled Successfully!");
			callMenuAgain();
		} else {
			//System.out.println("Refill unsuccessfull! Please try later!");
			throw new RuntimeException("Refill unsuccessfull! Please try later!");
		}}
		catch(Exception e){
			System.out.println(e.getMessage());
			callMenuAgain();
		}
		
		/*System.out.println("Please select container to be refilled.");
		System.out.println(
				"1. Tea Container\n2. Coffee Container\n3. Sugar Container\n4. Water Container\n5. Milk Container");
		int inputChoice = scanner.nextInt();

		containerServiceImpl.refillContainer(inputChoice);*/
	}
	
public Integer selectRefillContainer(){
		
		Integer inputChoice = 0; 
		
		System.out.println("Please select container type: ");
		System.out.println(
				  "1. Water Container\n"
				+ "2. Milk Container\n"
				+ "3. Coffee Container\n"
				+ "4. Tea Container\n"
				+ "5. SugarContainer\n");
		
			inputChoice = scanner.nextInt();
			
			if(inputChoice<=5 && inputChoice>0)
				return inputChoice;
			else{
				System.out.println("Please provide valid input!");
				callMenuAgain();
			}
			
		return inputChoice;
	}

	public void getReport() {

		System.out.println(
				"Please select Report to be generated. \n1. Total Tea-Coffee Sale Report Drink Wise (Cup and Cost)\n2. Total Tea-Coffee Sale (Cup and Cost)\n3. Container Status Report (Quantity of Material Present)\n4. Refilling Counter (How many times refilling is done)");

		try {
			int inputChoice = scanner.nextInt();
			if (inputChoice == 1)
				reportService.generateTeaCoffeeRportDrinkwise(mapOfItemAndQuantity);
			else if (inputChoice == 2)
				reportService.generateTotalTeaCoffeeReport(mapOfItemAndQuantity);
			else if (inputChoice == 3)
				reportService.containerStatusReport();
			else if (inputChoice == 4)
				reportService.refillingCounterStatus();
			else
				throw new RuntimeException("Please enter valid choice");
		} catch (Exception e) {
			System.out.println("Please enter valid choice");
			callGetReportAgain();
			//getReport();
		}

	}
	
	public void callGetReportAgain(){
		System.out.println("Please press 0 to go back to main menu else press 1");
		int a = scanner.nextInt();
		if (a == 0) {
			getReport();
		}
	}

}
