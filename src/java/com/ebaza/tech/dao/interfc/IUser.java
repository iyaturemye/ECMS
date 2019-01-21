/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.interfc;

import com.ebaza.tech.domain.User;

/**
 *
 * @author Godwin
 */
public interface IUser extends Allrelated<User> {
   public User gettUserById(int userId,String primaryKeyclomunName); 
}
