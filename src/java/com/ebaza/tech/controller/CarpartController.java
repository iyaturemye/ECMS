/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.controller;

import com.ebaza.tech.common.SendEmail;
import com.ebaza.tech.dao.impl.CarsparepartImpl;
import com.ebaza.tech.domain.Carsparepart;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Godwin
 */
@ManagedBean
@ViewScoped
public class CarpartController {

    private List<Carsparepart> allOfThem = new CarsparepartImpl().getAll();
    private String choosenOne;
    private Carsparepart carsparepart = new Carsparepart();
    private String action = "Save";

    public void create() {
        try {
            if (this.action.equals("Save")) {
                if (choosenOne.equalsIgnoreCase("parent")) {
                    this.carsparepart.setCarsparepart(null);
                } else {
                    Carsparepart car = new Carsparepart();
                    car.setId(choosenOne);
                    this.carsparepart.setCarsparepart(car);
                }
                Carsparepart cars = new CarsparepartImpl().create(carsparepart);
                if (cars == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "There is an error please try again later", null));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Have been successfull Saved", null));
                }
                allOfThem = new CarsparepartImpl().getAll();
                this.carsparepart = new Carsparepart();
            } else {
                if (choosenOne.equalsIgnoreCase("parent")) {
                    this.carsparepart.setCarsparepart(null);
                } else {
                    Carsparepart car = new Carsparepart();
                    car.setId(choosenOne);
                    this.carsparepart.setCarsparepart(car);
                }
                Carsparepart cars = new CarsparepartImpl().updateInfo(carsparepart);
                if (cars == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "There is an error please try again later", null));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Have been successfull Saved", null));
                }
                allOfThem = new CarsparepartImpl().getAll();
                this.carsparepart = new Carsparepart();
               this.action= "Save";
            }

        } catch (Exception e) {
            new SendEmail().sendEmail("iyaturemyeclaude@gmail.com", "error", e.getLocalizedMessage());
        }
    }

    public void init(Carsparepart carsparepart) {
        this.carsparepart = carsparepart;
        this.action = "Save Change";
    }

    public void delete(Carsparepart carsparepart) {

        try {
            new CarsparepartImpl().delete(carsparepart);
              allOfThem = new CarsparepartImpl().getAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Have been successfull deleted", null));
        } catch (Exception e) {

        }
    }

    public List<Carsparepart> getAllOfThem() {
        return allOfThem;
    }

    public void setAllOfThem(List<Carsparepart> allOfThem) {
        this.allOfThem = allOfThem;
    }

    public String getChoosenOne() {
        return choosenOne;
    }

    public void setChoosenOne(String choosenOne) {
        this.choosenOne = choosenOne;
    }

    public Carsparepart getCarsparepart() {
        return carsparepart;
    }

    public void setCarsparepart(Carsparepart carsparepart) {
        this.carsparepart = carsparepart;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
