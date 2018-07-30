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

import sf322015.osa.dto.ItemDTO;
import sf322015.osa.entity.Item;
import sf322015.osa.service.AuctionServiceInterface;
import sf322015.osa.service.ItemServiceInterface;

@RestController
@RequestMapping(value="api/items")
public class ItemContoller {
		
	@Autowired
	private ItemServiceInterface itemService;
	
	@Autowired
	private AuctionServiceInterface auctionService;
	
	@GetMapping
	public ResponseEntity<List<ItemDTO>> getItems() {
		List<Item> items = itemService.findAll();
		List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();
		for (Item i : items) {
			itemsDTO.add(new ItemDTO(i));
		}
		return new ResponseEntity<List<ItemDTO>>(itemsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/unsold")
	public ResponseEntity<List<ItemDTO>> getUnsoldItems() {
		List<Item> items = itemService.findAll();
		List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();
		for (Item i : items) {
			if (auctionService.findItem(i.getId()) == null) {
				itemsDTO.add(new ItemDTO(i));
			}
		}
		return new ResponseEntity<List<ItemDTO>>(itemsDTO, HttpStatus.OK); 
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ItemDTO> getItem(@PathVariable("id") Integer id){
		Item item = itemService.findOne(id);
		if(item == null){
			return new ResponseEntity<ItemDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ItemDTO>(new ItemDTO(item), HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<ItemDTO> saveItem(@RequestBody ItemDTO itemDTO){
		Item item = new Item();
		item.setName(itemDTO.getName());
		item.setDescription(itemDTO.getDescription());
		item.setPicture(itemDTO.getPicture());
		item.setSold(itemDTO.isSold());
		item = itemService.save(item);
		return new ResponseEntity<ItemDTO>(new ItemDTO(item), HttpStatus.CREATED);	
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<ItemDTO> updateItem(@RequestBody ItemDTO itemDTO, @PathVariable("id") Integer id){
		Item item = itemService.findOne(id); 
		
		if (item == null) {
			return new ResponseEntity<ItemDTO>(HttpStatus.BAD_REQUEST);
		}
		
		item.setName(itemDTO.getName());
		item.setDescription(itemDTO.getDescription());
		item.setPicture(itemDTO.getPicture());
		item.setSold(itemDTO.isSold());
		item = itemService.save(item);
		
		return new ResponseEntity<ItemDTO>(new ItemDTO(item), HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable("id") Integer id){
		Item item = itemService.findOne(id);
		if (item != null){
			itemService.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
