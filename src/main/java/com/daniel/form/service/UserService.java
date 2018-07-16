package com.daniel.form.service;

import com.daniel.form.model.User;

import java.util.List;

/**
 * Service for user object to interact with db form web
 *
 * @author daniel
 * @version 1.0
 */
public interface UserService {

    /**
     * Finds user by ID if exists
     *
     * @param id The user's ID
     * @return A User object if found, null otherwise
     */
    User findById(Integer id);

    /**
     * Returns a list of ALL users (Select * command)
     *
     * @return A List of User objects representing all users in table
     */
    List<User> findAll();

    /**
     * Method used to Save new user or update existing user
     *
     * @param user The user object
     */
    void saveOrUpdate(User user);

    /**
     * Deletes user with matching ID
     *
     * @param id The User's id.
     */
    void delete(int id);

}