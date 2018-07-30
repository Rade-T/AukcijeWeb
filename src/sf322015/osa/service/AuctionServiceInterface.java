package sf322015.osa.service;

import java.util.List;

import sf322015.osa.entity.Auction;
import sf322015.osa.entity.Item;

public interface AuctionServiceInterface {
	
	Auction findOne(Integer id);
	
	List<Auction> findAll();
	
	Auction save(Auction auction);
	
	void remove(Integer id);
	
	public Item findItem(Integer itemId);
}
