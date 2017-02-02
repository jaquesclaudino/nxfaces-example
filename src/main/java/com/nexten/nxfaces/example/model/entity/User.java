package com.nexten.nxfaces.example.model.entity;

import com.nexten.nxfaces.model.entity.AutoIdEntity;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Jaques Claudino
 */
@Entity
public class User extends AutoIdEntity {

    public static final String GROUP_NAME_ADMIN = "admin";
    public static final String GROUP_NAME_USER = "user";
    public static final String DEFAULT_PASSWORD = "1";
    
    @Column(unique = true)
    private String name;
    
    @Column(unique = true)
    private String login;
        
    private String password = DEFAULT_PASSWORD;
        
    private String groupName = GROUP_NAME_USER;
    
    private boolean enabled = true;   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
}
