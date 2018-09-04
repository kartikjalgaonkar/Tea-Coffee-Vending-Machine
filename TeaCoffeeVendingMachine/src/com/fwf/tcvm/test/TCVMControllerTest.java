package com.fwf.tcvm.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

import com.fwf.tcvm.controller.InputScanner;
import com.fwf.tcvm.controller.TCVMController;
import com.fwf.tcvm.dao.ContainerDAO;
import com.fwf.tcvm.pojo.ContainerType;
import com.fwf.tcvm.pojo.Item;
import com.fwf.tcvm.pojo.ItemType;
import com.fwf.tcvm.service.ContainerServiceImpl;
import com.fwf.tcvm.service.ItemDespenseService;
import com.fwf.tcvm.service.ReportService;

@RunWith(MockitoJUnitRunner.class)
public class TCVMControllerTest {
	
	@InjectMocks
	TCVMController tcvmController;
	
	@Mock
	InputScanner inputScanner;
	
	@Mock
	ContainerServiceImpl containerServiceImpl;
	
	@Mock
	ItemDespenseService itemDespenseService;
	
	@Mock
	Item item;
	
	@Mock
	ReportService reportService;;
	
	@Mock
	Logger logger;

	@Test
	public void shouldReturnSelectedOptioFromMenu() {
		
		when(inputScanner.getString()).thenReturn("2");
		String actualResult = tcvmController.viewMenu();
		
		assertEquals("2", actualResult);
	}
	
	@Test
	public void shouldReturnQuanityIfChoiceIsAnyDrink(){
		when(inputScanner.getString()).thenReturn("2");
		int actualResult = tcvmController.getDrinksQuantity();
		assertEquals(2, actualResult);
	}
	
	@Test
	public void shouldGetMapOfItemAndQuanity(){
		Map<String, Integer> expectedMap = new HashMap<>();
		expectedMap.put("tea", 1);
		Map<String, Integer> actualMap = tcvmController.getItemAndItsQuantity(ItemType.TEA, 1);
		
		assertEquals(expectedMap.size(), actualMap.size());
	}
	
	@Test
	public void shouldGetMapOfItemAndQuanityWithSizeMoreThanOne(){
		Map<String, Integer> expectedMap = new HashMap<>();
		expectedMap.put("tea", 1);
		Map<String, Integer> actualMap = tcvmController.getItemAndItsQuantity(ItemType.TEA, 1);
		
		assertEquals(expectedMap.size(), actualMap.size());
	}
	
	@Test
	public void shouldGetOrderIfAvailable(){
		Item item = new Item();
		item.setItemType(ItemType.TEA);
		item.setItemCost(20.0);
		when(containerServiceImpl.getOrder(item, 2)).thenReturn(true);
		when(itemDespenseService.calculateAmount(ItemType.TEA, 2)).thenReturn(20.0);
		when(inputScanner.nextDouble()).thenReturn(20.0);
		when(itemDespenseService.checkInputAmount(item.getItemCost(), 20.0)).thenReturn(true);
		doNothing().when(itemDespenseService).dispenseItem(2, ItemType.TEA);
		tcvmController.checkOrder(item, 2);
		verify(itemDespenseService).calculateAmount(ItemType.TEA, 2);
	}
	
	@Test
	public void shouldNotGetOrderIfNotAvailable(){
		Item item = new Item();
		item.setItemType(ItemType.TEA);
		when(containerServiceImpl.getOrder(item, 2)).thenReturn(false);
		doNothing().when(itemDespenseService).dispenseItem(2, ItemType.TEA);
		tcvmController.checkOrder(item, 2);
		verify(containerServiceImpl).getOrder(item, 2);
	}
	
	@Test
	public void shouldNotDespenseItemIfAmountIsNotCorrect(){
		Item item = new Item();
		item.setItemType(ItemType.TEA);
		when(containerServiceImpl.getOrder(item, 2)).thenReturn(true);
		when(itemDespenseService.calculateAmount(ItemType.TEA, 2)).thenReturn(20.0);
		when(itemDespenseService.checkInputAmount(20.0, 21.0)).thenReturn(true);
		doNothing().when(itemDespenseService).dispenseItem(2, ItemType.TEA);
		tcvmController.checkOrder(item, 2);
		verify(itemDespenseService).calculateAmount(ItemType.TEA, 2);
	}
	
