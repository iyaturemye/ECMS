/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.domain;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Godwin
 */
@Entity
public class Payment {

    @Id
    private String uuid = UUID.randomUUID().toString();
    private String creditCard;
    @ManyToOne
    @JoinColumn(name = "completedCar")
    private CompletedCar completedCar;
    private Date createdAt;
    
}
