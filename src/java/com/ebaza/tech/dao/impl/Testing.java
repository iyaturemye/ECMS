/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.controller.AfricasTalkingGateway;
import com.ebaza.tech.dao.generic.SessionManager;
import com.ebaza.tech.domain.User;
import java.security.NoSuchAlgorithmException;
import org.json.JSONArray;

/**
 *
 * @author Godwin
 */
public class Testing {

    private static String username = "iyaturemye";
    private static String apiKey = "6cee4a31d2456e8a28d3018acdf71ca36ca8b229448cb308ce31cd0a688063dc";

    public static void main(String[] args) throws NoSuchAlgorithmException {
//        SessionManager.getSession();
//        User user = new User();
//        user.setUserName("bafaloclaude@gmail.com");
//        user.setPassword(new LoginImpl().criptPassword("123456"));
//        user.setUserType("admin");
//        user.setStatus("active");
//        new UserImpl().create(user);
//        System.out.println("Have been successfull registered");
//        String value = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod\n"
//                + "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,\n"
//                + "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\n"
//                + "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\n"
//                + "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non\n"
//                + "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
//        System.out.println(value.substring(0,20));
//        SessionManager.getSession();
//        System.out.println("here we goo boss wanjye");
//        AfricasTalkingGateway gateway = new AfricasTalkingGateway(username, apiKey);
//        try {
//            JSONArray results = gateway.sendMessage("+250724587179", "Umva ni Mutabazi Mukanya ndaje uteke byinshi ");
//                        JSONArray result = gateway.sendMessage("+250781409511", "Umva ni Mutabazi Mukanya ndaje uteke byinshi ");
//
//        } catch (Exception e) {
//        }
    
        String out="Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod\n" +
"tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,\n" +
"quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\n" +
"consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\n" +
"cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non\n" +
"proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        System.out.println(out.length());

    

    }
}
