package sf322015.osa.service;

import java.util.List;

import sf322015.osa.entity.Item;

public interface ItemServiceInterface {

	Item findOne(Integer id);
	
	List<Item> findAll();
	
	Item save(Item item);
	
	void remove(Integer id);
}
