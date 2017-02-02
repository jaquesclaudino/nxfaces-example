package com.nexten.nxfaces.example.service.dao;

import com.nexten.nxfaces.example.model.entity.User;
import com.nexten.nxfaces.dao.AbstractDAO;
import javax.ejb.Stateless;

/**
 *
 * @author Jaques Claudino
 */
@Stateless
public class UserDAO extends AbstractDAO<User> {

    public User findByLogin(String login) {
        return findFirstByAttributeEqual("login", login);
    }

}
