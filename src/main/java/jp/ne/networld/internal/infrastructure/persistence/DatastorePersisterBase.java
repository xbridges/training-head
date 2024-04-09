package jp.ne.networld.internal.infrastructure.persistence;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DatastorePersisterBase<T>{
	private Connection conn;
	private String lastSql;
	private List<Object> params;
	
	
	public DatastorePersisterBase(Connection conn) {
		this.conn = conn;
		this.lastSql = null;
		this.params = new ArrayList<Object>();
	}
	
	public Map<String, List<Object>> getLastStatementInfo(){	
		Map<String, List<Object>> stateWithParams = new HashMap<String, List<Object>>();
		stateWithParams.put(this.lastSql, this.params);
		return stateWithParams;
	}
	
	protected PreparedStatement newStatement(String sql) throws SQLException{
		return this.conn.prepareStatement(sql);
	}
	
	protected void closeStatement(PreparedStatement ps) throws SQLException {
		ps.close();
	}
	
	protected void beginTx() throws SQLException {
		if(this.conn.getAutoCommit()) {
			this.conn.setAutoCommit(false);
		}
	}
	
	public  void commit() throws SQLException {
		if(!this.conn.getAutoCommit()) {
			this.conn.commit();
			this.conn.setAutoCommit(true);
		}
	}

	public  void rollback() throws SQLException {
		if(!this.conn.getAutoCommit()) {
			this.conn.rollback();
			this.conn.setAutoCommit(true);
		}
	}
	
	private ResultSet read(PreparedStatement statement) throws SQLException {
		ResultSet rs;
		synchronized (conn) {
			rs = statement.executeQuery();
		}
		return rs;
	}
	
	private ResultSet read(PreparedStatement statement, Object... args) throws SQLException {
		ResultSet rs;
		preparePlaceholder(statement, args);
		this.lastSql = statement.toString();
		this.params.clear();
		this.params.addAll(Arrays.asList(args));
		rs = this.read(statement);
		return rs;
	}
	
	public ResultSet read(String sql, Object... args) throws SQLException {
		ResultSet rs;
		PreparedStatement statement = this.newStatement(sql);
		rs = this.read(statement, args);
		return rs;
	}
	
	private int write(PreparedStatement statement) throws SQLException {
		int affected = 0;
		synchronized (conn) {
			affected = statement.executeUpdate();
		}
		return affected;
	}
	
	private int write(PreparedStatement statement, Object... args) throws SQLException {
		int affected = 0;
		preparePlaceholder(statement, args);
		this.lastSql = statement.toString();
		this.params.clear();
		this.params.addAll(Arrays.asList(args));
		affected = this.write(statement);
		return affected;
	}
	
	public int write(String sql, Object... args) throws SQLException {
		int affected = 0;
		PreparedStatement statement = this.newStatement(sql);
		affected = this.write(statement, args);
		return affected;
	}
	
	private boolean preparePlaceholder(PreparedStatement statement, Object... args) throws SQLException {
		int cnt = 1;
		for(Object arg: args) {
			if(arg instanceof List) { 
				List<?> list = (List<?>)arg;
				String[] values = list.toArray(new String[list.size()]);
				Array array = statement.getConnection().createArrayOf("VARCHAR", values);
				statement.setArray(cnt, array);
			} else if(arg instanceof String) {
				statement.setString(cnt, (String)arg);
			} else if(arg instanceof Integer) {
				statement.setInt(cnt, (int)arg);
			} else if(arg instanceof Long) {
				statement.setLong(cnt, (long)arg);
			} else if(arg instanceof Timestamp) {
				statement.setTimestamp(cnt, (Timestamp)arg);
			} else if(arg instanceof Double) {
				statement.setDouble(cnt, (Double)arg);
			} else if(arg instanceof Float) {
				statement.setFloat(cnt,(Float)arg);
			} else {
				return false;
			}
			cnt++;
		}
		return true;
	}
}
