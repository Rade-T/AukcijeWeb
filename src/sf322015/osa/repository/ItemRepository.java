package sf322015.osa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sf322015.osa.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
