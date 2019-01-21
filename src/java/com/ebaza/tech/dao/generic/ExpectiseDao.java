/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.generic;

import com.ebaza.tech.domain.ExpectiseGarage;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Godwin
 */
public class ExpectiseDao {

    public List<ExpectiseGarage> search(String location, String insuranceId) {
        List<ExpectiseGarage> list = new ArrayList<>();
        try {
            Session session = SessionManager.getSession();
            Criteria cr = session.createCriteria(ExpectiseGarage.class);
            cr.createAlias("insurance", "i");
            cr.createAlias("garage", "g");
            cr.add(Restrictions.eq("i.uuid", insuranceId));
            cr.add(Restrictions.like("g.location", location, MatchMode.START));
            list = cr.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ExpectiseGarage> searchByName(String searchKey) {
        List<ExpectiseGarage> list = new ArrayList<>();
        try {
            Session session = SessionManager.getSession();
            Criteria cr = session.createCriteria(ExpectiseGarage.class);
            cr.createAlias("garage", "g");
            cr.add(Restrictions.like("g.name", searchKey, MatchMode.START));
            list = cr.list();
            session.close();
        } catch (Exception e) {
        }
        return list;
    }
}
