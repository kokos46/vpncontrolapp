package com.rps.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String uuid;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private Date expire_date;

}
