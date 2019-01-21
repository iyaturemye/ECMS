/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.interfc;

import com.ebaza.tech.domain.GarageOwner;

/**
 *
 * @author Godwin
 */
public interface IGarageOwner extends Allrelated<GarageOwner> {
    
    public GarageOwner getOwner(String userId);
}
