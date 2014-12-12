package it.marcoberri.dockitech.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

public class DTBase {

   public DTBase(DTEncryptionMethod encryptClass) {
	super();
	this.encryptClass = encryptClass;
    }

    @Id
    private ObjectId id;

    @Reference
    private DTEncryptionMethod encryptClass;

    
    public byte[] encrypt(byte[] plain, String criptClass) {
	
	try {
	    final Class<?> clazz = Class.forName(criptClass);
	    final Method method = clazz.newInstance().getClass().getMethod("encrypt", byte[].class);
	    plain = (byte[]) method.invoke(clazz.newInstance(), plain);
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
    
    public String encrypt(String plain, String criptClass) {

	try {
	    final Class<?> clazz = Class.forName(criptClass);
	    final Method method = clazz.newInstance().getClass().getMethod("encrypt", String.class);
	    plain = (String) method.invoke(clazz.newInstance(), plain);
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

    
    public byte[] decrypt(byte[] cript, String criptClass) {

	try {
	    final Class<?> clazz = Class.forName(criptClass);
	    final Method method = clazz.newInstance().getClass().getMethod("decrypt", String.class);
	    cript = (byte[]) method.invoke(clazz.newInstance(), cript);
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

    
    public String decrypt(String cript, String criptClass) {

	try {
	    final Class<?> clazz = Class.forName(criptClass);
	    final Method method = clazz.newInstance().getClass().getMethod("decrypt", String.class);
	    cript = (String) method.invoke(clazz.newInstance(), cript);
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

    public DTEncryptionMethod getEncryptClass() {
	return encryptClass;
    }

    public void setEncryptClass(DTEncryptionMethod encryptClass) {
	this.encryptClass = encryptClass;
    }

}
