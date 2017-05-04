package com.dm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Created by liran on 5/4/17.
 */
@Entity(name = "MEDICINE")
public class Medicine {

    @Id
    @Column(name = "MED_SERIAL_NUMBER")
    private String serialNumber;

    @Column(name = "MED_NAME")
    private String medicineName;

    @Column(name = "MED_LIMITATION")
    private String limitation;

    @Lob
    @Column(name = "MED_INFO")
    private String info;

    public Medicine(String serialNumber, String medicineName, String limitation, String info) {
        this.serialNumber = serialNumber;
        this.medicineName = medicineName;
        this.limitation = limitation;
        this.info = info;
    }

    public Medicine(){}

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getLimitation() {
        return limitation;
    }

    public void setLimitation(String limitation) {
        this.limitation = limitation;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
