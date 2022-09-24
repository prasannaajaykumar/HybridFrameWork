package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyUtilFile {
public static String getValueforKey(String key) throws Throwable {
	Properties configprop=new Properties();
	configprop.load(new FileInputStream("C:\\PROJECT TESTING\\Hybrid_Framework\\PropertyFiles\\Environment.properties"));
	return configprop.getProperty(key);
}
}
