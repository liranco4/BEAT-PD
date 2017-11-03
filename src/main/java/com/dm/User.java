package com.dm;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by liran on 5/4/17.
 */

@Entity (name = "USER_DETAILS")
public class User {
    @Id
    @Column(name = "ID")
    private String id;

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
        return String.format("{id:\"%s\",name:\"%s\",pass:\"%s\",role:\"%s\",lastLogin:\"%s\"}",id, name, pass, role, lastLogin);
    }
    public User(){}

    public User(String id, String name, String pass, String role, Date lastLogin) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.role = role;
        this.lastLogin = lastLogin;
    }

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
