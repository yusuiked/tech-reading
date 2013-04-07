package org.yukung.practice.chap7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.yukung.practice.chap7.ReadPropertiesTest.Pref;

public class ReadProperties {

	Properties prop;

	public ReadProperties() {
	}

	public ReadProperties(String path) {
		prop = new Properties();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			prop.load(br);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Pref getPref(String name) {
		String capital = prop.getProperty(name + ".capital");
		String food = prop.getProperty(name + ".food");
		Pref pref = new Pref();
		pref.setCapital(capital);
		pref.setFood(food);
		return pref;
	}
}
