package rs.ac.uns.ftn.informatika.osa.vezbe06.service;

import rs.ac.uns.ftn.informatika.osa.vezbe06.dto.CreditCardDTO;
import rs.ac.uns.ftn.informatika.osa.vezbe06.entity.PurchaseOrder;

public interface PurchaseServiceInterface {
  
  public boolean processOrder(PurchaseOrder order, CreditCardDTO card);

}
