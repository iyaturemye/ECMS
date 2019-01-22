/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.domain;


import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
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
public class VehicleImage implements Serializable{
    @Id
    private  String vimageId=UUID.randomUUID().toString();
    private String image;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdAt=new Date();
    @ManyToOne
    @JoinColumn(name="vehicleDetailId")
    private VehicleDetail vehicleDetail;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getVimageId() {
        return vimageId;
    }

    public void setVimageId(String vimageId) {
        this.vimageId = vimageId;
    }

    public VehicleDetail getVehicleDetail() {
        return vehicleDetail;
    }

    public void setVehicleDetail(VehicleDetail vehicleDetail) {
        this.vehicleDetail = vehicleDetail;
    }
}
