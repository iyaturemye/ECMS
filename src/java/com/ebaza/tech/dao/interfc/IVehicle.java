/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.interfc;

import com.ebaza.tech.domain.Vehicle;
import java.util.List;

/**
 *
 * @author RTAP4
 */
public interface IVehicle extends Allrelated<Vehicle> {

    public Vehicle getVehicleById(int vehicleId, String primaryKeyclomunName);

    public Vehicle getVehicle(String plateNumber);

}
