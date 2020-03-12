/**
 * 
 */
package com.sakila.service;

import java.util.List;

import com.sakila.vo.InventoryVO;

/**
 * @author bc887d
 *
 */
public interface InventoryService {

	public List<InventoryVO> getInventory();

}
