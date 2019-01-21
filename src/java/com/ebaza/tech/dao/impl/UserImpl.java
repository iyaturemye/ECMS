/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.interfc.IUser;
import com.ebaza.tech.domain.User;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author RTAP4
 */
public class UserImpl extends AbstractDao<Long, User> implements IUser {

    private static final transient Logger LOGGER = LoggerFactory
            .getLogger(UserImpl.class);

    @Override
    public User gettUserById(int userId, String primaryKeyclomunName) {
        try {
            return (User) getModelById(userId, primaryKeyclomunName);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //To change body of generated methods, choose Tools | Templates.
    }

    public boolean createTable() {
        creatingNewTable();
        return true;
    }

    @Override
    public User create(User t) {
        return saveIntable(t);
    }

    @Override
    public List<User> getAll() {
        try {
            return (List<User>) (Object) getModelList(); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public User deleteInfo(User t) {
        return t;
    }

    @Override
    public User updateInfo(User t) {
        try {
                    return updateIntable(t);//To change body of generated methods, choose Tools | Templates.

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
