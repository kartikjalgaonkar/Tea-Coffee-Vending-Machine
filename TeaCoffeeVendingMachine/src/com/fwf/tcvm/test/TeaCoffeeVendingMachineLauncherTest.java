package com.fwf.tcvm.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.fwf.tcvm.controller.TCVMController;
import com.fwf.tcvm.controller.TeaCoffeeVendingMachineLauncher;

@RunWith(MockitoJUnitRunner.class)
public class TeaCoffeeVendingMachineLauncherTest {

	@InjectMocks
	private TeaCoffeeVendingMachineLauncher teaCoffeeVendingMachineLauncher;
	
	@Mock
	private static TCVMController tcvmontroller;
	
	@Test
	public void test() {
		Mockito.doNothing().when(tcvmontroller).callOption();
		//TeaCoffeeVendingMachineLauncher.main(null);
	}

}
