package com.Security;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;

public class RSAUtils {
    public static final String RSA_ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";

    public static final int KEY_SIZE_2048 = 2048;
    public static final int KEY_SIZE_1024 = 1024;

    private RSAUtils() {
    }

    private static final String ALGORITHM = "RSA";

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        return generateKeyPair(KEY_SIZE_2048);
    }

    public static KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException {

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGenerator.initialize(keySize);
            return keyPairGenerator.generateKeyPair();

    }

    public static PublicKey getPublicKey(String base64PublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(base64PublicKey));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;

    }

    public static PublicKey getPublicKey(BigInteger modulus, BigInteger exponent) throws InvalidKeySpecException, NoSuchAlgorithmException {

            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
            return keyFactory.generatePublic(keySpec);

    }

    public static String getBase64PublicKey(PublicKey publicKey) {
        return Base64.encodeBase64String(publicKey.getEncoded());
    }

    public static PrivateKey getPrivateKey(String base64PrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(base64PrivateKey));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;

    }

    public static PrivateKey getPrivateKey(BigInteger modulus, BigInteger exponent) throws NoSuchAlgorithmException, InvalidKeySpecException {

            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus, exponent);
            return keyFactory.generatePrivate(keySpec);

    }

    public static String getBase64PrivateKey(PrivateKey privateKey) {
        return Base64.encodeBase64String(privateKey.getEncoded());
    }

    public static byte[] encryptAsByteArray(String data, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

            Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(data.getBytes());

    }

    public static byte[] encryptAsByteArray(String data, String base64PublicKey) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        return encryptAsByteArray(data, getPublicKey(base64PublicKey));
    }

    public static String encryptAsString(String data, PublicKey publicKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return Base64.encodeBase64String(encryptAsByteArray(data, publicKey));
    }

    public static String encryptAsString(String data, String base64PublicKey) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        return Base64.encodeBase64String(encryptAsByteArray(data, getPublicKey(base64PublicKey)));
    }

    public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

            Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(data));

    }

    public static String decrypt(byte[] data, String base64PrivateKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
        return decrypt(data, getPrivateKey(base64PrivateKey));
    }

    public static String decrypt(String data, PrivateKey privateKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return decrypt(Base64.decodeBase64(data), privateKey);
    }

    public static String decrypt(String data, String base64PrivateKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
        return decrypt(Base64.decodeBase64(data), getPrivateKey(base64PrivateKey));
    }
}