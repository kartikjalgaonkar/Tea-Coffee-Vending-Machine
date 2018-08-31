package com.fwf.tcvm.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.fwf.tcvm.dao.MaterialDAO;
import com.fwf.tcvm.pojo.ItemType;
import com.fwf.tcvm.pojo.Material;

public class MaterialDAOTest {

	@Test
	public void shouldReturnListWithTeaMaterial() {
		MaterialDAO materialDAO = new MaterialDAO();
		List<Material> listOfMaterial = materialDAO.getMaterialList(ItemType.TEA);
		int actualSizeOfList = listOfMaterial.size();
		assertEquals(4, actualSizeOfList);
	}

}
