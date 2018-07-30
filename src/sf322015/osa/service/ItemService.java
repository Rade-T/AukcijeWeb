package sf322015.osa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sf322015.osa.entity.Item;
import sf322015.osa.repository.ItemRepository;

@Service
public class ItemService implements ItemServiceInterface {
	
	@Autowired
	ItemRepository itemRepository;

	@Override
	public Item findOne(Integer id) {
		return itemRepository.findOne(id);
	}

	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public Item save(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public void remove(Integer id) {
		itemRepository.delete(id);
	}

}
