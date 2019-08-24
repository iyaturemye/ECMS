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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Godwin
 */
@Entity
public class ForgetPassword implements Serializable{
    @Id
    private final String uuid=UUID.randomUUID().toString();
    @ManyToOne
    private User user;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;
    private int generatedValue;
    
   
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getGeneratedValue() {
        return generatedValue;
    }

    public void setGeneratedValue(int generatedValue) {
        this.generatedValue = generatedValue;
    }
    
    
    
}
