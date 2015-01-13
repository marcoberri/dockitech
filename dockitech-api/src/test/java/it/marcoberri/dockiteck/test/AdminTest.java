package it.marcoberri.dockiteck.test;

import it.marcoberri.dockitech.apiadmin.DockitectApiAdmin;

import org.junit.Assert;
import org.junit.Test;



public class AdminTest extends BaseTest {

    final DockitectApiAdmin client = new DockitectApiAdmin(TESTURL);
    
    @Test
    public void createAndDeleteWord(){
	boolean ok = client.createWorld("WORLD");
	Assert.assertTrue("problemi a ceare il clinet", ok);
	boolean del = client.dropUniverse();
    	Assert.assertTrue("problemi a droppare l'universo", del);
    }
    
    
    
    
    
}
