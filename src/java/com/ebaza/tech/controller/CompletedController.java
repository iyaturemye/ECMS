/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.controller;

import com.ebaza.tech.domain.CompletedCar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Godwin
 */
@ManagedBean
@ViewScoped
public class CompletedController implements  Serializable {
    private CompletedCar completed;
    private List<CompletedCar> listOfCompletedCar=new ArrayList<>();
    
    
    public void completeWork(String biddingId){
    
    }

    public CompletedCar getCompleted() {
        return completed;
    }

    public void setCompleted(CompletedCar completed) {
        this.completed = completed;
    }

    public List<CompletedCar> getListOfCompletedCar() {
        return listOfCompletedCar;
    }

    public void setListOfCompletedCar(List<CompletedCar> listOfCompletedCar) {
        this.listOfCompletedCar = listOfCompletedCar;
    }
    
    
    
    
    
    
}
