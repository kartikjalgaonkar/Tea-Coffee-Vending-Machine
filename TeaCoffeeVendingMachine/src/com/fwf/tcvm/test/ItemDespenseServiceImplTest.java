package com.fwf.tcvm.test;


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;
import org.mockito.runners.MockitoJUnitRunner;

import com.fwf.tcvm.dao.ItemDespenseDAO;
import com.fwf.tcvm.pojo.ItemType;
import com.fwf.tcvm.service.ContainerService;
import com.fwf.tcvm.service.ItemDespenseServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ItemDespenseServiceImplTest {
	@Mock
	ItemDespenseDAO itemDespenseDAO;
	
	@Mock
	ContainerService containerServiceImpl;
	
	@InjectMocks
	ItemDespenseServiceImpl itemDespenseServiceImpl = new ItemDespenseServiceImpl();
	
	@Test
	public void shouldReturnCalculatedAmountForSelectedItem(){
		when(itemDespenseDAO.getItemAndCost(ItemType.TEA)).thenReturn(10.0);
		double expectedResult = itemDespenseServiceImpl.calculateAmount(ItemType.TEA, 5);
		verify(itemDespenseDAO).getItemAndCost(ItemType.TEA);
		assertEquals(50.0, expectedResult, 0);
		
	}
	
	@Test
	public void shouldReturnTrueIfEnteredAmountIsCorrect(){
		boolean expectedResult = itemDespenseServiceImpl.checkInputAmount(50,50);
		assertEquals(true, expectedResult);
	}
	
	@Test
	public void shouldReturnFalseIfEnteredAmountIsIncorrect(){
		boolean expectedResult = itemDespenseServiceImpl.checkInputAmount(60,50);
		assertFalse(expectedResult);
	}
	
	@Test 
	public void shouldDispenseItem(){
		doNothing().when(containerServiceImpl).updateContainerQuantityAfterProductIsDispensed(ItemType.BLACK_COFFEE, 5);
		itemDespenseServiceImpl.dispenseItem(5, ItemType.BLACK_COFFEE);
	}
}
