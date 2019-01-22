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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Godwin
 */
@Entity
public class Vehicle implements Serializable {

    @Id
    private String vehicleId = UUID.randomUUID().toString();
    private String name;
    private String plateNum;
    private String chasisNum;
    private String policyNumber;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vehicle")
    private List<VehicleDetail> vehicleDetails;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iyagonzweVehicle")
    private List<PoliceReport> iyagonzwe;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iyagonzeVehicle")
    private List<PoliceReport> iyagonze;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vehicleb")
    private List<VehicleDetail> vehicleDetail;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getChasisNum() {
        return chasisNum;
    }

    public void setChasisNum(String chasisNum) {
        this.chasisNum = chasisNum;
    }

    @XmlTransient
    public List<VehicleDetail> getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(List<VehicleDetail> vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    @XmlTransient
    public List<PoliceReport> getIyagonzwe() {
        return iyagonzwe;
    }

    public void setIyagonzwe(List<PoliceReport> iyagonzwe) {
        this.iyagonzwe = iyagonzwe;
    }

    @XmlTransient
    public List<PoliceReport> getIyagonze() {
        return iyagonze;
    }

    public void setIyagonze(List<PoliceReport> iyagonze) {
        this.iyagonze = iyagonze;
    }

    public List<VehicleDetail> getVehicleDetail() {
        return vehicleDetail;
    }

    public void setVehicleDetail(List<VehicleDetail> vehicleDetail) {
        this.vehicleDetail = vehicleDetail;
    }
    
    
    

}
