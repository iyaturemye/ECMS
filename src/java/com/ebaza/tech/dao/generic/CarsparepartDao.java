/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.generic;

import com.ebaza.tech.domain.Carsparepart;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Godwin
 */
public class CarsparepartDao {

    public List<Carsparepart> allParent() {
        try {
            Session s = SessionManager.getSession();
            Criteria cr=s.createCriteria(Carsparepart.class);
            cr.add(Restrictions.isNull("carsparepart"));
            List<Carsparepart> list=cr.list();

            s.close();
            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }

    }
    
    public List<Carsparepart> allChild() {
        try {
            Session s = SessionManager.getSession();
            Criteria cr=s.createCriteria(Carsparepart.class);
            cr.add(Restrictions.isNotNull("carsparepart"));
            List<Carsparepart> list=cr.list();
            s.close();
            System.out.println("here we have the following output"+list.size());
            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }

    }
    
}
