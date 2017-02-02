package com.nexten.nxfaces.example.jsf.mb;

import com.nexten.nxfaces.example.model.entity.User;
import com.nexten.nxfaces.example.service.dao.UserDAO;
import com.nexten.nxfaces.util.FacesUtil;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Jaques Claudino
 */
@Named
@ViewScoped
public class PasswordMB implements Serializable {
    
    @Inject
    private LoginMB loginMB;

    @Inject
    private UserDAO userDAO;
    
    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;
    
    public void changePassword() {
        User user = loginMB.getCurrentUser();
        
        if (!user.getPassword().equals(currentPassword)) {
            FacesUtil.addMsgErrorBundle("CurrentPasswordDoesNotMatch");
        } else if (!newPassword.equals(confirmNewPassword)) {
            FacesUtil.addMsgErrorBundle("ConfirmationPasswordDoesNotMatch");
        } else {
            user.setPassword(newPassword);
            userDAO.save(user);            
            FacesUtil.addMsgInfoBundle("PasswordChangedSuccessfully");
        }
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }    
    
}
