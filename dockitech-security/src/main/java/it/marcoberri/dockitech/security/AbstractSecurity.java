package it.marcoberri.dockitech.security;

public abstract class AbstractSecurity {

    public abstract byte[] encrypt(byte[] b, String key);

    public abstract String encrypt(String b, String key);

    public abstract byte[] decrypt(byte[] b, String key);

    public abstract String decrypt(String b, String key);

}
