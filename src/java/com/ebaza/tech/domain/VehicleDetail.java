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
import javax.persistence.Column;
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
public class VehicleDetail implements Serializable {

    @Id
    private String uuid = UUID.randomUUID().toString();
    @ManyToOne
    @JoinColumn(name = "vehicleId")
    private Vehicle vehicle;
    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "insuranceId")
    private InsuranceCompany insurance;
    private String status;
    private String location;
    @Column(columnDefinition = "TEXT")
    private String whatHappen;
    @Column(columnDefinition = "TEXT")
    private String statementOfVehicle;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdAt = new Date();
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date UpdatedAt = new Date();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicleDetail")
    private List<VehicleImage> vehicleImage;
    private String readOrUnread="unread";
    @ManyToOne
    @JoinColumn(name="expectiseGarageId")
    private ExpectiseGarage expectiseGarage;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public InsuranceCompany getInsurance() {
        return insurance;
    }

    public void setInsurance(InsuranceCompany insurance) {
        this.insurance = insurance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatementOfVehicle() {
        return statementOfVehicle;
    }

    public void setStatementOfVehicle(String statementOfVehicle) {
        this.statementOfVehicle = statementOfVehicle;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(Date UpdatedAt) {
        this.UpdatedAt = UpdatedAt;
    }

    @XmlTransient
    public List<VehicleImage> getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(List<VehicleImage> vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    public String getWhatHappen() {
        return whatHappen;
    }

    public void setWhatHappen(String whatHappen) {
        this.whatHappen = whatHappen;
    }

    public String getReadOrUnread() {
        return readOrUnread;
    }

    public void setReadOrUnread(String readOrUnread) {
        this.readOrUnread = readOrUnread;
    }

    public ExpectiseGarage getExpectiseGarage() {
        return expectiseGarage;
    }

    public void setExpectiseGarage(ExpectiseGarage expectiseGarage) {
        this.expectiseGarage = expectiseGarage;
    } 

}
