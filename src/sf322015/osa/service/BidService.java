package sf322015.osa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sf322015.osa.entity.Bid;
import sf322015.osa.repository.BidRepository;

@Service
public class BidService implements BidServiceInterface {
	
	@Autowired
	BidRepository bidRepository;

	@Override
	public Bid findOne(Integer id) {
		return bidRepository.findOne(id);
	}

	@Override
	public List<Bid> findAll() {
		return bidRepository.findAll();
	}

	@Override
	public Bid save(Bid bid) {
		return bidRepository.save(bid);
	}

	@Override
	public void remove(Integer id) {
		bidRepository.delete(id);
	}

}
