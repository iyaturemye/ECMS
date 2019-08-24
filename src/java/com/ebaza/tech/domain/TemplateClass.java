/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.domain;

import java.util.Date;

/**
 *
 * @author Godwin
 */
public class TemplateClass {

    private String biddingId;
    private String GarageName;
    private double totalAmount;
    private String carName;
    private String insuranceId;
    private String vehicleDetailsId;
    private String phoneNumber;
    private Date createdAt;
    private String plateNumber;
    private Date estamatedDate;

    public String getBiddingId() {
        return biddingId;
    }

    public void setBiddingId(String biddingId) {
        this.biddingId = biddingId;
    }

    public String getGarageName() {
        return GarageName;
    }

    public void setGarageName(String GarageName) {
        this.GarageName = GarageName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getVehicleDetailsId() {
        return vehicleDetailsId;
    }

    public void setVehicleDetailsId(String vehicleDetailsId) {
        this.vehicleDetailsId = vehicleDetailsId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Date getEstamatedDate() {
        return estamatedDate;
    }

    public void setEstamatedDate(Date estamatedDate) {
        this.estamatedDate = estamatedDate;
    }
    
    
}
