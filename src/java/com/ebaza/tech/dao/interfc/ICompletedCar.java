/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.interfc;

import com.ebaza.tech.domain.CompletedCar;
import java.util.List;

/**
 *
 * @author Godwin
 */
public interface  ICompletedCar extends Allrelated<CompletedCar> {
    public List<CompletedCar> getAllCompleted(String insuranceId);
}
