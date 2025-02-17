package com.reist.reservation.dto;

import com.reist.reservation.entity.Product;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class BookingDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 8601375530658119685L;

    private Date create;
    private Date init;
    private Date end;
    private Product product;
    private Long userId;

    public BookingDto() {
        super();
    }

}
