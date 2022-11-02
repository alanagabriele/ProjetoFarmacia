package br.com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import br.com.farmacia.factory.ConnectionFactory;
import br.com.farmacia.model.Cliente;

public class ClienteDAO {

	public void save (Cliente cliente) {
			
			String insert = "INSERT INTO cliente(nome, cpf, data_nascimento, sexo, telefone, email, data_cadastro) values(?,?,?,?,?,?,?)";  
			
			Connection conn = null;
			PreparedStatement pstm = null;
			
			try {
				conn = ConnectionFactory.createConnectionToMysql();
				
				pstm = (PreparedStatement) conn.prepareStatement(insert);
				pstm.setString(1, cliente.getNome());
				pstm.setString(2, cliente.getCpf());
				pstm.setString(3, cliente.getData_nascimento());
				pstm.setString(4, cliente.getSexo());
				pstm.setString(5, cliente.getTelefone());
				pstm.setString(6, cliente.getEmail());
				pstm.setString(7, cliente.getData_cadastro());

				pstm.execute();
				
				System.out.println("\nCLIENTE SALVO!");
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
	public void update(Cliente cliente) {
			
		
			String update = "UPDATE cliente SET nome =?, cpf = ?, data_nascimento = ?, sexo = ?, telefone = ?, email = ?, data_cadastro = ? " + "WHERE cliente_id = ?";
		
			Connection conn = null;
			PreparedStatement pstm = null;
			
			try {
				conn = ConnectionFactory.createConnectionToMysql();				
				pstm = (PreparedStatement) conn.prepareStatement(update);
				
				pstm.setString(1, cliente.getNome());
				pstm.setString(2, cliente.getCpf());
				pstm.setString(3, cliente.getData_nascimento());
				pstm.setString(4, cliente.getSexo());
				pstm.setString(5, cliente.getTelefone());
				pstm.setString(6, cliente.getEmail());
				pstm.setString(7, cliente.getData_cadastro());

				//id q deseja atualizar
				pstm.setInt(8, cliente.getCliente_id());
				
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
	public List<Cliente> getClientes(int id) {
			
			String detalhar = "SELECT *FROM cliente WHERE cliente_id = ?;";
			
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rset = null;
	
			List<Cliente> clientes = new ArrayList<Cliente>();
			
			try {
				conn = ConnectionFactory.createConnectionToMysql();
				pstm = (PreparedStatement) conn.prepareStatement(detalhar);
				pstm.setInt(1, id);
				rset = pstm.executeQuery();
			
			while(rset.next()) {
				
				Cliente cliente = new Cliente();
				
				cliente.setCliente_id((rset.getInt("cliente_id")));
				cliente.setNome((rset.getString("nome")));
				cliente.setCpf((rset.getString("cpf")));
				cliente.setData_nascimento((rset.getString("data_nascimento")));
				cliente.setSexo((rset.getString("sexo")));
				cliente.setTelefone((rset.getString("telefone")));
				cliente.setEmail((rset.getString("email")));
				cliente.setData_cadastro((rset.getString("data_cadastro")));

				clientes.add(cliente);
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
				return clientes;
		}		
	public void delete(int id) {
			String delete = "DELETE FROM cliente WHERE cliente_id = ?";
			
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
	public List<Cliente> getClientes(){
			
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rset = null;
	
			String select = "SELECT cliente_id, nome FROM cliente;";
			
			List<Cliente> clientes = new ArrayList<Cliente>();
			
			try {
				conn = ConnectionFactory.createConnectionToMysql();
				pstm = (PreparedStatement) conn.prepareStatement(select);
				rset = pstm.executeQuery();
			
			while(rset.next()) {
				
				Cliente cliente = new Cliente();
				
				cliente.setCliente_id((rset.getInt("cliente_id")));
				cliente.setNome((rset.getString("nome")));
	
				clientes.add(cliente);
	
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
				return clientes;
		}
}
