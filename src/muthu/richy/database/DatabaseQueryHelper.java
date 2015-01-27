package muthu.richy.database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DatabaseQueryHelper {

	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	DatabaseDAO databaseDAO = (DatabaseDAO) context.getBean("databaseDAO");

	public void executeInsertQuery(String query) {
		try {
			long starttime = Calendar.getInstance().getTimeInMillis();
			databaseDAO.executeInsertQuery(query);
			long endtime = Calendar.getInstance().getTimeInMillis();
			log(query, starttime, endtime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void executeUpdateQuery(String query) {
		try {
			long starttime = Calendar.getInstance().getTimeInMillis();
			databaseDAO.executeUpdateQuery(query);
			long endtime = Calendar.getInstance().getTimeInMillis();
			log(query, starttime, endtime);

		} catch (Exception e) {
		}
	}

	public void executeDeleteQuery(String query) {
		try {
			long starttime = Calendar.getInstance().getTimeInMillis();
			databaseDAO.executeDeleteQuery(query);
			long endtime = Calendar.getInstance().getTimeInMillis();
			log(query, starttime, endtime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Map<String, Object>> executeQueryGetResult(String query) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		try {
			long starttime = Calendar.getInstance().getTimeInMillis();
			result = databaseDAO.executeQueryGetResult(query);
			long endtime = Calendar.getInstance().getTimeInMillis();
			// System.out.println("in : "+(endtime - starttime));
			log(query, starttime, endtime);
		} catch (Exception e) {
		}
		return result;
	}

	public int executeQueryGetSingleInt(String query) {
		int res = 0;
		try {
			long starttime = Calendar.getInstance().getTimeInMillis();
			res = databaseDAO.executeQueryGetSingleInt(query);
			long endtime = Calendar.getInstance().getTimeInMillis();
			log(query, starttime, endtime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public void batchUpdateQuery(String[] queryArray) {
		try {
			long starttime = Calendar.getInstance().getTimeInMillis();
			databaseDAO.batchUpdateQuery(queryArray);
			long endtime = Calendar.getInstance().getTimeInMillis();
			log("batch update query -- " + queryArray[0], starttime, endtime);
		} catch (Exception e) {
		}
	}

	public String execeuteQueryGetSingleString(String query) {
		String res = "";
		try {
			long starttime = Calendar.getInstance().getTimeInMillis();
			res = databaseDAO.executeQueryGetSingleString(query);
			long endtime = Calendar.getInstance().getTimeInMillis();
			log(query, starttime, endtime);
		} catch (Exception e) {
		}
		return res;
	}

	public void executePreparedStatemet(String query, Object[] object) {
		try {
			long starttime = Calendar.getInstance().getTimeInMillis();
			databaseDAO.executePreparedStmt(query, object);
			long endtime = Calendar.getInstance().getTimeInMillis();
			log(query, starttime, endtime);
		} catch (Exception e) {
		}
	}

	/**
	 * I need information about the queries that take longer then 3 secs.!! This
	 * is where i need to optimize.
	 * 
	 * @param qry
	 * @param starttime
	 * @param endtime
	 */
	private void log(String qry, long starttime, long endtime) {
		// System.out.println(">>>>>>>>>>>>>>>Just now executed query : " + qry
		// + " : time taken : " + ((endtime - starttime)) + " ms");
		// System.out.println(qry + " : time taken : " + ((endtime - starttime))
		// + " ms");
		long timetaken = endtime - starttime;
		if (timetaken >= 2000) {
		}
	}
}
