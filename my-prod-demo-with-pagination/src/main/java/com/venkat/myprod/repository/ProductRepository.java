package com.venkat.myprod.repository;

import com.venkat.myprod.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface  ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p where CONCAT(p.id, '', p.name, ' ', " +
            " p.madeIn, ' ', p.price) LIKE %?1%")
    public Page<Product> findAll(String keyword, Pageable pageable);
}
