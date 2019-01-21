/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.interfc.IVehicleImage;
import com.ebaza.tech.domain.VehicleImage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Godwin
 */
public class VehicleImageImpl extends AbstractDao<Long, VehicleImage> implements IVehicleImage {

    @Override
    public VehicleImage create(VehicleImage t) {
        try {
                   return saveIntable(t);
 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<VehicleImage> getAll() {
        try {
           return (List<VehicleImage>) (Object) getModelList(); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    @Override
    public VehicleImage deleteInfo(VehicleImage t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VehicleImage updateInfo(VehicleImage t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VehicleImage> allsWithGroupBy(String value) {
        try {
            return getAllWithGroupBy(value, new String[]{"vimageId", "image", "createdAt", "vehicleDetail"});
        } catch (Exception ex) {
            Logger.getLogger(VehicleImageImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<VehicleImage> getAllChoosenImg(String vdId) {
        try {
            return getGenericListWithHQLParameter(new String[]{"vehicleDetailId"}, new Object[]{vdId}, "VehicleImage");
        } catch (Exception ex) {
            Logger.getLogger(VehicleImageImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
