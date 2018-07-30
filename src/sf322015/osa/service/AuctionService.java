package sf322015.osa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sf322015.osa.entity.Auction;
import sf322015.osa.entity.Item;
import sf322015.osa.repository.AuctionRepository;

@Service
public class AuctionService implements AuctionServiceInterface {
	@Autowired
	AuctionRepository auctionRepository;

	@Override
	public Auction findOne(Integer id) {
		return auctionRepository.findOne(id);
	}

	@Override
	public List<Auction> findAll() {
		return auctionRepository.findAll();
	}

	@Override
	public Auction save(Auction auction) {
		return auctionRepository.save(auction);
	}

	@Override
	public void remove(Integer id) {
		auctionRepository.delete(id);
	}
	
	public Item findItem(Integer itemId) {
		Item found = null;
		
		List<Auction> auctions = findAll();
		for (Auction auction : auctions) {
			if (auction.getItem().getId() == itemId) {
				found = auction.getItem();
			}
		}
		
		return found;
	}
}
