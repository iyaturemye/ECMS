/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.common.DbConstant;
import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.generic.CarsparepartDao;
import com.ebaza.tech.dao.interfc.ICarsparepart;
import com.ebaza.tech.domain.Carsparepart;
import java.util.List;

/**
 *
 * @author Godwin
 */
public class CarsparepartImpl extends AbstractDao<Long, Carsparepart> implements ICarsparepart, DbConstant {

    @Override
    public Carsparepart create(Carsparepart t) {
        try {
            return saveIntable(t);
        } catch (Exception e) {
            
            return null;
        }
    }

    @Override
    public List<Carsparepart> getAll() {
        try {
            return (List<Carsparepart>) (Object) getModelList();
        } catch (Exception e) {
           
            return null;
        }

    }

    @Override
    public Carsparepart deleteInfo(Carsparepart t) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Carsparepart updateInfo(Carsparepart t) {
        try {
            return updateIntable(t);
        } catch (Exception e) {
           
            return null;
        }

    }

    @Override
    public List<Carsparepart> allParentsparepart() {
        return new CarsparepartDao().allParent();
    }

    @Override
    public List<Carsparepart> allChildCarsparepart() {
        return new CarsparepartDao().allChild();
    }
    
}
