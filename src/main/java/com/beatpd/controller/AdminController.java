package com.beatpd.controller;
import com.dao.*;
import com.dm.Activity;
import com.dm.Patient;
import com.dm.User;
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
    private ModelGenerics modelGenerics = new ModelGenerics();
    private UserModel userModel = new UserModel();
    private PatientModel patientModel = new PatientModel();
    private ActivityModel activityModel = new ActivityModel();

    private PatientRecordModel patientRecordModel = new PatientRecordModel();

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

    @RequestMapping(value = "/GET/User", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public String getUserByID(String value) {
        return userModel.getUserByUserLoginName(value);
    }

    @RequestMapping(value = "/Add/Patient", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String addPatient(@RequestBody Patient patient) {

        try {
            return patientModel.addObjectToDB(patient);
        } catch (Exception e) {
            return format("{error:%s}", e.getMessage());
        }
    }

    @RequestMapping(value = "/Add/Activity", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public String addActivity(@RequestBody Activity activity) {

        try {
            return activityModel.addObjectToDB(activity);
        } catch (Exception e) {
            return format("{error:%s}", e.getMessage());
        }
    }


}
