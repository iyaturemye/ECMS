/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.interfc.IGarageOwner;
import com.ebaza.tech.dao.interfc.IInsuranceCompany;
import com.ebaza.tech.domain.GarageOwner;
import com.ebaza.tech.domain.InsuranceCompany;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Godwin
 */
public class InsuranceImpl extends AbstractDao<Long, InsuranceCompany> implements IInsuranceCompany {

    @Override
    public InsuranceCompany create(InsuranceCompany t) {
        try {
            return saveIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<InsuranceCompany> getAll() {
        try {
            return (List<InsuranceCompany>) (Object) getModelList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public InsuranceCompany deleteInfo(InsuranceCompany t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InsuranceCompany updateInfo(InsuranceCompany t) {
        try {
            return updateIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public InsuranceCompany getOne(String userId) {
        try {
            return getModelWithMyHQL(new String[]{"userId"}, new Object[]{userId}, "from InsuranceCompany");
        } catch (Exception ex) {
            Logger.getLogger(InsuranceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
