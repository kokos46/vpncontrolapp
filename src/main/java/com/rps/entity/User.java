package com.rps.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String uuid;
    private String password;
    private String role;
    private String email;
    private Date passwordExpire;

    public Long getId(){ return id; }
    public String getUuid(){ return uuid;}
    public String getPassword(){ return password; }
    public String getRole(){ return role; }
    public String getEmail(){ return email; }

    public void setPassword(String password){ this.password = password; }

    public void setRole(String role){
        this.role = role;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setUuid(String uuid){ this.uuid = uuid; }
}
