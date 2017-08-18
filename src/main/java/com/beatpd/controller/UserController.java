package com.beatpd.controller;

import com.dao.ActivityModel;
import com.dao.ModelGenerics;
import com.dao.PatientRecordModel;
import com.dm.*;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;

import static com.utils.SingleLogger.LOGGER;
import static com.utils.Utils.getObjectListAsJsonList;
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

    @RequestMapping(value = "/Update/PatientRecord", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity updatePatientRecord(@RequestBody PatientRecord patientRecord) {
        try {
            patientRecordModel.addPatientRecord(patientRecord);
            return ResponseEntity.ok(format("{success: update for the following patientRecord: %s}", patientRecord.getPatientID()));
        } catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in updatePatientRecord: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            LOGGER.log(Level.INFO, format("error in updatePatientRecord: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/GET/AllActivities", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getAllActivities() {
        try {
            return ResponseEntity.ok(format("{success: The following are all Activities,activities:%s}", getObjectListAsJsonList(modelGenerics.findAllByClass(Activity.class))));
        } catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in getAllActivities: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            LOGGER.log(Level.INFO, format("error in getAllActivities: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/GET/AllMedicines", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getAllMedicines() {
        try {
            return ResponseEntity.ok(format("{success: The following are all Medicines,medicines:%s}", getObjectListAsJsonList(modelGenerics.findAllByClass(Medicine.class))));
        } catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in getAllMedicines: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            LOGGER.log(Level.INFO, format("error in getAllMedicines: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/GET/AllHabits", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getAllHabits() {
        try {
            return ResponseEntity.ok(format("{success: The following are all Habits,habits:%s}", getObjectListAsJsonList(modelGenerics.findAllByClass(Habit.class))));
        } catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in getAllHabits: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            LOGGER.log(Level.INFO, format("error in getAllHabits: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/GET/AllLinks", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getAllLinks() {
        try {
            return ResponseEntity.ok(format("{success: The following are all Links,links:%s}", getObjectListAsJsonList(modelGenerics.findAllByClass(Link.class))));
        } catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in getAllLinks: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            LOGGER.log(Level.INFO, format("error in getAllLinks: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/GET/AllMoodConditions", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getAllMoodConditions() {
        try {
            return ResponseEntity.ok(format("{success: The following are all MoodConditions,moodConditions:%s}", getObjectListAsJsonList(modelGenerics.findAllByClass(MoodCondition.class))));
        } catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in getAllMoodConditions: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            LOGGER.log(Level.INFO, format("error in getAllMoodConditions: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/GET/AllSleepDisorders", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getAllSleepDisorders() {
        try {
            return ResponseEntity.ok(format("{success: The following are all SleepDisorders,sleepDisorders:%s}", getObjectListAsJsonList(modelGenerics.findAllByClass(SleepDisorder.class))));
        } catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in getAllSleepDisorders: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            LOGGER.log(Level.INFO, format("error in getPatientDetails: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/GET/AllSleepQuality", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getAllSleepQuality() {
        try {
            return ResponseEntity.ok(format("{success: The following are all SleepQuality,sleepQuality:%s}", getObjectListAsJsonList(modelGenerics.findAllByClass(SleepQuality.class))));
        } catch(HibernateException e) {
            LOGGER.log(Level.INFO, format("error in getAllSleepQuality: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            LOGGER.log(Level.INFO, format("error in getAllSleepQuality: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/GET/PatientDetails/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getPatientDetails( @PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(format("{success:%s}", modelGenerics.retrieveObjectFromDBbyID(Patient.class,id)));
        } catch(NoResultException e){
            LOGGER.log(Level.INFO, format("error in getPatientDetails: no result was found\n%s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(format("{error:%s}", e.getMessage()));
        }
        catch(HibernateException e) {
        LOGGER.log(Level.INFO, format("error in getPatientDetails: %s", e.getStackTrace().toString()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {
            LOGGER.log(Level.INFO, format("error in getPatientDetails: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }


}