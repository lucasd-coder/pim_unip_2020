package com.pim.lucas.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pim.lucas.domain.Categoria;
import com.pim.lucas.domain.Cidade;
import com.pim.lucas.domain.Cliente;
import com.pim.lucas.domain.Conta;
import com.pim.lucas.domain.Endereco;
import com.pim.lucas.domain.Estado;
import com.pim.lucas.domain.ItemPedido;
import com.pim.lucas.domain.Pagamento;
import com.pim.lucas.domain.PagamentoComBoleto;
import com.pim.lucas.domain.PagamentoComCartao;
import com.pim.lucas.domain.Pedido;
import com.pim.lucas.domain.Produto;
import com.pim.lucas.domain.enums.EstadoPagamento;
import com.pim.lucas.domain.enums.Perfil;
import com.pim.lucas.domain.enums.TipoCliente;
import com.pim.lucas.repositories.CategoriaRepository;
import com.pim.lucas.repositories.CidadeRepository;
import com.pim.lucas.repositories.ClienteRepository;
import com.pim.lucas.repositories.ContaRepository;
import com.pim.lucas.repositories.EnderecoRepository;
import com.pim.lucas.repositories.EstadoRepository;
import com.pim.lucas.repositories.ItemPedidoRepository;
import com.pim.lucas.repositories.PagamentoRepository;
import com.pim.lucas.repositories.PedidoRepository;
import com.pim.lucas.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ContaRepository contaRepository;

	
	public void instantiateTestDatabase() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Criptomoedas");
		
		
		Produto p1 = new Produto(null, "Bitcoin", 20.00);
		Produto p2 = new Produto(null, "Ethereum", 800.00);
		Produto p3 = new Produto(null, "Litecoin", 80.00);
		Produto p4 = new Produto(null, "Eletrônicos", 50.00);
		Produto p5 = new Produto(null, "Ripple", 60.00);
		Produto p6 = new Produto(null,"Monero", 70.00);
		Produto p7 = new Produto(null, "Dash", 30.00);
		Produto p8 = new Produto(null, "Chainlink", 70.00);
		Produto p9 = new Produto(null, "yearn.finance", 70.00);
		Produto p10 = new Produto(null, "Litecoin", 70.00);
		Produto p11 = new Produto(null, "Bitcoin Cash", 70.00);
		Produto p12 = new Produto(null, "XRP", 70.00);
		Produto p13 = new Produto(null, "Uniswap Protocol Token", 70.00);
		Produto p14 = new Produto(null, "EOS", 70.00);
		Produto p15 = new Produto(null, "TRON", 70.00);
		Produto p16 = new Produto(null, "Cardano", 70.00);
		Produto p17 = new Produto(null, "Polkadot", 70.00);
		Produto p18 = new Produto(null, "DFI.money", 70.00);
		Produto p19 = new Produto(null, "Bitcoin SV", 70.00);
		Produto p20 = new Produto(null, "Binance Coin", 70.00);
		Produto p21 = new Produto(null, "Tezos", 70.00);
		Produto p22 = new Produto(null, "ZCash", 70.00);
		Produto p23 = new Produto(null, "Ethereum Classic", 60.00);
		Produto p24 = new Produto(null, "Monero", 60.00);
		
		
		
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, 
				p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, 
				p20, p21, p22, p23, p24));
		
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat1));
		p5.getCategorias().addAll(Arrays.asList(cat1));
		p6.getCategorias().addAll(Arrays.asList(cat1));
		p7.getCategorias().addAll(Arrays.asList(cat1));
		p8.getCategorias().addAll(Arrays.asList(cat1));
		p9.getCategorias().addAll(Arrays.asList(cat1));
		p10.getCategorias().addAll(Arrays.asList(cat1));
		p11.getCategorias().addAll(Arrays.asList(cat1));
		p12.getCategorias().addAll(Arrays.asList(cat1));
		p13.getCategorias().addAll(Arrays.asList(cat1));
		p14.getCategorias().addAll(Arrays.asList(cat1));
		p15.getCategorias().addAll(Arrays.asList(cat1));
		p16.getCategorias().addAll(Arrays.asList(cat1));
		p17.getCategorias().addAll(Arrays.asList(cat1));
		p18.getCategorias().addAll(Arrays.asList(cat1));
		p19.getCategorias().addAll(Arrays.asList(cat1));
		p20.getCategorias().addAll(Arrays.asList(cat1));
		p21.getCategorias().addAll(Arrays.asList(cat1));
		p22.getCategorias().addAll(Arrays.asList(cat1));
		p23.getCategorias().addAll(Arrays.asList(cat1));
		p24.getCategorias().addAll(Arrays.asList(cat1));




				
		
		categoriaRepository.saveAll(Arrays.asList(cat1));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, 
				p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		

		
		Cliente cli1 = new Cliente(null, "Maria Silva", "30/09/2017","maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA, pe.encode("123456"));
		Cliente adm = new Cliente(null, "lucas da silva", "06/06/1998", "lucas.silva532@aluno.unip.br", "62303987024", TipoCliente.PESSOAFISICA ,pe.encode("123456"));
		
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		adm.getTelefones().addAll(Arrays.asList("076644323", "5677368393"));
		adm.addPerfil(Perfil.ADMIN);

		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800","Centro", "38777012", cli1, c2);
		
		cli1.getEndereco().addAll(Arrays.asList(e1, e2));
			
		clienteRepository.saveAll(Arrays.asList(cli1, adm));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 200.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		Conta co1 = new Conta( null, "98764554", "Bradesco", "Maria Silva", "36378912377", TipoCliente.PESSOAFISICA, 0.0, cli1);
		contaRepository.saveAll(Arrays.asList(co1));
		
		
		
	
	}

}