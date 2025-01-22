package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {
   
	public void createFolder(String path) {
    	File folder = new File(path);
        if(!folder.exists()) folder.mkdir();
     }
    
    public void renameFolder(String oldpath,String newpath) {
    	
    	File oldFolder = new File(oldpath);
    	File newFolder = new File(newpath);
    	if(oldFolder.exists()) oldFolder.renameTo(newFolder); 
    
    }
    
    public void deleteFolder(String path) {
    	File removableFolder = new File(path);
    			
    	for(File inside : removableFolder.listFiles()) {
    		for(File root : inside.listFiles()) {
    		//inside.deleteOnExit();
        	  root.delete();
    		}
    	   inside.delete();
         }
    	removableFolder.delete();
    }
    
    public void createFile(String path) {
    	
    	try {
    	  File file = new File(path);
			 file.createNewFile();
		} catch (IOException e) {
		   e.printStackTrace();
		}
    }
    
    public boolean fileExists(String path) {
    	File file = new File(path);
    	return file.exists();
    }
    
    public void writeFile(String path,String data) {
    	
    	try {
			FileWriter writer = new FileWriter(path);
			writer.write(data);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    public boolean renameFile(String oldpath,String newpath) {
    	File oldfile = new File(oldpath);
    	File newfile = new File(newpath);
    	
    	if(oldfile.exists()) { 
    	  oldfile.renameTo(newfile);
    	  return true;
    	}
    	else {
    	  return false;
    	}
    }
    
  public void readFile(String path) {
     try {
    	File file = new File(path);
    	Scanner sc = new Scanner(file);
    	  while(sc.hasNextLine()) {
    		  String data = sc.nextLine();
    		  System.out.println(data);
    	  }
    	sc.close();	  
     }catch(IOException e) {System.out.println("Error occured while reading.."); e.printStackTrace();}	
   }
  
  public void deleteFile(String path) {
	  File toBeDeleted = new File(path);
	  if(toBeDeleted.exists()) { toBeDeleted.delete(); System.out.println("File deleted successfully in: "+path);}
	  else {System.out.println("Unable to delete file does not exist in : "+path);}
  }
  
  //create file
  public void create(String path) {
	  
	  try {
		File nf = new File(path);  
		nf.createNewFile();
	}catch (IOException e) {
		e.printStackTrace();
	}
  }
  
  //write in the file
  public void writeIntoFile(String path) {
	  try {
		FileWriter fw = new FileWriter(path);
		fw.write("ஓம் விநாயகனே போற்றி");
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  
  //read a file
  
  public void read(String path) {
	  try(BufferedReader br = new BufferedReader(new FileReader(path)))
	  { while(br.ready()) { System.out.print(br.readLine()); }
	  }catch(IOException e){ e.printStackTrace();}
  }

}
