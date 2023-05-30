package com.nitin.springdatajpa.repository;

import com.nitin.springdatajpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    @Rollback(false)
    void givenProduct_whenSave_thenReturnSavedProduct() {
        //given a product
        final Product product = Product.builder().name("Test Smart TV")
                .sku("108TSMTV")
                .active(true)
                .description("Test Smart TV description")
                .price(new BigDecimal(100))
                .imageURL("test_smart_tv.png")
                .build();
        // when saving it
        final Product savedProduct = productRepository.save(product);
        //then check the product information
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isPositive();

    }

}