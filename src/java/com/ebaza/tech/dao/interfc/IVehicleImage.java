/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.interfc;

import com.ebaza.tech.domain.VehicleImage;
import java.util.List;

/**
 *
 * @author Godwin
 */
public interface IVehicleImage extends Allrelated<VehicleImage> {
    public List<VehicleImage> allsWithGroupBy(String value);
    public List<VehicleImage> getAllChoosenImg(String vdId);
}
