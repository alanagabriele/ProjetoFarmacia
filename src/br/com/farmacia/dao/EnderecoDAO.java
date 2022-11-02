package br.com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import br.com.farmacia.model.Endereco;
import br.com.farmacia.factory.ConnectionFactory;

public class EnderecoDAO {
	public void save (Endereco endereco) {
			
		String insert = "INSERT INTO endereco(endereco, numero, complemento, bairro, cidade, cep, cpf) values(?,?,?,?,?,?,?)";  
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();
			
			pstm = (PreparedStatement) conn.prepareStatement(insert);
			pstm.setString(1, endereco.getEndereco());
			pstm.setInt(2, endereco.getNumero());
			pstm.setString(3, endereco.getComplemento());
			pstm.setString(4, endereco.getBairro());
			pstm.setString(5, endereco.getCidade());
			pstm.setString(6, endereco.getCep());
			pstm.setString(7, endereco.getCpf());

			pstm.execute();
			
			//System.out.println("\nENDERECO SALVO!");
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
	public void update(Endereco endereco) {

		
		
		String update = "UPDATE endereco SET endereco =?, numero = ?, complemento = ?, bairro = ?, cidade = ?, cep = ?, cpf = ? " + "WHERE endereco_id = ?";
	
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();
			pstm = (PreparedStatement) conn.prepareStatement(update);
			
			pstm.setString(1, endereco.getEndereco());
			pstm.setInt(2, endereco.getNumero());
			pstm.setString(3, endereco.getComplemento());
			pstm.setString(4, endereco.getBairro());
			pstm.setString(5, endereco.getCidade());
			pstm.setString(6, endereco.getCep());
			pstm.setString(7, endereco.getCpf());

			//id q deseja atualizar
			pstm.setInt(8, endereco.getEndereco_id());
			
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
	public List<Endereco> getEnderecos(int id) {
		
		String detalhar = "SELECT *FROM endereco WHERE endereco_id = ?;";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		List<Endereco> enderecos = new ArrayList<Endereco>();
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();
			pstm = (PreparedStatement) conn.prepareStatement(detalhar);
			pstm.setInt(1, id);
			rset = pstm.executeQuery();
		
		while(rset.next()) {
			
			Endereco endereco = new Endereco();
			
			endereco.setEndereco_id((rset.getInt("endereco_id")));
			endereco.setEndereco((rset.getString("endereco")));
			endereco.setNumero((rset.getInt("numero")));
			endereco.setComplemento((rset.getString("complemento")));
			endereco.setBairro((rset.getString("bairro")));
			endereco.setCidade((rset.getString("cidade")));
			endereco.setCep((rset.getString("cep")));
			endereco.setCpf((rset.getString("cpf")));

			enderecos.add(endereco);

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
			return enderecos;
	
	}
	public void delete(int id) {
		String delete = "DELETE FROM endereco WHERE endereco_id = ?";
		
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
