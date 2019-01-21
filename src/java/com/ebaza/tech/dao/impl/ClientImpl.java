/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.common.DbConstant;
import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.interfc.IClient;
import com.ebaza.tech.dao.interfc.IGarage;
import com.ebaza.tech.domain.Client;
import com.ebaza.tech.domain.Garage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Godwin
 */
public class ClientImpl extends AbstractDao<Long, Client> implements IClient, DbConstant {

    @Override
    public Client searchElement(String value, String columName) {
        try {
            return this.getModelWithMyHQL(new String[]{columName}, new Object[]{value}, "from Client");
        } catch (Exception ex) {

            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Client create(Client t) {
        try {
            return saveIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Client> getAll() {
        try {
            return (List<Client>) (Object) getModelList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Client deleteInfo(Client t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Client updateInfo(Client t) {
        try {
            return updateIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
