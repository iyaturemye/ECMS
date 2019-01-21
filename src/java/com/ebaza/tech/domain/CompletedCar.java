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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class CompletedCar implements Serializable {

    @ManyToOne
    @JoinColumn(name = "bidding")
    private Bidding bidding;
    private String comment;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date CreatedAt = new Date();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "completedCar")
    private List<Payment> payment;
    private String status = "unread";
    private boolean isPaid = false;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long purchaseOrdernum;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updatedAt=new Date();


    public Bidding getBidding() {
        return bidding;
    }

    public void setBidding(Bidding bidding) {
        this.bidding = bidding;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    @XmlTransient
    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Long getPurchaseOrdernum() {
        return purchaseOrdernum;
    }

    public void setPurchaseOrdernum(Long purchaseOrdernum) {
        this.purchaseOrdernum = purchaseOrdernum;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    

}
