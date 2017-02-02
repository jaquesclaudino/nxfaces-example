package com.nexten.nxfaces.example.service;

import com.nexten.nxfaces.example.model.entity.User;
import com.nexten.nxfaces.example.service.dao.UserDAO;
import com.nexten.nxfaces.dao.AbstractDAO;
import com.nexten.nxfaces.logging.HtmlHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Jaques Claudino
 */
@Singleton
@Startup
public class InitService {

    private static final Logger LOG = Logger.getLogger(InitService.class.getName());
       
    @Inject
    private UserDAO userDAO;
   
    @PostConstruct
    public void init() {
        try {
            initLogger();
            logEntityCount(userDAO);
            checkUserAdmin();               
            
            LOG.info("Init OK");
            
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Init error", ex);
        }        
    }
    
    private void checkUserAdmin() {
        if (userDAO.findByLogin("admin") == null) {
            LOG.warning("Creating User Administrator");
                        
            User user = new User();
            user.setName("Administrator");
            user.setLogin("admin");
            user.setPassword(User.DEFAULT_PASSWORD);
            user.setGroupName(User.GROUP_NAME_ADMIN);
            user.setEnabled(true);
            userDAO.persist(user);
        }
    }
    
    private void initLogger() {
        HtmlHandler.getInstance().setLevel(Level.FINE);
    }
    
    private void logEntityCount(AbstractDAO... daos) {
        for (AbstractDAO dao : daos) {
            LOG.log(Level.INFO, "Count {0}: {1}", 
                    new Object[] {dao.getEntityClass().getSimpleName(), dao.findCount(null)});
        }
    } 
    
}
