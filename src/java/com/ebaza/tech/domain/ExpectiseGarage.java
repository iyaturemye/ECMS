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
public class ExpectiseGarage implements Serializable {

    @Id
    private String uuid = UUID.randomUUID().toString();
    @ManyToOne
    @JoinColumn(name = "garageId")
    private Garage garage;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createAt=new Date();
    private String status="active";
    @ManyToOne
    @JoinColumn(name = "insuranceId")
    private InsuranceCompany insurance;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "expectiseGarage")
    private List<VehicleDetail> vehicleDetails;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public InsuranceCompany getInsurance() {
        return insurance;
    }

    public void setInsurance(InsuranceCompany insurance) {
        this.insurance = insurance;
    }

    @XmlTransient
    public List<VehicleDetail> getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(List<VehicleDetail> vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
}
