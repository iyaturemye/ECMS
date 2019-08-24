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
import javax.persistence.FetchType;
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
public class InsuranceCompany implements Serializable {

    @Id
    private String uuid = UUID.randomUUID().toString();
    private String name;
    private String description;
    private String terms;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private String phoneNumber;
    private String logo;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurance")
    private List<VehicleDetail> insuranceCompanyVehicle;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurance")
    private List<ExpectiseGarage> expectiseGarage;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insuranceOfvehicleb")
    private List<VehicleDetail> vehicleDetails;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @XmlTransient
    public List<VehicleDetail> getInsuranceCompanyVehicle() {
        return insuranceCompanyVehicle;
    }

    public void setInsuranceCompanyVehicle(List<VehicleDetail> insuranceCompanyVehicle) {
        this.insuranceCompanyVehicle = insuranceCompanyVehicle;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @XmlTransient
    public List<ExpectiseGarage> getExpectiseGarage() {
        return expectiseGarage;
    }

    public void setExpectiseGarage(List<ExpectiseGarage> expectiseGarage) {
        this.expectiseGarage = expectiseGarage;
    }

    public List<VehicleDetail> getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(List<VehicleDetail> vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    

}
