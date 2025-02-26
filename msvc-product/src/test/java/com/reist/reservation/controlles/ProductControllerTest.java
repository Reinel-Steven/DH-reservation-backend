package com.reist.reservation.controlles;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.reist.reservation.entity.Product;
import com.reist.reservation.services.IProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IProductService productService;

    @InjectMocks
    private ProductController productController;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(new BigDecimal("1500.99"));
        product.setDescription("High-end gaming laptop");
        product.setCategory("Electronics");
        product.setBrand("BrandX");
    }

    @Test
    void testListProducts_ReturnsProductList() throws Exception {
        when(productService.findAll()).thenReturn(List.of(product));

        mockMvc.perform(get("/api/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Laptop"));

        verify(productService, times(1)).findAll();
    }

    @Test
    void testListProducts_NoProductsFound() throws Exception {
        when(productService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/product"))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).findAll();
    }

    @Test
    void testProductDetail_ProductExists() throws Exception {
        when(productService.findById(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/api/product/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"));

        verify(productService, times(1)).findById(1L);
    }

    @Test
    void testProductDetail_ProductNotFound() throws Exception {
        when(productService.findById(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/product/2"))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).findById(2L);
    }

    @Test
    void testProductCreate_Success() throws Exception {
        doNothing().when(productService).save(any(Product.class));

        mockMvc.perform(post("/api/product/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Laptop\",\"price\":1500.99,\"description\":\"Gaming laptop\",\"category\":\"Electronics\",\"brand\":\"BrandX\"}"))
                .andExpect(status().isAccepted());

        verify(productService, times(1)).save(any(Product.class));
    }

    @Test
    void testProductUpdate_ProductExists() throws Exception {
        when(productService.findById(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(put("/api/product/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Laptop\",\"price\":1700.99,\"description\":\"Updated description\",\"category\":\"Computers\",\"brand\":\"BrandY\"}"))
                .andExpect(status().isAccepted());

        verify(productService, times(1)).findById(1L);
        verify(productService, times(1)).save(any(Product.class));
    }

    @Test
    void testProductUpdate_ProductNotFound() throws Exception {
        when(productService.findById(2L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/product/update/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Laptop\",\"price\":1700.99}"))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).findById(2L);
    }

    @Test
    void testDelete_ProductExists() throws Exception {
        when(productService.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productService).delete(1L);

        mockMvc.perform(delete("/api/product/delete/1"))
                .andExpect(status().is(204));

        verify(productService, times(1)).findById(1L);
        verify(productService, times(1)).delete(1L);
    }

    @Test
    void testDelete_ProductNotFound() throws Exception {
        when(productService.findById(2L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/product/delete/2"))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).findById(2L);
        verify(productService, never()).delete(anyLong());
    }
}