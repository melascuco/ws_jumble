package eu.atosresearch.health.pruebas.propertiesfuera;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;


public class PropertiesLeidoDelPathDelJar 
{
	
	final static String PROPERTIESFILE = "prueba.properties";

	private static Logger logger = PropertiesLeidoDelPathDelJar.logger;
	
	
	public PropertiesLeidoDelPathDelJar() {
		PropertiesConfiguration propiedades;
		try {
			propiedades = getPropertiesFile(PROPERTIESFILE);
			System.out.println("Propiedad1: " + propiedades.getProperty("propiedad1"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	public static PropertiesConfiguration getPropertiesFile(String propertiesFileName) throws FileNotFoundException {
		PropertiesConfiguration prop = null;
	    try {
	        CodeSource codeSource = PropertiesLeidoDelPathDelJar.class.getProtectionDomain().getCodeSource();
	        File jarFile = new File(codeSource.getLocation().toURI().getPath());
	        File jarDir = jarFile.getParentFile();
	        
	        //System.out.println("Path: " + jarDir.getAbsolutePath());
	 
	        if (jarDir != null && jarDir.isDirectory()) {
	            File propFile = new File(jarDir, propertiesFileName);
	            prop = new PropertiesConfiguration();
	            prop.load(new BufferedReader(new FileReader(propFile.getAbsoluteFile())));
	        }
	    } catch (URISyntaxException ex) {
	        logger.error("File URI sintax: " + propertiesFileName, ex);
	    } catch (FileNotFoundException ex) {
	        logger.error("File not found: " + propertiesFileName, ex);
	        throw ex;
	    } catch (IOException ex) {
	        logger.error("File exception: " + propertiesFileName, ex);
	    } catch (ConfigurationException cex) {
	        logger.error("File configuration error: " + propertiesFileName, cex);
		}
	    return prop;
	}
	
	
    public static void main( String[] args )
    {
        System.out.println( "PROPERTIES FUERA" );
        
        new PropertiesLeidoDelPathDelJar();
        
        System.out.println("FIN");
    }
}
