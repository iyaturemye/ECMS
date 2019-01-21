/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.interfc;

import com.ebaza.tech.domain.Garage;
import java.util.List;

/**
 *
 * @author Godwin
 */
public interface IGarage extends Allrelated<Garage> {
    public List<Garage> getNotApproved();
    public List<Garage> searchGarage(String value,String columName);
    public Garage getOne(String uuid);
   
}
