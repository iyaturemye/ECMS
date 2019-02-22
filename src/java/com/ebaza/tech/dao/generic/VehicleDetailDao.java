/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.generic;

import com.ebaza.tech.domain.Quotation;
import com.ebaza.tech.domain.VehicleDetail;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author Godwin
 */
public class VehicleDetailDao {

    public List<VehicleDetail> getClientFinishedCar(String userId) {
        List<VehicleDetail> list = new ArrayList<>();
        try {
            Session s = SessionManager.getSession();
            Query q = s.createQuery("select a from VehicleDetail a where a.client.user.userId=? AND a.status=? ORDER BY a.createdAt DESC");
            q.setString(0, userId);
            q.setString(1, "completed");
            list = q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<VehicleDetail> getExpectiseHistory(String garageId) {
        List<VehicleDetail> list = new ArrayList<>();
        try {
            Session s = SessionManager.getSession();
            Query q = s.createQuery("select a from VehicleDetail a where a.expectiseGarage.uuid=? ORDER BY a.createdAt DESC");
            q.setString(0, garageId);
            list = q.list();
            s.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
    }

    public List<VehicleDetail> getSome(String garageId, String condition) {
        List<VehicleDetail> list = new ArrayList<>();
        try {
            Session s = SessionManager.getSession();
            Query q;
            if (condition.equalsIgnoreCase("new")) {
                q = s.createQuery("select a from VehicleDetail a where a.expectiseGarage.garage.garageId=? and a.readOrUnread='unread' and a.status='start'");
            } else {
                q = s.createQuery("select a from VehicleDetail a where a.expectiseGarage.garage.garageId=? and a.status='start'");
            }
            q.setString(0, garageId);
            list = q.list();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getCount(VehicleDetail vd) {
        try {
            String sql = "select count(vehicleDetail.vehicleId) as numbers from VehicleDetail "
                    + "where status='completed' AND YEAR(createdAt)=YEAR(now()) and vehicleDetail.vehicleId='" + vd.getVehicle().getVehicleId() + "' group by VehicleDetail.vehicleId";
            Session ss = SessionManager.getSession();
            SQLQuery qry = ss.createSQLQuery(sql);
            qry.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = qry.list();
            int num = map2ListToEntity(data);
            ss.close();
            return num;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int map2ListToEntity(List data) throws ParseException {

        int number = 0;
        for (Object object : data) {
            Quotation obj = new Quotation();
            Map row = (Map) object;
            number = Integer.parseInt(row.get("numbers").toString());
        }
        return number;
    }

}
