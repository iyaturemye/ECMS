/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.controller;

import com.ebaza.tech.common.FileUpload;
import com.ebaza.tech.dao.generic.SessionManager;
import com.ebaza.tech.dao.impl.GarageImpl;
import com.ebaza.tech.dao.impl.LoginImpl;
import com.ebaza.tech.dao.impl.UserImpl;
import com.ebaza.tech.domain.Garage;
import com.ebaza.tech.domain.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Godwin
 */
@ManagedBean
@ViewScoped
public class TestController implements  Serializable {

    private String searchKey;
    private List<Garage> list;
    
     public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        UploadedFile file = event.getFile();
        try{
            System.out.println("here we go boss wanjye we");
            System.out.println(file.getFileName());
            FileOutputStream fos = new FileOutputStream(new File(file.getFileName()));
            InputStream is = file.getInputstream();
            int BUFFER_SIZE = 8192;
            byte[] buffer = new byte[BUFFER_SIZE];
            int a;
            while(true){
                a = is.read(buffer);
                if(a < 0) break;
                fos.write(buffer, 0, a);
                fos.flush();
            }
            fos.close();
            is.close();
        }catch(IOException e){
        }
    }
    
    public void Upload(FileUploadEvent event) {
        new FileUpload().Upload(event, "/layout/");
        System.out.println("here we go boss wanjye we ");
    }

    public void testing() throws NoSuchAlgorithmException {
        User user=new User();
        user.setUserName("bafaloclaude@gmail.com");
        user.setPassword(new LoginImpl().criptPassword("123456"));
        user.setUserType("admin");
        user.setStatus("active");
        new UserImpl().create(user);
    }

    public void doSearch() {
        if (searchKey.isEmpty()) {
            list = new ArrayList<>();
        } else {
            list = new GarageImpl().searchGarage(searchKey, "name");
        }

    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public List<Garage> getList() {
        return list;
    }

    public void setList(List<Garage> list) {
        this.list = list;
    }

}
