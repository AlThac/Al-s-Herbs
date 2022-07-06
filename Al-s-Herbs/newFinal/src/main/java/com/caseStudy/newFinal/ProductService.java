package com.caseStudy.newFinal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public List<Product> listAll(String keyword) {
		if(keyword != null) {
			return productRepository.findAll(keyword);
		}
		return  productRepository.findAll();
	}
	
	public void save(Product product) {
		productRepository.save(product);
	}
	
	public Product get(Long id) {
		return productRepository.findById(id).get();
	}
	
	public void delete(Long id) {
		productRepository.deleteById(id);
	}
	
	/*public Product get(String name) {
		return productRepository.findByName(name).get();
	}*/

	
	
}
