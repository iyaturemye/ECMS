/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Godwin
 */
@Entity
@XmlRootElement
public class Bidding {

    @Id
    private String bidId = UUID.randomUUID().toString();
    @ManyToOne()
    @JoinColumn(name = "garageId")
    private Garage garage;
    @ManyToOne
    @JoinColumn(name = "vehicleDetailsId")
    private VehicleDetail vehicleDetails;
    private String status = "unread";
    private boolean isApproved = false;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bidding")
    private List<Quotation> quotation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bidding")
    private List<CompletedCar> completedCar;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createAt = new Date();
    private String comment = "";
    private double totalPrice;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date estimatedDate;

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public VehicleDetail getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(VehicleDetail vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    @XmlTransient
    public List<Quotation> getQuotation() {
        return quotation;
    }

    public void setQuotation(List<Quotation> quotation) {
        this.quotation = quotation;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @XmlTransient
    public List<CompletedCar> getCompletedCar() {
        return completedCar;
    }

    public void setCompletedCar(List<CompletedCar> completedCar) {
        this.completedCar = completedCar;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getEstimatedDate() {
        return estimatedDate;
    }

    public void setEstimatedDate(Date estimatedDate) {
        this.estimatedDate = estimatedDate;
    }
    
    
    
    
}
