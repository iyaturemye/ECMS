/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.generic;

import com.ebaza.tech.domain.Employee;
import com.ebaza.tech.domain.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Godwin
 */
public class EmployeeDao {
    public List<Employee> getEmployee(User user){
        Session s=SessionManager.getSession();
        Query qry=s.createQuery("select a from Employee a where a.user.parent=?");
        qry.setEntity(0,user);
        List<Employee> empList=qry.list();
        s.close();
        return empList;
    }
}
