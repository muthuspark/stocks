package muthu.richy.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {

	public static void main(String[] args) throws MalformedURLException, IOException {
//		//FileUtils.copyURLToFile(new URL("http://real-chart.finance.yahoo.com/table.csv?s=SOBHA.NS&a=07&b=12&c=2002&d=06&e=18&f=2014&g=w&ignore=.csv"),new File( "D:\\temp\\asdfa.csv"));
//		List<String> arraylist = new ArrayList<>();
//		try {
//		     
//		    FileInputStream file = new FileInputStream(new File("D:\\India Stock Details.xlsx"));
//		     
//		  //Get the workbook instance for XLS file 
//		    XSSFWorkbook workbook = new XSSFWorkbook (file);
//		     
//		    //Get first sheet from the workbook
//		    XSSFSheet sheet = workbook.getSheetAt(0);
//		     
//		    //Iterate through each rows from first sheet
//		    Iterator<Row> rowIterator = sheet.iterator();
//		    String query = "delete from stock_details";
//	    	arraylist.add(query);
//		    while(rowIterator.hasNext()) {
//		        Row row = rowIterator.next();
//		        String name = row.getCell(1)!=null?  StringEscapeUtils.escapeSql(row.getCell(1).toString()) : "";
//		        String category = row.getCell(3)!=null?  StringEscapeUtils.escapeSql(row.getCell(3).toString()) : "";
//		        query = "insert into stock_details (symbol, name, exchange, category) values ('"+ row.getCell(0)+"','"+name+"','"+ row.getCell(2)+"','"+category+"')";
//		        arraylist.add(query);
//		    }
//		    file.close();
//		    
//		    String[] qarray = arraylist.toArray(new String[arraylist.size()]);
//		    Datawarehouse.executeQueryArrays(qarray);
//		   
//		} catch (FileNotFoundException e) {
//		    e.printStackTrace();
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}
		test();
	}
	
	public static void test(){
		int tortoise = 0;
		int rabbit = 2;
		for(int i=0;i<40;i++){
			if(tortoise == 0){
				System.out.println("<start>");
			}
			System.out.println("<span6>");
			tortoise++;
			System.out.println("</span6>");
			if(tortoise == rabbit){
				tortoise=0;
				System.out.println("<end>");
			}
		}
	}
}
