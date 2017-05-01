package com.dm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by liran on 25/04/17.
 */
@Entity (name = "COW_DETAILS")
public class Cow{

    private int cowID;
    private String cowName;

    public Cow(){}

    @Id
    @Column (name = "COW_ID")
    public int getCowID() {
        return cowID;
    }
    public void setCowID(int cowID) {
        this.cowID = cowID;
    }

    @Column (name = "COW_NAME")
    public String getCowName() {
        return cowName + "FROM GETTER";
    }
    public void setCowName(String cowName) {
        this.cowName = cowName;
    }
    @Override
    public String toString(){
        return String.format("{cowID:%d,cowName:%s}",cowID,cowName);
    }
}
