/**
 * 
 */
package com.sakila.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakila.dao.PaymentDAO;
import com.sakila.service.PaymentService;
import com.sakila.vo.PaymentVO;

/**
 * @author bc887d
 *
 */
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	public PaymentDAO paymentDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.service.PaymentService#getPayment()
	 */
	@Override
	public List<PaymentVO> getPayment() {
		return paymentDAO.getPayment();
	}

}
