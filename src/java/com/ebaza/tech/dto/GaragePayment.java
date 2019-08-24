/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dto;

import java.util.Date;

/**
 *
 * @author Godwin
 */
public class GaragePayment {

    private Long purchasingOrderNum;
    private String plateNum;
    private String insuranceName;
    private double totalAmount;
    private boolean status;
    private Date estimatedDate;
    private Date completeCarDate;
    private String document;

    public Long getPurchasingOrderNum() {
        return purchasingOrderNum;
    }

    public void setPurchasingOrderNum(Long purchasingOrderNum) {
        this.purchasingOrderNum = purchasingOrderNum;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getEstimatedDate() {
        return estimatedDate;
    }

    public void setEstimatedDate(Date estimatedDate) {
        this.estimatedDate = estimatedDate;
    }

    public Date getCompleteCarDate() {
        return completeCarDate;
    }

    public void setCompleteCarDate(Date completeCarDate) {
        this.completeCarDate = completeCarDate;
    }  

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
    
    
}
