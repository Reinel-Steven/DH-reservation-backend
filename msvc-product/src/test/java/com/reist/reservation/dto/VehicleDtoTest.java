package com.reist.reservation.dto;

import static org.junit.jupiter.api.Assertions.*;

import com.reist.reservation.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

class VehicleDtoTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(new BigDecimal("1500.99"));
        product.setDescription("High-end gaming laptop");
        product.setCategory("Electronics");
        product.setBrand("BrandX");
        product.setImages(Arrays.asList("img1.jpg", "img2.jpg"));
    }

    @Test
    void testVehicleDtoConstructor_FromProduct() {
        VehicleDto dto = new VehicleDto(product);

        assertEquals(1L, dto.getId());
        assertEquals("Laptop", dto.getName());
        assertEquals(new BigDecimal("1500.99"), dto.getPrice());
        assertEquals("High-end gaming laptop", dto.getDescription());
        assertEquals("Electronics", dto.getCategory());
        assertEquals("BrandX", dto.getBrand());
        assertEquals(Arrays.asList("img1.jpg", "img2.jpg"), dto.getImages());
    }

    @Test
    void testVehicleDtoConstructor_EmptyProduct() {
        Product emptyProduct = new Product();
        VehicleDto dto = new VehicleDto(emptyProduct);

        assertNull(dto.getId());
        assertNull(dto.getName());
        assertNull(dto.getPrice());
        assertNull(dto.getDescription());
        assertNull(dto.getCategory());
        assertNull(dto.getBrand());
        assertEquals(0, dto.getImages().size());
    }

    @Test
    void testVehicleDtoSetterGetter() {
        VehicleDto dto = new VehicleDto();
        dto.setId(2L);
        dto.setName("Tablet");
        dto.setPrice(new BigDecimal("800.50"));
        dto.setDescription("High-end tablet");
        dto.setCategory("Tablets");
        dto.setBrand("BrandY");
        dto.setImages(Arrays.asList("imgA.jpg", "imgB.jpg"));

        assertEquals(2L, dto.getId());
        assertEquals("Tablet", dto.getName());
        assertEquals(new BigDecimal("800.50"), dto.getPrice());
        assertEquals("High-end tablet", dto.getDescription());
        assertEquals("Tablets", dto.getCategory());
        assertEquals("BrandY", dto.getBrand());
        assertEquals(Arrays.asList("imgA.jpg", "imgB.jpg"), dto.getImages());
    }
}
