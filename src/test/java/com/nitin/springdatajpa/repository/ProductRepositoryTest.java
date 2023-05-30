package com.nitin.springdatajpa.repository;

import com.nitin.springdatajpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Annotate the specific test with @Rollback(false) inorder to persist the changes in DB
 * Uncomment import org.springframework.test.annotation.Rollback;
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
        //@Rollback(false)
    void givenProduct_whenSave_thenReturnSavedProduct() {
        //given a product
        final Product product = Product.builder()
                .name("Apple® AirPods (3rd generation)")
                .sku("108APPAIRPOD")
                .active(true)
                .description("Apple® AirPods (3rd generation) with Lightning Charging Case")
                .price(new BigDecimal(229))
                .imageURL("test_smart_tv2.png")
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

    @Test
    void givenProduct_whenFindByID_thenFetchProduct() {
        final Long productId = 1L;
        //find product by ID
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            final Product fetchedProduct = product.get();
            assertThat(fetchedProduct.getId()).isNotNull();
        }
    }

    @Test
    void giveAllProducts_whenFindAll_thenSortResultsBySku() {
        //find product by sorting based on SKU
        List<Product> listOfProductsSortedBySku = productRepository.findAll(Sort.by(Sort.Direction.DESC, "description"));

        //Usage of Sorting
        listOfProductsSortedBySku.forEach(product -> System.out.println(product.getDescription()));

    }

}