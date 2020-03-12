/**
 * 
 */
package com.sakila.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakila.dao.InventoryDAO;
import com.sakila.service.InventoryService;
import com.sakila.vo.InventoryVO;

/**
 * @author bc887d
 *
 */
@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	public InventoryDAO invertoryDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.service.InventoryService#getInventory()
	 */
	@Override
	public List<InventoryVO> getInventory() {
		return invertoryDAO.getInventory();
	}

}
