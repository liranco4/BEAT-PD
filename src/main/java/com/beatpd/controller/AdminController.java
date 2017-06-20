package com.beatpd.controller;
import com.dao.*;
import com.dm.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static java.lang.String.format;

/**
 * Created by liran on 5/4/17.
 */

@Controller
@RequestMapping("/BEAT-PD/Admin/")
public class AdminController {
    private ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    private ObjectMapper objectMapper = new ObjectMapper();
    private UserModel userModel = UserModel.getUserModelInstance();
    private PatientModel patientModel = PatientModel.getPatientModelInstance();
    private ActivityModel activityModel = ActivityModel.getActivityModelInstance();
    private ModelGenerics modelGenerics = ModelGenerics.getModelGenericsInstance();

    @RequestMapping(value = "/Add/User", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String addUser(@RequestBody User user) {

        try {
            //User user = objectMapper.readValue(user, User.class);
            System.out.println(user);
            return userModel.addObjectToDB(user);
        } catch (Exception e) {
            return format("{error:%s}", e.getMessage());
        }
    }

    @RequestMapping(value = "/Add/Activity", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String addActivity(@RequestBody Activity activity) {

        try {
            return modelGenerics.addObjectToDB(activity);
        } catch (Exception e) {
            return format("{error:%s}", e.getMessage());
        }
    }

    @RequestMapping(value = "/Add/Habit", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String addHabit(@RequestBody Habit habit) {

        try {
            return modelGenerics.addObjectToDB(habit);
        } catch (Exception e) {
            return format("{error:%s}", e.getMessage());
        }
    }

    @RequestMapping(value = "/Add/MoodCondition", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String addHabit(@RequestBody MoodCondition moodCondition) {

        try {
            return modelGenerics.addObjectToDB(moodCondition);
        } catch (Exception e) {
            return format("{error:%s}", e.getMessage());
        }
    }

    @RequestMapping(value = "/Add/Link", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String addHabit(@RequestBody Link link) {

        try {
            return modelGenerics.addObjectToDB(link);
        } catch (Exception e) {
            return format("{error:%s}", e.getMessage());
        }
    }

    @RequestMapping(value = "/Add/SleepDisorder", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String addHabit(@RequestBody SleepDisorder sleepDisorder) {

        try {
            return modelGenerics.addObjectToDB(sleepDisorder);
        } catch (Exception e) {
            return format("{error:%s}", e.getMessage());
        }
    }

    @RequestMapping(value = "/Add/HospitalData", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String addHabit(@RequestBody HospitalData hospitalData) {

        try {
            return modelGenerics.addObjectToDB(hospitalData);
        } catch (Exception e) {
            return format("{error:%s}", e.getMessage());
        }
    }

    @RequestMapping(value = "/Add/Medicine", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String addMedicine(@RequestBody Medicine medicine) {

        try {
            return modelGenerics.addObjectToDB(medicine);
        } catch (Exception e) {
            return format("{error:%s}", e.getMessage());
        }
    }

    @RequestMapping(value = "/GET/User", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String getUserByID(String value) {
        return userModel.getUserByUserLoginName(value);
    }

    @RequestMapping(value = "/Add/Patient", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String addPatient(@RequestBody Patient patient) {

        try {
            return modelGenerics.addObjectToDB(patient);
        } catch (Exception e) {
            return format("{error:%s}", e.getMessage());
        }
    }

    @RequestMapping(value = "/GET/ReportBYPatientID", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String getReportBYPatientID(String value) {

        try {
            return format("{success:%s}",patientModel.getAllUpdatesByPatientID(value));
        } catch (Exception e) {
            return format("{error:%s}", e.getMessage());
        }
    }
}
