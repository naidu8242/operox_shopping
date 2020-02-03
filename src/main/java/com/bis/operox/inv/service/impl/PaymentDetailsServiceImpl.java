package com.bis.operox.inv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bis.operox.inv.dao.AddressDao;
import com.bis.operox.inv.dao.PaymentDetailsDao;
import com.bis.operox.inv.dao.entity.Address;
import com.bis.operox.inv.dao.entity.PaymentDetails;
import com.bis.operox.inv.service.PaymentDetailsService;

@Service
@Repository
public class PaymentDetailsServiceImpl implements PaymentDetailsService{

	@Autowired
	private PaymentDetailsDao paymentDetailsDao;

	@Override
	public PaymentDetails addPaymentDetails(PaymentDetails paymentDetails) {
		return paymentDetailsDao.addPaymentDetails(paymentDetails);
	}

	@Override
	public PaymentDetails getPaymentDetailsById(Long id) {
		return paymentDetailsDao.getPaymentDetailsById(id);
	}

	@Override
	public List<PaymentDetails> paymentDetailsList() {
		return this.paymentDetailsDao.paymentDetailsList();
	}
}
