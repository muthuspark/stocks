package muthu.richy.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import muthu.richy.database.DatabaseQueryHelper;
import muthu.richy.model.Stock;
import muthu.richy.model.StockValue;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Datawarehouse {

	static DatabaseQueryHelper helper = new DatabaseQueryHelper();

	public static int saveStockData(String symbol, String json, String jsonlast12, String jsonlast6, String jsonlast3,String jsonlast1,String jsonlast0,double price) {
//		try {
//			System.out.println("https://in.finance.yahoo.com/q?s="+symbol.substring(0,symbol.length()-4));
//			Document document = Jsoup.connect("https://in.finance.yahoo.com/q?s="+symbol.substring(0,symbol.length()-4)).get();
//			Element elementById = document.getElementById("yfs_l84_"+symbol.toLowerCase());
//			System.out.println(elementById.toString());
//			currentprice = Double.parseDouble(elementById.text());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// first delete old data
		String dquery = "delete from stock where symbol='" + symbol + "'";
		helper.executeDeleteQuery(dquery);
		String query = "insert into stock (symbol,json,last1year,last6months,last3months,last1month,last1week,cprice) values ('" + symbol
				+ "','" + StringEscapeUtils.escapeSql(json) + "','" + StringEscapeUtils.escapeSql(jsonlast12) + "','" + StringEscapeUtils.escapeSql(jsonlast6) + "','" + StringEscapeUtils.escapeSql(jsonlast3) + "','" + StringEscapeUtils.escapeSql(jsonlast1) + "','" + StringEscapeUtils.escapeSql(jsonlast0) + "',"+price+")";
		helper.executeInsertQuery(query);
		String squery = "select id from stock where symbol='" + symbol + "'";
		return helper.executeQueryGetSingleInt(squery);
	}

	public static void saveStockValues(int stockid, List<StockValue> values) {
		String[] queries = new String[values.size()];
		for (int i = 0; i < values.size(); i++) {
			StockValue s = values.get(i);
			queries[i] = "insert into prices (stockid, open, close, low, high, volume, adjclose, adate) values ("
					+ stockid
					+ ","
					+ s.getOpen()
					+ ","
					+ s.getClose()
					+ ", "
					+ s.getLow()
					+ " , "
					+ s.getHigh()
					+ " , "
					+ s.getVolume()
					+","
					+ s.getAdjclose() + ",'" + s.getDate() + "')";
		}
		helper.batchUpdateQuery(queries);
	}
	public static void star(String symbol){
		String query = "update stock set star=1 where symbol='"+symbol+"'";
		helper.executeUpdateQuery(query);
	}
	public static void updatePrice(String symbol,double price){
		String query = "update stock set cprice="+price+" where symbol='"+symbol+"'";
		helper.executeUpdateQuery(query);
	}
	public static List<String> getAllSymbols(){
		List<String> syms = new ArrayList<>();
		String query = "select symbol from stock_details order by symbol asc";
		List<Map<String,Object>> result = helper.executeQueryGetResult(query);
		for(Map rs : result){
			syms.add(rs.get("symbol").toString());
		}
		return syms;
	}
	
	public static List<String> getAllSymbolsWithLatestPrice(){
		List<String> syms = new ArrayList<>();
		String query = "select symbol from stock_details order by symbol asc";
		List<Map<String,Object>> result = helper.executeQueryGetResult(query);
		for(Map rs : result){
			syms.add(rs.get("symbol").toString());
		}
		return syms;
	}
	
	public static Map<String, Double> getTop5Stocks1Month(){
		Map<String, Double> syms = new LinkedHashMap<>();
		String query = "select symbol,slope1 from stock order by slope1 desc limit 20";
		List<Map<String,Object>> result = helper.executeQueryGetResult(query);
		for(Map rs : result){
			Object object = rs.get("slope1");
			double val = 0.0;
			if(object!=null){
				val = Double.parseDouble(object.toString());
			}
			syms.put(rs.get("symbol").toString(),val);
		}
		return syms;
	}
	
	public static Map<String, Double> getTop5Stocks3Month(){
		Map<String, Double> syms = new LinkedHashMap<>();
		String query = "select symbol,slope3 from stock order by slope3 desc limit 20";
		List<Map<String,Object>> result = helper.executeQueryGetResult(query);
		for(Map rs : result){
			Object object = rs.get("slope3");
			double val = 0.0;
			if(object!=null){
				val = Double.parseDouble(object.toString());
			}
			syms.put(rs.get("symbol").toString(),val);
		}
		return syms;
	}

	public static Map<String, Double> getTop5Stocks6Month(){
		Map<String, Double> syms = new LinkedHashMap<>();
		String query = "select symbol,slope6 from stock order by slope6 desc limit 20";
		List<Map<String,Object>> result = helper.executeQueryGetResult(query);
		for(Map rs : result){
			Object object = rs.get("slope6");
			double val = 0.0;
			if(object!=null){
				val = Double.parseDouble(object.toString());
			}
			syms.put(rs.get("symbol").toString(),val);
		}
		return syms;
	}

	public static Map<String, Double> getTop5Stocks1Week(){
		Map<String, Double> syms = new LinkedHashMap<>();
		String query = "select symbol,slope0 from stock order by slope0 desc limit 20";
		List<Map<String,Object>> result = helper.executeQueryGetResult(query);
		for(Map rs : result){
			Object object = rs.get("slope0");
			double val = 0.0;
			if(object!=null){
				val = Double.parseDouble(object.toString());
			}
			syms.put(rs.get("symbol").toString(),val);
		}
		return syms;
	}
	
	public static List<Stock> getStocksWithMaxLessthanCurrent(){
		List<Stock> sts = new ArrayList<>();
		String query = "select symbol,highestprice,cprice from stock where highestprice <= cprice order by slope3";
		List<Map<String,Object>> result = helper.executeQueryGetResult(query);
		for(Map rs : result){
			Stock st = new Stock();
			Object highestprice = rs.get("highestprice");
			Object cprice = rs.get("cprice");
			double val = 0.0;
			if(highestprice!=null){
				st.setHighestprice(Double.parseDouble(highestprice.toString()));
			}
			if(cprice!=null){
				st.setPrice(Double.parseDouble(cprice.toString()));
			}
			st.setSymbol(rs.get("symbol").toString());
			sts.add(st);
		}
		return sts;
	}

	public static List<Stock> getStocksWithMaxGreaterthanCurrent(){
		List<Stock> sts = new ArrayList<>();
		String query = "select symbol,highestprice,cprice from stock where highestprice > cprice order by slope3 ";
		List<Map<String,Object>> result = helper.executeQueryGetResult(query);
		for(Map rs : result){
			Stock st = new Stock();
			Object highestprice = rs.get("highestprice");
			Object cprice = rs.get("cprice");
			double val = 0.0;
			if(highestprice!=null){
				st.setSlope3(Double.parseDouble(highestprice.toString()));
			}
			if(cprice!=null){
				st.setPrice(Double.parseDouble(cprice.toString()));
			}
			st.setSymbol(rs.get("symbol").toString());
			sts.add(st);
		}
		return sts;
	}

	public static String getstockhistory(String symbol) {
		String query = "select json,last1year,last6months,last3months,last1month,last1week from stock where symbol='" + symbol + ".csv'";
		List<Map<String,Object>> result = helper.executeQueryGetResult(query);
		if(!result.isEmpty()){
			Map rs = result.get(0);
			StringBuilder blr = new StringBuilder();
			blr.append("{");
			blr.append("\"history\":  "+rs.get("json")+"  ,");
			blr.append("\"jsonlast12\":  "+(rs.get("last1year")!=null? rs.get("last1year") : "[0]")+"  ,");
			blr.append("\"jsonlast6\":  "+(rs.get("last6months")!=null? rs.get("last6months") : "[0]")+"  ,");
			blr.append("\"jsonlast3\":  "+(rs.get("last3months")!=null? rs.get("last3months") : "[0]")+", ");
			blr.append("\"jsonlast1\":  "+(rs.get("last1month")!=null? rs.get("last1month") : "[0]")+",  ");
			blr.append("\"jsonlast0\":  "+(rs.get("last1week")!=null? rs.get("last1week") : "[0]")+"  ");
			blr.append("}");
			return blr.toString();
		}
		return "";
	}

	public static void saveSlope(String stockid, double slope6, double slope3, double slope1,double slope0,double max) {
		String query = "update stock set slope6="+slope6+", slope3="+slope3+", slope1="+slope1+",slope0="+slope0+",highestprice="+max+" where symbol='"+stockid+"'";
		//System.out.println(query);
		helper.executeUpdateQuery(query);
	}
	
	public static void executeQueryArrays(String queryArray[]){
		helper.batchUpdateQuery(queryArray);
	}
	
	public static List<Stock> getBearStocks(int period){
		List<Stock> sts = new ArrayList<>();
		String slopevar = "slope3";
		switch(period){
		case Constants.ONE_WEEK:
			slopevar  = "slope0";
			break;
		case Constants.ONE_MONTH:
			slopevar  = "slope1";
			break;
		case Constants.THREE_MONTH:
			slopevar  = "slope3";
			break;
		case Constants.SIX_MONTH:
			slopevar  = "slope6";
			break;
		}
		String query  = "select symbol,"+slopevar+",cprice from stock where "+slopevar+"<>0 order by "+slopevar+" asc limit 30";
		List<Map<String,Object>> result = helper.executeQueryGetResult(query);
		for(Map rs : result){
			Stock st = new Stock();
			Object slope3 = rs.get("slope3");
			Object cprice = rs.get("cprice");
			double val = 0.0;
			if(slope3!=null){
				st.setSlope3(Double.parseDouble(slope3.toString()));
			}
			if(cprice!=null){
				st.setPrice(Double.parseDouble(cprice.toString()));
			}
			st.setSymbol(rs.get("symbol").toString());
			sts.add(st);
		}
		return sts;
	}
	
	public static List<Stock> getBullStocks(int period){
		List<Stock> sts = new ArrayList<>();
		String slopevar = "slope3";
		
		switch(period){
		case Constants.ONE_WEEK:
			slopevar  = "slope0";
			break;
		case Constants.ONE_MONTH:
			slopevar  = "slope1";
			break;
		case Constants.THREE_MONTH:
			slopevar  = "slope3";
			break;
		case Constants.SIX_MONTH:
			slopevar  = "slope6";
			break;
		}
		String query  = "select symbol,"+slopevar+",cprice from stock where "+slopevar+"<>0 order by "+slopevar+" desc limit 30";
		List<Map<String,Object>> result = helper.executeQueryGetResult(query);
		for(Map rs : result){
			Stock st = new Stock();
			Object slope3 = rs.get(slopevar);
			Object cprice = rs.get("cprice");
			double val = 0.0;
			if(slope3!=null){
				st.setSlope3(Double.parseDouble(slope3.toString()));
			}
			if(cprice!=null){
				st.setPrice(Double.parseDouble(cprice.toString()));
			}
			st.setSymbol(rs.get("symbol").toString());
			sts.add(st);
		}
		return sts;
	}
	
	
	
	public static List<Stock> getStocksPricedBetween(int max, int min){
		List<Stock> sts = new ArrayList<>();
		String query  = "select symbol,slope3,cprice from stock where cprice between "+min+" and "+max+" order by slope6 desc";
		List<Map<String,Object>> result = helper.executeQueryGetResult(query);
		for(Map rs : result){
			Stock st = new Stock();
			Object slope3 = rs.get("slope3");
			Object cprice = rs.get("cprice");
			double val = 0.0;
			if(slope3!=null){
				st.setSlope3(Double.parseDouble(slope3.toString()));
			}
			if(cprice!=null){
				st.setPrice(Double.parseDouble(cprice.toString()));
			}
			st.setSymbol(rs.get("symbol").toString());
			sts.add(st);
		}
		return sts;
	}
	
}
