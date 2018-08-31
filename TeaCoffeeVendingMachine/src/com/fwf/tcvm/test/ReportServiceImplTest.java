package com.fwf.tcvm.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.fwf.tcvm.dao.ItemDespenseDAO;
import com.fwf.tcvm.service.ContainerService;
import com.fwf.tcvm.service.ReportService;
import com.fwf.tcvm.service.ReportServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceImplTest {
	
	@InjectMocks
	ReportServiceImpl reportServiceImpl = new ReportServiceImpl();
	
	@Mock
	ItemDespenseDAO itemDespenseDAO;
	
	@Mock
	ContainerService containerService ;
	
	@Test
	public void shouldGenerateTotalTeaCoffeeDrinkwiseReport(){
		Map<String, Integer> mapOfItemAndQuantity = new HashMap<>();
		mapOfItemAndQuantity.put("tea", 5);
		ReportService reportService = new ReportServiceImpl();
		Map<String, Double> itemCostMap = new HashMap<>();
		itemCostMap.put("tea", 10d);
		itemCostMap.put("black tea", 5d);
		itemCostMap.put("coffee", 15d);
		itemCostMap.put("black coffee", 10d);
		Mockito.when(itemDespenseDAO.getMapOfItemAndCost()).thenReturn(itemCostMap);
		reportService.generateTeaCoffeeRportDrinkwise(mapOfItemAndQuantity);
	}
	
	@Test
	public void shouldGetContainerStatus(){
		Mockito.doNothing().when(containerService).getContainerStatus();
		reportServiceImpl.containerStatusReport();
	}
	
	@Test
	public void shouldGenerateTotalTeaCoffeeReport(){
		Map<String, Integer> mapOfItemAndQuantity = new HashMap<>();
		mapOfItemAndQuantity.put("tea", 5);
		ReportService reportService = new ReportServiceImpl();
		Map<String, Double> itemCostMap = new HashMap<>();
		itemCostMap.put("tea", 10d);
		itemCostMap.put("black tea", 5d);
		itemCostMap.put("coffee", 15d);
		itemCostMap.put("black coffee", 10d);
		Mockito.when(itemDespenseDAO.getMapOfItemAndCost()).thenReturn(itemCostMap);
		reportService.generateTotalTeaCoffeeReport(mapOfItemAndQuantity);
	}
	
	@Test
	public void shouldCallRefillingCounterStatus(){
		ReportServiceImpl reportServiceImpl = new ReportServiceImpl();
		reportServiceImpl.refillingCounterStatus();
		
	}

}
