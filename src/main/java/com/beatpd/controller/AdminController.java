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
    private PatientModel patientModel = PatientModel.getPatientModelInstance();
    private ModelGenerics modelGenerics = ModelGenerics.getModelGenericsInstance();
    @RequestMapping(value = "/Add/User", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addUser(@RequestBody User user) {

        try {
           return ResponseEntity.ok(modelGenerics.addObjectToDB(user));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in addUser: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Add/Activity", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addActivity(@RequestBody Activity activity) {

        try {
            return ResponseEntity.ok(modelGenerics.addObjectToDB(activity));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in addActivity: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Add/Habit", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addHabit(@RequestBody Habit habit) {

        try {
            return ResponseEntity.ok(modelGenerics.addObjectToDB(habit));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in addHabit: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Add/MoodCondition", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addMoodCondition(@RequestBody MoodCondition moodCondition) {

        try {
            return ResponseEntity.ok(modelGenerics.addObjectToDB(moodCondition));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in addMoodCondition: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Add/Link", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addLink(@RequestBody Link link) {

        try {
            return ResponseEntity.ok(modelGenerics.addObjectToDB(link));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in addLink: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Add/SleepDisorder", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addSleepDisorder(@RequestBody SleepDisorder sleepDisorder) {

        try {
            return ResponseEntity.ok(modelGenerics.addObjectToDB(sleepDisorder));
        }catch(HibernateException e) {
            e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in addSleepDisorder: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Add/Medicine", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addMedicine(@RequestBody Medicine medicine) {

        try {
            return ResponseEntity.ok(modelGenerics.addObjectToDB(medicine));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in addMedicine: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Add/SleepQuality", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addSleepQuality(@RequestBody SleepQuality sleepQuality) {

        try {
            return ResponseEntity.ok(modelGenerics.addObjectToDB(sleepQuality));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in addSleepQuality: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/GET/User", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getUserByID(String ID) {
        try {
            return ResponseEntity.ok(modelGenerics.retrieveObjectFromDBbyID(User.class,ID));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in getUserByID: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Add/Patient", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addPatient(@RequestBody Patient patient) {

        try {
            return ResponseEntity.ok(modelGenerics.addObjectToDB(patient));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in addPatient: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Add/KeepAlive", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addKeepAlive() {

        try {
            return ResponseEntity.ok(modelGenerics.addObjectToDB(KeepAlive.getKeepAliveInstance()));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in keepAlive: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }


    @RequestMapping(value = "/GET/ReportBYPatientID", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getReportBYPatientID(String value) {

        try {
            return ResponseEntity.ok(format("{success:%s}",patientModel.getAllUpdatesByPatientID(value)));
        }catch(NoResultException e){ e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in getReportBYPatientID: no result was found\n%s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(format("{error:%s}", e.getMessage()));
        }
        catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in getReportBYPatientID: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in getReportBYPatientID: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Login", method = RequestMethod.GET )
    public String Login() {
        return "redirect:/pages/LoginPage.html";
    }

}
