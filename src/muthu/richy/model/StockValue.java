package muthu.richy.model;

public class StockValue {

	private int stockid;

	
	public int getStockid() {
		return stockid;
	}

	public void setStockid(int stockid) {
		this.stockid = stockid;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getAdjclose() {
		return adjclose;
	}

	public void setAdjclose(double adjclose) {
		this.adjclose = adjclose;
	}

	private double open;
	private double close;
	private double low;
	private double high;
	private double volume;
	private double adjclose;
	private String date;
	
	public StockValue(double open, double high,  double low, double close,double volume, double adjclose, String date) {
		this.open = open;
		this.close = close;
		this.low = low;
		this.high = high;
		this.volume = volume;
		this.adjclose = adjclose;
		this.date = date;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
