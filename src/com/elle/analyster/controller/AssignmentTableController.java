/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elle.analyster.controller;

import com.elle.analyster.dao.AssignmentDAO;
import com.elle.analyster.entities.Assignment;

/**
 *
 * @author Yi
 */
public class AssignmentTableController extends DBTableController<Assignment> {
    
    public AssignmentTableController(){
        super();
        tableName = ASSIGNMENTS_TABLE_NAME;
        onlineDAO = new AssignmentDAO();
       
        //load issues from db to map
        getAll();
        
       
    }
    
}
