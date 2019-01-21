/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.interfc;

import com.ebaza.tech.domain.ExpectiseGarage;
import java.util.List;

/**
 *
 * @author Godwin
 */
public interface IExpectise extends Allrelated<ExpectiseGarage> {

    public List<ExpectiseGarage> getSomeOne(String insuranceId);

    public void deleteOne(ExpectiseGarage exp);

    public List<ExpectiseGarage> SearckValue(String insuranceId, String location);

    public List<ExpectiseGarage> SearckName(String value);

}
