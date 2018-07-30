package sf322015.osa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sf322015.osa.dto.BidDTO;
import sf322015.osa.entity.Bid;
import sf322015.osa.service.AuctionServiceInterface;
import sf322015.osa.service.BidServiceInterface;
import sf322015.osa.service.UserServiceInterface;

@RestController
@RequestMapping(value="api/bids")
public class BidController {
		
	@Autowired
	private BidServiceInterface bidService;
	
	@Autowired
	private UserServiceInterface userService;
	
	@Autowired
	private AuctionServiceInterface auctionService;
	
	@GetMapping
	public ResponseEntity<List<BidDTO>> getBids() {
		List<Bid> bids = bidService.findAll();
		List<BidDTO> bidsDTO = new ArrayList<BidDTO>();
		for (Bid b : bids) {
			bidsDTO.add(new BidDTO(b));
		}
		return new ResponseEntity<List<BidDTO>>(bidsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<BidDTO> getBid(@PathVariable("id") Integer id){
		Bid bid = bidService.findOne(id);
		if(bid == null){
			return new ResponseEntity<BidDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<BidDTO>(new BidDTO(bid), HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<BidDTO> saveBid(@RequestBody BidDTO bidDTO){
		Bid bid = new Bid();
		bid.setPrice(bidDTO.getPrice());
		bid.setDateTime(bidDTO.getDateTime());
		bid.setUser(userService.findOne(bidDTO.getUser().getId()));
		bid.setAuction(auctionService.findOne(bidDTO.getAuction().getId()));
		bid = bidService.save(bid);
		return new ResponseEntity<BidDTO>(new BidDTO(bid), HttpStatus.CREATED);	
	}
	
	@PostMapping(value="/bid")
	public ResponseEntity<Void> newBid(@RequestParam(value="userId") Integer userId,
			@RequestParam(value="auctionId") Integer auctionId,
			@RequestParam(value="price") Double price) {
		
		Bid bid = new Bid();
		bid.setPrice(price);
		bid.setDateTime(new Date());
		bid.setAuction(auctionService.findOne(auctionId));
		bid.setUser(userService.findOne(userId));
		bid = bidService.save(bid);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<BidDTO> updateBid(@RequestBody BidDTO bidDTO, @PathVariable("id") Integer id){
		Bid bid = bidService.findOne(id); 
		
		if (bid == null) {
			return new ResponseEntity<BidDTO>(HttpStatus.BAD_REQUEST);
		}
		
		bid.setPrice(bidDTO.getPrice());
		bid.setDateTime(bidDTO.getDateTime());
		bid.setUser(userService.findOne(bidDTO.getUser().getId()));
		bid.setAuction(auctionService.findOne(bidDTO.getUser().getId()));
		bid = bidService.save(bid);
		
		return new ResponseEntity<BidDTO>(new BidDTO(bid), HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteBid(@PathVariable("id") Integer id){
		Bid bid = bidService.findOne(id);
		if (bid != null){
			bidService.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
