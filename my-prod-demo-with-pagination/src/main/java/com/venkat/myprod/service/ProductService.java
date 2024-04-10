package com.venkat.myprod.service;

import com.venkat.myprod.model.Product;
import com.venkat.myprod.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product){
        return this.productRepository.save(product);
    }

    public Product update(Product product){
        return this.productRepository.save(product);
    }

    public void deleteById(Integer id){
        this.productRepository.deleteById(id);
    }

    public Optional<Product> findById(Integer id){
        return this.productRepository.findById(id);
    }

    public Page<Product> findAll(int pageNo, int pageSize, String sortField, String sortDirection){

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return this.productRepository.findAll(pageable);
    }

    public Page<Product> findAllByKeywordAndPagination(String keyword, int pageNo, int pageSize, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.productRepository.findAll(keyword, pageable);
    }
}
