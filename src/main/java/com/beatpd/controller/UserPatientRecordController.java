package com.beatpd.controller;

import com.dao.PatientRecordModel;
import com.dm.PatientRecord;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static java.lang.String.format;

/**
 * Created by liran on 13/05/17.
 */
@Controller
@RequestMapping("/BEAT-PD/User/")
public class UserPatientRecordController {
    private PatientRecordModel patientRecordModel = new PatientRecordModel();

    @RequestMapping(value = "/Update/PatientRecord/ActivitiesAndMedicines", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String updatePatientRecord(@RequestBody PatientRecord patientRecord) {
        try {
            patientRecordModel.addPatientActivitiesAndMedicinesByID(patientRecord);
            return format("{success: update for the following patientRecord: %s}", patientRecord.getPatientID());
        } catch (Exception e) {
            return format("{error:%s}", e.getMessage());
        }
    }
}
