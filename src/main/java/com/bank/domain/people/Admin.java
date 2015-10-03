package com.bank.domain.people;

import javax.persistence.Entity;

/**
 * Created by pafanasenko on 28.09.15.
 */
//@Entity
public class Admin extends Person {

    public Admin(String firstName, String secondName) {
        super(firstName, secondName, ROLE.Admin);
    }
}
