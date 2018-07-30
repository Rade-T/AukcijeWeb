package sf322015.osa.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import sf322015.osa.dto.AuctionDTO;
import sf322015.osa.entity.Auction;
import sf322015.osa.service.AuctionServiceInterface;
import sf322015.osa.service.ItemServiceInterface;
import sf322015.osa.service.UserServiceInterface;

@RestController
@RequestMapping(value="api/auctions")
public class AuctionController {

	@Autowired
	private AuctionServiceInterface auctionService;
	
	@Autowired
	private UserServiceInterface userService;
	
	@Autowired
	private ItemServiceInterface itemService;
	
	@GetMapping
	public ResponseEntity<List<AuctionDTO>> getAuctions() {
		List<Auction> auctions = auctionService.findAll();
		
		List<AuctionDTO> auctionsDTO = new ArrayList<>();
		for (Auction auction : auctions) {
			auctionsDTO.add(new AuctionDTO(auction));
		}
		return new ResponseEntity<List<AuctionDTO>>(auctionsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/not_started")
	public ResponseEntity<List<AuctionDTO>> getAuctionsNotStarted() {
		List<Auction> auctions = auctionService.findAll();
		
		List<AuctionDTO> auctionsDTO = new ArrayList<>();
		for (Auction auction : auctions) {
			if (auction.getStartDate() == null) {
				auctionsDTO.add(new AuctionDTO(auction));
			}
		}
		return new ResponseEntity<List<AuctionDTO>>(auctionsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<AuctionDTO> getAuction(@PathVariable("id") Integer id) {
		Auction auction = auctionService.findOne(id);
		if (auction == null) {
			return new ResponseEntity<AuctionDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AuctionDTO>(new AuctionDTO(auction), HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<AuctionDTO> saveAuction(@RequestBody AuctionDTO auctionDTO) {
		Auction auction = new Auction();
		auction.setStartDate(auctionDTO.getStartDate());
		auction.setEndDate(auctionDTO.getEndDate());
		auction.setStartPrice(auctionDTO.getStartPrice());
		auction.setUser(userService.findOne(auctionDTO.getUser().getId()));
		auction.setItem(itemService.findOne(auctionDTO.getItem().getId()));
		auction = auctionService.save(auction);
		return new ResponseEntity<AuctionDTO>(new AuctionDTO(auction), HttpStatus.OK);
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<AuctionDTO> updateAuction(@RequestBody AuctionDTO auctionDTO, @PathVariable("id") Integer id) {
		Auction auction = auctionService.findOne(id);
		
		if (auction == null) {
			return new ResponseEntity<AuctionDTO>(HttpStatus.NOT_FOUND);
		}
		
		auction.setStartDate(auctionDTO.getStartDate());
		auction.setEndDate(auctionDTO.getEndDate());
		auction.setStartPrice(auctionDTO.getStartPrice());
		auction.setUser(userService.findOne(auctionDTO.getUser().getId()));
		auction.setItem(itemService.findOne(auctionDTO.getItem().getId()));
		auction = auctionService.save(auction);
		return new ResponseEntity<AuctionDTO>(new AuctionDTO(auction), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteAuction(@PathVariable("id") Integer id) {
		Auction auction = auctionService.findOne(id);
		if (auction != null){
			auctionService.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
