package muthu.richy.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import muthu.richy.utility.Datawarehouse;

/**
 * Servlet implementation class UpdateCurrentPrice
 */
@WebServlet("/UpdateCurrentPrice")
public class UpdateCurrentPrice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCurrentPrice() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> allSymbols = Datawarehouse.getAllSymbols();
		for(String stockid : allSymbols){
			try{
				Document document = Jsoup.connect("https://in.finance.yahoo.com/q?s="+stockid).get();
				Element element = document.getElementById("yfs_l84_"+stockid.toLowerCase());
				Datawarehouse.updatePrice(stockid, Double.parseDouble(element.text()));
				System.out.println("Updated price of " + stockid);
			}
			catch(Exception e){
				//ignore and continue
				e.printStackTrace();
				System.out.println("Failed to update price of " + stockid);
			}
		}
		response.getWriter().write("Updated Prices");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
