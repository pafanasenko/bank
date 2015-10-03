package com.bank.domain.entities;

import com.bank.domain.people.Person;

import java.util.ArrayList;

/**
 * Created by pafanasenko on 28.09.15.
 */
public class Bank {
    private String name;
    private ArrayList<Person> persons;
    private ArrayList<Account> accounts;

    public Bank (String name) {
        this.name = name;
    }
}
