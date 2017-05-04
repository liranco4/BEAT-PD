package com.dm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by liran on 5/4/17.
 */
@Entity(name = "USER_DETAILS")
public class Patient {
    @Id
    @Column(name = "LOGIN_NAME")
    private String loginName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "STATUS") //TODO ENUM FOR ALL STATUS
    private String status;

    @Column(name = "AGE")
    private String age;

    @Column(name = "M_SERIAL_NUMBER")
    private Long serialNumber;

    public Patient(){}

    public Patient(String loginName, String firstName, String lastName, String status, String age, Long serialNumber, String activityName) {
        this.loginName = loginName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.age = age;
        this.serialNumber = serialNumber;
        this.activityName = activityName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    @Column(name = "ACTIVITY_NAME")//TODO NEED TO KNOW WHAT TO DO WITH FOREIGN KEY
    private String activityName;

}
