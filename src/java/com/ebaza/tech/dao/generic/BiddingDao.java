/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.generic;

import com.ebaza.tech.domain.ApprovedTemplate;
import com.ebaza.tech.domain.Bidding;
import com.ebaza.tech.domain.TemplateClass;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class BiddingDao {

    public List<Bidding> listOfBid(String insuranceId) {
        Session s = SessionManager.getSession();
        Query qry = s.createQuery("select a from Bidding a where a.vehicleDetails.insuranceCompany_VehicleDetailses.insuranceCompany.uuid a.vehicleDetails.insuranceCompany_VehicleDetailses.insuranceCompany.uuid=? and a.vehicleDetails.status='pending'");
        qry.setString(0, insuranceId);
        List<Bidding> list = qry.list();
        s.close();
        return list;
    }

    public List<TemplateClass> getAnOrderList(String insuranceId) {
        try {
            String query = "select sum(CASE WHEN BrokenCarPart.carsparepart_id IS NULL  THEN Quotation.price "
                    + " ELSE Quotation.price * BrokenCarPart.quantity END) as totalAmount,"
                    + "Bidding.createAt,VehicleDetail.insuranceId as insuranceId, "
                    + "Bidding.estimatedDate,Quotation.uuid,Bidding.bidId,Garage.name as garagename,GarageOwner.phonenumber,Vehicle.name,Vehicle.plateNum,Vehicle.chasisNum "
                    + "from Quotation,Bidding,BrokenCarPart,Garage,GarageOwner,Vehicle,VehicleDetail "
                    + "where Quotation.biddingId=Bidding.bidId "
                    + "AND Quotation.brokenCarPart=BrokenCarPart.id "
                    + "AND Garage.garageId=Bidding.garageId "
                    + "AND Garage.owner=GarageOwner.ownerId "
                    + "AND VehicleDetail.status='proccessed' "
                    + "AND Bidding.VehicleDetailsId=VehicleDetail.uuid "
                    + "AND VehicleDetail.vehicleId=Vehicle.vehicleId "
                    + "AND VehicleDetail.insuranceId=?"
                    + " group by Bidding.bidId ";

            List<TemplateClass> list;
            Session s = SessionManager.getSession();
            SQLQuery q = s.createSQLQuery(query);

            q.setString(0, insuranceId);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = q.list();
            list = mapListToEntity(data);
            s.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TemplateClass> listOfForCombo(String insuranceId) {
        try {
            String query = "select Bidding.bidId,Garage.name as garagename,Bidding.createAt,GarageOwner.phonenumber,"
                    + "sum(Quotation.price * BrokenCarPart.quantity) AS totalAmount,Vehicle.name,Vehicle.plateNum,"
                    + "VehicleDetail.insuranceId from Vehicle,Garage,GarageOwner,"
                    + "Quotation,VehicleDetail,Bidding,InsuranceCompany,BrokenCarPart "
                    + "where Garage.owner=GarageOwner.ownerId and "
                    + "Bidding.garageId=Garage.garageId and "
                    + "Quotation.brokenCarPart=BrokenCarPart.id AND "
                    + " VehicleDetail.status='proccessed'  AND "
                    + "Bidding.bidId=Quotation.biddingId and "
                    + "Bidding.vehicleDetailsId=VehicleDetail.uuid and "
                    + "VehicleDetail.vehicleId=Vehicle.vehicleId AND "
                    + "VehicleDetail.status!='done' AND "
                    + "VehicleDetail.status!='completed' AND "
                    + "VehicleDetail.insuranceId='" + insuranceId + "'"
                    + "group by Vehicle.vehicleId";
            List<TemplateClass> list;
            Session s = SessionManager.getSession();
            SQLQuery q = s.createSQLQuery(query);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = q.list();
            list = mapListToEntity(data);
            s.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ApprovedTemplate> list(String garageId) {
        String query = "select ins.name,cl.phoneNumber,bid.status,bid.comment,bid.estimatedDate,"
                + "sum(CASE WHEN BrokenCarPart.carsparepart_id IS NULL  THEN q.price "
                + " ELSE q.price * BrokenCarPart.quantity END) as totalPrice,"
                + "q.uuid,bid.bidId,vd.location "
                + "from InsuranceCompany ins,Bidding bid,Quotation q,VehicleDetail vd,Client cl,BrokenCarPart "
                + " where "
                + " BrokenCarPart.id=q.brokenCarPart AND "
                + "q.biddingId=bid.bidId "
                + "and bid.vehicleDetailsId=vd.uuid "
                + "AND vd.insuranceId=ins.uuid "
                + "AND vd.uuid=bid.vehicleDetailsId "
                + " AND cl.clientId=vd.clientId "
                + "AND bid.garageId='" + garageId + "'"
                + "AND bid.status!='completed'"
                + " AND bid.isApproved=1"
                + " GROUP BY q.biddingId";
        List<ApprovedTemplate> list;
        Session s = SessionManager.getSession();
        SQLQuery q = s.createSQLQuery(query);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List data = q.list();
        list = map2ListToEntity(data);
        s.close();
        return list;
    }

    // to display list of completed car which needs a document
    public List<ApprovedTemplate> listMissContract(String garageId) {
        String query = "select ins.name,cl.phoneNumber,bid.status,bid.comment,bid.estimatedDate,"
                + "sum(CASE WHEN BrokenCarPart.carsparepart_id IS NULL  THEN q.price "
                + " ELSE q.price * BrokenCarPart.quantity END) as totalPrice,"
                + "q.uuid,bid.bidId,vd.location "
                + "from InsuranceCompany ins,Bidding bid,Quotation q,VehicleDetail vd,Client cl,BrokenCarPart,CompletedCar "
                + " where "
                + " BrokenCarPart.id=q.brokenCarPart AND "
                + "q.biddingId=bid.bidId "
                + "and bid.vehicleDetailsId=vd.uuid "
                + "AND vd.insuranceId=ins.uuid "
                + "AND vd.uuid=bid.vehicleDetailsId "
                + " AND cl.clientId=vd.clientId "
                + "AND  CompletedCar.bidding=bid.bidId "
                + "AND bid.garageId='" + garageId + "'"
                + "AND bid.status='completed'"
                + " AND bid.isApproved=1"
                + " AND CompletedCar.document is NULL"
                + " GROUP BY q.biddingId";
        List<ApprovedTemplate> list;
        Session s = SessionManager.getSession();
        SQLQuery q = s.createSQLQuery(query);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List data = q.list();
        list = map2ListToEntity(data);
        s.close();
        return list;
    }

   
    public List<ApprovedTemplate> map2ListToEntity(List data) {
        List<ApprovedTemplate> list = new ArrayList<>();
        try {
            for (Object object : data) {
                ApprovedTemplate obj = new ApprovedTemplate();
                Map row = (Map) object;
                obj.setInsuranceName(row.get("name").toString());
                obj.setPhoneNumber(row.get("phoneNumber").toString());
                obj.setStatus(row.get("status").toString());
                obj.setTotalPrice(Double.parseDouble(row.get("totalPrice").toString()));
                obj.setLocation(row.get("location").toString());
                obj.setBidId(row.get("bidId").toString());
                obj.setComment(row.get("comment").toString());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateFormat = sdf.format(row.get("estimatedDate"));
                obj.setEstimatedDate(sdf.parse(dateFormat));
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<TemplateClass> mapListToEntity(List data) {
        List<TemplateClass> list = new ArrayList<>();
        for (Object object : data) {
            TemplateClass obj = new TemplateClass();
            Map row = (Map) object;
            obj.setBiddingId(row.get("bidId").toString());
            obj.setCarName(row.get("name").toString());
            obj.setGarageName(row.get("garagename").toString());
            obj.setTotalAmount(Double.parseDouble(row.get("totalAmount").toString()));
            obj.setInsuranceId(row.get("insuranceId").toString());
            obj.setCreatedAt((Date) row.get("createAt"));
            obj.setEstamatedDate((Date) row.get("estimatedDate"));
            obj.setPhoneNumber(row.get("phonenumber").toString());
            obj.setPlateNumber(row.get("plateNum").toString());
            list.add(obj);
        }
        return list;
    }

    public List<TemplateClass> getAnOrderByDesc(String insuranceId, String plateNumber) {
        String query
                = "select sum(CASE WHEN BrokenCarPart.carsparepart_id IS NULL  THEN Quotation.price "
                + " ELSE Quotation.price * BrokenCarPart.quantity END) as totalAmount,"
                + "Bidding.createAt,VehicleDetail.insuranceId as insuranceId, "
                + "Bidding.estimatedDate,Quotation.uuid,Bidding.bidId,Garage.name as garagename,GarageOwner.phonenumber,Vehicle.name,Vehicle.plateNum,Vehicle.chasisNum "
                + "from Quotation,Bidding,BrokenCarPart,Garage,GarageOwner,Vehicle,VehicleDetail "
                + "where Quotation.biddingId=Bidding.bidId "
                + "AND Quotation.brokenCarPart=BrokenCarPart.id "
                + "AND Garage.garageId=Bidding.garageId "
                + "AND Garage.owner=GarageOwner.ownerId "
                + "AND Bidding.VehicleDetailsId=VehicleDetail.uuid "
                + "AND VehicleDetail.vehicleId=Vehicle.vehicleId "
                + "AND VehicleDetail.insuranceId=?"
                + "AND Vehicle.plateNum=?"
                + " group by Bidding.bidId "
                + "ORDER BY totalAmount DESC";

        List<TemplateClass> list;
        Session s = SessionManager.getSession();
        SQLQuery q = s.createSQLQuery(query);
        q.setString(0, insuranceId);
        q.setString(1, plateNumber);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List data = q.list();
        list = mapListToEntity(data);
        s.close();
        return list;
    }

    public List<TemplateClass> getAnOrderByAsc(String insuranceId, String plateNumber) {
        String query
                = "select sum(CASE WHEN BrokenCarPart.carsparepart_id IS NULL  THEN Quotation.price "
                + " ELSE Quotation.price * BrokenCarPart.quantity END) as totalAmount,"
                + "Bidding.createAt,VehicleDetail.insuranceId as insuranceId, "
                + "Bidding.estimatedDate,Quotation.uuid,Bidding.bidId,Garage.name as garagename,GarageOwner.phonenumber,Vehicle.name,Vehicle.plateNum,Vehicle.chasisNum "
                + "from Quotation,Bidding,BrokenCarPart,Garage,GarageOwner,Vehicle,VehicleDetail "
                + "where Quotation.biddingId=Bidding.bidId "
                + "AND Quotation.brokenCarPart=BrokenCarPart.id "
                + "AND Garage.garageId=Bidding.garageId "
                + "AND Garage.owner=GarageOwner.ownerId "
                + "AND Bidding.VehicleDetailsId=VehicleDetail.uuid "
                + "AND VehicleDetail.vehicleId=Vehicle.vehicleId "
                + "AND VehicleDetail.insuranceId=?"
                + "AND Vehicle.plateNum=?"
                + " group by Bidding.bidId "
                + "ORDER BY totalAmount ASC";
        List<TemplateClass> list;
        Session s = SessionManager.getSession();
        SQLQuery q = s.createSQLQuery(query);
        q.setString(0, insuranceId);
        q.setString(1, plateNumber);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List data = q.list();
        list = mapListToEntity(data);
        s.close();
        return list;
    }

    public List<TemplateClass> getNewOne(String insuranceId) {
        String query
                = "select sum(CASE WHEN BrokenCarPart.carsparepart_id IS NULL  THEN Quotation.price "
                + " ELSE Quotation.price * BrokenCarPart.quantity END) as totalAmount,"
                + "Bidding.createAt,VehicleDetail.insuranceId as insuranceId, "
                + "Bidding.estimatedDate,Quotation.uuid,Bidding.bidId,Garage.name as garagename,GarageOwner.phonenumber,Vehicle.name,Vehicle.plateNum,Vehicle.chasisNum "
                + "from Quotation,Bidding,BrokenCarPart,Garage,GarageOwner,Vehicle,VehicleDetail "
                + "where Quotation.biddingId=Bidding.bidId "
                + "AND Quotation.brokenCarPart=BrokenCarPart.id "
                + "AND Garage.garageId=Bidding.garageId "
                + "AND Garage.owner=GarageOwner.ownerId "
                + "AND VehicleDetail.status='proccessed' "
                + "AND Bidding.VehicleDetailsId=VehicleDetail.uuid "
                + "AND VehicleDetail.vehicleId=Vehicle.vehicleId "
                + "AND VehicleDetail.insuranceId=?"
                + " AND Bidding.status='unread' "
                + " group by Bidding.bidId ";
        List<TemplateClass> list = new ArrayList<>();
        try {
            Session s = SessionManager.getSession();
            SQLQuery q = s.createSQLQuery(query);
            q.setString(0, insuranceId);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = q.list();
            list = mapListToEntity(data);
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
