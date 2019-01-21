/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.generic;


import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Godwin
 */
public class VinsuranceDao {

//    public List<InsuranceCompany_VehicleDetails> search(String insuranceId, String plateNumber) {
//        Session session = SessionManager.getSession();
//        Query qry = session.createQuery("select a from InsuranceCompany_VehicleDetails a where a.vehicleDetails.vehicle.plateNum like ? AND a.insuranceCompany.uuid=? ");
//        qry.setString(0, "'%" + plateNumber + "%'");
//        qry.setString(1, insuranceId);
//        List<InsuranceCompany_VehicleDetails> list = qry.list();
//        System.out.println("we have the following result " + list.size() + "***********************888");
//        session.close();
//        return list;
//    }
}
