package it.marcoberri.dockitech.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public class DTBase {

    @Id
    private ObjectId id;

    public byte[] encrypt(byte[] plain, DTClient client) {

	final String criptClass = client.getEncryptClass().getEncryptClass();
	final String key = client.getEncryptKey();

	try {
	    final Class<?> clazz = Class.forName(criptClass);
	    final Method method = clazz.newInstance().getClass().getMethod("encrypt", byte[].class, String.class);
	    return (byte[]) method.invoke(clazz.newInstance(), plain, (String) key);
	} catch (final ClassNotFoundException e) {
	    e.printStackTrace();

	} catch (final InstantiationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final IllegalAccessException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final NoSuchMethodException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final SecurityException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final IllegalArgumentException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final InvocationTargetException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return plain;
    }

    public String encrypt(String plain, DTClient client) {

	final String criptClass = client.getEncryptClass().getEncryptClass();
	final String key = client.getEncryptKey();

	try {
	    final Class<?> clazz = Class.forName(criptClass);
	    final Method method = clazz.newInstance().getClass().getMethod("encrypt", String.class, String.class);
	    return (String) method.invoke(clazz.newInstance(), plain, (String) key);
	} catch (final ClassNotFoundException e) {
	    e.printStackTrace();
	} catch (final InstantiationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final IllegalAccessException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final NoSuchMethodException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final SecurityException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final IllegalArgumentException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final InvocationTargetException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return plain;

    }

    public byte[] decrypt(byte[] cript, DTClient client) {

	final String criptClass = client.getEncryptClass().getEncryptClass();
	final String key = client.getEncryptKey();

	try {
	    final Class<?> clazz = Class.forName(criptClass);
	    final Method method = clazz.newInstance().getClass().getMethod("decrypt", byte[].class, String.class);
	    return (byte[]) method.invoke(clazz.newInstance(), cript, (String) key);
	} catch (final ClassNotFoundException e) {
	    e.printStackTrace();

	} catch (final InstantiationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final IllegalAccessException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final NoSuchMethodException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final SecurityException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final IllegalArgumentException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final InvocationTargetException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return cript;

    }

    public String decrypt(String cript, DTClient client) {

	final String criptClass = client.getEncryptClass().getEncryptClass();
	final String key = client.getEncryptKey();

	try {
	    final Class<?> clazz = Class.forName(criptClass);
	    final Method method = clazz.newInstance().getClass().getMethod("decrypt", String.class, String.class);
	    return cript = (String) method.invoke(clazz.newInstance(), cript, (String) key);
	} catch (final ClassNotFoundException e) {

	    e.printStackTrace();

	} catch (final InstantiationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final IllegalAccessException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final NoSuchMethodException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final SecurityException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final IllegalArgumentException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (final InvocationTargetException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return cript;

    }

    public ObjectId getId() {
	return id;
    }

    public void setId(ObjectId id) {
	this.id = id;
    }

}
