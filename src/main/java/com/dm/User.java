package com.dm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by liran on 5/4/17.
 */

@Entity (name = "USER_DETAILS")
public class User {
    @Id
    @Column(name = "LOGIN_NAME")
    private String loginName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "LAST_LOGIN")
    private String lastLogin; //TODO need to change to DateTime

    public User(){}

    public User(String loginName, String password, String role, String lastLogin) {
        this.loginName = loginName;
        this.password = password;
        this.role = role;
        this.lastLogin = lastLogin;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString(){
        return String.format("{loginName:%s,password:%s,role:%s,lastLogin:%s}",loginName,password,role,lastLogin);
    }

}
