/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.interfc.IThirdParty;
import com.ebaza.tech.domain.ThirdParty;
import java.util.List;

/**
 *
 * @author Godwin
 */
public class ThirdPartyImpl extends AbstractDao<Long, ThirdParty> implements IThirdParty {

    @Override
    public ThirdParty create(ThirdParty t) {
        try {
            return saveIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ThirdParty> getAll() {
        try {
            return (List<ThirdParty>) (Object) getModelList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ThirdParty deleteInfo(ThirdParty t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ThirdParty updateInfo(ThirdParty t) {
        try {
            return updateIntable(t);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
