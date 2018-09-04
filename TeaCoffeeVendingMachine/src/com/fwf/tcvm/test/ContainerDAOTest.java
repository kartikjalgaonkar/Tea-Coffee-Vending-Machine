package com.fwf.tcvm.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.fwf.tcvm.controller.TeaCoffeeVendingMachineLauncher;
import com.fwf.tcvm.dao.ContainerDAO;

public class ContainerDAOTest {

	@Test
	public void shouldReturnSelectedContainer() {
		ContainerDAO containerDAO = new ContainerDAO();
		String actual = containerDAO.getMapOfContainers(1);
		assertEquals("Tea Container", actual);
	}

}
