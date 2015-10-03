package com.bank.db.dao;

import com.bank.domain.people.Person;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by pafanasenko on 29.09.15.
 */
public interface IPersonDAO {
    public void add(Person person) throws SQLException;
    public void update(Person student) throws SQLException;
    public Person getById(Long id) throws SQLException;
    public List getAll() throws SQLException;
    public void destroy(Person student) throws SQLException;
}
