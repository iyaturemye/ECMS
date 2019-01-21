/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.common.DbConstant;
import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.generic.BiddingDao;
import com.ebaza.tech.dao.interfc.IBidding;
import com.ebaza.tech.domain.ApprovedTemplate;
import com.ebaza.tech.domain.Bidding;
import com.ebaza.tech.domain.TemplateClass;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Godwin
 */
public class BiddingImpl extends AbstractDao<Long, Bidding> implements IBidding, DbConstant {

    @Override
    public Bidding create(Bidding t) {
        try {
            return saveIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Bidding> getAll() {
        try {
            return (List<Bidding>) (Object) getModelList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Bidding deleteInfo(Bidding t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bidding updateInfo(Bidding t) {
        try {
            return updateIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<TemplateClass> listOfBidding(String uid) {
        try {
            return new BiddingDao().getAnOrderList(uid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<TemplateClass> newOne(String uid) {
        try {
            return new BiddingDao().getNewOne(uid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<TemplateClass> orderByDesc(String uid, String plateNum) {
        try {
            return new BiddingDao().getAnOrderByDesc(uid, plateNum);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<TemplateClass> orderByAsc(String uid, String plateNum) {
        try {
            return new BiddingDao().getAnOrderByAsc(uid, plateNum);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Bidding getOne(String bidId) {
        try {
            return getModelWithMyHQL(new String[]{"bidId"}, new Object[]{bidId}, "from Bidding");
        } catch (Exception ex) {
            Logger.getLogger(BiddingImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<ApprovedTemplate> getApprovedBd(String uid) {
        try {
            return new BiddingDao().list(uid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
