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

/**
 *
 * @author Godwin
 */
@Entity
public class Carsparepart implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "carsparepart")
    private List<Carsparepart> sparePartslist;
    @ManyToOne
    @JoinColumn(name = "parent")
    private Carsparepart carsparepart;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "carsparepart")
    private List<BrokenCarPart> brokenCarParts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Carsparepart> getSparePartslist() {
        return sparePartslist;
    }

    public void setSparePartslist(List<Carsparepart> sparePartslist) {
        this.sparePartslist = sparePartslist;
    }

    public Carsparepart getCarsparepart() {
        return carsparepart;
    }

    public void setCarsparepart(Carsparepart carsparepart) {
        this.carsparepart = carsparepart;
    }

    public List<BrokenCarPart> getBrokenCarParts() {
        return brokenCarParts;
    }

    public void setBrokenCarParts(List<BrokenCarPart> brokenCarParts) {
        this.brokenCarParts = brokenCarParts;
    }
    
    
    
}
