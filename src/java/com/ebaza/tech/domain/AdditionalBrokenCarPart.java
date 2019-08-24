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

/**
 *
 * @author Godwin
 */
@Entity
public class AdditionalBrokenCarPart implements Serializable {
    @Id
    private String id=UUID.randomUUID().toString();
    private String description;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "additionInfo")
    private List<BrokenCarPart> brokenCarPart;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BrokenCarPart> getBrokenCarPart() {
        return brokenCarPart;
    }

    public void setBrokenCarPart(List<BrokenCarPart> brokenCarPart) {
        this.brokenCarPart = brokenCarPart;
    }
    
}
