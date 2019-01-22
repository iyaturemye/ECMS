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
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Godwin
 */
@Entity
public class User implements Serializable {

    @Id
    private String userId = UUID.randomUUID().toString();
    private String userName;
    private String userType;
    private String password;
    private String status;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Client> client;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<GarageOwner> garageOwner;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<InsuranceCompany> insuranceCompany;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Police> police;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public List<Client> getClient() {
        return client;
    }

    public void setClient(List<Client> client) {
        this.client = client;
    }

    @XmlTransient
    public List<GarageOwner> getGarageOwner() {
        return garageOwner;
    }

    public void setGarageOwner(List<GarageOwner> garageOwner) {
        this.garageOwner = garageOwner;
    }

    @XmlTransient
    public List<InsuranceCompany> getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(List<InsuranceCompany> insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    @XmlTransient
    public List<Police> getPolice() {
        return police;
    }

    public void setPolice(List<Police> police) {
        this.police = police;
    }

}
