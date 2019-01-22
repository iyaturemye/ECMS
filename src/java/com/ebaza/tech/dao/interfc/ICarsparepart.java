/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebaza.tech.dao.interfc;

import com.ebaza.tech.domain.Carsparepart;
import java.util.List;

/**
 *
 * @author Godwin
 */
public interface ICarsparepart extends Allrelated<Carsparepart> {

    List<Carsparepart> allParentsparepart();

    List<Carsparepart> allChildCarsparepart();

}
