package it.marcoberri.dockitech.security;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64Security extends AbstractSecurity {

    @Override
    public byte[] encrypt(byte[] b, String key) {
	return Base64.encodeBase64(b);
    }

    @Override
    public String encrypt(String b, String key) {
	try {
	    return  new String(encrypt(b.getBytes(), key),"UTF-8");
	} catch (final UnsupportedEncodingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

    @Override
    public byte[] decrypt(byte[] b, String key) {
	return Base64.decodeBase64(b);
    }

    @Override
    public String decrypt(String b, String key) {
	try {
	    return new String(decrypt(b.getBytes(), key),"UTF-8");
	} catch (final UnsupportedEncodingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }
}
