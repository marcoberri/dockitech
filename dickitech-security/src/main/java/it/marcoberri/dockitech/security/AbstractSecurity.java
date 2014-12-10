package it.marcoberri.dockitech.security;

public abstract class AbstractSecurity {

    public abstract byte[] encrypt(byte[] b);
    
    public abstract String encrypt(String b);

    public abstract byte[] decrypt(byte[] b);
    
    public abstract String decrypt(String b);
    
}
