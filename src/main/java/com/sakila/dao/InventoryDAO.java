/**
 * 
 */
package com.sakila.dao;

import java.util.List;

import com.sakila.vo.InventoryVO;

/**
 * @author bc887d
 *
 */
public interface InventoryDAO {
	
	
	public List<InventoryVO> getInventory();

}
