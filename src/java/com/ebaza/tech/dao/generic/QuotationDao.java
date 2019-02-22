/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.generic;

import com.ebaza.tech.domain.Bidding;
import com.ebaza.tech.domain.InsuranceCompany;
import com.ebaza.tech.domain.Quotation;
import com.ebaza.tech.domain.Vehicle;
import com.ebaza.tech.domain.VehicleDetail;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author Godwin
 */
public class QuotationDao {

    public List<Quotation> checkinf(String vehicleDetailsId, String garageId) {
        Session s = SessionManager.getSession();
        Query qry = s.createQuery("select a from Quotation a where a.bidding.vehicleDetails.uuid=? and a.bidding.garage.garageId=?");
        qry.setString(0, vehicleDetailsId);
        qry.setString(1, garageId);
        List<Quotation> q = qry.list();
        s.close();
        return q;
    }

    public List<Quotation> listOfApprovedBid(String garageId) {
        Session s = SessionManager.getSession();
        
        Query qry = s.createQuery("select a,SUM(a.price * a.brokenCarPart.quantity)AS price  from Quotation a where a.bidding.garage.garageId=? and a.bidding.status='clientSide'  group by a.bidding");
        qry.setString(0, garageId);
        List<Quotation> list = qry.list();
        s.close();
        return list;
    }

    public List<Quotation> getGarageBid(String garageId) {
        try {
            List<Quotation> list = new ArrayList<>();
            String query = "select v.name as vname,v.chasisNum,v.plateNum,ins.name,SUM(q.price * BrokenCarPart.quantity) as"
                    + " totalPrice,bid.status bidStatus,bid.bidId,"
                    + "vd.status,bid.createAt from VehicleDetail vd,InsuranceCompany ins,Vehicle v,Quotation q,BrokenCarPart,"
                    + "Bidding bid where "
                    + "ins.uuid=vd.insuranceId "
                    + "and bid.vehicleDetailsId=vd.uuid "
                    + "AND vd.vehicleId=v.vehicleId "
                    + "and bid.garageId='" + garageId + "' "
                    +" AND BrokenCarPart.id=q.brokenCarPart "
                    + "AND bid.bidId=q.biddingId "
                    + "ORDER BY bid.createAt DESC";

            Session s = SessionManager.getSession();
            SQLQuery q = s.createSQLQuery(query);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = q.list();
            if (!data.isEmpty()) {
                list = map2ListToEntity(data);
            }
            s.close();
            return list;
        } catch (ParseException ex) {
            Logger.getLogger(QuotationDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<Quotation> map2ListToEntity(List data) throws ParseException {
        List<Quotation> list = new ArrayList<>();

        for (Object object : data) {
            Quotation obj = new Quotation();
            Map row = (Map) object;
            if (row.get("bidStatus") != null) {

                Bidding bid = new Bidding();
                Vehicle v = new Vehicle();
                InsuranceCompany ins = new InsuranceCompany();
                VehicleDetail vd = new VehicleDetail();
                Quotation q = new Quotation();
                q.setPrice(Double.parseDouble(row.get("totalPrice").toString()));
                bid.setCreateAt((Date) row.get("createAt"));
                System.out.println(bid.getCreateAt());
                bid.setBidId(row.get("bidId").toString());
                bid.setStatus(row.get("bidStatus").toString());
                v.setChasisNum((row.get("chasisNum")!=null )? row.get("chasisNum").toString():"");
                v.setPlateNum(row.get("plateNum").toString());
                v.setName(row.get("vname").toString());
                q.setInsuranceName(row.get("name").toString());
                vd.setVehicle(v);
                vd.setStatus(row.get("status").toString());
                bid.setVehicleDetails(vd);
                q.setBidding(bid);
                list.add(q);
            }
        }
        return list;
    }

    public List<Quotation> oneElement(String bidding) {
        Session s = SessionManager.getSession();
        Query qry = s.createQuery("select a from Quotation a where a.bidding.bidId=?");
        qry.setString(0, bidding);
        List<Quotation> q = qry.list();
        s.close();
        return q;
    }

}
