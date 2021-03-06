package rs.ac.uns.ftn.informatika.osa.vezbe06.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.vezbe06.dto.CreditCardDTO;
import rs.ac.uns.ftn.informatika.osa.vezbe06.entity.PurchaseOrder;

@Service
public class PurchaseService implements PurchaseServiceInterface {
  
	@Override
	public boolean processOrder(PurchaseOrder order, CreditCardDTO card) {
		boolean paymentOK = payment.processCreditCard(card);
		return paymentOK;
	}
  
	  @Autowired
	  private PaymentServiceInterface payment;
}
