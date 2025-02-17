package com.reist.reservation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "booking")
public class Booking implements Serializable {

    @Serial
    private static final long serialVersionUID = 8601375530658119685L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_at")
    private Date create;
    @Column(name = "init_at")
    private Date init;
    @Column(name = "end_at")
    private Date end;
    private Long userId;

    @JsonIgnore
    @JoinColumn(name="product_id")
    @ManyToOne(fetch = FetchType.EAGER )
    private Product product;

    public Booking(){
        super();
    }

}
