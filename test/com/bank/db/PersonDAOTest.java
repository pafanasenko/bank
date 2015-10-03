package com.bank.db;

import com.bank.db.dao.impl.Factory;
import com.bank.domain.people.Admin;
import com.bank.domain.people.Customer;
import com.bank.domain.people.Person;
import org.junit.Test;

import java.util.List;

/**
 * Created by pafanasenko on 29.09.15.
 */
public class PersonDAOTest {

    @Test
    public void testAdd() throws Exception {
        Person c1 = new Person("John", "Smith", Person.ROLE.Customer);
        Person c2 = new Person("Peter", "Jolt", Person.ROLE.Admin);
        //Person c1 = new Customer("John", "Smith");
        //Person c2 = new Admin("Peter", "Jolt");

        Factory.getInstance().getPersonDAO().add(c1);
        Factory.getInstance().getPersonDAO().add(c2);

        List<Person> persons = Factory.getInstance().getPersonDAO().getAll();
        System.out.println("========Persons=========");
        for(int i = 0; i < persons.size(); ++i) {
            System.out.println("Person : " + persons.get(i).getFirstName() + " "
                    + persons.get(i).getLastName() +",  id : " + persons.get(i).getId()
                    + ", role: " + persons.get(i).getRole() + ", deleted: "
                    + (persons.get(i).isDeleted() ? "true" : "false"));
            System.out.println("=============================");
        }
    }
}