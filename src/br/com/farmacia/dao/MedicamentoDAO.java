package br.com.farmacia.dao;

import br.com.farmacia.factory.ConnectionFactory;
import br.com.farmacia.model.Medicamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO {
	
	public void save (Medicamento medicamento) {
		
		String insert = "INSERT INTO medicamento(nome_medicamento, nome_laboratorio, preco, dt_vencimento, qtd_estoque, tipo_id) values(?,?,?,?,?,?)";  
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();
			
			pstm = (PreparedStatement) conn.prepareStatement(insert);
			pstm.setString(1, medicamento.getNome_medicamento());
			pstm.setString(2, medicamento.getNome_laboratorio());
			pstm.setDouble(3, medicamento.getPreco());
			pstm.setString(4, medicamento.getDt_vencimento());
			pstm.setInt(5, medicamento.getQtd_estoque());
			pstm.setInt(6, medicamento.getTipoMedicamento().getTipo_id());

			pstm.execute();
			
			System.out.println("\nMEDICAMENTO SALVO!");
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
	public void update(Medicamento medicamento) {
		
	
		String update = "UPDATE medicamento SET nome_medicamento =?, nome_laboratorio = ?, preco = ?, dt_vencimento = ?, qtd_estoque = ?, tipo_id = ? " + "WHERE medicamento_id = ?";
	
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();
			pstm = (PreparedStatement) conn.prepareStatement(update);
			
			pstm.setString(1, medicamento.getNome_medicamento());
			pstm.setString(2, medicamento.getNome_laboratorio());
			pstm.setDouble(3, medicamento.getPreco());
			pstm.setString(4, medicamento.getDt_vencimento());
			pstm.setInt(5, medicamento.getQtd_estoque());
			pstm.setInt(6, medicamento.getTipoMedicamento().getTipo_id());
			
			//id q deseja atualizar
			pstm.setInt(6, medicamento.getMedicamento_id());
			
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
	public List<Medicamento> getMedicamentos(int id) {
		
		String detalhar = "SELECT *FROM medicamento WHERE medicamento_id = ?;";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		List<Medicamento> medicamentos = new ArrayList<Medicamento>();
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();
			pstm = (PreparedStatement) conn.prepareStatement(detalhar);
			pstm.setInt(1, id);
			rset = pstm.executeQuery();
		
		while(rset.next()) {
			
			Medicamento medicamento = new Medicamento();
			
			medicamento.setMedicamento_id((rset.getInt("medicamento_id")));
			medicamento.setNome_medicamento((rset.getString("nome_medicamento")));
			medicamento.setNome_laboratorio((rset.getString("nome_laboratorio")));
			medicamento.setPreco((rset.getInt("preco")));
			medicamento.setDt_vencimento((rset.getString("dt_vencimento")));
			medicamento.setQtd_estoque((rset.getInt("qtd_estoque")));

			medicamentos.add(medicamento);
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
			return medicamentos;
	
	}	
	public void delete(int id) {
		String delete = "DELETE FROM medicamento WHERE medicamento_id = ?";
		
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
	public List<Medicamento> getMedicamentos(){
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco ***SELECT***
		ResultSet rset = null;

		String select = "SELECT medicamento_id, nome_medicamento FROM medicamento;";
		
		List<Medicamento> medicamentos = new ArrayList<Medicamento>();
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();
			pstm = (PreparedStatement) conn.prepareStatement(select);
			rset = pstm.executeQuery();
		
		while(rset.next()) {
			
			Medicamento medicamento = new Medicamento();
			
			medicamento.setMedicamento_id((rset.getInt("medicamento_id")));
			medicamento.setNome_medicamento((rset.getString("nome_medicamento")));

			medicamentos.add(medicamento);

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
			return medicamentos;
	}
}