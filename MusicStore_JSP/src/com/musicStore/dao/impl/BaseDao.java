package com.musicStore.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String dbUrl = "jdbc:sqlserver://localhost:1433; DatabaseName=MusicStore;";
	private static String dbUser="sa";
	private static String dbPassword = "sa";
	
	protected Connection conn;
	protected ResultSet rs;
	protected PreparedStatement stmt;
	
	public void openConnection() throws ClassNotFoundException, SQLException{
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeResource(){
		try {
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean executeUpdate(String sql, Object[] parameters) throws SQLException{
		createPrepareStatement(sql, parameters);
		int result = stmt.executeUpdate();
		return result>0;
	}

	private void createPrepareStatement(String sql, Object[] parameters) throws SQLException {
		stmt = conn.prepareStatement(sql);
		if(parameters != null){
			for(int i=0; i<parameters.length; i++){
				stmt.setObject(i+1, parameters[i]);
			}
		}
	}
	
	public ResultSet executeQuery(String sql, Object[] parameters) throws SQLException{
		createPrepareStatement(sql, parameters);
		rs = stmt.executeQuery();
		return rs;
	}
}
