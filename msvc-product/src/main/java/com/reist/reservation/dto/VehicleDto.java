package com.reist.reservation.dto;

import com.reist.reservation.entity.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Getter
@Setter
public class VehicleDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -3561990368545425106L;

    private String name;
    private List<String> image;
    private String category;
    private String brand;
    private BigDecimal price;
    private String description;

    public VehicleDto() { super(); }

    public VehicleDto(Product v) {
        this.name = v.getName();
        this.image = v.getImage();
        this.category = v.getCategory();
        this.brand = v.getBrand();
        this.price = v.getPrice();
        this.description = v.getDescription();
    }
}
