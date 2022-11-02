package br.com.farmacia.model;

public class Funcionario {
	
	private int funcionario_id;
	private String nome;
	private String cpf;
	private String data_nascimento;
	private String sexo;
	private String telefone;
	private String email;
	private Endereco endereco;
	private Cargo cargo;
	private String data_registro;
	private String usuario;
	private String senha;
	
	public int getFuncionario_id() {
		return funcionario_id;
	}
	public void setFuncionario_id(int funcionario_id) {
		this.funcionario_id = funcionario_id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getData_registro() {
		return data_registro;
	}

	public void setData_registro(String data_registro) {
		this.data_registro = data_registro;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
