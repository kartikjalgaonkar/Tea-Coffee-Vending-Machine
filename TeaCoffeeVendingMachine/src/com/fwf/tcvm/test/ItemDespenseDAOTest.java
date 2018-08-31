package com.fwf.tcvm.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fwf.tcvm.dao.ItemDespenseDAO;
import com.fwf.tcvm.pojo.ItemType;

public class ItemDespenseDAOTest {

	@Test
	public void shouldReturnCostOfTea() {
		ItemDespenseDAO itemDespenseDAO = new ItemDespenseDAO();
		double actualCost = itemDespenseDAO.getItemAndCost(ItemType.TEA);
		assertEquals(10, actualCost,0);
	}

}
