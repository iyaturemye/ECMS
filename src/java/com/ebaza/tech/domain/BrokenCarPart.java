/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Godwin
 */
@Entity
public class BrokenCarPart implements Serializable {

    @Id
    private String id=UUID.randomUUID().toString();
    @ManyToOne
    private Carsparepart carsparepart;
    @ManyToOne
    private VehicleDetail vehicleDetails;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdAt=new Date();
    private int quantity;
    private String partNumber;
    @OneToMany(mappedBy = "brokenCarPart",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Quotation> quotation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Carsparepart getCarsparepart() {
        return carsparepart;
    }

    public void setCarsparepart(Carsparepart carsparepart) {
        this.carsparepart = carsparepart;
    }

    public VehicleDetail getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(VehicleDetail vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    } 

    public List<Quotation> getQuotation() {
        return quotation;
    }

    public void setQuotation(List<Quotation> quotation) {
        this.quotation = quotation;
    }

}
