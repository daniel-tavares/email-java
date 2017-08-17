package br.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.ConnectionEvent;

public class BaseRepositorio {

	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL = TCP)(HOST=racdev-scan.sefa.pa.ipa)(PORT=1521))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME = apps_extranet.sefa.pa.gov.br)))";
	String user = "usr_nfaeextranet";
	String pass = "sefa123";
	
	
	
	public Connection getConnection() {
		try{	
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, pass);
			return conn;
		}catch(Exception e){
           e.printStackTrace();	
           return null;
		}
	}
}
