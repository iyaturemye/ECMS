/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.controller;

import com.ebaza.tech.dao.generic.SessionManager;

/**
 *
 * @author Godwin
 */
public class Test {
    public static void main(String[] args) {
        SessionManager.getSession();
        System.out.println("done with it boss wanjye");
    }
}
