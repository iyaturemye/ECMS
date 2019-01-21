/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.common;

import com.mysql.jdbc.log.LogFactory;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author RTAP4
 */
public class Formating {

//    private static final transient Log LOGGER = LogFactory.getLog(Formating.class);

    public String getCurrentMysqlDateFormt() {
        try {
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf
                    = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(dt);
        } catch (Exception jj) {
//            LOGGER.info(jj.getMessage());
        }
        return null;
    }

    public String getCurrentMysqlDateFormtNOTime() {
        try {
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf
                    = new java.text.SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(dt);
        } catch (Exception jj) {
//            LOGGER.info(jj.getMessage());
        }
        return null;
    }

    public Date getCurrentDateFormtNOTime() {
        java.text.SimpleDateFormat sdf
                = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dt = new java.util.Date();
        Date rdate = null;
        try {

            sdf.format(dt);
            rdate = sdf.parse(sdf.format(dt));
        } catch (Exception jj) {
//            LOGGER.info(jj.getMessage());
        }
        return rdate;

    }

    public Date getMysqlDateFormt(String dateValue) {
        Date datee = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            datee = new java.sql.Date(simpleDateFormat.parse(dateValue).getTime());
        } catch (ParseException ex) {
//            LOGGER.info(ex.getMessage());
            return null;
        }
        return datee;
    }

    public Date getMysqlTimeFormt(String timeValue) {
        Date timee = null;
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
        try {
            timee = new java.sql.Time(simpleTimeFormat.parse(timeValue).getTime());
        } catch (ParseException ex) {
//            LOGGER.info(ex.getMessage());
            return null;
        }
        return timee;
    }

    public java.util.Date getFormtDateReturnMysqlFormat(String inputDate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date d = null;
        try {
            d = sdf.parse(inputDate);
        } catch (ParseException ex) {
//            Logger.getLogger(requestRequisitionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        sdf.applyPattern("yyyy-MM-dd");
        return sdf.parse(sdf.format(d));

    }

    public Date getFormtTimeReturnMysqlFormat(String inputTime) throws ParseException {
        SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm a");
        java.util.Date d2 = null;
        try {
            d2 = sdf2.parse(inputTime);
        } catch (ParseException ex) {

        }
        sdf2.applyPattern("hh:mm:ss a");
        return (Date) (sdf2.parse(sdf2.format(d2)));

    }

    public static void main(String... aa) throws ParseException {

    }
}
