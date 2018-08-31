package com.fwf.tcvm.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.fwf.tcvm.dao.ContainerDAO;
import com.fwf.tcvm.dao.MaterialDAO;
import com.fwf.tcvm.pojo.ContainerType;
import com.fwf.tcvm.pojo.Item;
import com.fwf.tcvm.pojo.ItemType;
import com.fwf.tcvm.pojo.Material;
import com.fwf.tcvm.service.ContainerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ContainerServiceImplTest {
	
	@Mock
	MaterialDAO materialDao;
	
	@InjectMocks
	ContainerServiceImpl containerServiceImpl = new ContainerServiceImpl();
	@Test
	public void shouldReturnTrueIfOrderIsAvailable(){
		
		List<Material> listOfMaterial = new ArrayList<>();
		listOfMaterial.add(new Material(ItemType.TEA, "tea", 5, 1));
		listOfMaterial.add(new Material(ItemType.TEA, "water", 60, 5));
		listOfMaterial.add(new Material(ItemType.TEA, "milk", 40, 4));
		listOfMaterial.add(new Material(ItemType.TEA, "sugar", 15, 2));
		
		Item item = new Item();
		item.setItemType(ItemType.TEA);
		Mockito.when(materialDao.getMaterialList(item.getItemType())).thenReturn(listOfMaterial);
		
		boolean actualResult = containerServiceImpl.getOrder(item, 5);
		Assert.assertTrue(actualResult);
	}
	
	@Test
	public void shouldCheckIfOrderIsAvailableOrNot(){
		List<Material> listOfMaterial = new ArrayList<>();
		listOfMaterial.add(new Material(ItemType.TEA, "tea", 5, 1));
		listOfMaterial.add(new Material(ItemType.TEA, "water", 60, 5));
		listOfMaterial.add(new Material(ItemType.TEA, "milk", 40, 4));
		listOfMaterial.add(new Material(ItemType.TEA, "sugar", 15, 2));
		
		Item item = new Item();
		item.setItemType(ItemType.TEA);
		Mockito.when(materialDao.getMaterialList(item.getItemType())).thenReturn(listOfMaterial);
		
		boolean actualResult = containerServiceImpl.getOrder(item, 1000);
		Assert.assertFalse(actualResult);
	}
	

	@Test
	public void shouldGetContainerStatus(){
		containerServiceImpl.getContainerStatus();
	}
	
	@Test
	public void shouldGetWasteOfMaterial(){
		List<Material> listOfMaterial = new ArrayList<>();
		listOfMaterial.add(new Material(ItemType.TEA, "tea", 5, 1));
		listOfMaterial.add(new Material(ItemType.TEA, "water", 60, 5));
		listOfMaterial.add(new Material(ItemType.TEA, "milk", 40, 4));
		listOfMaterial.add(new Material(ItemType.TEA, "sugar", 15, 2));
		
		Mockito.when(materialDao.getMaterialList(ItemType.TEA)).thenReturn(listOfMaterial);
		containerServiceImpl.getWasteOfMaterial(listOfMaterial, 5);
	}
	
	@Test
	public void shouldResetContainer(){
		containerServiceImpl.resetContainer();
		
	}
	
	@Test
	public void shouldUpdateContainerQuantityAfterProductIsDispensed(){
		containerServiceImpl.updateContainerQuantityAfterProductIsDispensed(ItemType.COFFE, 5);
	}
	
	@Test
	public void shouldReturnTrueWhenRefillContainerIsSuccessfullForMilk(){
		ContainerDAO.milkContainerAvailableQuantity = 2000.0;
		Boolean actual = containerServiceImpl.refillContainer(ContainerType.Milk, 200.00);
		Assert.assertTrue(actual);
		
	} 
	@Test
	public void shouldReturnTrueWhenRefillContainerIsSuccessfullForWater(){
		ContainerDAO.waterContainerAvailableQuantity = 2000.0;
		Boolean actual = containerServiceImpl.refillContainer(ContainerType.Water, 200.00);
		Assert.assertTrue(actual);
		
	} 
	
	@Test
	public void shouldReturnTrueWhenRefillContainerIsSuccessfullForSugar(){
		ContainerDAO.sugarContainerAvailableQuantity = 2000.0;
		Boolean actual = containerServiceImpl.refillContainer(ContainerType.Sugar, 200.00);
		Assert.assertTrue(actual);
		
	} 
	
	@Test
	public void shouldReturnTrueWhenRefillContainerIsSuccessfullForCoffee(){
		ContainerDAO.coffeeContainerAvailableQuantity = 1500.0;
		Boolean actual = containerServiceImpl.refillContainer(ContainerType.Coffee, 200.00);
		Assert.assertTrue(actual);
		
	} 
	
	@Test
	public void shouldReturnTrueWhenRefillContainerIsSuccessfullForTea(){
		ContainerDAO.teaContainerAvailableQuantity = 1400.0;
		Boolean actual = containerServiceImpl.refillContainer(ContainerType.Tea, 200.00);
		Assert.assertTrue(actual);
		
	} 
	
	@Test
	public void shouldReturnFalseWhenRefillContainerIfContainerIsAlreadyFilled(){
		ContainerDAO.teaContainerAvailableQuantity = 2000.0;
		Boolean actual = containerServiceImpl.refillContainer(ContainerType.Tea, 200.00);
		Assert.assertFalse(actual);
		
	}
	
}
