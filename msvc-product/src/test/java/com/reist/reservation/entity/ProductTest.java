package com.reist.reservation.entity;

import com.reist.reservation.dto.VehicleDto;
import com.reist.reservation.utils.StringConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class ProductTest {

    Product product = new Product();

    @BeforeEach
    void setUp() {
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(new BigDecimal("1500.99"));
        product.setDescription("High-end gaming laptop");
        product.setCategory("Electronics");
        product.setBrand("BrandX");
    }
    @Test
    void testProductNoArgsConstructor() {
        Product emptyProduct = new Product();
        assertNotNull(emptyProduct);
    }

    @Test
    void testProductConstructorFromDto() {
        VehicleDto dto = new VehicleDto();
        dto.setName("Smartphone");
        dto.setImages(Arrays.asList("img1.jpg", "img2.jpg"));
        dto.setPrice(new BigDecimal("799.99"));
        dto.setDescription("Latest model smartphone");
        dto.setCategory("Mobile");
        dto.setBrand("BrandY");

        try (MockedStatic<StringConverter> mockedConverter = mockStatic(StringConverter.class)) {
            mockedConverter.when(() -> StringConverter.convertListToString(dto.getImages()))
                    .thenReturn("img1.jpg,img2.jpg");

            Product productFromDto = new Product(dto);

            assertEquals(dto.getName(), productFromDto.getName());
            assertEquals("img1.jpg,img2.jpg", productFromDto.getListImages());
            assertEquals(dto.getPrice(), productFromDto.getPrice());
            assertEquals(dto.getDescription(), productFromDto.getDescription());
            assertEquals(dto.getCategory(), productFromDto.getCategory());
            assertEquals(dto.getBrand(), productFromDto.getBrand());
        }
    }
    @Test
    void testSetAndGetImages() {
        List<String> images = Arrays.asList("img1.jpg", "img2.jpg");

        try (MockedStatic<StringConverter> mockedConverter = mockStatic(StringConverter.class)) {
            mockedConverter.when(() -> StringConverter.convertListToString(images))
                    .thenReturn("img1.jpg,img2.jpg");

            product.setImages(images);
            assertEquals("img1.jpg,img2.jpg", product.getListImages());

            mockedConverter.when(() -> StringConverter.convertStringToList("img1.jpg,img2.jpg"))
                    .thenReturn(images);

            assertEquals(images, product.getImages());
        }
    }

    @Test
    void getAndSetId() {
        product.setId(1L);
        assertEquals(1L, product.getId());
    }

    @Test
    void getAndSetName() {
        product.setName("Laptop");
        assertEquals("Laptop", product.getName());
    }


    @Test
    void getAndSetPrice() {
        product.setPrice(new BigDecimal("1500.99"));
        assertEquals(new BigDecimal("1500.99"), product.getPrice());
    }

    @Test
    void getAndSetDescription() {
        product.setDescription("High-end gaming laptop");
        assertEquals("High-end gaming laptop", product.getDescription());
    }

    @Test
    void getAndSetCategory() {
        product.setCategory("Electronics");
        assertEquals("Electronics", product.getCategory());
    }

    @Test
    void getAndSetBrand() {
        product.setBrand("BrandX");
        assertEquals("BrandX", product.getBrand());
    }

}