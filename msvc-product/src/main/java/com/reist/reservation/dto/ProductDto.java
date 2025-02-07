package com.reist.reservation.dto;

import com.reist.reservation.entity.Product;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -3561990368845425106L;

    private String name;
    private String image;
    private BigDecimal price;
    private String description;

    public ProductDto(Product p) {
        this.name = p.getName();
        this.image = p.getImage();
        this.price = p.getPrice();
        this.description = getDescription();
    }
}
