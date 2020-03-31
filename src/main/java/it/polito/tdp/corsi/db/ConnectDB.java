package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectDB {

	private static final String jdbcUrl = "jdbc:mysql://localhost/iscritticorsi";
	private static  HikariDataSource ds; 
	
	public static Connection getConnection() {
		if(ds == null) {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(jdbcUrl);
			config.setUsername("root");
			config.setPassword("forzainter2");
			
			config.addDataSourceProperty("cachePrepStmts", true);
			config.addDataSourceProperty("prepStmtCacheSize", 250);
			config.addDataSourceProperty("prepStmtCacheSglLimit", "2048");
			
			ds = new HikariDataSource(config);
			
		}
		
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			
			System.err.println("errore di connessione al db");
			throw new RuntimeException(e);
		}
		
	}
	
	
}
