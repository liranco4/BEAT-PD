package com.beatpd.controller;
import com.dao.*;
import com.dm.*;
import com.utils.CustomDate;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;


import static com.utils.SingleLogger.LOGGER;
import static com.utils.Utils.getObjectListAsJsonList;
import static java.lang.String.format;

/**
 * Created by liran on 5/4/17.
 */

@Controller
@RequestMapping("/BEAT-PD/Admin/")
public class AdminController {
    private PatientModel patientModel = PatientModel.getPatientModelInstance();
    private ModelGenerics modelGenerics = ModelGenerics.getModelGenericsInstance();
    private PatientRecordModel patientRecoedModel = PatientRecordModel.getPatientRecordModelInstance();

    @RequestMapping(value = "/Add/User", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addUser(@RequestBody User user) {

        try {
           return ResponseEntity.ok(modelGenerics.addObjectToDB(user));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in add User: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Update/User", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public ResponseEntity updateUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(modelGenerics.updateObjectToDB(user));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in update User: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Delete/User", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public ResponseEntity deleteUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(modelGenerics.deleteObjectToDB(user));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in delete User: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/GET/AllUsers", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getAllAdmins() {
        try {
            Collection <User> users =  modelGenerics.findAllByClass(User.class);
            users.forEach(user -> user.setPass(""));
            return ResponseEntity.ok(format("{success: The following are all Users,users:%s}",getObjectListAsJsonList(users)));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in delete User: %s", e.getStackTrace().toString()));
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

    @RequestMapping(value = "/Update/Activity", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity updateActivity(@RequestBody Activity activity) {

        try {
            return ResponseEntity.ok(modelGenerics.updateObjectToDB(activity));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in updateActivity: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Delete/Activity", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity deleteActivity(@RequestBody Activity activity) {

        try {
            return ResponseEntity.ok(modelGenerics.deleteObjectToDB(activity));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in deleteActivity: %s", e.getStackTrace().toString()));
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

    @RequestMapping(value = "/Update/Habit", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity updateHabit(@RequestBody Habit habit) {

        try {
            return ResponseEntity.ok(modelGenerics.updateObjectToDB(habit));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in updateHabit: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Delete/Habit", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity deleteHabit(@RequestBody Habit habit) {

        try {
            return ResponseEntity.ok(modelGenerics.deleteObjectToDB(habit));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in deleteHabit: %s", e.getStackTrace().toString()));
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

    @RequestMapping(value = "/Update/MoodCondition", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity updateMoodCondition(@RequestBody MoodCondition moodCondition) {

        try {
            return ResponseEntity.ok(modelGenerics.updateObjectToDB(moodCondition));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in updateMoodCondition: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Delete/MoodCondition", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity deleteMoodCondition(@RequestBody MoodCondition moodCondition) {

        try {
            return ResponseEntity.ok(modelGenerics.deleteObjectToDB(moodCondition));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in deleteMoodCondition: %s", e.getStackTrace().toString()));
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

    @RequestMapping(value = "/Update/Link", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity updateLink(@RequestBody Link link) {

        try {
            return ResponseEntity.ok(modelGenerics.updateObjectToDB(link));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in updateLink: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Delete/Link", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity deleteMoodCondition(@RequestBody Link link) {

        try {
            return ResponseEntity.ok(modelGenerics.deleteObjectToDB(link));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in deleteLink: %s", e.getStackTrace().toString()));
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

    @RequestMapping(value = "/Update/SleepDisorder", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity updateSleepDisorder(@RequestBody SleepDisorder sleepDisorder) {

        try {
            return ResponseEntity.ok(modelGenerics.updateObjectToDB(sleepDisorder));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in updateSleepDisorder: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Delete/SleepDisorder", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity deleteSleepDisorder(@RequestBody SleepDisorder sleepDisorder) {

        try {
            return ResponseEntity.ok(modelGenerics.deleteObjectToDB(sleepDisorder));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in deleteSleepDisorder: %s", e.getStackTrace().toString()));
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

    @RequestMapping(value = "/Update/Medicine", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity updateMedicine(@RequestBody Medicine medicine) {

        try {
            return ResponseEntity.ok(modelGenerics.updateObjectToDB(medicine));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in updateMedicine: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Delete/Medicine", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity deleteMedicine(@RequestBody Medicine medicine) {

        try {
            return ResponseEntity.ok(modelGenerics.deleteObjectToDB(medicine));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in deleteMedicine: %s", e.getStackTrace().toString()));
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

    @RequestMapping(value = "/Update/SleepQuality", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity updateSleepQuality(@RequestBody SleepQuality sleepQuality) {

        try {
            return ResponseEntity.ok(modelGenerics.updateObjectToDB(sleepQuality));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in updateSleepQuality: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Delete/SleepQuality", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity deleteSleepQuality(@RequestBody SleepQuality sleepQuality) {

        try {
            return ResponseEntity.ok(modelGenerics.deleteObjectToDB(sleepQuality));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in deleteSleepQuality: %s", e.getStackTrace().toString()));
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

    @RequestMapping(value = "/Update/Patient", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity updatePatient(@RequestBody Patient patient) {

        try {
            return ResponseEntity.ok(modelGenerics.updateObjectToDB(patient));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in updatePatient: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Delete/Patient", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity deletePatient(@RequestBody Patient patient) {

        try {
            return ResponseEntity.ok(modelGenerics.deleteObjectToDB(patient));
        }catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in deletePatient: %s", e.getStackTrace().toString()));
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


    @RequestMapping("/GET/PatientReportByID/{ID}")
    public void downloadPatientReportByID(HttpServletRequest request, HttpServletResponse response )  throws IOException{
        String patientID = request.getServletPath().split("/")[5];
        LOGGER.log(Level.INFO,format("request report for patientID: %s",patientID));
        String fileName = format("patients_report_%s.xlsx",CustomDate.getDateFormat().format(new Date()));
        String downloadFolder = format("%s/src/main/resources/reports/",System.getProperty("user.dir"));
        try {
            patientRecoedModel.createNewExcelReport(format("%s%s",downloadFolder,fileName), Optional.of(patientID));
        }catch(IllegalArgumentException e){
            response.setContentType("application/json");
            response.sendError(404, e.getMessage());
        }
        LOGGER.log(Level.INFO,format("Downloading file :- %s",fileName));
        Path file = Paths.get(downloadFolder, format("%s",fileName));
        // Check if file exists
        if (Files.exists(file)) {
            // set content type
            response.setContentType("application/xlsx");
            // add response header
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            try {
                //copies all bytes from a file to an output stream
                Files.copy(file, response.getOutputStream());
                //flushes output stream
                response.getOutputStream().flush();
            } catch (IOException e) {
                LOGGER.log(Level.INFO,"Error :- " + e.getMessage());
                response.sendError(500, "Error :- " + e.getMessage());
            }
            finally {
                Files.delete(file);
                LOGGER.log(Level.INFO,format("File deleted successfully: %s",file));
            }
        } else {
            response.sendError(500, "Error: File not found!!!!");
            LOGGER.log(Level.INFO,"Error: File not found!!!!");
        }
    }

    @RequestMapping(value = "/GET/AllPatients", method = RequestMethod.GET, produces = "application/json;charset=UTF-8", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getAllSleepDisorders() {
        try {
            return ResponseEntity.ok(format("{success: The following are all patients, patients:%s}", getObjectListAsJsonList(modelGenerics.findAllByClass(Patient.class))));
        } catch(HibernateException e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in getAllPatients: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        catch (Exception e) {     e.printStackTrace();
            LOGGER.log(Level.INFO, format("error in getAllPatients: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping("/GET/PatientsReport")
    public void downloadPatientsReport(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String fileName = format("patients_report_%s.xlsx",CustomDate.getDateFormat().format(new Date()));
        String downloadFolder = format("%s/src/main/resources/reports/",System.getProperty("user.dir"));
        patientRecoedModel.createNewExcelReport(format("%s%s",downloadFolder,fileName), Optional.empty());
        LOGGER.log(Level.INFO,format("Downloading file :- %s",fileName));
        Path file = Paths.get(downloadFolder, fileName);
        // Check if file exists
        if (Files.exists(file)) {
            // set content type
            response.setContentType("application/xlsx");
            // add response header
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            try {
                //copies all bytes from a file to an output stream
                Files.copy(file, response.getOutputStream());
                //flushes output stream
                response.getOutputStream().flush();
            } catch (IOException e) {
                LOGGER.log(Level.INFO,"Error :- " + e.getMessage());
                response.sendError(500, "Error :- " + e.getMessage());
            }
            finally {
                Files.delete(file);
                LOGGER.log(Level.INFO,format("File deleted successfully: %s",file));
            }
        } else {
            response.sendError(500, "Sorry File not found!!!!");
            LOGGER.log(Level.INFO,"Sorry File not found!!!!");
        }
    }
}
