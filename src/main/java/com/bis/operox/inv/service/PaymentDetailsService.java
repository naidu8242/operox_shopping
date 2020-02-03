package com.bis.operox.inv.service;

import java.util.List;

import com.bis.operox.inv.dao.entity.PaymentDetails;

public interface PaymentDetailsService {
	
   PaymentDetails addPaymentDetails (PaymentDetails paymentDetails);
	
	PaymentDetails getPaymentDetailsById(Long id);

	List<PaymentDetails> paymentDetailsList();


}
