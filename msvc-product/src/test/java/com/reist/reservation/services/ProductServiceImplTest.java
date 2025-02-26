package com.reist.reservation.services;

import com.reist.reservation.entity.Product;
import com.reist.reservation.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(new BigDecimal("1500.99"));
        product.setDescription("High-end gaming laptop");
        product.setCategory("Electronics");
        product.setBrand("BrandX");
    }


    @Test
    void findAllTest() {
        List<Product> productList = Arrays.asList(product);
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.findAll();

        assertEquals(1, result.size());
        assertEquals(product.getName(), result.get(0).getName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById_ProductExists() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(product.getName(), result.get().getName());
        verify(productRepository, times(1)).findById(1L);
    }
    @Test
    void testFindById_ProductNotFound() {
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Product> result = productService.findById(2L);

        assertFalse(result.isPresent());
        verify(productRepository, times(1)).findById(2L);
    }
    @Test
    void testSave() {
        productService.save(product);

        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testDelete() {
        productService.delete(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }
}