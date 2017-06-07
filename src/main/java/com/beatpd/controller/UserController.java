package com.beatpd.controller;

import com.dao.ActivityModel;
import com.dao.ModelGenerics;
import com.dao.PatientRecordModel;
import com.dm.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;

import static java.lang.String.format;

/**
 * Created by liran on 13/05/17.
 */
@Controller
@RequestMapping("/BEAT-PD/User/")
public class UserController {
    private PatientRecordModel patientRecordModel = PatientRecordModel.getPatientRecordModelInstance();
    private ActivityModel activityModel = ActivityModel.getActivityModelInstance();
    private ModelGenerics modelGenerics = ModelGenerics.getModelGenericsInstance();

    @RequestMapping(value = "/Update/PatientRecord/ActivitiesAndMedicines", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String updatePatientRecord(@RequestBody PatientRecord patientRecord) {
        try {
            patientRecordModel.addPatientRecord(patientRecord);
            return format("{success: update for the following patientRecord: %s}", patientRecord.getPatientID());
        } catch (Exception e) {
            return format("{error:%s}", e.getMessage());
        }
    }

    @RequestMapping(value = "/GET/AllPatientOptions", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String getAllPatientOptions() {
        try {
            return format("{success: The following are all options,activities:%s,medicines:%s,habits:%s,links:%s,moodConditions:%s,sleepDisorders:%s}",
                    modelGenerics.getObjectListAsJsonList(modelGenerics.findAllByClass(Activity.class)),
                    modelGenerics.getObjectListAsJsonList(modelGenerics.findAllByClass(Medicine.class)),
                    modelGenerics.getObjectListAsJsonList(modelGenerics.findAllByClass(Habit.class)),
                    modelGenerics.getObjectListAsJsonList(modelGenerics.findAllByClass(Link.class)),
                    modelGenerics.getObjectListAsJsonList(modelGenerics.findAllByClass(MoodCondition.class)),
                    modelGenerics.getObjectListAsJsonList(modelGenerics.findAllByClass(SleepDisorder.class)));
        } catch (Exception e) {
            return format("{error:%s}", e.getMessage());
        }
    }
}
