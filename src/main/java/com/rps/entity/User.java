package com.rps.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private Long tg_id;
    private String uuid;
    private String username;
    private String password;
//    private Date passwordExpire;
    private Long trx_count;

    public Long getId(){ return id; }
    public String getUuid(){ return uuid;}
    public String getPassword(){ return password; }
    public String getUsername(){ return username; }
    public Long getTg_id(){ return tg_id; }

    public void setPassword(String password){ this.password = password; }
    public void setUsername(String username){ this.username = username; }
    public void setUuid(String uuid){ this.uuid = uuid; }
    public void setTg_id(Long tg_id){ this.tg_id = tg_id; }
    public void setTrx_count(Long trx_count){this.trx_count = trx_count;}

    public User(){}

    public User(String username, String password, Long tg_id, Long trx_count) {
        this.username = username;
        this.password = password;
        this.tg_id = tg_id;
        this.trx_count = trx_count;
    }
}
