/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.common.DbConstant;
import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.interfc.IPolice;
import com.ebaza.tech.domain.Police;
import java.util.List;

/**
 *
 * @author Godwin
 */
public class PoliceImpl extends AbstractDao<Long, Police> implements IPolice, DbConstant {

    @Override
    public Police create(Police t) {
        try {
            return saveIntable(t);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Police> getAll() {
        try {
            return (List<Police>) (Object) getModelList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Police deleteInfo(Police t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Police updateInfo(Police t) {
        try {
            return updateIntable(t);
        } catch (Exception e) {
            return null;
        }
    }

}
