package com.nexten.nxfaces.example.jsf.mb;

import com.nexten.nxfaces.example.model.entity.User;
import com.nexten.nxfaces.example.service.dao.UserDAO;
import com.nexten.nxfaces.util.FacesUtil;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jaques Claudino
 */
@Named
@SessionScoped
public class LoginMB implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(LoginMB.class.getName());

    @Inject
    private UserDAO userDAO;
        
    private String login;
    private String password;
    
    private User currentUser;    
           
    public String login() {
        LOG.log(Level.INFO, "Login: {0}", login);
        try {
            HttpServletRequest request = (HttpServletRequest) getExternalContext().getRequest();
            
            // throws an exception if is authenticated
            if (isAuthenticated()) {
                currentUser = null;
                request.logout();
            }                
            request.login(login, password);
                         
            return "/user/index";
            
        } catch (Exception ex) {
            LOG.log(Level.FINE, "Login error", ex);
            FacesUtil.addMsgErrorBundle("InvalidLogin");
            return "/public/login";
        }        
    }
    
    public String logout() {        
        try {
            currentUser = null;
            
            HttpServletRequest request = (HttpServletRequest) getExternalContext().getRequest();
            request.logout();
            
            HttpSession session = (HttpSession) getExternalContext().getSession(false);
            if (session != null) {
                session.invalidate();
            }            
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Logout error", ex);
        }        
        return "/public/login?faces-redirect=true";
    }
        
    private String getPrincipalName() {
        Principal principal = getExternalContext().getUserPrincipal();
        if (principal != null) {
            return principal.getName();
        }
        return null;
    }
    
    public User getCurrentUser() {
        if (currentUser == null && getPrincipalName() != null) {
            currentUser = userDAO.findByLogin(getPrincipalName());
        }
        return currentUser;
    }
    
    public boolean isAdmin() {
        return getExternalContext().isUserInRole(User.GROUP_NAME_ADMIN);
    }
    
    public boolean isAuthenticated() {
        return getCurrentUser() != null;
    }
    
    private ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }  
            
    public String getVersion() {
        return getClass().getPackage().getImplementationVersion();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    
    
}
