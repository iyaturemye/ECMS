/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.interfc;

import com.ebaza.tech.domain.Quotation;
import java.util.List;

/**
 *
 * @author Godwin
 */
public interface IQuotation extends Allrelated<Quotation> {
    public List<Quotation> validateIfThereAnotherBidding(String vehicleId,String garageId);
    public List<Quotation> getOne(String billingId);
    public List<Quotation> getApprovedBidd(String garageId);
    public List<Quotation> getGarageBid(String garageId);
    
}
