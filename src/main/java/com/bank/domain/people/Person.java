package com.bank.domain.people;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Created by pafanasenko on 28.09.15.
 */
@Entity
@Table(name="Person")
public class Person {
    //TODO return to package
    public enum ROLE {Customer, Admin};

    private long id;
    private String firstName;
    private String lastName;
    private ROLE role;
    private boolean isDeleted = false;

    //TODO return to protected
    public Person (String firstName, String lastName, ROLE role) {
        super ();

        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    Person () {} //for ORM

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    public Long getId() {
        return id;
    }

    @Column(name="firstName")
    public String getFirstName () { return firstName; }

    @Column(name="lastName")
    public String getLastName() { return lastName; }

    @Column(name="role")
    public ROLE getRole () { return role; }

    @Column(name="isDeleted")
    public boolean isDeleted () { return isDeleted; }

    private void setId(Long i){
        id = i;
    }

    public void setDeleted(boolean deleted){
        isDeleted = deleted;
    }

    public void setFirstName(String name){
        firstName = name;
    }

    public void setLastName(String name){
        lastName = name;
    }

    public void setRole(ROLE role){
        this.role = role;
    }

    public void delete () { isDeleted = true; }
}
