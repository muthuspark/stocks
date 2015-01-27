package muthu.richy.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import muthu.richy.utility.CSVReader;
import muthu.richy.utility.Datawarehouse;

/**
 * Servlet implementation class GetStock
 */
@WebServlet("/GetStock")
public class GetStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStock() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stockid = request.getParameter("stockid");
		boolean fromdb = request.getParameter("fromdb")!=null? true: false;
		response.setContentType("application/json");
		if(!fromdb){
			CSVReader csvReader = new CSVReader(); 
			response.getWriter().write(csvReader.applyIntelligence(stockid));
		}else{
			response.getWriter().write(Datawarehouse.getstockhistory(stockid));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
