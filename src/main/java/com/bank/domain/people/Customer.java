package com.bank.domain.people;

import com.bank.domain.entities.Account;

import javax.persistence.Entity;
import java.util.Set;

/**
 * Created by pafanasenko on 28.09.15.
 */
//@Entity
public class Customer extends Person {

    private Set<Account> accounts;

    public Customer(String firstName, String secondName) {
        super(firstName, secondName, ROLE.Customer);
    }
}
