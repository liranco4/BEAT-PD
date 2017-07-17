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
    private String medicineSerialNumber;

    @Column(name = "MED_NAME")
    private String medicineName;

    @Column(name = "MED_LIMITATION")
    private String medicineLimitation;

    @Lob
    @Column(name = "MED_INFO")
    private String info;

    @Override
    public String toString(){
        return String.format("{medicineSerialNumber:\"%s\",medicineName:\"%s\",medicineLimitation:\"%s\",info:\"%s\"}", medicineSerialNumber, medicineName, medicineLimitation, info);
    }

    public Medicine() {}

    public Medicine(String medicineSerialNumber, String medicineName, String medicineLimitation, String info) {
        this.medicineSerialNumber = medicineSerialNumber;
        this.medicineName = medicineName;
        this.medicineLimitation = medicineLimitation;
        this.info = info;
    }

    public String getMedicineSerialNumber() {
        return medicineSerialNumber;
    }

    public void setMedicineSerialNumber(String medicineSerialNumber) {
        this.medicineSerialNumber = medicineSerialNumber;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineLimitation() {
        return medicineLimitation;
    }

    public void setMedicineLimitation(String medicineLimitation) {
        this.medicineLimitation = medicineLimitation;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
