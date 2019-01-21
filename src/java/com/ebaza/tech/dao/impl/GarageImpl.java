/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.common.DbConstant;
import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.interfc.IGarage;
import com.ebaza.tech.domain.Garage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Godwin
 */
public class GarageImpl extends AbstractDao<Long, Garage> implements IGarage, DbConstant,Serializable {

    @Override
    public Garage create(Garage t) {
        try {
            return saveIntable(t);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Garage> getAll() {
        try {
            return (List<Garage>) (Object) getModelList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Garage updateInfo(Garage t) {
        try {
            return updateIntable(t);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Garage> getNotApproved() {
        List<Garage> listlist = new ArrayList<>();
        try {
            listlist = getGenericListWithHQLParameter(new String[]{"status"}, new Object[]{NOTAPPROVED}, "Garage");
        } catch (Exception ex) {
            Logger.getLogger(GarageImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listlist;
    }

    @Override
    public Garage deleteInfo(Garage t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Garage> searchGarage(String value, String columName) {
        try {
            return search(value, columName, Garage.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Garage getOne(String uuid) {
        try {
            return getModelWithMyHQL(new String[]{"owner"}, new Object[]{uuid}, "from Garage");
        } catch (Exception ex) {
            Logger.getLogger(GarageImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
