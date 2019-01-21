/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.controller;

import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Godwin
 */
@ManagedBean
@SessionScoped
public class ChangeLanguage implements Serializable {

    private String language;
    private Locale locale;

    @PostConstruct
    public void init() {
        if (language == null) {
            locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        } else {
            System.out.println("here we have the following message" + language);
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
        }
    }

    public void changeLanguage() {
        if (this.language != null) {
            locale = new Locale(this.language);
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
        }
    }

    public void changeLanguage(String language) {
        this.language = language;
        if (this.language != null) {
            locale = new Locale(this.language);
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
        }
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
