package com.venkat.myprod.data;

import com.github.javafaker.Faker;
import com.venkat.myprod.model.Product;
import com.venkat.myprod.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class LoadData implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(LoadData.class);

    private final Faker faker;
    private final ProductRepository productRepository;

    public LoadData(Faker faker, ProductRepository productRepository) {
        this.faker = faker;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading Product Data...");
        List<Product> products = IntStream.rangeClosed(1, 100)
                .mapToObj(n -> new Product(null,faker.commerce().productName(),
                        faker.company().name(),faker.country().name(),faker.commerce().price())
                ).toList();
        this.productRepository.saveAll(products);
    }
}
