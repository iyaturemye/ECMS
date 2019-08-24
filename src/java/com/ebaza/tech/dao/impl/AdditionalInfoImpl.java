/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.common.DbConstant;
import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.interfc.IAdditionalInfo;
import com.ebaza.tech.domain.AdditionalBrokenCarPart;
import java.util.List;

/**
 *
 * @author Godwin
 */
public class AdditionalInfoImpl extends AbstractDao<Long, AdditionalBrokenCarPart> implements IAdditionalInfo, DbConstant {

    @Override
    public AdditionalBrokenCarPart create(AdditionalBrokenCarPart t) {
        try {
            return saveIntable(t);
        } catch (Exception e) {
            
            return null;
        }
    }

    @Override
    public List<AdditionalBrokenCarPart> getAll() {
        try {
            return (List<AdditionalBrokenCarPart>) (Object) getModelList();
        } catch (Exception e) {
           
            return null;
        }

    }

    @Override
    public AdditionalBrokenCarPart deleteInfo(AdditionalBrokenCarPart t) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdditionalBrokenCarPart  updateInfo(AdditionalBrokenCarPart t) {
        try {
            return updateIntable(t);
        } catch (Exception e) {
           
            return null;
        }

    }

}
