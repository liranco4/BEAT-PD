package com.beatpd.controller;

import com.Security.AuthEnc;
import com.Security.RSAUtils;
import com.dao.PatientModel;
import com.dao.UserModel;
import com.dm.Patient;
import com.dm.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;

import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import static com.dao.PatientModel.getPatientModelInstance;
import static com.dao.UserModel.getUserModelInstance;
import static com.utils.SingleLogger.LOGGER;
import static java.lang.String.format;

/**
 * Created by liran on 15/08/17.
 */
@Controller
@RequestMapping("/BEAT-PD/")
public class LoginController {

    private UserModel userModel = getUserModelInstance();
    private PatientModel patientModel = getPatientModelInstance();

    @RequestMapping(value = "/Login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity loginUser(@RequestBody User user) {
        try {
            if (userModel.checkCredentials(user))
                return ResponseEntity.ok("{success: user logged-in}");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{error:not found}");
        } catch (HibernateException e) {
            LOGGER.log(Level.INFO, format("error in login: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        } catch (Exception e) {
            LOGGER.log(Level.INFO, format("error in login: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String Login() {
        return "redirect:/pages/HTML/LoginPage.html";
    }

    @RequestMapping(value = "/encryption-parameters", method = RequestMethod.GET)
    public ResponseEntity<?> getEncryptionPublicKey(HttpServletRequest request) {
        KeyPair keyPair;
        try {
            keyPair = RSAUtils.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
        PrivateKey privateKey = keyPair.getPrivate();
        request.getSession().setAttribute("_private_key", privateKey);

        Map<String, Object> publicKeyMap = new HashMap<>();
        publicKeyMap.put("publicKey", Base64.encodeBase64String(keyPair.getPublic().getEncoded()));
        return ResponseEntity.ok(publicKeyMap);
    }

    @RequestMapping(value = "/encryption-data", method = RequestMethod.POST)
    public ResponseEntity<?> decrypt(HttpServletRequest request, @RequestBody AuthEnc encryptedData)  {
        try{
            PrivateKey privateKey = (PrivateKey) request.getSession().getAttribute("_private_key");
            String decryptedMsg = RSAUtils.decrypt(encryptedData.i, privateKey);
            LOGGER.log(Level.INFO,format("Decrypt data = {%s}",decryptedMsg));
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(decryptedMsg, User.class);
            try {
                if (getUserModelInstance().checkCredentials(user)){
                    String key = encryptedData.p.replaceAll("(-----BEGIN PUBLIC KEY-----)|(-----END PUBLIC KEY-----)|(\n)","");
                    return ResponseEntity.ok(format("{\"success\":\"%s\"}",RSAUtils.encryptAsString("OK",key)));
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error: Wrong Credentials!!!}"));
            }catch(HibernateException | NoResultException | InvalidKeySpecException e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error: Wrong Credentials!!!}"));
            }
            } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/Patient/Login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> patientLogin(HttpServletRequest request, @RequestBody AuthEnc encryptedData) {
        try{
            PrivateKey privateKey = (PrivateKey) request.getSession().getAttribute("_private_key");
            String decryptedMsg = RSAUtils.decrypt(encryptedData.i, privateKey);
            LOGGER.log(Level.INFO,format("Decrypt data = {%s}",decryptedMsg));
            ObjectMapper mapper = new ObjectMapper();
            Patient patient = mapper.readValue(decryptedMsg, Patient.class);
            try {
                if (patientModel.checkCredentials(patient)) {
                    return ResponseEntity.ok(format("{\"success\":\"%s\"}",RSAUtils.encryptAsString("OK",encryptedData.p)));
                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{error:not found}");
            } catch (HibernateException e) {
                LOGGER.log(Level.INFO, format("error in login: %s", e.getStackTrace().toString()));
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
            } catch (Exception e) {
                LOGGER.log(Level.INFO, format("error in login: %s", e.getStackTrace().toString()));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
            }
        }catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        }
    }
}