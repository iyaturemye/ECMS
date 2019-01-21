/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.interfc.IPoliceReport;
import com.ebaza.tech.domain.PoliceReport;
import java.util.List;

/**
 *
 * @author Godwin
 */
public class PoliceReportImpl extends AbstractDao<Long, PoliceReport> implements IPoliceReport {

    @Override
    public PoliceReport create(PoliceReport t) {
        try {
            return saveIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PoliceReport> getAll() {
        try {
            return (List<PoliceReport>) (Object) getModelList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PoliceReport deleteInfo(PoliceReport t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PoliceReport updateInfo(PoliceReport t) {
        try {
            return updateIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
