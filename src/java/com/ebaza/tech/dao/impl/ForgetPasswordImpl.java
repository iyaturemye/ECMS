/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.common.DbConstant;
import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.interfc.IForgetPassword;
import com.ebaza.tech.domain.ExpectiseGarage;
import com.ebaza.tech.domain.ForgetPassword;
import java.util.List;

/**
 *
 * @author Godwin
 */
public class ForgetPasswordImpl extends AbstractDao<Long, ForgetPassword> implements IForgetPassword, DbConstant {

    @Override
    public ForgetPassword create(ForgetPassword t) {
        return saveIntable(t);
    }

    @Override
    public List<ForgetPassword> getAll() {
        return (List<ForgetPassword>) (Object) getModelList();
    }

    @Override
    public ForgetPassword deleteInfo(ForgetPassword t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ForgetPassword updateInfo(ForgetPassword t) {
        try {
            return updateIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
