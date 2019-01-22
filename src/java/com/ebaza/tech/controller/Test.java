/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.controller;

import com.ebaza.tech.dao.impl.LoginImpl;
import com.ebaza.tech.dao.impl.UserImpl;
import com.ebaza.tech.domain.User;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Godwin
 */
public class Test {

    public static void main(String[] args) throws NoSuchAlgorithmException {
//        SessionManager.getSession();
        User user = new User();
        user.setUserName("bafaloclaude@gmail.com");
        user.setPassword(new LoginImpl().criptPassword("123"));
        user.setUserType("member");
        new UserImpl().create(user);
        System.out.println("here we go boss we");
    }
}