	@Test
	public void shouldCallCallOprtionWithTea(){
		doNothing().when(logger).info("Please select option from the following");
		when(inputScanner.getString()).thenReturn("1");
		when(inputScanner.nextInt()).thenReturn(1);
		tcvmController.callOption();
	}
	@Test
	public void shouldCallCallOprtionWithBlackTea(){
		doNothing().when(logger).info("Please select option from the following");
		when(inputScanner.getString()).thenReturn("2");
		when(inputScanner.nextInt()).thenReturn(1);
		tcvmController.callOption();
	}
	@Test
	public void shouldCallCallOprtionWithCoffee(){
		doNothing().when(logger).info("Please select option from the following");
		when(inputScanner.getString()).thenReturn("3");
		when(inputScanner.nextInt()).thenReturn(1);
		tcvmController.callOption();
	}
	@Test
	public void shouldCallCallOprtionWithBlackCoffee(){
		doNothing().when(logger).info("Please select option from the following");
		when(inputScanner.getString()).thenReturn("4");
		when(inputScanner.nextInt()).thenReturn(1);
		tcvmController.callOption();
	}
	

	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldCallGetReport(){
		when(inputScanner.nextInt()).thenReturn(1);
		doNothing().when(reportService).generateTeaCoffeeRportDrinkwise(Mockito.anyMap());
		tcvmController.getReport();
		verify(reportService).generateTeaCoffeeRportDrinkwise(Mockito.anyMap());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldCallGetReportForTotalSale(){
		Map<String, Integer> mapOfItemAndQuantity = new HashMap<>();
		mapOfItemAndQuantity.put("black tea", 2);
		when(inputScanner.nextInt()).thenReturn(2);
		doNothing().when(reportService).generateTotalTeaCoffeeReport(Mockito.anyMap());
		tcvmController.getReport();
		verify(reportService).generateTotalTeaCoffeeReport(Mockito.anyMap());
	}
	
	@Test
	public void shouldCallGetcontainerStatusReport(){
		Map<String, Integer> mapOfItemAndQuantity = new HashMap<>();
		mapOfItemAndQuantity.put("coffee", 3);
		when(inputScanner.nextInt()).thenReturn(3);
		doNothing().when(reportService).containerStatusReport();
		tcvmController.getReport();
		verify(reportService).containerStatusReport();
	}
	

	@Test
	public void shouldNotDisplayANyReport(){
		when(inputScanner.nextInt()).thenReturn(5);
		tcvmController.getReport();
	}
	

	@Test
	public void shouldCallCallOprtionToGetReport(){
		when(inputScanner.getString()).thenReturn("6");
		when(inputScanner.nextInt()).thenReturn(0).thenReturn(1);
		tcvmController.callOption();
	}
	

	@Test
	public void shouldCallCallOprtionToGetContainerStatus(){
		when(inputScanner.getString()).thenReturn("7");
		when(inputScanner.nextInt()).thenReturn(5);
		doNothing().when(containerServiceImpl).getContainerStatus();
		tcvmController.callOption();
	}
	
	@Test
	public void shouldCallCallOprtionToGetResetContainer(){
		when(inputScanner.getString()).thenReturn("8");
		when(inputScanner.nextInt()).thenReturn(5);
		doNothing().when(containerServiceImpl).resetContainer();
		tcvmController.callOption();
	}

	
	@Test
	public void shouldBreakOutFromMainMenuIF9IsPressed(){
		when(inputScanner.getString()).thenReturn("9");
		tcvmController.callOption();
	}
	
	@Test
	public void shouldCallCallOprtionWithDefaultChoice(){
		when(inputScanner.getString()).thenReturn("10");
		when(inputScanner.nextInt()).thenReturn(5);
		tcvmController.callOption();
		verify(inputScanner).getString();
	}
	

	
	@Test
	public void shouldCreateConstructorOfTCVMController(){
		@SuppressWarnings("unused")
		TCVMController tcvmController = new TCVMController();
	}
	
	@Test
	public void shouldCallCallMenuTwice(){
		when(inputScanner.getString()).thenReturn("8");
		when(inputScanner.nextInt()).thenReturn(0).thenReturn(1);
		doNothing().when(containerServiceImpl).resetContainer();
		tcvmController.callOption();
		verify(inputScanner, Mockito.times(2)).getString();
	}
	
	@Test
	public void shouldCallGetReportTwice(){
		when(inputScanner.nextInt()).thenReturn(0).thenReturn(5).thenReturn(1);
		tcvmController.callGetReportAgain();
		verify(inputScanner, times(3)).nextInt();
	}
	
	@Test
	public void shouldReturnValidOptionForRefillContainer(){
		
		when(inputScanner.nextInt()).thenReturn(2);
		Integer actual = tcvmController.selectRefillContainer();
		verify(inputScanner).nextInt();
		assertEquals(new Integer(2), actual);
	}
	
	@Test
	public void shouldReturnInvalidOptionMessageForRefillContainer(){
		
		when(inputScanner.nextInt()).thenReturn(6);
		tcvmController.selectRefillContainer();
		
		verify(inputScanner, times(2)).nextInt();
	}
	
	@Test
	public void shouldCallRefillContainerWhenSendingValidEntry(){
		
		ContainerDAO.teaContainerAvailableQuantity = 500.0;
		when(inputScanner.getString()).thenReturn("5");
		when(inputScanner.nextInt()).thenReturn(4).thenReturn(1);
		when(inputScanner.nextDouble()).thenReturn(200.0);
		when(containerServiceImpl.refillContainer(Mockito.any(ContainerType.class), Mockito.anyDouble())).thenReturn(true);
		tcvmController.callOption();
		
		verify(inputScanner).getString();
		verify(inputScanner, times(3)).nextInt();
		verify(inputScanner).nextDouble();
	}
	
	@Test
	public void shouldgetContainerStatus(){
		when(inputScanner.getString()).thenReturn("6");
		when(inputScanner.nextInt()).thenReturn(4).thenReturn(1);
		doNothing().when(reportService).refillingCounterStatus();
		tcvmController.callOption();
		verify(reportService).refillingCounterStatus();
	}
	
	@Test
	public void shouldThrowRuntimeExceptionInCaseOfOverflowInContainerRefilling(){
		//TCVMController tcvmController = new TCVMController();
		//when(inputScanner.nextDouble()).thenReturn(new Double(100));
		when(inputScanner.nextDouble()).thenReturn(100.0);
		when(containerServiceImpl.refillContainer(ContainerType.Water, 100.0)).thenReturn(false);
		
		when(inputScanner.nextInt()).thenReturn(1);
		tcvmController.refillContainer(ContainerType.Water);
		verify(containerServiceImpl).refillContainer(ContainerType.Water, 100.0);
		
	}
	
	@Test
	public void shouldCallDefaultIfNegavtiveNumberIsEntered(){
		when(inputScanner.getString()).thenReturn("-1");
		when(inputScanner.nextInt()).thenReturn(5);
		tcvmController.callOption();
		verify(inputScanner).getString();
	}

	
	
	


}
