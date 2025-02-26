package com.reist.reservation.dto;

import static org.junit.jupiter.api.Assertions.*;

import com.reist.reservation.entity.Product;
import com.reist.reservation.utils.StringConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;

class ProductDtoTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setName("Laptop");
        product.setPrice(new BigDecimal("1500.99"));
        product.setDescription("High-end gaming laptop");

        try (MockedStatic<StringConverter> mockedConverter = Mockito.mockStatic(StringConverter.class)) {
            mockedConverter.when(() -> StringConverter.convertStringToList("img1.jpg;img2.jpg"))
                    .thenReturn(Arrays.asList("img1.jpg", "img2.jpg"));

            product.setImages(Arrays.asList("img1.jpg", "img2.jpg"));
        }
    }

    @Test
    void testProductDtoConstructor() {
        ProductDto dto = new ProductDto(product);

        assertEquals("Laptop", dto.getName());
        assertEquals(new BigDecimal("1500.99"), dto.getPrice());
        assertEquals("High-end gaming laptop", dto.getDescription());
        assertEquals(Arrays.asList("img1.jpg", "img2.jpg"), dto.getImage());
    }

    @Test
    void testProductDtoConstructor_NullProduct() {
        Product nullProduct = new Product();
        ProductDto dto = new ProductDto(nullProduct);

        assertNull(dto.getName());
        assertNull(dto.getPrice());
        assertNull(dto.getDescription());
        assertTrue(dto.getImage().isEmpty());
    }
}
