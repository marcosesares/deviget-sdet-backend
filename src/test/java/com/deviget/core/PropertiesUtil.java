package com.deviget.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	private static final PropertiesUtil instance = new PropertiesUtil();
	
	private PropertiesUtil() {}

	public Properties getProperties(String filename) {
		try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {
			if (input == null) {
				throw new RuntimeException("Sorry, unable to find " + filename);
			}
			Properties prop = new Properties();
			prop.load(input);
			return prop;
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage());
		}
	}

	public static PropertiesUtil getInstance() {
		return instance;
	}
}
