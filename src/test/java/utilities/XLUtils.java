  package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import testcases.Base_Test;
import static testcases.Base_Test.*;

public class XLUtils {
   
	   private  FileInputStream fis;
	   private  FileOutputStream fos;
	   private  XSSFWorkbook wrb;
	   private  XSSFSheet sheet;
	   private  XSSFRow row;
	   private  XSSFCell cell;
	   private  CellStyle style;
	   private  String filepath;
		
	   public XLUtils(String filepath){
		   this.filepath = filepath;
	   }
	   public void createSheet(String xlsheet) throws IOException {
		   logger.info("in sheet creation");
		   fis = new FileInputStream(filepath);
		   wrb = new XSSFWorkbook(fis);
		   if(wrb.getSheet(xlsheet)== null) {sheet = wrb.createSheet(xlsheet);}else { sheet = wrb.getSheet(xlsheet);}
		   //fis.close();
		   //wrb.close();
		   logger.info("out of sheet creation");
	   }
	   public synchronized int getRowCount(String xlsheet) throws IOException {
		   
		   fis = new FileInputStream(filepath);
		   wrb = new XSSFWorkbook(fis);
		   sheet = wrb.getSheet(xlsheet);
		   int rowcount = sheet.getLastRowNum();
		   fis.close();
		   wrb.close();
		   return rowcount;     
	   }
	   
	   public synchronized int getCellCount(String xlsheet,int rowindex) throws IOException {
		   fis = new FileInputStream(filepath);
		   wrb = new XSSFWorkbook(fis);
		   sheet = wrb.getSheet(xlsheet);
		   row = sheet.getRow(rowindex);
		   
		   fis.close();
		   wrb.close();
		   return row.getLastCellNum();
		   
	   }
	   public synchronized String getCellData (String xlsheet,int rowindex,int colindex) throws IOException {
		   fis = new FileInputStream(filepath);
		   wrb = new XSSFWorkbook(fis);
		   sheet = wrb.getSheet(xlsheet);
		   row = sheet.getRow(rowindex);
		   cell = row.getCell(colindex);
		   
		   String data;
		   try {
			   DataFormatter formatter = new DataFormatter();
			   data = formatter.formatCellValue(cell); 
		   }catch(Exception e) {
			   data = "";
		   }
		   finally {
			   wrb.close();
			   fis.close();
		   }  
		   return data; 
	   }
	   
	   public void setCellData(String xlsheet,int rowindex,int colindex,String data) throws IOException {
		   
		   fis = new FileInputStream(filepath);
		   wrb = new XSSFWorkbook(fis);
		   sheet = wrb.getSheet(xlsheet);
		   row = sheet.getRow(rowindex);
		   cell = row.createCell(colindex);
		   cell.setCellValue(data);
		   
		   fos = new FileOutputStream(filepath);
		   wrb.write(fos);
		   wrb.close();
		   fos.close();
		   
	   }
	   
	   public  void fillGreenColor(String xlsheet,int rowindex,int colindex) throws IOException {
		   
		   fis = new FileInputStream(filepath);
		   wrb = new XSSFWorkbook(fis);
		   sheet = wrb.getSheet(xlsheet);
		   row = sheet.getRow(rowindex);
		   cell = row.createCell(colindex);
	       
		   style = wrb.createCellStyle();
		   style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		   style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
		   cell.setCellStyle(style);
		   fos = new FileOutputStream(filepath);
		   fis.close();
		   wrb.write(fos);
		   fos.close(); 
		   wrb.close();
	   }
	   
	  public  void fillRedColor(String xlsheet,int rowindex,int colindex) throws IOException {
		   
		   fis = new FileInputStream(filepath);
		   wrb = new XSSFWorkbook(fis);
		   sheet = wrb.getSheet(xlsheet);
		   row = sheet.getRow(rowindex);
		   cell = row.createCell(colindex);
	       
		   style = wrb.createCellStyle();
		   style.setFillForegroundColor(IndexedColors.RED.getIndex());
		   style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
		   cell.setCellStyle(style);
		   fos = new FileOutputStream(filepath);
		   fis.close();
		   wrb.write(fos);
		   fos.close();
		   wrb.close();
		   
	   }

	  public void createCellData(String[][]data,String xlsheet) throws IOException {
		  logger.info("create xlsheet begins");//
		  createSheet(xlsheet);
		  logger.info("create xlsheet over");//
		   for(int i=0;i<data.length;i++) {
		       row = sheet.createRow(i+1);
		       logger.info("row got->"+i);//
		     for(int j=0;j<data[i].length;j++) {
		    	 logger.info("second loop");
		    	 if(data[i][j]== null) continue;
		    	 try {
		    	     cell = row.createCell(j);
		    	 }catch(Exception e) {logger.info(" nee keta error idho:"+ e.getMessage());}
		    	 logger.info("cell got");
		    	 cell.setCellValue(data[i][j].toString());
		    	 logger.info(data[i][j].toString());//
				 logger.info("set data in "+"row:"+i+"colm:"+j);//
		      }
		   }
		   logger.info("all datas were created");//
			 fos = new FileOutputStream(filepath);
		     wrb.write(fos); 
		     fis.close();
		     fos.close();
		     wrb.close();
		   logger.info("excel file successful"); //
	   }
}
