package com.Security;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;

public class GenerateKeys {

    private KeyPairGenerator keyGen;
    private static KeyPair pairInstance = null;
    private PrivateKey privateKey;
    private PublicKey publicKey;


    private GenerateKeys(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keylength);
        pairInstance = this.keyGen.generateKeyPair();
    }

    public static KeyPair getKeyPairInstance() throws NoSuchAlgorithmException, NoSuchProviderException {

        if(pairInstance==null) {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            pairInstance = keyGen.generateKeyPair();
            return pairInstance;
        }
        else
           return pairInstance;
    }

    public static void writeToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
    }

    public static String DecryptString(String encryptedString) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, getKeyPairInstance().getPrivate());
        return new String(cipher.doFinal(Base64.decodeBase64(encryptedString)), "UTF-8");
    }








//    public static void main(String[] args) throws InvalidKeyException,NoSuchPaddingException,IllegalBlockSizeException,BadPaddingException {
//        KeyPair keyPair;
//        try {
//            keyPair = GenerateKeys.getKeyPairInstance();
//            Cipher cipher = Cipher.getInstance("RSA");
//            cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());
//            String msg = "HELLO";
//            String encrypted = Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
//            cipher.init(Cipher.DECRYPT_MODE, getKeyPairInstance().getPublic());
//            String decryptFromserver = new String(cipher.doFinal(Base64.decodeBase64(encrypted)), "UTF-8");
//
//            String ss = Hex.encodeHexString(keyPair.getPublic().getEncoded());
//            String g = "e2kRw0M=";
//            String sss = new String(cipher.doFinal(Base64.decodeBase64(g)), "UTF-8");
//            GenerateKeys.writeToFile("KeyPair/publicKey", keyPair.getPublic().getEncoded());
//            GenerateKeys.writeToFile("KeyPair/privateKey", keyPair.getPrivate().getEncoded());
//        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
//            System.err.println(e.getMessage());
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
//
//    }

}