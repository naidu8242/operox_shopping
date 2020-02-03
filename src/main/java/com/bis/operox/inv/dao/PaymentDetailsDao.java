package com.bis.operox.inv.dao;

import java.util.List;

import com.bis.operox.inv.dao.entity.PaymentDetails;

public interface PaymentDetailsDao {
	

	PaymentDetails addPaymentDetails (PaymentDetails paymentDetails);
	
	PaymentDetails getPaymentDetailsById(Long id);

	List<PaymentDetails> paymentDetailsList();


}