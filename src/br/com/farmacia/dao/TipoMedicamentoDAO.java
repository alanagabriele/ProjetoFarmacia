package br.com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import br.com.farmacia.factory.ConnectionFactory;
import br.com.farmacia.model.TipoMedicamento;

public class TipoMedicamentoDAO {
	public void save (TipoMedicamento tipoMedicamento) {
		
		String insert = "INSERT INTO tipoMedicamento(tipoMedicamento) values(?)";  
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();
			
			pstm = (PreparedStatement) conn.prepareStatement(insert);
			pstm.setString(1, tipoMedicamento.getTipoMedicamento());

			pstm.execute();
			
			System.out.println("\nTIPO DE MEDICAMENTO SALVO!");
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
	public void update(TipoMedicamento tipoMedicamento) {
		  
		
		String update = "UPDATE tipoMedicamento  SET  tipoMedicamento =? " + "WHERE tipo_id = ?";
	
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();				
			pstm = (PreparedStatement) conn.prepareStatement(update);
			
			pstm.setString(1, tipoMedicamento.getTipoMedicamento());
			pstm.setInt(2, tipoMedicamento.getTipo_id());
			
			
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
	public List<TipoMedicamento> getTipoMedicamentos(){
			
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rset = null;
	
			String select = "SELECT tipo_id, tipoMedicamento FROM tipoMedicamento;";
			
			List<TipoMedicamento> tipoMedicamentos = new ArrayList<TipoMedicamento>();
			
			try {
				conn = ConnectionFactory.createConnectionToMysql();
				pstm = (PreparedStatement) conn.prepareStatement(select);
				rset = pstm.executeQuery();
			
			while(rset.next()) {
				
				TipoMedicamento tipoMedicamento = new TipoMedicamento();
				
				tipoMedicamento.setTipo_id((rset.getInt("tipo_id")));
				tipoMedicamento.setTipoMedicamento((rset.getString("tipoMedicamento")));
	
				tipoMedicamentos.add(tipoMedicamento);
	
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
				return tipoMedicamentos;
		}
	public void delete(int id) {
		String delete = "DELETE FROM tipoMedicamento WHERE tipo_id = ?";
		
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
