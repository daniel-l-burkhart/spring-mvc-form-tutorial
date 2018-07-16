package com.daniel.form.dao;

import com.daniel.form.model.User;

import java.util.List;

/**
 * User Data Access Object interface
 *
 * @author daniel
 * @version 1.0
 */
public interface UserDao {

    /**
     * Finds the user by their ID
     *
     * @param id The user's id as an integer
     * @return Returns User object if found, otherwise null
     */
    User findById(Integer id);

    /**
     * Selects ALl from Users table
     *
     * @return Returns a List of User objects
     */
    List<User> findAll();

    /**
     * Saves user object to database table, inserts the record
     *
     * @param user The user object representation
     */
    void save(User user);

    /**
     * Updates a user's information
     *
     * @param user The user object representation
     */
    void update(User user);

    /**
     * Deletes the user with the matching ID.
     *
     * @param id The user's ID.
     */
    void delete(Integer id);

}