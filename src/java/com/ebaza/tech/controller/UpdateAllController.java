/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.controller;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Godwin
 */
@ManagedBean
public class UpdateAllController {

    public UpdateAllController() {
    }

    public void changeStatus() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext el = facesContext.getELContext();
        Application app = facesContext.getApplication();
        ExpressionFactory ef = app.getExpressionFactory();
        ValueExpression ve = ef.createValueExpression(el, "#{garageController}", GarageController.class);
        GarageController gcon=new GarageController();
        gcon.setStatus("here we go boss wanjye");
        ve.setValue(el,gcon);
        System.out.println("done boss wanjye we");
    }

}
