/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.controller;

import com.ebaza.tech.dao.impl.ClientImpl;
import com.ebaza.tech.dao.impl.UserImpl;
import com.ebaza.tech.domain.Client;
import com.ebaza.tech.domain.User;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Godwin
 */
@ManagedBean
public class ClientController implements Serializable {

    private Client client = new Client();
    private List<Client> list = new ClientImpl().getAll();

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

}
