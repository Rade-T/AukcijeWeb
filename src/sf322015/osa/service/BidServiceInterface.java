package sf322015.osa.service;

import java.util.List;

import sf322015.osa.entity.Bid;

public interface BidServiceInterface {
	
	Bid findOne(Integer id);
	
	List<Bid> findAll();
	
	Bid save(Bid bid);
	
	void remove(Integer id);
}
