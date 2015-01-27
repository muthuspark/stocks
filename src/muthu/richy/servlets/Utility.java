package muthu.richy.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import muthu.richy.model.Stock;
import muthu.richy.utility.Datawarehouse;

/**
 * Servlet implementation class Utility
 */
@WebServlet("/Utility")
public class Utility extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Utility() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if(operation.equals("star")){
			String symbol = request.getParameter("symbol");
			Datawarehouse.star(symbol);
		}
		else if(operation.equals("pricefilter")){
			int max = Integer.parseInt(request.getParameter("max").toString());
			int min = Integer.parseInt(request.getParameter("min").toString());
			List<Stock> stocksPricedBetween = Datawarehouse.getStocksPricedBetween(max, min);
			StringBuilder blr = new StringBuilder();
			blr.append("<li class=\"list-group-item active\" >Stocks priced between "+max+" - "+min+"</li>"); 
			for(Stock s : stocksPricedBetween){
				blr.append("<li class=\"list-group-item\"><a href=\"#\" onclick=\"javascript:getHistoryOfSymbol('"+s.getSymbol()+"');return false;\">"+s.getSymbol()+"</a> <span class=\"badge\">"+s.getPrice()+" </span></li>"); 
			}
			response.setContentType("text/html");
			response.getWriter().write(blr.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
