package muthu.richy.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import muthu.richy.utility.CSVReader;
import muthu.richy.utility.Datawarehouse;

/**
 * Servlet implementation class UpdateStocks
 */
@WebServlet("/UpdateStocks")
public class UpdateStocks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStocks(){
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> allSymbols = Datawarehouse.getAllSymbols();
		for(String symbol : allSymbols){
			CSVReader csvReader = new CSVReader(); 
			try
			{
				csvReader.applyIntelligence(symbol);
			}catch(Exception e){
				//ignore and continue
				System.out.println("Failed to load-->" + symbol);
			}
			System.out.println("done-->" + symbol);
		}
		response.getWriter().write("done");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
