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
    List<VehicleDetail> getBrokenCar(int number);
    List<VehicleDetail> getAllBrokenCar();
    List<VehicleDetail> getListOfVehicleDetails(String InsuranceId);
    List<VehicleDetail> getNewAccident(String InsuranceId,String condition);
    List<VehicleDetail> expertiseGarage(String garageId,String condition);
    List<VehicleDetail> clientFinishedCar(String userId);
}
