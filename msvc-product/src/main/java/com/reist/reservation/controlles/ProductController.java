package com.reist.reservation.controlles;

import com.reist.reservation.dto.ProductDto;
import com.reist.reservation.entity.Product;
import com.reist.reservation.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private static final String ERROR_DB = "Error connection data base";
    private static final String PRODUCT_NOT_EXIST = "El producto no existe";
    @Autowired
    private IProductService service;
    @GetMapping
    public ResponseEntity<List<ProductDto>> listProducts(){
        try{
            List<ProductDto> listProductDto = new ArrayList<>();
            for (Product p : service.findAll()){
                listProductDto.add(new ProductDto(p));
            }
            if(!listProductDto.isEmpty()){
                return ResponseEntity.ok(listProductDto);
            }
            return ResponseEntity.noContent().build();
        }catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_DB, e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> productDetail(@PathVariable Long id){
        try {
            Optional<Product> product = service.findById(id);
            if(product.isPresent()){
                return ResponseEntity.ok(new ProductDto(product.get()));
            }
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, PRODUCT_NOT_EXIST);
        }catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_DB, e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> productCreate(ProductDto productDto){
        try {
            Product product = new Product(productDto);
            service.save(product);
            return ResponseEntity.accepted().build();
        }catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_DB, e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> productUpdate(@PathVariable Long id, @RequestBody ProductDto productDto){
        try {
            Optional<Product> productUpdate = service.findById(id);
            if(productUpdate.isPresent()){
                Product product = productUpdate.get();
                product.setName(productDto.getName());
                product.setDescription(productDto.getDescription());
                product.setPrice(productDto.getPrice());
                product.setImage(productDto.getImage());
                service.save(product);
                return ResponseEntity.accepted().build();
            }
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, PRODUCT_NOT_EXIST);
        }catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_DB, e);
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
        try {
            Optional<Product> user = service.findById(id);
            if(user.isPresent()){
                service.delete(id);
            }
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, PRODUCT_NOT_EXIST);
        }catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_DB, e);
        }
    }
}
