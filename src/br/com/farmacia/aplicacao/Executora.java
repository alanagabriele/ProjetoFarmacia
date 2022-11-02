package br.com.farmacia.aplicacao;

import java.util.Scanner;

import br.com.farmacia.dao.CargoDAO;
import br.com.farmacia.dao.ClienteDAO;
import br.com.farmacia.dao.EnderecoDAO;
import br.com.farmacia.dao.FuncionarioDAO;
import br.com.farmacia.dao.MedicamentoDAO;
import br.com.farmacia.dao.TipoMedicamentoDAO;
import br.com.farmacia.model.Cargo;
import br.com.farmacia.model.Cliente;
import br.com.farmacia.model.Endereco;
import br.com.farmacia.model.Funcionario;
import br.com.farmacia.model.Medicamento;
import br.com.farmacia.model.TipoMedicamento;

public class Executora {
	public static void main (String[] args) {
		
		menuInicial();
		
	}
	public static void menuInicial() {
		Scanner ler = new Scanner(System.in);

		System.out.println("===============================");
		System.out.println("SUPER SISFARMA PREMIUM 2022");
		System.out.println("===============================");
		System.out.println("\nEscolha uma opcao:");
		System.out.println("(1) Medicamentos");
		System.out.println("(2) Clientes");
		System.out.println("(3) Funcionarios");
		System.out.println("(4) Cargos");

		System.out.println("===============================");
		System.out.print("Opcao:");
		String opcao = ler.nextLine();

		do {
			switch(opcao.toUpperCase()){
				case "1":
					menuMedicamento();
				case "2":
					menuCliente();
				case "3":
					menuFuncionario();
				case "4":
					menuCargo();
				default:
					System.out.println("\nDigite uma opcao correta!");
					System.out.print("Opcao:");
					opcao = ler.nextLine();
			}
		}while ((opcao != "1") && (opcao != "2") && (opcao != "3") && (opcao != "4"));
		ler.close();
	}
	
	//MEDICAMENTO
	
