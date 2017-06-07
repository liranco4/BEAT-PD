package com.dm;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by liran on 5/4/17.
 */

@Entity (name = "USER_DETAILS")
public class User {
    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "LOGIN_NAME")
    private String userLoginName;

    @Column(name = "PASSWORD")
    private String userPassword;

    @Column(name = "ROLE")
    private String userRole;

    @Column(name = "LAST_LOGIN")
    private String userLastLogin; //TODO need to change to DateTime

    @Override
    public String toString(){
        return String.format("{userLoginName:\"%s\",userPassword:\"%s\",userRole:\"%s\",userLastLogin:\"%s\"}", userLoginName, userPassword, userRole, userLastLogin);
    }
    public User(){}
    public User(String userLoginName, String userPassword, String userRole, String userLastLogin) {
        this.userLoginName = userLoginName;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userLastLogin = userLastLogin;
    }

    public String getUserLoginName() {
        return userLoginName;
    }


    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserLastLogin() {
        return userLastLogin;
    }

    public void setUserLastLogin(String userLastLogin) {
        this.userLastLogin = userLastLogin;
    }
}
