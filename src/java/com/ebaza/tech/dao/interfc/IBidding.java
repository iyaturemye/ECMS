/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.interfc;

import com.ebaza.tech.domain.ApprovedTemplate;
import com.ebaza.tech.domain.Bidding;
import com.ebaza.tech.domain.TemplateClass;
import java.util.List;

/**
 *
 * @author Godwin
 */
public interface IBidding extends Allrelated<Bidding> {

    public List<TemplateClass> listOfBidding(String uid);

    public List<TemplateClass> newOne(String uid);

    public List<TemplateClass> orderByDesc(String uid,String plateNumber);

    public List<TemplateClass> orderByAsc(String uid,String plateNumber);
    public List<ApprovedTemplate> getApprovedBd(String uid);
    public Bidding getOne(String bidId);
    List<ApprovedTemplate> getMissingDocument(String garageId);
    
}
