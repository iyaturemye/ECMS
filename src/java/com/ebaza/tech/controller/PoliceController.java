/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.controller;

import com.ebaza.tech.dao.impl.LoginImpl;
import com.ebaza.tech.dao.impl.PoliceImpl;
import com.ebaza.tech.dao.impl.UserImpl;
import com.ebaza.tech.domain.Police;
import com.ebaza.tech.domain.User;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Godwin
 */
@ManagedBean
public class PoliceController {
    
    private List<Police> list = new PoliceImpl().getAll();
    private Police police = new Police();
    private User user = new User();

    public void create() throws Exception {
        User user = new UserImpl().getModelWithMyHQL(new String[]{"username"}, new Object[]{this.user.getUserName()}, "from User");
        if (user == null) {
            this.user.setPassword(new LoginImpl().criptPassword("police"));
            this.user.setStatus("active");
            this.user.setUserType("police");
            this.user = new UserImpl().create(this.user);
            this.police.setUser(this.user);
            Police p = new PoliceImpl().create(this.police);
            if (p == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "There is an error please try again later", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved Successfull his default password is police", null));
                this.user = new User();
                this.police = new Police();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "User " + this.user.getUserName() + " Already Exist", null));
        }
        System.out.println("here we go boss wanjye we");

    }

    public List<Police> getList() {
        return list;
    }

    public void setList(List<Police> list) {
        this.list = list;
    }

    public Police getPolice() {
        return police;
    }

    public void setPolice(Police police) {
        this.police = police;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
