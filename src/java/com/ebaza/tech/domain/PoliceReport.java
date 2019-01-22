/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Godwin
 */
@Entity
public class PoliceReport implements Serializable {
    @Id
    private String uuid = UUID.randomUUID().toString();
    @ManyToOne
    private Vehicle iyagonzweVehicle;
    @ManyToOne
    @JoinColumn(name="")
    private Vehicle iyagonzeVehicle;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdAt = new Date();
    @ManyToOne
    @JoinColumn(name = "thirdParty")
    private ThirdParty thirdParty;
    private String location;
    @ManyToOne
    @JoinColumn(name = "reportedBy")
    private Police police;
    private String additionalDocument;
    private String status="not Approved";
    //private Stri

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Vehicle getIyagonzweVehicle() {
        return iyagonzweVehicle;
    }

    public void setIyagonzweVehicle(Vehicle iyagonzweVehicle) {
        this.iyagonzweVehicle = iyagonzweVehicle;
    }

    public Vehicle getIyagonzeVehicle() {
        return iyagonzeVehicle;
    }

    public void setIyagonzeVehicle(Vehicle iyagonzeVehicle) {
        this.iyagonzeVehicle = iyagonzeVehicle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ThirdParty getThirdParty() {
        return thirdParty;
    }

    public void setThirdParty(ThirdParty thirdParty) {
        this.thirdParty = thirdParty;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Police getPolice() {
        return police;
    }

    public void setPolice(Police police) {
        this.police = police;
    }

    public String getAdditionalDocument() {
        return additionalDocument;
    }

    public void setAdditionalDocument(String additionalDocument) {
        this.additionalDocument = additionalDocument;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
}
