/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.interfc;

import com.ebaza.tech.domain.Client;
import java.util.List;

/**
 *
 * @author Godwin
 */
public interface IClient extends Allrelated<Client> {
   public Client searchElement(String value,String columName);
}
