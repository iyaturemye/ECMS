/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.domain;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author Godwin
 */
@Entity
public class Quotation implements Serializable {

    @Id
    private String uuid = UUID.randomUUID().toString();
    private double price;
    @ManyToOne
    @JoinColumn(name = "biddingId")
    private Bidding bidding;
    @Transient
    private String insuranceName;
    @ManyToOne
    @JoinColumn(name = "brokenCarPart")
    private BrokenCarPart brokenCarPart;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Bidding getBidding() {
        return bidding;
    }

    public void setBidding(Bidding bidding) {
        this.bidding = bidding;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public BrokenCarPart getBrokenCarPart() {
        return brokenCarPart;
    }

    public void setBrokenCarPart(BrokenCarPart brokenCarPart) {
        this.brokenCarPart = brokenCarPart;
    }
    
}
