package org.alex.image_ai.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author Alex Vazquez <vazqueza2000@gmail.com>
 */
public class Settings {
	private static final String PROPERTIES_FILE = "confidential.properties";

	private static Settings instance;

	Properties prop = new Properties();

	private Settings() {
		try (InputStream input = Settings.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
			if (input == null) {
				System.err.println("Sorry, unable to find " + PROPERTIES_FILE);
				throw new RuntimeException("Sorry, unable to find " + PROPERTIES_FILE);
			}

			prop.load(input);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Settings getInstance() {
		if (instance == null) {
			instance = new Settings();
		}
		return instance;
	}
	
	public static String getProperty(String key) {
		return getInstance().prop.getProperty(key);
	}
		
}
