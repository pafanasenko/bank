package com.bank.db.dao.impl;

import com.bank.db.dao.IPersonDAO;

/**
 * Created by pafanasenko on 29.09.15.
 */
public class Factory {
    private static IPersonDAO personDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public static synchronized IPersonDAO getPersonDAO(){
        if (personDAO == null){
            personDAO = new PersonDAOImpl();
        }
        return personDAO;
    }
}
