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
    private String ID;

    @Column(name = "USER_NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String pass;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "LAST_LOGIN")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Type(type = "date")
    private Date lastLogin;

    @Override
    public String toString(){
        return format("{ID:\"%s\",name:\"%s\",pass:\"%s\",role:\"%s\",lastLogin:\"%s\"}",ID, name, pass, role, lastLogin);
    }
    public User(){}

    public String getUserID() {
        return ID;
    }

    public void setUserID(String ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String Name) {
        this.name = Name;
    }

    public String getUserPassword() {
        return pass;
    }

    public void setUserPassword(String Pass) {
        this.pass = Pass;
    }

    public String getUserRole() {
        return role;
    }

    public void setUserRole(String userRole) {
        this.role = userRole;
    }

    public Date getUserLastLogin() {
        return lastLogin;
    }

    public void setUserLastLogin(Date userLastLogin) {
        this.lastLogin = userLastLogin;
    }
}
