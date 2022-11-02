package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String url = "jdbc:mysql://localhost:3306/farmacia"; 
	private static final String user = "root";
	private static final String pass = "@05AlanA19";
	
	public static Connection createConnectionToMysql() {
			
		Connection conn = null;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pass);
		} catch(SQLException e) {
			System.out.println("Erro SQL...");
		} catch (Exception ex) {
			System.out.println("Erro geral...");
		}
		return conn; 
	}
	public static void main(String[] args) {
		
		Connection conn = createConnectionToMysql();
		if(conn != null) {
			System.out.println("Conexao obtida com sucesso!");
		}
	}
}	

