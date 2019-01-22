/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.common.DbConstant;
import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.interfc.IDriver;
import com.ebaza.tech.domain.Driver;
import java.util.List;

/**
 *
 * @author Godwin
 */
public class DriverImpl extends AbstractDao<Long, Driver> implements IDriver, DbConstant {

    
    @Override
    public Driver create(Driver t) {
        try {
            return saveIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Driver> getAll() {
        try {
            return (List<Driver>) (Object) getModelList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Driver deleteInfo(Driver t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Driver updateInfo(Driver t) {
        try {
            return updateIntable(t);
        } catch (Exception e) {
            return null;
        }
    }

}
