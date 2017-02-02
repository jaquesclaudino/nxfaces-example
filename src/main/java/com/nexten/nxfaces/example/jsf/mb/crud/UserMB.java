package com.nexten.nxfaces.example.jsf.mb.crud;

import com.nexten.nxfaces.example.model.entity.User;
import com.nexten.nxfaces.example.service.dao.UserDAO;
import com.nexten.nxfaces.crud.AbstractSelectableCRUD;
import com.nexten.nxfaces.dao.AbstractDAO;
import java.util.Arrays;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Jaques Claudino
 */
@Named
@ViewScoped
public class UserMB extends AbstractSelectableCRUD<User> {
   
    @Inject
    private UserDAO dao;
    
    @Override
    public AbstractDAO<User> getDAO() {
        return dao;
    }
    
    @Override
    protected List<String> getGlobalFilterAttributeNames() {
        return Arrays.asList("name", "login", "groupName");
    }
    
}
