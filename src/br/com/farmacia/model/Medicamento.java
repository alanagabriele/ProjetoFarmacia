package br.com.farmacia.model;

public class Medicamento {
	
	private int medicamento_id;
	private String nome_medicamento;
	private String nome_laboratorio;
	private float preco;
	private String dt_vencimento;
	private int qtd_estoque;
	private TipoMedicamento tipoMedicamento;
	
	public int getMedicamento_id() {
		return medicamento_id;
	}
	public void setMedicamento_id(int medicamento_id) {
		this.medicamento_id = medicamento_id;
	}
	public String getNome_medicamento() {
		return nome_medicamento;
	}
	public void setNome_medicamento(String nome_medicamento) {
		this.nome_medicamento = nome_medicamento;
	}
	public String getNome_laboratorio() {
		return nome_laboratorio;
	}
	public void setNome_laboratorio(String nome_laboratorio) {
		this.nome_laboratorio = nome_laboratorio;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public String getDt_vencimento() {
		return dt_vencimento;
	}
	public void setDt_vencimento(String dt_vencimento) {
		this.dt_vencimento = dt_vencimento;
	}
	public int getQtd_estoque() {
		return qtd_estoque;
	}
	public void setQtd_estoque(int qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}
	public TipoMedicamento getTipoMedicamento() {
		return tipoMedicamento;
	}
	public void setTipoMedicamento(TipoMedicamento tipoMedicamento) {
		this.tipoMedicamento = tipoMedicamento;
	}
	
}
