package com.dm.updateDM;

import com.interfaces.UpdateDM;

import javax.persistence.*;

/**
 * Created by liran on 5/4/17.
 */
@Entity(name = "MEDICINE_UPDATE")
public class MedicineUpdate implements UpdateDM{

    @Id
    @SequenceGenerator(name = "MED_UPDATE_SEQ", sequenceName = "MED_UPDATE_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "MED_UPDATE_SEQ")
    @Column(name = "MED_UPDATE_ID")
    private Long medicineUpdateID;

    @Column(name = "MED_SERIAL_NUMBER")
    private String medicineSerialNumber;

    @Column(name = "MED_NAME")
    private String medicineName;


    @Override
    public String toString(){
        return String.format("{medicineSerialNumber:\"%s\",medicineName:\"%s\"}", medicineSerialNumber, medicineName);
    }

    public MedicineUpdate() {}

    public void setMedicineSerialNumber(String medicineSerialNumber) {
        this.medicineSerialNumber = medicineSerialNumber;
    }

    public String getMedicineSerialNumber() {
        return medicineSerialNumber;
    }

    @Override
    public String getName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }
}
