/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.controller;

import com.ebaza.tech.dao.impl.ClientImpl;
import com.ebaza.tech.dao.impl.UserImpl;
import com.ebaza.tech.dao.impl.VehicleDetailsImpl;
import com.ebaza.tech.domain.Client;
import com.ebaza.tech.domain.User;
import com.ebaza.tech.domain.VehicleDetail;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Godwin
 */
@ManagedBean
@SessionScoped
public class ClientController implements Serializable {

    private Client client = new Client();
    private List<Client> list = new ClientImpl().getAll();
    List<VehicleDetail> clientFinishedMentainance = new ArrayList<>();
    @ManagedProperty(value = "#{loginController.loggedInUser}")
    private User user;

    @PostConstruct
    public void init() {
        clientFinishedMentainance = new VehicleDetailsImpl().clientFinishedCar(user.getUserId());
    }

    public void changeStatus(User user) {
        String newStatus = (user.getStatus().equalsIgnoreCase("block") ? "active" : "block");
        user.setStatus(newStatus);
        User userd = new UserImpl().updateInfo(user);
        if (userd == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Something went Wrong please", null));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Client Have been successfull " + (newStatus.equalsIgnoreCase("active") ? "activated" : "blocked"), null));
            list = new ClientImpl().getAll();
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Client> getList() {
        return list;
    }

    public void setList(List<Client> list) {
        this.list = list;
    }

    public List<VehicleDetail> getClientFinishedMentainance() {
        return clientFinishedMentainance;
    }

    public void setClientFinishedMentainance(List<VehicleDetail> clientFinishedMentainance) {
        this.clientFinishedMentainance = clientFinishedMentainance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
