package it.marcoberri.dockitech.adapter;

import it.marcoberri.dockitech.resources.CollectionNames;
import it.marcoberri.dockitech.resources.Configuration;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.gridfs.GridFS;

public enum MongoDBHelper { // the best way to implement singletons, due to the
			    // author of Effective Java

    INSTANCE;

    private DB db;
    private Datastore datastore;
    private String dbName;
    private GridFS gridFS;

    private Logger log = LogManager.getLogger(MongoAdapter.class);

    private MongoDBHelper() {

	try {
	    Properties properties = Configuration.readPropertiesFile("/store.properties");
	    log.info("Load properties:" + properties);

	    final String s = properties.getProperty("dockitech.db.hosts", "localhost");

	    final List<String> servers_host = Arrays.asList(s.split(","));

	    final List<ServerAddress> serveHostList = new ArrayList<ServerAddress>();

	    for (String sName : servers_host) {
		String name = sName.split(":")[0];
		int port = 27017;
		if (sName.indexOf(":") > -1) {
		    port = Integer.parseInt(sName.split(":")[1]);
		}

		try {
		    serveHostList.add(new ServerAddress(name, port));
		} catch (final UnknownHostException e) {
		    log.fatal("MongoDBHelper.initMongo -->", e);
		}

	    }

	    this.dbName = properties.getProperty("dockitech.db.name", "test");

	    final MongoClient mongoClient = new MongoClient(serveHostList);

	    final Morphia morphia = new Morphia();
	    morphia.mapPackage("it.marcoberri.dockitech.model");
	    this.datastore = morphia.createDatastore(mongoClient, this.dbName);
	    this.gridFS = new GridFS(datastore.getDB(), CollectionNames.COLLECTION_FILES);

	} catch (final IOException e) {
	    log.fatal("MongoDBHelper.initAdapter -->", e);
	    e.printStackTrace();
	}

    }

    public GridFS getGridFS() {
        return gridFS;
    }

    public void setGridFS(GridFS gridFS) {
        this.gridFS = gridFS;
    }

    public DB getDB() {
	return this.db;
    }

    public Datastore getDatastore() {
	return this.datastore;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    
    
}
