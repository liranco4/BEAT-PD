package com.beatpd.controller;

import com.Security.AsymmetricCryptography;
import com.Security.GenerateKeys;
import com.dao.UserModel;
import com.dm.User;
import org.apache.commons.codec.binary.Hex;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.logging.Level;

import static com.utils.SingleLogger.LOGGER;
import static java.lang.String.format;
import static java.lang.String.valueOf;

/**
 * Created by liran on 15/08/17.
 */
@Controller
@RequestMapping("/BEAT-PD/")
public class LoginController {

    private UserModel userModel = UserModel.getUserModelInstance();

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


    @RequestMapping(value = "/publicKey", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity getPublicKey() {
        try {
            return ResponseEntity.ok(format("{success: %s}",  Hex.encodeHexString(GenerateKeys.getKeyPairInstance().getPublic().getEncoded())));
        } catch (NoSuchAlgorithmException| NoSuchProviderException e) {
            LOGGER.log(Level.INFO, format("error getting publicKey: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        } catch (Exception e) {
            LOGGER.log(Level.INFO, format("error getting publicKey: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }

    @RequestMapping(value = "/decrypt/{encryptedMsg}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity sendDecryptMessage(@PathVariable("encryptedMsg") String encryptedMsg) {
        try {

            String result = GenerateKeys.DecryptString(encryptedMsg);
            return ResponseEntity.ok(format("{success: %s}",  result));
        } catch (NoSuchAlgorithmException| NoSuchProviderException e) {
            LOGGER.log(Level.INFO, format("error getting publicKey: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(format("{error:%s}", e.getMessage()));
        } catch (Exception e) {
            LOGGER.log(Level.INFO, format("error getting publicKey: %s", e.getStackTrace().toString()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(format("{error:%s}", e.getMessage()));
        }
    }
    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String Login() {
        return "redirect:/pages/LoginPage.html";
    }
}