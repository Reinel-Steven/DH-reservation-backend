package com.reist.reservation.entity;

import com.reist.reservation.dto.VehicleDto;
import com.reist.reservation.utils.StringConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "product")
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 8601375530658119685L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String listImages;
    private BigDecimal price;
    private String description;
    private String category;
    private String brand;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    private List<Booking> bookings;

    public Product() { super(); }

    public Product(VehicleDto p) {
        this.name = p.getName();
        this.listImages = StringConverter.convertListToString(p.getImages());
        this.price = p.getPrice();
        this.description = p.getDescription();
        this.category = p.getCategory();
        this.brand = p.getBrand();
    }

    public List<String> getImages() {
        return StringConverter.convertStringToList(this.listImages);
    }

    public void setImages(List<String> list) {
        this.listImages = StringConverter.convertListToString(list);
    }
}
