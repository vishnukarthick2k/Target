package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfigProps {
   
   private Properties property;	
   private String path;
  
   public ReadConfigProps(String path) {	
     this.path = path;	  
     property = new Properties();
  }
	public void loadfile() {
		try (FileInputStream fis= new FileInputStream(path)){
			
			property.load(fis);
			fis.close();
		}catch(Exception e) {
			System.out.println("Error occured while reading the config file...");
		}
	}
	public String url() {
		return property.getProperty("Url");
	}
	public String email() {
		return property.getProperty("email");
	}
	public String password() {
		return property.getProperty("password");
	}
	public String executeOn() {
		return property.getProperty("execution_env");
	}
	public String hubUrl() {
		return property.getProperty("grid_hub");
	}

}
