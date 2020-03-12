/**
 * 
 */
package com.sakila.dao;

import java.util.List;

import com.sakila.vo.PaymentVO;

/**
 * @author bc887d
 *
 */
public interface PaymentDAO {

	public List<PaymentVO> getPayment();

}
