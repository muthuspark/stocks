package muthu.richy.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Muthukrishnan
 */
public class DatabaseDAOImpl implements DatabaseDAO
{

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void executeDeleteQuery(String query)
	{
		try
		{
			jdbcTemplate.update(query);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void executeInsertQuery(String query)
	{
		try
		{
			jdbcTemplate.update(query);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Map<String, Object>> executeQueryGetResult(String query)
	{
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		try
		{
			rows = jdbcTemplate.queryForList(query);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public void executeUpdateQuery(String query)
	{
		try
		{
			jdbcTemplate.update(query);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public int executeQueryGetSingleInt(String query)
	{
		int result = 0;
		try
		{
			result = jdbcTemplate.queryForInt(query);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void batchUpdateQuery(String[] queryArray)
	{
		try
		{
			jdbcTemplate.batchUpdate(queryArray);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public String executeQueryGetSingleString(String query)
	{
		Object result = "";
		try
		{
			result = jdbcTemplate.queryForObject(query, String.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result.toString();
	}

	@Override
	public void executePreparedStmt(String query, Object[] objects)
	{
		jdbcTemplate.update(query,objects);
	}


}
