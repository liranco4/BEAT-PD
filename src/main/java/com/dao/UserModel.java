package com.dao;

import com.Security.AsymmetricCryptography;
import com.Security.GenerateKeys;
import com.dm.User;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.ResponseEntity;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import static java.lang.String.format;

/**
 * Created by liran on 5/4/17.
 */

public class UserModel extends ModelGenerics {

    private UserModel() {
    }

    private static UserModel userModelInstance;
    private ModelGenerics modelGenerics = getModelGenericsInstance();

    public static UserModel getUserModelInstance() {
        if (userModelInstance == null)
            userModelInstance = new UserModel();
        return userModelInstance;
    }

    public Boolean checkCredentials(User user) {
        User userFromDB = (User) modelGenerics.retrieveObjectFromDBbyID(User.class, user.getUserID());
        return (userFromDB.getUserPassword().equals(user.getUserPassword()) && user.getUserRole().equals(userFromDB.getUserRole()));
    }

    public static void main(String[] argv) throws Exception {
        // Generate a 1024-bit Digital Signature Algorithm (DSA) key pair
//        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
//        keyGen.initialize(1024);
//        KeyPair keypair = keyGen.genKeyPair();
//        PrivateKey privateKey = keypair.getPrivate();
//        //System.out.println(privateKey);
//        PublicKey publicKey = keypair.getPublic();
//        System.out.println(publicKey.getEncoded());
//
//        Cipher cipher = Cipher.getInstance("RSA");
//
//        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
//
//        String msg ="HELLO";
//
//        String encryptetedMSG = Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
//
//        cipher.init(Cipher.DECRYPT_MODE, publicKey);
//
//        System.out.print(new String(cipher.doFinal(Base64.decodeBase64(encryptetedMSG)), "UTF-8"));

        AsymmetricCryptography ac = new AsymmetricCryptography();
        String msg = ac.encryptText("HELLO", GenerateKeys.getKeyPairInstance().getPrivate());
int x =1;
    }
}