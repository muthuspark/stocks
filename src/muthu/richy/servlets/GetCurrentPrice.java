package muthu.richy.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import muthu.richy.utility.Datawarehouse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Servlet implementation class GetCurrentPrice
 */
@WebServlet("/GetCurrentPrice")
public class GetCurrentPrice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCurrentPrice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stockid = request.getParameter("symbol");
//		Document document = Jsoup.connect("http://finance.yahoo.com/q?s="+stockid).get();
//		Elements elementsByClass = document.getElementsByClass("yfi_rt_quote_summary");
//		Element element = elementsByClass.get(0);
//		element.getElementsByClass("app_promo").remove();
//		Datawarehouse.updatePrice(stockid, Double.parseDouble(element.toString()));
		response.getWriter().write("");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
