package rs.ac.uns.ftn.informatika.osa.vezbe06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.vezbe06.entity.Product;
import rs.ac.uns.ftn.informatika.osa.vezbe06.repository.ProductRepository;

@Service
public class ProductService implements ProductServiceInterface {
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product findOne(Integer productId){
		return productRepository.findOne(productId);
	}
	
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void remove(Integer id) {
		productRepository.delete(id);
	}

}
