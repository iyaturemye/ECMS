/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.generic;

import com.ebaza.tech.domain.PoliceReport;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Godwin
 */
public class PoliceReportDao {

    public List<PoliceReport> getPoliceReport(String vehicleId) {
        try {
            Session ss = SessionManager.getSession();
            Query qry = ss.createQuery("select a from PoliceReport a where a.iyagonzeVehicle.vehicleId=? "
                    + " or a.iyagonzweVehicle.vehicleId=? AND a.status='not Approved'");
            qry.setString(0, vehicleId);
            qry.setString(1, vehicleId);
            List<PoliceReport> list = qry.list();
            ss.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
