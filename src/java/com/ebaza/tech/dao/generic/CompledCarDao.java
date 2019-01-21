/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.generic;

import com.ebaza.tech.domain.CompletedCar;
import com.ebaza.tech.dto.GaragePayment;
import java.math.BigInteger;
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
public class CompledCarDao {

    public List<CompletedCar> getAll(String Insuranceid) {
        try {
            Session s = SessionManager.getSession();
            Query qry = s.createQuery("select a from CompletedCar a where a.bidding.vehicleDetails.insurance.uuid=?");
            qry.setString(0, Insuranceid);
            qry.setMaxResults(10);
            List<CompletedCar> list = qry.list();
            s.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<GaragePayment> getAllNotPaid(String garageId) {

        try {
            Session s = SessionManager.getSession();
            SQLQuery sql = s.createSQLQuery("select CompletedCar.createdAt,CompletedCar.isPaid,Vehicle.plateNum,InsuranceCompany.name,CompletedCar.purchaseOrdernum,"
                    + "sum(Quotation.price*Quotation.quantity) as totalAmount from Vehicle,CompletedCar,InsuranceCompany,Quotation,Bidding,VehicleDetail  "
                    + "where "
                    + "Bidding.bidid=Quotation.biddingId "
                    + "AND Bidding.status='completed' "
                    + "AND Bidding.garageId='" + garageId + "' "
                    + "AND Bidding.vehicleDetailsId=VehicleDetail.uuid "
                    + "AND VehicleDetail.vehicleId=Vehicle.vehicleId "
                    + "AND VehicleDetail.insuranceId=InsuranceCompany.uuid "
                    + "AND CompletedCar.bidding=Bidding.bidid "
                    + "AND CompletedCar.isPaid='0'"
                    + " Group by Quotation.biddingId");
            List<GaragePayment> list;
            sql.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = sql.list();
            System.out.println(data);
            list = mapListToEntity(data);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<GaragePayment> getAllTransaction(String garageId) {

        try {
            Session s = SessionManager.getSession();
            SQLQuery sql = s.createSQLQuery("select CompletedCar.createdAt,CompletedCar.isPaid,Vehicle.plateNum,InsuranceCompany.name,CompletedCar.purchaseOrdernum,"
                    + "sum(Quotation.price*Quotation.quantity) as totalAmount from Vehicle,CompletedCar,InsuranceCompany,Quotation,Bidding,VehicleDetail  "
                    + "where "
                    + "Bidding.bidid=Quotation.biddingId "
                    + "AND Bidding.status='completed' "
                    + "AND Bidding.garageId='" + garageId + "' "
                    + "AND Bidding.vehicleDetailsId=VehicleDetail.uuid "
                    + "AND VehicleDetail.vehicleId=Vehicle.vehicleId "
                    + "AND VehicleDetail.insuranceId=InsuranceCompany.uuid "
                    + "AND CompletedCar.bidding=Bidding.bidid "
                   
                    + " Group by Quotation.biddingId");
            List<GaragePayment> list;
            sql.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            sql.setMaxResults(10);
            List data = sql.list();
            System.out.println(data);
            list = mapListToEntity(data);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<GaragePayment> notPaidInTotalForGarage(String garageId) {
        try {
            Session s = SessionManager.getSession();
            SQLQuery sql = s.createSQLQuery("select CompletedCar.createdAt,CompletedCar.isPaid,Vehicle.plateNum,InsuranceCompany.name,CompletedCar.purchaseOrdernum,"
                    + "sum(Quotation.price*Quotation.quantity) as totalAmount from Vehicle,CompletedCar,InsuranceCompany,Quotation,Bidding,VehicleDetail  "
                    + "where "
                    + "Bidding.bidid=Quotation.biddingId "
                    + "AND Bidding.status='completed' "
                    + "AND Bidding.garageId='" + garageId + "' "
                    + "AND Bidding.vehicleDetailsId=VehicleDetail.uuid "
                    + "AND VehicleDetail.vehicleId=Vehicle.vehicleId "
                    + "AND VehicleDetail.insuranceId=InsuranceCompany.uuid "
                    + "AND CompletedCar.bidding=Bidding.bidid "
                    + "AND CompletedCar.isPaid='0'"
                    + " Group by Quotation.biddingId"
                    + " AND InsuranceCompany.uuid");
            List<GaragePayment> list;
            sql.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = sql.list();
            System.out.println(data);
            list = mapListToEntity(data);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<GaragePayment> notPaidInsurance(String insuranceId) {
        try {
            Session s = SessionManager.getSession();
            SQLQuery sql = s.createSQLQuery("select CompletedCar.createdAt,CompletedCar.isPaid,"
                    + "Vehicle.plateNum,Garage.name,CompletedCar.purchaseOrdernum,"
                    + "sum(Quotation.price*Quotation.quantity) as totalAmount "
                    + "from Vehicle,CompletedCar,InsuranceCompany,Quotation,Bidding,VehicleDetail,Garage"
                    + "  where Bidding.bidid=Quotation.biddingId "
                    +"AND Bidding.garageId=Garage.garageId "
                    + "AND Bidding.status='completed' "
                    + "AND Bidding.vehicleDetailsId=VehicleDetail.uuid AND "
                    + "VehicleDetail.insuranceId='" + insuranceId + "' AND "
                    + "VehicleDetail.vehicleId=Vehicle.vehicleId AND "
                    + "VehicleDetail.insuranceId=InsuranceCompany.uuid AND "
                    + "CompletedCar.bidding=Bidding.bidid AND "
                    + "CompletedCar.isPaid='0' "
                    + "Group by Quotation.biddingId AND InsuranceCompany.uuid");
            List<GaragePayment> list;
            sql.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List data = sql.list();
            System.out.println(data);
            list = mapListToEntity(data);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<GaragePayment> mapListToEntity(List data) {
        List<GaragePayment> list = new ArrayList<>();
        for (Object object : data) {
            GaragePayment obj = new GaragePayment();
            Map row = (Map) object;
            obj.setInsuranceName(row.get("name").toString());
            obj.setPlateNum(row.get("plateNum").toString());
            obj.setStatus((boolean) row.get("isPaid"));
            BigInteger big = (BigInteger) row.get("purchaseOrdernum");
            obj.setPurchasingOrderNum(big.longValue());
            obj.setTotalAmount(Double.parseDouble(row.get("totalAmount").toString()));
            list.add(obj);
        }
        return list;
    }

}
