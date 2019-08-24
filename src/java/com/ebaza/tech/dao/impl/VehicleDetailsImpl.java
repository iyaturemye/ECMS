/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.generic.VehicleDetailDao;
import com.ebaza.tech.dao.interfc.IVehicleDetails;
import com.ebaza.tech.domain.VehicleDetail;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Godwin
 */
public class VehicleDetailsImpl extends AbstractDao<Long, VehicleDetail> implements IVehicleDetails {

    @Override
    public VehicleDetail create(VehicleDetail t) {
        try {
            return saveIntable(t);
        } catch (Exception ex) {
            Logger.getLogger(VehicleDetailsImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<VehicleDetail> getAll() {
        try {
            return (List<VehicleDetail>) (Object) getModelList();

        } catch (Exception ex) {
            Logger.getLogger(VehicleDetailsImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public VehicleDetail updateInfo(VehicleDetail t) {
        try {
            return updateIntable(t);

        } catch (Exception ex) {
            Logger.getLogger(VehicleDetailsImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public VehicleDetail deleteInfo(VehicleDetail t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VehicleDetail> getBrokenCar(int number) {
        List<VehicleDetail> list = new ArrayList<>();
        try {
            list = getListModelWithMyHQL(new String[]{"status"}, new Object[]{"proccessed"}, "from VehicleDetail", number);
        } catch (Exception ex) {
            Logger.getLogger(VehicleDetailsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<VehicleDetail> getAllBrokenCar() {
        List<VehicleDetail> list = new ArrayList<>();
        try {
            list = getListModelWithMyHQL(new String[]{"status"}, new Object[]{"proccessed"}, "from VehicleDetail", 0);
        } catch (Exception ex) {
            Logger.getLogger(VehicleDetailsImpl.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;
    }

    @Override
    public List<VehicleDetail> getListOfVehicleDetails(String InsuranceId) {
        List<VehicleDetail> list = new ArrayList<>();
        try {
            list = getListModelWithMyHQL(new String[]{"insuranceId"}, new Object[]{InsuranceId}, "from VehicleDetail", 0);
        } catch (Exception ex) {
            Logger.getLogger(VehicleDetailsImpl.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;
    }

    @Override
    public List<VehicleDetail> getNewAccident(String InsuranceId, String condition) {
        List<VehicleDetail> list = new ArrayList<>();
        try {
            if (condition.equalsIgnoreCase("new")) {
                list = getListModelWithMyHQL(new String[]{"insuranceId", "status", "readOrUnread"}, new Object[]{InsuranceId, "pending", "unread"}, "from VehicleDetail", 0);
            }
            list = getListModelWithMyHQL(new String[]{"insuranceId", "status"}, new Object[]{InsuranceId, "pending"}, "from VehicleDetail", 0);
        } catch (Exception ex) {
            Logger.getLogger(VehicleDetailsImpl.class.getName()).log(Level.SEVERE, null, ex);

        }
        return list;
    }

    @Override
    public List<VehicleDetail> expertiseGarage(String garageId, String condition) {
        List<VehicleDetail> list = new VehicleDetailDao().getSome(garageId, condition);
        return list;
    }

    @Override
    public List<VehicleDetail> clientFinishedCar(String userId) {
        return new VehicleDetailDao().getClientFinishedCar(userId);
    }

    public List<VehicleDetail> getCarNeedExpertise(String insuranceId) {
        try {
            return new VehicleDetailDao().getCarNeedExpert(insuranceId);
        } catch (Exception e) {
            Logger.getLogger(VehicleDetailsImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }

    }

}
