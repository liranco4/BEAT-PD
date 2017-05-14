package com.beatpd.controller;

import com.dao.ModelGenerics;
import com.dao.PatientModel;
import com.dm.Activity;
import com.dm.Patient;
import org.hibernate.mapping.Collection;
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
public class PatientController {
    private PatientModel patientModel = new PatientModel();

    @RequestMapping(value = "/Update/Patient/ActivitiesAndMedicines", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String updatePatient(@RequestBody Patient patient) {
        try {
            patientModel.updatePatientActivitiesAndMedicinesByID(patient.getPatientID(), patient.getListOfActivitiy(), patient.getListOfMedicine());
            return format("{success: update for the following patient: %s}",patient.getPatientID());
        } catch (Exception e) {
            return format("{error:%s}", e.getMessage());
        }
    }
}
