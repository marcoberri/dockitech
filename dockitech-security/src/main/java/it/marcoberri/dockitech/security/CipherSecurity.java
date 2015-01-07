package it.marcoberri.dockitech.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CipherSecurity extends AbstractSecurity {

    static String IV = "AAAAAAAAAAAAAAAA";

    @Override
    public byte[] encrypt(byte[] b, String key) {
	try {
	    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
	    SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	    cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(IV.getBytes("UTF-8")));
	    return cipher.doFinal(new String(b).getBytes("UTF-8"));

	} catch (BadPaddingException e) {

	} catch (NoSuchAlgorithmException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (NoSuchProviderException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (NoSuchPaddingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InvalidKeyException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InvalidAlgorithmParameterException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IllegalBlockSizeException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return null;
    }

    @Override
    public String encrypt(String b, String key) {
	try {
	    return new String(encrypt(b.getBytes("UTF-8"), key), "UTF-8");
	} catch (UnsupportedEncodingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return null;
    }

    @Override
    public byte[] decrypt(byte[] b, String key) {

	try {
	    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
	    SecretKeySpec keySepc = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	    cipher.init(Cipher.DECRYPT_MODE, keySepc, new IvParameterSpec(IV.getBytes("UTF-8")));
	    return cipher.doFinal(b);

	} catch (NoSuchAlgorithmException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (NoSuchProviderException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (NoSuchPaddingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InvalidKeyException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InvalidAlgorithmParameterException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IllegalBlockSizeException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (BadPaddingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    @Override
    public String decrypt(String b, String key) {

	try {
	    return new String(decrypt(b.getBytes("UTF-8"), key), "UTF-8");
	} catch (UnsupportedEncodingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }
}
