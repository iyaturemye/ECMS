/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.common.DbConstant;
import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.interfc.IBrokenCarPart;
import com.ebaza.tech.domain.BrokenCarPart;
import java.util.List;

/**
 *
 * @author Godwin
 */
public class BrokenCarPartImpl extends AbstractDao<Long, BrokenCarPart> implements IBrokenCarPart, DbConstant {

    @Override
    public BrokenCarPart create(BrokenCarPart t) {
        try {
            return saveIntable(t);
        } catch (Exception e) {
            
            return null;
        }
    }

    @Override
    public List<BrokenCarPart> getAll() {
        try {
            return (List<BrokenCarPart>) (Object) getModelList();
        } catch (Exception e) {
           
            return null;
        }

    }

    @Override
    public BrokenCarPart deleteInfo(BrokenCarPart t) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BrokenCarPart updateInfo(BrokenCarPart t) {
        try {
            return updateIntable(t);
        } catch (Exception e) {
           
            return null;
        }

    }

    @Override
    public List<BrokenCarPart> getBrokenCarPart(String vehicleDetailsId) {
        try {
            return getGenericListWithHQLParameter(new String[]{"vehicleDetails_uuid"},new Object[]{vehicleDetailsId},"BrokenCarPart");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
