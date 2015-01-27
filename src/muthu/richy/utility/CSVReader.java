package muthu.richy.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import muthu.richy.model.StockValue;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;

public class CSVReader {
	private List<Double> closing = new ArrayList<>();
	private List<Double> opening = new ArrayList<>();
	private List<Double> gap = new ArrayList<>();
	List<StockValue> values = new ArrayList<>();
	private List<String> timeaxis = new ArrayList<>();
	private String stockid;
	private double price;
	public String applyIntelligence(File file) throws FileNotFoundException,
			IOException {
		return parseFile(file);
	}

	private void addtowarehouse(File file, String json,String jsonlast12, String jsonlast6, String jsonlast3,String jsonlast1, String jsonlast0, double price) {
		try{
		Datawarehouse.saveStockData(file.getName(), json, jsonlast12, jsonlast6, jsonlast3,jsonlast1,jsonlast0,price);
		Collections.reverse(values);
		Double max = Collections.max(closing);
		//Datawarehouse.saveStockValues(stockid, values);
		Datawarehouse.saveSlope(stockid, slope6, slope3, slope1,slope0,max);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void extractRawData(File file) throws NumberFormatException, IOException{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		this.stockid = file.getName();
		String line = null;
		int i = 0;
		while ((line = reader.readLine()) != null) {
			if (i == 0) {
				i++;// skip first column
				continue;
			}
			String[] split = line.split(","); // csv files
			closing.add(Double.parseDouble(split[6]));
			opening.add(Double.parseDouble(split[1]));
			timeaxis.add("\"" + split[0] + "\"");
			gap.add(Double
					.parseDouble(split[2]) - Double.parseDouble(split[3]));
			values.add(new StockValue(Double.parseDouble(split[1]), Double
					.parseDouble(split[2]), Double.parseDouble(split[3]),
					Double.parseDouble(split[4]), Double.parseDouble(split[5]),
					Double.parseDouble(split[6]), split[0]));

		}
		reader.close();
		price = closing.get(0);
	}

	/**
	 * 
	 * return { history : {} , last1year : {}, last6months : {} , last3months : {}}
	 * @param stockid
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public String applyIntelligence(String stockid)
			throws MalformedURLException, IOException {
		this.stockid = stockid;
		File mainfile = new File("D:\\temp\\" + stockid + ".csv");
		DateTime dt = new DateTime();
//		System.out.println(dt.getDayOfMonth() + "-" + dt.getMonthOfYear() + "-" + dt.getYear());
		FileUtils
				.copyURLToFile(
						new URL(
								"http://real-chart.finance.yahoo.com/table.csv?s="
										+ stockid
										+ "&a=01&b=01&c=2002&d="+dt.getDayOfMonth()+"&e="+dt.getMonthOfYear()+"&f="+dt.getYear()+"&g=w&ignore=.csv"),
										mainfile);
		return parseFile(mainfile);
	}

	private String parseFile(File mainfile) throws IOException {
		//extract raw data
		extractRawData(mainfile);
		//extract all data
		String jsonCompleteHistory =  createJsonHistoryData(closing, opening);
		String jsonlast12 = "[0]";
		String jsonlast6 ="[0]";
		String jsonlast3 ="[0]";
		String jsonlast1 ="[0]";
		String jsonlast0 ="[0]";
		//cut short 12 months
		if(closing.size() > 365){
			jsonlast12 = createJsonHistoryData(closing.subList(0, 365), opening.subList(0, 365));
		}
		if(closing.size() > 183){
			jsonlast6 = createJsonHistoryData(closing.subList(0, 183), opening.subList(0, 183));
		}
		
		if(closing.size() > 92){
			jsonlast3 = createJsonHistoryData(closing.subList(0, 92), opening.subList(0, 92));
		}
		if(closing.size() > 30){
			jsonlast1 = createJsonHistoryData(closing.subList(0, 30), opening.subList(0, 30));
		}
		if(closing.size() > 7){
			jsonlast0 = createJsonHistoryData(closing.subList(0, 7), opening.subList(0, 7));
		}
		addtowarehouse(mainfile, jsonCompleteHistory, jsonlast12, jsonlast6, jsonlast3,jsonlast1,jsonlast0,price);
		return getJsonResponse(jsonCompleteHistory, jsonlast12, jsonlast6, jsonlast3,
				jsonlast1,jsonlast0);
	}

	private String getJsonResponse(String json, String jsonlast12,
			String jsonlast6, String jsonlast3, String jsonlast1,String jsonlast0) {
		StringBuilder blr = new StringBuilder();
		blr.append("{");
		blr.append("\"history\":  "+json+"  ,");
		blr.append("\"jsonlast12\":  "+jsonlast12+"  ,");
		blr.append("\"jsonlast6\":  "+jsonlast6+"  ,");
		blr.append("\"jsonlast3\":  "+jsonlast3+",  ");
		blr.append("\"jsonlast1\":  "+jsonlast1+", ");
		blr.append("\"jsonlast0\":  "+jsonlast0+"  ");
		blr.append("}");
		return blr.toString();
	}

	private List<Double> cloneList(List<Double> list) {
	    List<Double> clone = new ArrayList<Double>(list.size());
	    for(Double item: list){
	    	clone.add(item);
	    }
	    return clone;
	}
	
	private String createJsonHistoryData(List<Double> closinglist, List<Double> openinglist) {
		StringBuilder blr = new StringBuilder();
		StringBuilder cls = new StringBuilder("\"closing\" : [ ");
		StringBuilder opn = new StringBuilder("\"opening\" : [ ");
		for (int j = (closinglist.size() - 1); j >= 0; j--) {
			cls.append(closinglist.get(j));
			opn.append(openinglist.get(j));
			if (j != 0) {
				cls.append(",");
				opn.append(",");
			}
		}
		cls.append(" ]");
		opn.append(" ]");
		
		List<Double> c = cloneList(closinglist); 
		List<Double> o =  cloneList(openinglist); 
		Collections.sort(c);
		Collections.sort(o);
		double ho = o.get(o.size()-1);
		double lo = o.get(0);
		double hc = c.get(c.size()-1);
		double lc = c.get(0);
		//lets build our json now
		blr.append("{");
		blr.append(cls.toString());
		blr.append(",");
		blr.append(opn.toString());
		blr.append(", \"from\" : " + timeaxis.get(0) + "");
		blr.append(", \"till\" : " + timeaxis.get(timeaxis.size() - 1) + "");
		blr.append(", \"openinglowest\" : " + lo + "");
		blr.append(", \"openinghighest\" : " + ho + "");
		blr.append(", \"closinglowest\" : " + lc + "");
		blr.append(", \"closinghighest\" : " +hc + "");
		if(closinglist.size() <=183){
			blr.append(", \"slopeeq\" : " +getSlopeJson(closinglist) + "");
		}
		blr.append("}");
		return blr.toString();
	}
	double slope6 = 0.0;
	double slope3 = 0.0;
	double slope1 = 0.0;
	double slope0 = 0.0;
	private String getSlopeJson(List<Double> values){
		List<Double> newvalues = cloneList(values);
		Collections.reverse(newvalues);
		int N = newvalues.size();
		int sumX = (N / 2) * (2 * 1 + (N - 1) * 1);
		double sumY = 0.0;
		double sumXY = 0.0;
		double sumX2 = 0.0;
		for (int i = 0; i < N; i++) {
			sumY += newvalues.get(i);
			sumXY += (newvalues.get(i) * (i + 1));
			sumX2 += ((i + 1) * (i + 1));
		}
		// Slope(b) = (NΣXY - (ΣX)(ΣY)) / (NΣX2 - (ΣX)2)
		double slope = ((N * sumXY - sumX * sumY) / (N * sumX2 - sumX * sumX));
		// Intercept(a) = (ΣY - b(ΣX)) / N
		double intercept = (sumY - slope * sumX) / N;
		StringBuffer blr = new StringBuffer();
		blr.append("{ \"slope\" : " + slope + " ,  ");
		if(N == 183){
		//save slope into db
			slope6 = slope;
		}else if(N == 92){
		//save slope into db
			slope3 = slope;
		}else if(N == 30){
		//save slope into db
			slope1 = slope;
		}
		else if(N == 7){
			//save slope into db
				slope0 = slope;
			}
		blr.append("\"intercept\" : " + intercept + " }");
		return blr.toString();
	}
	
	public static void main(String[] args) throws IOException {
		File file = new File("D:\\temp");
		File[] list = file.listFiles();
		for(File f : list){
			new CSVReader().parseFile(f);
		}
	}
}
