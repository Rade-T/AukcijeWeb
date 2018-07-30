package rs.ac.uns.ftn.informatika.osa.vezbe06.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.informatika.osa.vezbe06.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	List<Category> findByParent(Category parent);
}
