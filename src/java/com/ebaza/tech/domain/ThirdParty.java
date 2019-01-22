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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Godwin
 */
@Entity
public class ThirdParty implements Serializable {

    @Id
    private String uuid = UUID.randomUUID().toString();
    private String name;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dob;
    private String nationalId;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createAt = new Date();
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "thirdParty")
    private List<PoliceReport> policeReport;

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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    @XmlTransient
    public List<PoliceReport> getPoliceReport() {
        return policeReport;
    }

    public void setPoliceReport(List<PoliceReport> policeReport) {
        this.policeReport = policeReport;
    }
}
