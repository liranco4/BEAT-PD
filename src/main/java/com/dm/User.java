package com.dm;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

import static java.lang.String.format;

/**
 * Created by liran on 5/4/17.
 */

@Entity (name = "USER_DETAILS")
public class User {
    @Id
    @Column(name = "ID")
    private String userID;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String userPassword;

    @Column(name = "ROLE")
    private String userRole;

    @Column(name = "LAST_LOGIN")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Type(type = "date")
    private Date userLastLogin;

    @Override
    public String toString(){
        return format("{userID:\"%s\",userName:\"%s\",userPassword:\"%s\",userRole:\"%s\",userLastLogin:\"%s\"}",userID, userName, userPassword, userRole, userLastLogin);
    }
    public User(){}

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Date getUserLastLogin() {
        return userLastLogin;
    }

    public void setUserLastLogin(Date userLastLogin) {
        this.userLastLogin = userLastLogin;
    }
}
