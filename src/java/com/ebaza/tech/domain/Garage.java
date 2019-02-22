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
public class Garage implements Serializable {

    @Id
    private String garageId = UUID.randomUUID().toString();
    private String name;
    private String logo;
    private String background;
    private String website;
    private String status = "not approved";
    @ManyToOne
    @JoinColumn(name = "owner")
    private GarageOwner garageOwner;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "garage")
    private List<Bidding> bidding;
    private String creditCardNumber;
    private String location;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "garage")
    private List<ExpectiseGarage> expectiseGarage;
    private String garageType;

    public String getGarageId() {
        return garageId;
    }

    public void setGarageId(String garageId) {
        this.garageId = garageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public GarageOwner getGarageOwner() {
        return garageOwner;
    }

    public void setGarageOwner(GarageOwner garageOwner) {
        this.garageOwner = garageOwner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public List<Bidding> getBidding() {
        return bidding;
    }

    public void setBidding(List<Bidding> bidding) {
        this.bidding = bidding;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @XmlTransient
    public List<ExpectiseGarage> getExpectiseGarage() {
        return expectiseGarage;
    }

    public void setExpectiseGarage(List<ExpectiseGarage> expectiseGarage) {
        this.expectiseGarage = expectiseGarage;
    }

    public String getGarageType() {
        return garageType;
    }

    public void setGarageType(String garageType) {
        this.garageType = garageType;
    }
    
    
    

}
