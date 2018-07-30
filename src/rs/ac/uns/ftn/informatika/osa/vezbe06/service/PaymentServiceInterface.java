package rs.ac.uns.ftn.informatika.osa.vezbe06.service;

import rs.ac.uns.ftn.informatika.osa.vezbe06.dto.CreditCardDTO;

public interface PaymentServiceInterface {

  public boolean processCreditCard(CreditCardDTO card);
  
}
