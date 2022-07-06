package com.caseStudy.newFinal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	
	@Query("SELECT p FROM Product p WHERE "
			+"CONCAT(p.name,p.id,p.uses,p.vitamins)"
			+"LIKE %?1%")
	
	public List<Product> findAll(String keyword);

}
