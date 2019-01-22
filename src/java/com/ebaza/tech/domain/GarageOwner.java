/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.domain;


import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Godwin
 */
@Entity
public class GarageOwner implements Serializable{
    @Id
    private String ownerId=UUID.randomUUID().toString();
    private String name;
    private String phoneNumber;
    @OneToMany(cascade =CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "garageOwner")
    private List<Garage> garage;
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
    
    
    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @XmlTransient
    public List<Garage> getGarage() {
        return garage;
    }

    public void setGarage(List<Garage> garage) {
        this.garage = garage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    } 
}
