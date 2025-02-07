package com.reist.reservation.entity;

import com.reist.reservation.dto.ProductDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 8601375530658119685L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String image;
    private BigDecimal price;
    private String description;

    public Product() { super(); }

    public Product(ProductDto p) {
        this.name = p.getName();
        this.image = p.getImage();
        this.price = this.getPrice();
        this.description = getDescription();
    }
}
