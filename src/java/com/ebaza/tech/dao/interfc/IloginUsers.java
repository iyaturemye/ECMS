/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ebaza.tech.dao.interfc;

import com.ebaza.tech.domain.User;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author RTAP4
 */
public interface IloginUsers {
   public boolean  checkUserNameAndPasswod(String userName,String Password);
   public User userDetail(String userName);
   public String criptPassword(String password)throws NoSuchAlgorithmException; 
   public String getIpAddress()throws Exception;
}