	public static void menuMedicamento(){
		Scanner ler = new Scanner(System.in);

		System.out.println("\n===============================");
		System.out.println("SUPER SISFARMA PREMIUM 2022");
		System.out.println("===============================");
		System.out.println("MEDICAMENTOS");
		System.out.println("\nEscolha uma opcao:");
		System.out.println("(T) Tipos de medicamento");
		System.out.println("(A) Atualizar medicamento");
		System.out.println("(E) Exibir um medicamento pelo ID");
		System.out.println("(I) Inserir novo medicamento");
		System.out.println("(L) Listar todos os medicamentos");
		System.out.println("(X) Apagar medicamento");
		System.out.println("(V) Voltar para menu inicial");

		System.out.print("Opcao:");
		String opcao = ler.nextLine();
		
		do {
			switch(opcao.toUpperCase()){
			case "T":
				menuTipoMedicamento();
			case "A":
				opcaoAtualizarMedicamento();
			case "E":
				opcaoDetalharMedicamento();
			case "I":
				opcaoInserirMedicamento();
			case "L":
				opcaoListarMedicamento(); //
			case "X":
				opcaoApagarMedicamento();
			case "V":
				menuInicial();//
			default:
				System.out.println("\nDigite uma opcao correta!");
				System.out.print("Opcao:");
				opcao = ler.nextLine();
			}
		}while ((opcao != "T") &&(opcao != "A") && (opcao != "E") && (opcao != "I") && (opcao != "L") && (opcao != "X")&& (opcao != "V"));
		
		ler.close();
	}
	public static void opcaoInserirMedicamento(){

		Scanner ler1 = new Scanner(System.in);
		Scanner ler2 = new Scanner(System.in);

		Medicamento medicamentoNew = new Medicamento();
		MedicamentoDAO medicamentoDAO= new MedicamentoDAO();
		
		System.out.println("===============================");
		System.out.println("NOVO MEDICAMENTO");
		System.out.println("===============================");

		System.out.print("\nNome do medicamento: ");
		medicamentoNew.setNome_medicamento(ler1.nextLine());
		
		System.out.print("Nome do laboratorio: ");
		medicamentoNew.setNome_laboratorio(ler1.nextLine());
		
		System.out.print("Preco: ");
		medicamentoNew.setPreco(ler2.nextInt());
		
		System.out.print("Data de vencimento: ");
		medicamentoNew.setDt_vencimento(ler1.nextLine());
		
		System.out.print("Quantidade: ");
		medicamentoNew.setQtd_estoque(ler2.nextInt());
		
		System.out.println("Tipos de medicamentos: \n");
		
		TipoMedicamentoDAO tipoMedicamentoList = new TipoMedicamentoDAO();
		
		for(TipoMedicamento m: tipoMedicamentoList.getTipoMedicamentos()) {
			System.out.println(m.getTipo_id() + " - " + m.getTipoMedicamento());
		}
		TipoMedicamento tipoMedicamento = new TipoMedicamento();
		
		System.out.print("\nDigite o id do tipo do medicamento: ");
		tipoMedicamento.setTipo_id(ler2.nextInt());
		
		medicamentoNew.setTipoMedicamento(tipoMedicamento);
				
		medicamentoDAO.save(medicamentoNew);
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler1.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuMedicamento();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler1.nextLine();
			}
		}while (opcao != "V");
	
		ler1.close();
		ler2.close();
	}
	public static void opcaoListarMedicamento(){
		Scanner ler = new Scanner(System.in);
	
		System.out.println("\n===============================");
		System.out.println("SUPER SISFARMA PREMIUM 2022");
		System.out.println("===============================");
		System.out.println("\nLISTAGEM DE MEDICAMENTOS\n");
		System.out.println("ID - Nome");
		
		MedicamentoDAO medicamentoList = new MedicamentoDAO();
		
		for(Medicamento m: medicamentoList.getMedicamentos()) {
			System.out.println(m.getMedicamento_id() + " - " + m.getNome_medicamento());
		}

		System.out.print("\n(V) Voltar: ");
		String opcao = ler.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuMedicamento();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler.nextLine();
			}
		}while (opcao != "V");
		ler.close();
	}
	public static void opcaoAtualizarMedicamento() {
		Scanner ler1 = new Scanner(System.in);
		Scanner ler2 = new Scanner(System.in);
		
		System.out.println("\n===============================");
		System.out.println("ATUALIZACAO MEDICAMENTO");
		System.out.println("===============================");
		System.out.print("Digite o ID do medicamento que deseja atualizar: ");
		int id = ler2.nextInt();
		
		System.out.println("\n===============================");
		System.out.println("ATUALIZACAO MEDICAMENTO- ID: "+ id);

		Medicamento medicamentoUpdate = new Medicamento();
		MedicamentoDAO medicamentoCrud= new MedicamentoDAO();
				
		System.out.print("\nNome do medicamento: ");
		medicamentoUpdate.setNome_medicamento(ler1.nextLine());
		
		System.out.print("Nome do laboratorio: ");
		medicamentoUpdate.setNome_laboratorio(ler1.nextLine());
		
		System.out.print("Preco: ");
		medicamentoUpdate.setPreco(ler2.nextInt());
		
		System.out.print("Data de vencimento: ");
		medicamentoUpdate.setDt_vencimento(ler1.nextLine());
		
		System.out.print("Quantidade: ");
		medicamentoUpdate.setQtd_estoque(ler2.nextInt());
		
		TipoMedicamentoDAO tipoMedicamentoList = new TipoMedicamentoDAO();
		
		for(TipoMedicamento m: tipoMedicamentoList.getTipoMedicamentos()) {
			System.out.println(m.getTipo_id() + " - " + m.getTipoMedicamento());
		}
		TipoMedicamento tipoMedicamento = new TipoMedicamento();
		
		System.out.print("\nDigite o id do tipo do medicamento: ");
		tipoMedicamento.setTipo_id(ler2.nextInt());
		
		medicamentoUpdate.setTipoMedicamento(tipoMedicamento);
						
		medicamentoUpdate.setMedicamento_id(id);

		medicamentoCrud.update(medicamentoUpdate);
		
		System.out.println("\nMEDICAMENTO ATUALIZADO!");
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler1.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuMedicamento();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler1.nextLine();
			}
		}while (opcao != "V");
		ler1.close();
		ler2.close();
	}
	public static void opcaoDetalharMedicamento() {
		Scanner ler = new Scanner(System.in);
		
		MedicamentoDAO medicamentoList = new MedicamentoDAO();

		System.out.println("\nDETALHAR MEDICAMENTO");
		System.out.print("\nDigite o ID do medicamento: ");
		int id = ler.nextInt();
		
		for(Medicamento m: medicamentoList.getMedicamentos(id)) {
			System.out.println("\nDETALHES DO MEDICAMENTO ID: "+ m.getMedicamento_id());
			System.out.println("Nome: "+ m.getNome_medicamento());
			System.out.println("Laboratorio: "+ m.getNome_laboratorio());
			System.out.println("Preco: "+ m.getPreco());
			System.out.println("Estoque: "+ m.getQtd_estoque());
			//System.out.println("Tipo de medicamento id: "+ m.getTipoMedicamento().getTipo_id());

		}
		System.out.print("\n(V) Voltar: ");
		String opcao = ler.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuMedicamento();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler.nextLine();
			}
		}while (opcao != "V");
		
		ler.close();
	}
	public static void opcaoApagarMedicamento() {
		Scanner ler = new Scanner(System.in);
		
		MedicamentoDAO medicamentoDelete = new MedicamentoDAO();
		
		System.out.println("\nAPAGAR MEDICAMENTO");
		System.out.print("\nDigite o ID do medicamento: ");
		medicamentoDelete.delete(ler.nextInt());
		
		System.out.println("\nMEDICAMENTO APAGADO!");
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuMedicamento();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler.nextLine();
			}
		}while (opcao != "V");
		ler.close();
	}
	
	// TIPOS DE MEDICAMENTO
	
	public static void menuTipoMedicamento(){
		Scanner ler = new Scanner(System.in);

		System.out.println("===============================");
		System.out.println("CARGOS");
		System.out.println("\nEscolha uma opcao:");
		System.out.println("(A) Atualizar tipo de medicamento");
		System.out.println("(I) Inserir novo tipo de medicamento");
		System.out.println("(L) Listar todos os tipos de medicamentos");
		System.out.println("(X) Apagar tipo de medicamento");
		System.out.println("(V) Voltar");

		System.out.print("Opcao:");
		String opcao = ler.nextLine();
		
		do {
			switch(opcao.toUpperCase()){
			case "A":
				opcaoAtualizarTipo();
			case "I":
				opcaoInserirTipo();
			case "L":
				opcaoListarTipo(); //
			case "X":
				opcaoApagarTipo();
			case "V":
				menuMedicamento();//
			default:
				System.out.println("\nDigite uma opcao correta!");
				System.out.print("Opcao:");
				opcao = ler.nextLine();
			}
		}while ((opcao != "A") && (opcao != "I") && (opcao != "L") && (opcao != "X") && (opcao != "V"));
		
		ler.close();
	}
	public static void opcaoInserirTipo(){

		Scanner ler1 = new Scanner(System.in);

		TipoMedicamento tipoMedicamento = new TipoMedicamento();
		TipoMedicamentoDAO tipoMedicamentoDAO = new TipoMedicamentoDAO();
				
		System.out.println("\n===============================");
		System.out.println("NOVO TIPO DE MEDICAMENTO");
		System.out.println("===============================");


		System.out.print("\nNome do tipo de medicamento: ");
		tipoMedicamento.setTipoMedicamento(ler1.nextLine());
		
		tipoMedicamentoDAO.save(tipoMedicamento);
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler1.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuTipoMedicamento();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler1.nextLine();
			}
		}while (opcao != "V");
	
		ler1.close();

	}	
	public static void opcaoListarTipo(){
		Scanner ler = new Scanner(System.in);
	
		System.out.println("\n===============================");
		System.out.println("SUPER SISFARMA PREMIUM 2022");
		System.out.println("===============================");
		System.out.println("\nLISTAGEM DE TIPOS DE MEDICAMENTO\n");
		System.out.println("ID - Nome");
		
		TipoMedicamentoDAO tipoMedicamentoList = new TipoMedicamentoDAO();
		
		for(TipoMedicamento m: tipoMedicamentoList.getTipoMedicamentos()) {
			System.out.println(m.getTipo_id() + " - " + m.getTipoMedicamento());
		}
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuTipoMedicamento();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler.nextLine();
			}
		}while (opcao != "V");
		ler.close();
	}
	public static void opcaoAtualizarTipo(){
		Scanner ler1 = new Scanner(System.in);
		Scanner ler2 = new Scanner(System.in);
		
		System.out.println("===============================");
		System.out.println("ATUALIZACAO TIPO DE MEDICAMENTO");
		System.out.println("===============================");
		System.out.print("Digite o ID do tipo de medicamento que deseja atualizar: ");
		int id = ler2.nextInt();
		
		System.out.println("\n===============================");
		System.out.println("ATUALIZACAO TIPO DE MEDICAMENTO- ID: "+ id);

		TipoMedicamento tipoMedicamento = new TipoMedicamento();
		TipoMedicamentoDAO tipoMedicamentoDAO = new TipoMedicamentoDAO();
				
		System.out.print("\nTipo de medicamento: ");
		tipoMedicamento.setTipoMedicamento(ler1.nextLine());
		
		tipoMedicamentoDAO.update(tipoMedicamento);
		
		System.out.println("\nTIPO DE MEDICAMENTO ATUALIZADO!");
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler1.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuTipoMedicamento();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler1.nextLine();
			}
		}while (opcao != "V");
		ler1.close();
		ler2.close();
	}
	public static void opcaoApagarTipo() {
		Scanner ler = new Scanner(System.in);
		
		TipoMedicamentoDAO tipoMedicamentoDAO = new TipoMedicamentoDAO();
		
		System.out.println("\nTIPO DE MEDICAMENTO");
		System.out.print("\nDigite o ID do tipo de medicamento: ");
		int id = ler.nextInt();
		
		tipoMedicamentoDAO.delete(id);
		
		System.out.println("\nTIPO DE MEDICAMENTO APAGADO!");
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuTipoMedicamento();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler.nextLine();
			}
		}while (opcao != "V");
		ler.close();
	}

	//CLIENTE E ENDERECO
	
	public static void menuCliente(){
		Scanner ler = new Scanner(System.in);

		System.out.println("\n===============================");
		System.out.println("SUPER SISFARMA PREMIUM 2022");
		System.out.println("===============================");
		System.out.println("CLIENTES");
		System.out.println("\nEscolha uma opcao:");
		System.out.println("(A) Atualizar cliente");
		System.out.println("(E) Exibir um cliente pelo ID");
		System.out.println("(I) Inserir novo cliente");
		System.out.println("(L) Listar todos os clientes");
		System.out.println("(X) Apagar cliente");
		System.out.println("(V) Voltar para menu inicial");

		System.out.print("Opcao:");
		String opcao = ler.nextLine();
		
		do {
			switch(opcao.toUpperCase()){
			case "A":
				opcaoAtualizarCliente();
			case "E":
				opcaoDetalharCliente();
			case "I":
				opcaoInserirCliente();
			case "L":
				opcaoListarCliente(); //
			case "X":
				opcaoApagarCliente();
			case "V":
				menuInicial();//
			default:
				System.out.println("\nDigite uma opcao correta!");
				System.out.print("Opcao:");
				opcao = ler.nextLine();
			}
		}while ((opcao != "A") && (opcao != "E") && (opcao != "I") && (opcao != "L") && (opcao != "X")&& (opcao != "V"));
		
		ler.close();
	}
	public static void opcaoInserirCliente(){

		Scanner ler1 = new Scanner(System.in);
		Scanner ler2 = new Scanner(System.in);

		Cliente cliente = new Cliente();
		ClienteDAO clienteDAO = new ClienteDAO();
				
		System.out.println("\n===============================");
		System.out.println("NOVO CLIENTE");
		System.out.println("===============================");


		System.out.print("\nNome: ");
		cliente.setNome(ler1.nextLine());
		
		System.out.print("CPF: ");
		cliente.setCpf(ler1.nextLine());
		
		System.out.print("Data de nascimento: ");
		cliente.setData_nascimento(ler1.nextLine());
		
		System.out.print("Sexo: ");
		cliente.setSexo(ler1.nextLine());
		
		System.out.print("Telefone: ");
		cliente.setTelefone(ler1.nextLine());
		
		System.out.print("Email: ");
		cliente.setEmail(ler1.nextLine());

		Endereco endereco = new Endereco();
		EnderecoDAO enderecoDAO = new EnderecoDAO();

		System.out.println("Endereco: ");
		System.out.print("	Rua: ");
		endereco.setEndereco(ler1.nextLine());

		System.out.print("	Numero: ");
		endereco.setNumero(ler2.nextInt());
		
		System.out.print("	Complemento: ");
		endereco.setComplemento(ler1.nextLine());
		
		System.out.print("	Bairro: ");
		endereco.setBairro(ler1.nextLine());
		
		System.out.print("	Cidade: ");
		endereco.setCidade(ler1.nextLine());
		
		System.out.print("	CEP: ");
		endereco.setCep(ler1.nextLine());
		
		enderecoDAO.save(endereco);
		
		cliente.setEndereco(endereco);
		
		System.out.print("Data Cadastro: ");
		cliente.setData_cadastro(ler1.nextLine());
		
		clienteDAO.save(cliente);
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler1.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuCliente();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler1.nextLine();
			}
		}while (opcao != "V");
	
		ler1.close();
		ler2.close();

	}	
	public static void opcaoListarCliente(){
		Scanner ler = new Scanner(System.in);
	
		System.out.println("\n===============================");
		System.out.println("SUPER SISFARMA PREMIUM 2022");
		System.out.println("===============================");
		System.out.println("\nLISTAGEM DE CLIENTES\n");
		System.out.println("ID - Nome");
		
		ClienteDAO clienteList = new ClienteDAO();
		
		for(Cliente m: clienteList.getClientes()) {
			System.out.println(m.getCliente_id() + " - " + m.getNome());
		}

		System.out.print("\n(V) Voltar: ");
		String opcao = ler.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuCliente();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler.nextLine();
			}
		}while (opcao != "V");
		ler.close();
	}
	public static void opcaoAtualizarCliente() {
		Scanner ler1 = new Scanner(System.in);
		Scanner ler2 = new Scanner(System.in);
		
		System.out.println("===============================");
		System.out.println("ATUALIZACAO CLIENTE");
		System.out.println("===============================");
		System.out.print("Digite o ID do cliente que deseja atualizar: ");
		int id = ler2.nextInt();
		
		System.out.println("\n===============================");
		System.out.println("ATUALIZACAO CLIENTE- ID: "+ id);

		Cliente cliente = new Cliente();
		ClienteDAO clienteDAO = new ClienteDAO();
				
		System.out.print("\nNome: ");
		cliente.setNome(ler1.nextLine());
		
		System.out.print("CPF: ");
		cliente.setCpf(ler1.nextLine());
		
		System.out.print("Data de nascimento: ");
		cliente.setData_nascimento(ler1.nextLine());
		
		System.out.print("Sexo: ");
		cliente.setSexo(ler1.nextLine());
		
		System.out.print("Telefone: ");
		cliente.setTelefone(ler1.nextLine());
		
		System.out.print("Email: ");
		cliente.setEmail(ler1.nextLine());

		Endereco endereco = new Endereco();
		EnderecoDAO enderecoDAO = new EnderecoDAO();

		System.out.println("Endereco: ");
		System.out.print("	Rua: ");
		endereco.setEndereco(ler1.nextLine());

		System.out.print("	Numero: ");
		endereco.setNumero(ler2.nextInt());
		
		System.out.print("	Complemento: ");
		endereco.setComplemento(ler1.nextLine());
		
		System.out.print("	Bairro: ");
		endereco.setBairro(ler1.nextLine());
		
		System.out.print("	Cidade: ");
		endereco.setCidade(ler1.nextLine());
		
		System.out.print("	CEP: ");
		endereco.setCep(ler1.nextLine());
		
		System.out.print("	CPF: ");
		endereco.setCpf(ler1.nextLine());
		
		enderecoDAO.save(endereco);
		
		cliente.setEndereco(endereco);
		
		System.out.print("Data Cadastro: ");
		cliente.setData_cadastro(ler1.nextLine());
		
		cliente.setCliente_id(id);

		clienteDAO.update(cliente);
		
		System.out.println("\nCLIENTE ATUALIZADO!");
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler1.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuCliente();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler1.nextLine();
			}
		}while (opcao != "V");
		ler1.close();
		ler2.close();
	}
	public static void opcaoDetalharCliente() {
		Scanner ler = new Scanner(System.in);
		
		ClienteDAO clienteDAO = new ClienteDAO();
		EnderecoDAO enderecoDAO = new EnderecoDAO();

		System.out.println("\nDETALHAR CLIENTE");
		System.out.print("\nDigite o ID do cliente: ");
		int id = ler.nextInt();
		
		for(Cliente m: clienteDAO.getClientes(id)) {
			System.out.println("\nDETALHES DO CLIENTE ID: "+ m.getCliente_id());
			System.out.println("Nome: "+ m.getNome());
			System.out.println("CPF:: "+ m.getCpf());
			System.out.println("Data de nascimento: "+ m.getData_nascimento());
			System.out.println("Sexo: "+ m.getSexo());
			System.out.println("Telefone: "+ m.getTelefone());
			System.out.println("email: "+ m.getEmail());
			for(Endereco e: enderecoDAO.getEnderecos(id)) {
				System.out.println("Endereco:" );
				System.out.println("	Rua:" + e.getEndereco());
				System.out.println("	Numero:" + e.getNumero());
				System.out.println("	Complemento:" + e.getComplemento());
				System.out.println("	Bairro:" + e.getBairro());
				System.out.println("	Cidade:" + e.getCidade());
				System.out.println("	CEP:" + e.getCep());
				System.out.println("	CPF:" + e.getCpf());
			}
			System.out.println("data_cadastro: "+ m.getData_cadastro());
		}
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuCliente();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler.nextLine();
			}
		}while (opcao != "V");
		
		ler.close();
	}
	public static void opcaoApagarCliente() {
		Scanner ler = new Scanner(System.in);
		
		ClienteDAO clienteDAO = new ClienteDAO();
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		
		System.out.println("\nAPAGAR CLIENTE");
		System.out.print("\nDigite o ID do cliente:  ");
		int id = ler.nextInt();
		
		clienteDAO.delete(id);
		enderecoDAO.delete(id);
		
		System.out.println("\nCLIENTE APAGADO!");
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuCliente();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler.nextLine();
			}
		}while (opcao != "V");
		ler.close();
	}

	//FUNCIONARIO E ENDERECO
	
	public static void menuFuncionario(){
		Scanner ler = new Scanner(System.in);

		System.out.println("\n===============================");
		System.out.println("SUPER SISFARMA PREMIUM 2022");
		System.out.println("===============================");
		System.out.println("FUNCIONARIOS");
		System.out.println("\nEscolha uma opcao:");
		System.out.println("(A) Atualizar funcionario");
		System.out.println("(E) Exibir um funcionario pelo ID");
		System.out.println("(I) Inserir novo funcionario");
		System.out.println("(L) Listar todos os funcionarios");
		System.out.println("(X) Apagar funcionario");
		System.out.println("(V) Voltar para menu inicial");

		System.out.print("Opcao:");
		String opcao = ler.nextLine();
		
		do {
			switch(opcao.toUpperCase()){
			case "A":
				opcaoAtualizarFuncionario();
			case "E":
				opcaoDetalharFuncionario();
			case "I":
				opcaoInserirFuncionario();
			case "L":
				opcaoListarFuncionario(); //
			case "X":
				opcaoApagarFuncionario();
			case "V":
				menuInicial();//
			default:
				System.out.println("\nDigite uma opcao correta!");
				System.out.print("Opcao:");
				opcao = ler.nextLine();
			}
		}while ((opcao != "A") && (opcao != "E") && (opcao != "I") && (opcao != "L") && (opcao != "X") && (opcao != "V"));
		
		ler.close();
	}
	public static void opcaoInserirFuncionario(){

		Scanner ler1 = new Scanner(System.in);
		Scanner ler2 = new Scanner(System.in);

		Funcionario funcionario = new Funcionario();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				
		System.out.println("\n===============================");
		System.out.println("NOVO FUNCIONARIO");
		System.out.println("===============================");

		System.out.print("\nNome: ");
		funcionario.setNome(ler1.nextLine());
		
		System.out.print("CPF: ");
		funcionario.setCpf(ler1.nextLine());
		
		System.out.print("Data de nascimento: ");
		funcionario.setData_nascimento(ler1.nextLine());
		
		System.out.print("Sexo: ");
		funcionario.setSexo(ler1.nextLine());
		
		System.out.print("Telefone: ");
		funcionario.setTelefone(ler1.nextLine());
		
		System.out.print("Email: ");
		funcionario.setEmail(ler1.nextLine());
		
		Endereco endereco = new Endereco();
		EnderecoDAO enderecoDAO = new EnderecoDAO();

		System.out.println("Endereco: ");
		System.out.print("	Rua: ");
		endereco.setEndereco(ler1.nextLine());

		System.out.print("	Numero: ");
		endereco.setNumero(ler2.nextInt());
		
		System.out.print("	Complemento: ");
		endereco.setComplemento(ler1.nextLine());
		
		System.out.print("	Bairro: ");
		endereco.setBairro(ler1.nextLine());
		
		System.out.print("	Cidade: ");
		endereco.setCidade(ler1.nextLine());
		
		System.out.print("	CEP: ");
		endereco.setCep(ler1.nextLine());
		
		System.out.print("	CPF: ");
		endereco.setCpf(ler1.nextLine());
		
		enderecoDAO.save(endereco);
		
		funcionario.setEndereco(endereco);
	
		System.out.println("Cargos: \n");
		
		CargoDAO cargosList = new CargoDAO();
		
		for(Cargo m: cargosList.getCargos()) {
			System.out.println(m.getCargo_id() + " - " + m.getCargo());
		}
		Cargo cargo = new Cargo();
		
		System.out.print("\nDigite o id do cargo: ");
		cargo.setCargo_id(ler2.nextInt());
		
		funcionario.setCargo(cargo);
				
		System.out.print("Data Registro: ");
		funcionario.setData_registro(ler1.nextLine());
		
		System.out.print("Usuario: ");
		funcionario.setUsuario(ler1.nextLine());
		
		System.out.print("Senha: ");
		funcionario.setSenha(ler1.nextLine());
		
		funcionarioDAO.save(funcionario);
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler1.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuFuncionario();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler1.nextLine();
			}
		}while (opcao != "V");
	
		ler1.close();
		ler2.close();

	}	
	public static void opcaoListarFuncionario(){
		Scanner ler = new Scanner(System.in);
	
		System.out.println("\n===============================");
		System.out.println("SUPER SISFARMA PREMIUM 2022");
		System.out.println("===============================");
		System.out.println("\nLISTAGEM DE FUNCIONARIOS\n");
		System.out.println("ID - Nome");
		
		FuncionarioDAO funcionarioList = new FuncionarioDAO();
		
		for(Funcionario m: funcionarioList.getFuncionarios()) {
			System.out.println(m.getFuncionario_id() + " - " + m.getNome());
		}

		System.out.print("\n(V) Voltar: ");
		String opcao = ler.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuFuncionario();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler.nextLine();
			}
		}while (opcao != "V");
		ler.close();
	}
	public static void opcaoAtualizarFuncionario() {
		Scanner ler1 = new Scanner(System.in);
		Scanner ler2 = new Scanner(System.in);
		
		System.out.println("===============================");
		System.out.println("ATUALIZACAO FUNCIONARIO");
		System.out.println("===============================");
		System.out.print("Digite o ID do funcionario que deseja atualizar: ");
		int id = ler2.nextInt();
		
		System.out.println("\n===============================");
		System.out.println("ATUALIZACAO FUNCIONARIO- ID: "+ id);

		Funcionario funcionario = new Funcionario();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				
		System.out.print("\nNome: ");
		funcionario.setNome(ler1.nextLine());
		
		System.out.print("CPF: ");
		funcionario.setCpf(ler1.nextLine());
		
		System.out.print("Data de nascimento: ");
		funcionario.setData_nascimento(ler1.nextLine());
		
		System.out.print("Sexo: ");
		funcionario.setSexo(ler1.nextLine());
		
		System.out.print("Telefone: ");
		funcionario.setTelefone(ler1.nextLine());
		
		System.out.print("Email: ");
		funcionario.setEmail(ler1.nextLine());	
		
		Endereco endereco = new Endereco();
		EnderecoDAO enderecoDAO = new EnderecoDAO();

		System.out.println("Endereco: ");
		System.out.print("	Rua: ");
		endereco.setEndereco(ler1.nextLine());

		System.out.print("	Numero: ");
		endereco.setNumero(ler2.nextInt());
		
		System.out.print("	Complemento: ");
		endereco.setComplemento(ler1.nextLine());
		
		System.out.print("	Bairro: ");
		endereco.setBairro(ler1.nextLine());
		
		System.out.print("	Cidade: ");
		endereco.setCidade(ler1.nextLine());
		
		System.out.print("	CEP: ");
		endereco.setCep(ler1.nextLine());
		
		System.out.print("	CPF: ");
		endereco.setCpf(ler1.nextLine());
		
		enderecoDAO.save(endereco);
		
		funcionario.setEndereco(endereco);
		
		System.out.println("Cargos: \n");
		
		CargoDAO cargosList = new CargoDAO();
		
		for(Cargo m: cargosList.getCargos()) {
			System.out.println(m.getCargo_id() + " - " + m.getCargo());
		}
		Cargo cargo = new Cargo();
		
		System.out.print("\nDigite o id do cargo: ");
		cargo.setCargo_id(ler2.nextInt());
		
		funcionario.setCargo(cargo);
		
		System.out.print("Data Registro: ");
		funcionario.setData_registro(ler1.nextLine());
		
		System.out.print("Usuario: ");
		funcionario.setUsuario(ler1.nextLine());
		
		System.out.print("Senha: ");
		funcionario.setSenha(ler1.nextLine());
		
		funcionario.setFuncionario_id(id);

		funcionarioDAO.update(funcionario);
		
		System.out.println("\nFUNCIONARIO ATUALIZADO!");
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler1.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuFuncionario();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler1.nextLine();
			}
		}while (opcao != "V");
		ler1.close();
		ler2.close();
	}
	public static void opcaoDetalharFuncionario() {
		Scanner ler = new Scanner(System.in);
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		EnderecoDAO enderecoDAO = new EnderecoDAO();

		System.out.println("\nDETALHAR FUNCIONARIO");
		System.out.print("\nDigite o ID do funcionario: ");
		int id = ler.nextInt();
		
		for(Funcionario m: funcionarioDAO.getFuncionario(id)) {
			System.out.println("\nDETALHES DO FUNCIONARIO ID: "+ m.getFuncionario_id());
			System.out.println("Nome: "+ m.getNome());
			System.out.println("CPF:: "+ m.getCpf());
			System.out.println("Data de nascimento: "+ m.getData_nascimento());
			System.out.println("Sexo: "+ m.getSexo());
			System.out.println("Telefone: "+ m.getTelefone());
			System.out.println("email: "+ m.getEmail());
			for(Endereco e: enderecoDAO.getEnderecos(id)) {
				System.out.println("Endereco:" );
				System.out.println("	Rua:" + e.getEndereco());
				System.out.println("	Numero:" + e.getNumero());
				System.out.println("	Complemento:" + e.getComplemento());
				System.out.println("	Bairro:" + e.getBairro());
				System.out.println("	Cidade:" + e.getCidade());
				System.out.println("	CEP:" + e.getCep());
				System.out.println("	CPF:" + e.getCpf());
			}
			System.out.println("Cargo: "+ m.getCargo());
			System.out.println("Usuario: "+ m.getUsuario());
			System.out.println("Senha: "+ m.getSenha());
			System.out.println("data_registro: "+ m.getData_registro());
		}
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuFuncionario();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler.nextLine();
			}
		}while (opcao != "V");
		
		ler.close();
	}
	public static void opcaoApagarFuncionario() {
		Scanner ler = new Scanner(System.in);
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		EnderecoDAO enderecoDAO = new EnderecoDAO();

		
		System.out.println("\nAPAGAR FUNCINARIO");
		System.out.print("\nDigite o ID do funcionario: ");
		int id = ler.nextInt();
		
		funcionarioDAO.delete(id);
		enderecoDAO.delete(id);
		
		System.out.println("\nFUNCIONARIO APAGADO!");
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuFuncionario();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler.nextLine();
			}
		}while (opcao != "V");
		ler.close();
	}
	
	// CARGO	
	
	public static void menuCargo(){
		Scanner ler = new Scanner(System.in);

		System.out.println("===============================");
		System.out.println("CARGOS");
		System.out.println("\nEscolha uma opcao:");
		System.out.println("(A) Atualizar cargo");
		System.out.println("(I) Inserir novo cargo");
		System.out.println("(L) Listar todos os cargos");
		System.out.println("(X) Apagar cargo");
		System.out.println("(V) Voltar");

		System.out.print("Opcao:");
		String opcao = ler.nextLine();
		
		do {
			switch(opcao.toUpperCase()){
			case "A":
				opcaoAtualizarCargo();
			case "I":
				opcaoInserirCargo();
			case "L":
				opcaoListarCargo(); 
			case "X":
				opcaoApagarCargo();
			case "V":
				menuInicial();
			default:
				System.out.println("\nDigite uma opcao correta!");
				System.out.print("Opcao:");
				opcao = ler.nextLine();
			}
		}while ((opcao != "A") && (opcao != "I") && (opcao != "L") && (opcao != "X") && (opcao != "V"));
		
		ler.close();
	}
	public static void opcaoInserirCargo(){

		Scanner ler1 = new Scanner(System.in);

		Cargo cargo = new Cargo();
		CargoDAO cargoDAO = new CargoDAO();
				
		System.out.println("\n===============================");
		System.out.println("NOVO CARGO");
		System.out.println("===============================");


		System.out.print("\nNome do cargo: ");
		cargo.setCargo(ler1.nextLine());
		
		cargoDAO.save(cargo);
		
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler1.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuCargo();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler1.nextLine();
			}
		}while (opcao != "V");
	
		ler1.close();

	}	
	public static void opcaoListarCargo(){
		Scanner ler = new Scanner(System.in);
	
		System.out.println("\n===============================");
		System.out.println("SUPER SISFARMA PREMIUM 2022");
		System.out.println("===============================");
		System.out.println("\nLISTAGEM DE CARGOS\n");
		System.out.println("ID - Nome");
		
		CargoDAO cargosList = new CargoDAO();
		
		for(Cargo m: cargosList.getCargos()) {
			System.out.println(m.getCargo_id() + " - " + m.getCargo());
		}
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuCargo();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler.nextLine();
			}
		}while (opcao != "V");
		ler.close();
	}
	public static void opcaoAtualizarCargo(){
		Scanner ler1 = new Scanner(System.in);
		Scanner ler2 = new Scanner(System.in);
		
		System.out.println("===============================");
		System.out.println("ATUALIZACAO CARGO");
		System.out.println("===============================");
		System.out.print("Digite o ID do cargo que deseja atualizar: ");
		int id = ler2.nextInt();
		
		System.out.println("\n===============================");
		System.out.println("ATUALIZACAO CARGO- ID: "+ id);

		Cargo cargo = new Cargo();
		CargoDAO cargoDAO = new CargoDAO();
				
		System.out.print("\nNome: ");
		cargo.setCargo(ler1.nextLine());
		
		cargoDAO.update(cargo);
		
		System.out.println("\nCARGO ATUALIZADO!");
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler1.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuCargo();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler1.nextLine();
			}
		}while (opcao != "V");
		ler1.close();
		ler2.close();
	}
	public static void opcaoApagarCargo() {
		Scanner ler = new Scanner(System.in);
		
		CargoDAO cargoDAO = new CargoDAO();
		
		System.out.println("\nAPAGAR CARGO");
		System.out.print("\nDigite o ID do cargo: ");
		int id = ler.nextInt();
		
		cargoDAO.delete(id);
		
		System.out.println("\nCARGO APAGADO!");
		
		System.out.print("\n(V) Voltar: ");
		String opcao = ler.nextLine();
		do {
			switch(opcao.toUpperCase()){
				case "V":
					menuCargo();
				default:
					System.out.print("\n(V) Voltar: ");
					opcao = ler.nextLine();
			}
		}while (opcao != "V");
		ler.close();
}
