package com.sakila.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sakila.dao.PaymentDAO;
import com.sakila.db.util.SKUtility;
import com.sakila.modal.Payment;
import com.sakila.vo.CustomerVO;
import com.sakila.vo.PaymentVO;
import com.sakila.vo.RentalVO;
import com.sakila.vo.StaffVO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("paymentDAO")
public class PaymentDAOImpl implements PaymentDAO {

	@PersistenceContext
	public EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.dao.PaymentDAO#getPayment()
	 */
	@Override
	public List<PaymentVO> getPayment() {
		log.info("... Entered into getPayment() of PaymentDAOImpl ...");
		List<PaymentVO> paymentVOList = new ArrayList<PaymentVO>();

		Session session = (Session) entityManager.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Payment> criteria = builder.createQuery(Payment.class);
		Root<Payment> contactRoot = criteria.from(Payment.class);
		criteria.select(contactRoot);

		Query<Payment> query = session.createQuery(criteria);
		query.setFirstResult(0);
		query.setMaxResults(10);

		List<Payment> paymentList = query.getResultList();

		paymentList.stream().forEach(payment1 -> {

			PaymentVO payment = new PaymentVO();

			payment.setPaymentId(payment1.getPaymentId());
			payment.setPaymentDate(payment1.getPaymentDate());
			payment.setAmount(payment1.getAmount());
			payment.setLastUpdate(payment1.getLastUpdate());

			CustomerVO customer = SKUtility.getCustomerDetailsById(session, payment1.getCustomer().getCustomerId());
			payment.setCustomer(customer);

			RentalVO rental = SKUtility.getRentalDetailsById(session, payment1.getRental().getRentalId());
			payment.setRental(rental);

			StaffVO staff = SKUtility.getStaffDetailsById(session, payment1.getStaff().getStaffId());
			payment.setStaff(staff);

			paymentVOList.add(payment);
		});

		return paymentVOList;
	}

}
