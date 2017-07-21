package com.beatpd.controller;
import com.dao.*;
import com.dm.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.NoResultException;
import java.util.logging.Level;

import static com.utils.SingleLogger.LOGGER;
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
    public ResponseEntity addUser(@RequestBody User user) {

        try {
           return ResponseEntity.ok(userModel.addObjectToDB(user));
        }catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in addUser: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Add/Activity", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addActivity(@RequestBody Activity activity) {

        try {
            return ResponseEntity.ok(userModel.addObjectToDB(activity));
        }catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in addActivity: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Add/Habit", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addHabit(@RequestBody Habit habit) {

        try {
            return ResponseEntity.ok(userModel.addObjectToDB(habit));
        }catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in addHabit: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Add/MoodCondition", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addHabit(@RequestBody MoodCondition moodCondition) {

        try {
            return ResponseEntity.ok(userModel.addObjectToDB(moodCondition));
        }catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in addMoodCondition: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Add/Link", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addHabit(@RequestBody Link link) {

        try {
            return ResponseEntity.ok(userModel.addObjectToDB(link));
        }catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in addLink: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Add/SleepDisorder", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addHabit(@RequestBody SleepDisorder sleepDisorder) {

        try {
            return ResponseEntity.ok(userModel.addObjectToDB(sleepDisorder));
        }catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in addSleepDisorder: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Add/HospitalData", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addHabit(@RequestBody HospitalData hospitalData) {

        try {
            return ResponseEntity.ok(userModel.addObjectToDB(hospitalData));
        }catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in addHospitalData: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Add/Medicine", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addMedicine(@RequestBody Medicine medicine) {

        try {
            return ResponseEntity.ok(userModel.addObjectToDB(medicine));
        }catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in addMedicine: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/GET/User", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getUserByID(String value) {
        try {
            return ResponseEntity.ok(userModel.getUserByUserLoginName(value));
        }catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in getUserByID: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Add/Patient", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addPatient(@RequestBody Patient patient) {

        try {
            return ResponseEntity.ok(userModel.addObjectToDB(patient));
        }catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in addPatient: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/GET/ReportBYPatientID", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getReportBYPatientID(String value) {

        try {
            return ResponseEntity.ok(format("{success:%s}",patientModel.getAllUpdatesByPatientID(value)));
        }catch(NoResultException e){
            LOGGER.log(Level.INFO, format("error in getReportBYPatientID: no result was found\n%s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(format("{error:%s}", e.getMessage()));
        }
        catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in getReportBYPatientID: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            LOGGER.log(Level.INFO, format("error in getReportBYPatientID: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }
}
