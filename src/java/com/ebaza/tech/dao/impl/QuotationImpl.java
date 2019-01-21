/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.generic.QuotationDao;
import com.ebaza.tech.dao.interfc.IQuotation;
import com.ebaza.tech.domain.Quotation;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Godwin
 */
public class QuotationImpl extends AbstractDao<Long, Quotation> implements IQuotation {

    private Logger log;

    @Override
    public List<Quotation> validateIfThereAnotherBidding(String vehicleId, String garageId) {
        try {
            return new QuotationDao().checkinf(vehicleId, garageId);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Quotation create(Quotation t) {
        try {
            return saveIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Quotation> getAll() {
        try {
            return (List<Quotation>) (Object) getModelList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Quotation deleteInfo(Quotation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Quotation updateInfo(Quotation t) {
        try {
            return updateIntable(t);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Quotation> getOne(String billingId) {

        try {
            return new QuotationDao().oneElement(billingId);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Quotation> getApprovedBidd(String garageId) {
        try {
            return new QuotationDao().listOfApprovedBid(garageId);

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Quotation> getGarageBid(String garageId) {
        try {
            return new QuotationDao().getGarageBid(garageId);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
