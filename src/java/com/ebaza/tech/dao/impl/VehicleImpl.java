/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.interfc.IVehicle;
import com.ebaza.tech.domain.Vehicle;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Godwin
 */
public class VehicleImpl extends AbstractDao<Long, Vehicle> implements IVehicle,Serializable {

    @Override
    public Vehicle create(Vehicle t) {
        try {
            return saveIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Vehicle> getAll() {
        try {
            return (List<Vehicle>) (Object) getModelList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Vehicle updateInfo(Vehicle t) {
        try {
            return updateIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Vehicle getVehicleById(int vehicleId, String primaryKeyclomunName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vehicle deleteInfo(Vehicle t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vehicle getVehicle(String plateNumber) {
        try {
            return getModelWithMyHQL(new String[]{"plateNum"}, new Object[]{plateNumber}, "from Vehicle");
        } catch (Exception ex) {
            Logger.getLogger(VehicleImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
