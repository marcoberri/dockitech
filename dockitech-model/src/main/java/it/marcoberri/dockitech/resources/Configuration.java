package it.marcoberri.dockitech.resources;


import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;


public class Configuration {

    //private static final Logger logger = new Logger(Configuration.class);

    private static final Properties properties = initProperties();

    private Configuration() {
    }

    private static Properties initProperties() {
	final Properties prop = new Properties();
	try {
	    File[] files = getPropertiesFiles();
	  //  logger.debug("Found " + files.length + " properties files");
	    for (File f : files) {
		Properties p = readPropertiesFile(f);
		prop.putAll(p);
	    }
	//    logger.info("Loaded " + prop.size() + " properties");
	//    logger.debug("Properties loaded: " + prop.toString());
	} catch (final IOException e) {
	 //   logger.fatal("Error loading properties: ", e);
	}
	return prop;
    }

    private static File[] getPropertiesFiles() {
	final File baseDir = FileUtils.toFile(Configuration.class.getResource("/"));
	return baseDir.listFiles(new FilenameFilter() {

	    @Override
	    public boolean accept(File dir, String name) {
		return name.endsWith(".properties");
	    }
	});
    }

    private static Properties readPropertiesFile(File f) throws IOException {
	final Properties p = new Properties();
	p.load(FileUtils.openInputStream(f));
	return p;
    }

    public static Properties readPropertiesFile(String f_name) throws IOException {
	final File baseDir = FileUtils.toFile(Configuration.class.getResource("/"));
	final Properties p = new Properties();
	p.load(FileUtils.openInputStream(new File(baseDir + "/" + f_name)));
	return p;
    }


    public static String getProperty(String name) {
	return properties.getProperty(name);
    }



    public static String getProperty(String name, String defaultValue) {
	return properties.getProperty(name, defaultValue);
    }

    public static Properties getProperties() {
	return properties;
    }
}
