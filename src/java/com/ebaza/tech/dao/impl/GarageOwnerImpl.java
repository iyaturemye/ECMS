/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.interfc.IGarage;
import com.ebaza.tech.dao.interfc.IGarageOwner;
import com.ebaza.tech.domain.Garage;
import com.ebaza.tech.domain.GarageOwner;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Godwin
 */
public class GarageOwnerImpl extends AbstractDao<Long, GarageOwner> implements IGarageOwner {

    @Override
    public GarageOwner create(GarageOwner t) {
        try {
            return saveIntable(t);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<GarageOwner> getAll() {
        try {
            return (List<GarageOwner>) (Object) getModelList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GarageOwner deleteInfo(GarageOwner t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GarageOwner updateInfo(GarageOwner t) {
        try {
            return updateIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GarageOwner getOwner(String userId) {
        try {
            return getModelWithMyHQL(new String[]{"userId"}, new Object[]{userId}, "from GarageOwner ");
        } catch (Exception ex) {
            Logger.getLogger(GarageOwnerImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
