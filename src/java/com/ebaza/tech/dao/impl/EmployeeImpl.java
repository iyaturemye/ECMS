/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.impl;

import com.ebaza.tech.common.DbConstant;
import com.ebaza.tech.dao.generic.AbstractDao;
import com.ebaza.tech.dao.interfc.IEmployee;
import com.ebaza.tech.domain.Employee;
import java.util.List;

/**
 *
 * @author Godwin
 */
public class EmployeeImpl extends AbstractDao<Long, Employee> implements IEmployee, DbConstant {

    @Override
    public Employee create(Employee t) {
        try {
            return saveIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Employee> getAll() {
        try {
            return (List<Employee>) (Object) getModelList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee deleteInfo(Employee t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Employee updateInfo(Employee t) {
        try {
            return updateIntable(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
