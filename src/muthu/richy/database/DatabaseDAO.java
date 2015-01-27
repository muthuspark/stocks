package muthu.richy.database;

import java.util.List;
import java.util.Map;

public interface DatabaseDAO {

	public void executeInsertQuery(String query);
	public void executeUpdateQuery(String query);
	public void executeDeleteQuery(String query);
	public List<Map<String, Object>> executeQueryGetResult(String query);
	public int executeQueryGetSingleInt(String query);
	public void batchUpdateQuery(String[] queryArray);
	public String executeQueryGetSingleString(String query);
	public void executePreparedStmt(String query,Object[] objects);
}
