package it.marcoberri.dockitech.security;

import org.apache.commons.codec.binary.Base64;

public class Base64Security extends AbstractSecurity {

    @Override
    public byte[] encrypt(byte[] b) {
	return Base64.encodeBase64(b);
    }

    @Override
    public String encrypt(String b) {
	return new String(encrypt(b.getBytes()));
    }

    @Override
    public byte[] decrypt(byte[] b) {
	return Base64.decodeBase64(b);
    }

    @Override
    public String decrypt(String b) {
	return new String(decrypt(b.getBytes()));
    }
}
