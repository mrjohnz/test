package com.pcitc.impl.rtcal.common.redis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CalcConfigure {

	public static String RDHost;

    static {          
		try {
	        Properties properties = new Properties();  
	        
	        String filepath = "bizconfig.properties";
	        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	        InputStream inputStream = classloader.getResourceAsStream(filepath);
	        
			properties.load(inputStream);  

			RDHost = properties.getProperty("RDHost").trim();

			inputStream.close();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {   
            e.printStackTrace();   
        }   
    } 
    
}
