package com.fwf.tcvm.service;

import java.util.Map;

public interface ReportService {
	public void generateTeaCoffeeRportDrinkwise(Map<String, Integer> mapOfItemAndQuantity);
	public void generateTotalTeaCoffeeReport(Map<String, Integer> mapOfItemAndQuantity);
	public void containerStatusReport();
	public void refillingCounterStatus();
}
