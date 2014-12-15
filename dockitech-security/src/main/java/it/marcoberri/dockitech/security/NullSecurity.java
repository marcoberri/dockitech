package it.marcoberri.dockitech.security;

public class NullSecurity extends AbstractSecurity {

   
    @Override
    public byte[] encrypt(byte[] b, String key) {
	return b;
    }

    @Override
    public String encrypt(String b, String key) {
	return b;
    }

    @Override
    public byte[] decrypt(byte[] b, String key) {

	return b;
    }

    @Override
    public String decrypt(String b, String key) {
	return b;
    }
}
