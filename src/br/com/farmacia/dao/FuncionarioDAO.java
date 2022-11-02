package br.com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import br.com.farmacia.factory.ConnectionFactory;
import br.com.farmacia.model.Funcionario;

public class FuncionarioDAO {

	public void save(Funcionario funcionario) {
		
		String insert = "INSERT INTO funcionario(nome, cpf, data_nascimento, sexo, telefone, email, data_registro, usuario, senha, cargo_id) values(?,?,?,?,?,?,?,?,?,?)";  
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();
			
			pstm = (PreparedStatement) conn.prepareStatement(insert);
			pstm.setString(1, funcionario.getNome());
			pstm.setString(2, funcionario.getCpf());
			pstm.setString(3, funcionario.getData_nascimento());
			pstm.setString(4, funcionario.getSexo());
			pstm.setString(5, funcionario.getTelefone());
			pstm.setString(6, funcionario.getEmail());
			pstm.setString(7, funcionario.getData_registro());
			pstm.setString(8, funcionario.getUsuario());
			pstm.setString(9, funcionario.getSenha());
			pstm.setInt(10, funcionario.getCargo().getCargo_id());

			pstm.execute();
			
			System.out.println("\nFUNCIONARIO SALVO!");
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
	public void update(Funcionario funcionario) {
		
		
		String update = "UPDATE funcionario SET nome =?, cpf = ?, data_nascimento = ?, sexo = ?, telefone = ?, email = ?, data_registro = ?, usuario = ?, senha = ?, cargo_id = ? " + "WHERE funcionario_id = ?";
	
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();				
			pstm = (PreparedStatement) conn.prepareStatement(update);
			
			pstm.setString(1, funcionario.getNome());
			pstm.setString(2, funcionario.getCpf());
			pstm.setString(3, funcionario.getData_nascimento());
			pstm.setString(4, funcionario.getSexo());
			pstm.setString(5, funcionario.getTelefone());
			pstm.setString(6, funcionario.getEmail());
			pstm.setString(7, funcionario.getData_registro());
			pstm.setString(8, funcionario.getUsuario());
			pstm.setString(9, funcionario.getSenha());
			pstm.setInt(10, funcionario.getCargo().getCargo_id());

			//id q deseja atualizar
			pstm.setInt(11, funcionario.getFuncionario_id());
			
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
	public List<Funcionario> getFuncionario(int id) {
		
		String detalhar = "SELECT *FROM funcionario WHERE funcionario_id = ?;";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();
			pstm = (PreparedStatement) conn.prepareStatement(detalhar);
			pstm.setInt(1, id);
			rset = pstm.executeQuery();
		
		while(rset.next()) {
			
			Funcionario funcionario = new Funcionario();
			
			funcionario.setFuncionario_id((rset.getInt("funcionario_id")));
			funcionario.setNome((rset.getString("nome")));
			funcionario.setCpf((rset.getString("cpf")));
			funcionario.setData_nascimento((rset.getString("data_nascimento")));
			funcionario.setSexo((rset.getString("sexo")));
			funcionario.setTelefone((rset.getString("telefone")));
			funcionario.setEmail((rset.getString("email")));
			//funcionario.setCargo().setCargo_id((rset.getInt("cargo_id")));
			funcionario.setData_registro((rset.getString("data_registro")));
			funcionario.setUsuario((rset.getString("usuario")));
			funcionario.setSenha((rset.getString("senha")));

			funcionarios.add(funcionario);
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
			return funcionarios;
	}		
	public void delete(int id) {
		String delete = "DELETE FROM funcionario WHERE funcionario_id = ?";
		
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
	public List<Funcionario> getFuncionarios(){
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		String select = "SELECT funcionario_id, nome FROM funcionario;";
		
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();
			pstm = (PreparedStatement) conn.prepareStatement(select);
			rset = pstm.executeQuery();
		
		while(rset.next()) {
			
			Funcionario funcionario = new Funcionario();
			
			funcionario.setFuncionario_id((rset.getInt("funcionario_id")));
			funcionario.setNome((rset.getString("nome")));

			funcionarios.add(funcionario);

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
			return funcionarios;
	}
}

