/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.common.DbConstant;
import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.generic.CompledCarDao;
import com.ebaza.tech.domain.CompletedCar;
import com.ebaza.tech.dao.interfc.ICompletedCar;
import java.util.List;

/**
 *
 * @author Godwin
 */
public class CompletedCarImpl extends AbstractDao<Long, CompletedCar> implements ICompletedCar, DbConstant {

    @Override
    public CompletedCar create(CompletedCar t) {
        try {
            return saveIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CompletedCar> getAll() {
        try {
            return (List<CompletedCar>) (Object) getModelList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CompletedCar deleteInfo(CompletedCar t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompletedCar updateInfo(CompletedCar t) {
        try {
            return updateIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CompletedCar> getAllCompleted(String insuranceid) {
        try {
            return new CompledCarDao().getAll(insuranceid);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
