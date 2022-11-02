package br.com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import br.com.farmacia.factory.ConnectionFactory;
import br.com.farmacia.model.Cargo;

public class CargoDAO {
	public void save (Cargo cargo) {
		
		String insert = "INSERT INTO cargo(cargo) values(?)";  
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();
			
			pstm = (PreparedStatement) conn.prepareStatement(insert);
			pstm.setString(1, cargo.getCargo());

			pstm.execute();
			
			System.out.println("\nCARGO SALVO!");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void update(Cargo cargo) {
		  
		
		String update = "UPDATE cargo SET cargo =? " + "WHERE cargo_id = ?";
	
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();				
			pstm = (PreparedStatement) conn.prepareStatement(update);
			
			pstm.setString(1, cargo.getCargo());
			pstm.setInt(2, cargo.getCargo_id());
			
			pstm.execute();
	
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	
	public List<Cargo> getCargos(){
			
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rset = null;
	
			String select = "SELECT cargo_id, cargo FROM cargo;";
			
			List<Cargo> cargos = new ArrayList<Cargo>();
			
			try {
				conn = ConnectionFactory.createConnectionToMysql();
				pstm = (PreparedStatement) conn.prepareStatement(select);
				rset = pstm.executeQuery();
			
			while(rset.next()) {
				
				Cargo cargo = new Cargo();
				
				cargo.setCargo_id((rset.getInt("cargo_id")));
				cargo.setCargo((rset.getString("cargo")));
	
				cargos.add(cargo);
	
				}
			}catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						if (rset != null) {
							rset.close();
						}
						if (pstm != null) {
							pstm.close();
						}
						if (conn != null) {
							conn.close();
						}
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				return cargos;
		}
	public void delete(int id) {
		String delete = "DELETE FROM cargo WHERE cargo_id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();
			pstm = (PreparedStatement) conn.prepareStatement(delete);
			
			pstm.setInt(1, id);
			
			pstm.execute();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
