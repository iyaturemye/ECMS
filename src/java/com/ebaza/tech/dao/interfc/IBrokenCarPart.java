/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.interfc;

import com.ebaza.tech.domain.BrokenCarPart;
import java.util.List;

/**
 *
 * @author Godwin
 */
public interface IBrokenCarPart extends Allrelated<BrokenCarPart>{
    List<BrokenCarPart> getBrokenCarPart(String vehicleDetailsId);
}
