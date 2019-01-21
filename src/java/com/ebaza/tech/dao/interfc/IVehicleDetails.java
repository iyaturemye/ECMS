/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.interfc;

import com.ebaza.tech.domain.VehicleDetail;
import java.util.List;

/**
 *
 * @author Godwin
 */
public interface IVehicleDetails extends Allrelated<VehicleDetail> {
    public List<VehicleDetail> getBrokenCar(int number);
    public List<VehicleDetail> getAllBrokenCar();
    public List<VehicleDetail> getListOfVehicleDetails(String InsuranceId);
    public List<VehicleDetail> getNewAccident(String InsuranceId,String condition);
    public List<VehicleDetail> expertiseGarage(String garageId,String condition);
}
