package org.reist.msvc.user.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class SessionLog implements Serializable {

    @Serial
    private static final long serialVersionUID = -7939533531335783842L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private String ip;
    private Long timeSession;
    private Date dateSession;
    @ManyToOne
    @JoinColumn(name = "username_id")
    private Username username;

    public SessionLog() {
        super();
        this.dateSession = new Date();
    }

    public SessionLog(String location, String ip, Long timeSession, Username username) {
        this.location = location;
        this.ip = ip;
        this.timeSession = timeSession;
        this.dateSession = new Date();
        this.username = username;
    }

}
