package com.springboot.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.demo.entity.User;

/**
 * <p>
 * {@link UserDao} defines database functionalities for the table
 * <b>Users</b>.
 * It inherits from {@link CrudRepository}
 * </p>
 * 
 * @author ikkesh ramanna
 */
@Repository
public interface UserDao extends CrudRepository<User, String>{

}
