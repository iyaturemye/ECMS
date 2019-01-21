/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.common.DbConstant;
import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.generic.ExpectiseDao;
import com.ebaza.tech.dao.interfc.IExpectise;
import com.ebaza.tech.domain.ExpectiseGarage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Godwin
 */
public class ExpectiseImpl extends AbstractDao<Long, ExpectiseGarage> implements IExpectise, DbConstant {

    @Override
    public ExpectiseGarage create(ExpectiseGarage t) {
        try {
            return saveIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ExpectiseGarage> getAll() {

        try {
            return (List<ExpectiseGarage>) (Object) getModelList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ExpectiseGarage updateInfo(ExpectiseGarage t) {
        try {
            return updateIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ExpectiseGarage> getSomeOne(String insuranceId) {
        List<ExpectiseGarage> list = new ArrayList<>();
        try {
            list = getGenericListWithHQLParameter(new String[]{"insuranceId"}, new Object[]{insuranceId}, "ExpectiseGarage");
        } catch (Exception ex) {
            Logger.getLogger(ExpectiseImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ExpectiseGarage deleteInfo(ExpectiseGarage t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOne(ExpectiseGarage exp) {
        try {
            delete(exp);
        } catch (Exception ex) {
            Logger.getLogger(ExpectiseImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<ExpectiseGarage> SearckValue(String insuranceId, String location) {
        try {
            return new ExpectiseDao().search(location, insuranceId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ExpectiseGarage> SearckName(String value) {
        try {
                    return new ExpectiseDao().searchByName(value);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
