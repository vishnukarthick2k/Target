package utilities;

import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.Row;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	 public synchronized String[][] extract(String xlfile,String xlsheet) throws IOException {
		XLUtils excel1 = new XLUtils(xlfile);
		int [] rc = getRowandCol(xlfile,xlsheet);
    	String [][]logindata = new String[rc[0]][rc[1]];
		
		for(int i=1;i<=rc[0];i++) {
			for(int j=0;j<rc[1];j++) {
				logindata[i-1][j] = excel1.getCellData(xlsheet,i,j);			
			}
		}
	   return logindata;
	}
	 
    public int[] getRowandCol(String xlfile,String xlsheet) throws IOException {
    	XLUtils excel1 = new XLUtils(xlfile);
    	int row = excel1.getRowCount(xlsheet);
    	int colm = excel1.getCellCount(xlsheet,0);
    	return new int[] {row,colm};
    }
    
    //DataProvider 1 
	@DataProvider(name="LoginData")
	public String[][] provideData() throws IOException{
		String xlfile =".\\DD_output\\validlogs.xlsx";String xlsheet = "mylogins";
		return extract(xlfile,xlsheet); //return two dimension array
	}
    
    //DataProvider 2
	@DataProvider(name="LoginCreds")
	public String[][] getData() throws IOException{
		String xlfile=".\\testdata\\signup_creds3.xlsx"; String xlsheet="SHEET1";
    	return extract(xlfile,xlsheet); //return two dimension array
	}
    
    //DataProvider 3
    
    //DataProvider 4
}
