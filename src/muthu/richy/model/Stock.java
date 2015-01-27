package muthu.richy.model;

public class Stock {
	private String symbol;
	private double price;
	private double highestprice;
	private double slope3;
	
	
	
	public double getHighestprice() {
		return highestprice;
	}
	public void setHighestprice(double highestprice) {
		this.highestprice = highestprice;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol.substring(0,symbol.length()-4);
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSlope3() {
		return slope3;
	}
	public void setSlope3(double slope3) {
		this.slope3 = slope3;
	}
}
