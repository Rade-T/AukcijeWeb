package rs.ac.uns.ftn.informatika.osa.vezbe06.service;

import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.vezbe06.dto.CreditCardDTO;

@Service
public class PaymentService implements PaymentServiceInterface {

  @Override
	public boolean processCreditCard(CreditCardDTO card) {
    return true;
  }
}
