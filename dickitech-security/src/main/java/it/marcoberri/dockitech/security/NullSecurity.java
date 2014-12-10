package it.marcoberri.dockitech.security;

public class NullSecurity extends AbstractSecurity {

    @Override
    public byte[] encrypt(byte[] b) {
	return b;
    }

    @Override
    public String encrypt(String b) {
	return b;
    }

    @Override
    public byte[] decrypt(byte[] b) {
	return b;
    }

    @Override
    public String decrypt(String b) {
	return b;
    }
}
