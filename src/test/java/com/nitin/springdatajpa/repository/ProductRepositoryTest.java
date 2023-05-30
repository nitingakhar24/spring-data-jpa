package com.nitin.springdatajpa.repository;

import com.nitin.springdatajpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Annotate the specific test with @Rollback(false) inorder to persist the changes in DB
 * import org.springframework.test.annotation.Rollback;
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void givenProduct_whenSave_thenReturnSavedProduct() {
        //given a product
        final Product product = Product.builder()
                .name("Test Smart TV")
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

    @Test
    void givenProduct_whenUpdate_thenUpdateUsingSave() {
        final Long productId = 1L;
        //find product by ID
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            final Product fetchedProduct = product.get();
            // update product information
            fetchedProduct.setName("Updated Test Smart TV");
            fetchedProduct.setDescription("Updated Test Smart TV description");
            // Save updated product
            final Product modifiedProduct = productRepository.save(fetchedProduct);
            assertThat(modifiedProduct.getName()).isEqualTo(fetchedProduct.getName());
        }
    }

}