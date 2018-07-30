package rs.ac.uns.ftn.informatika.osa.vezbe06.service;

import rs.ac.uns.ftn.informatika.osa.vezbe06.entity.PurchaseOrder;
import rs.ac.uns.ftn.informatika.osa.vezbe06.entity.User;

public interface UserServiceInterface {

	User findByUsernameAndPassword(String username, String password);
	
	void add(User user, PurchaseOrder order);
}
