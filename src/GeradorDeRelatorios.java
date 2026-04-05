package src;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class GeradorDeRelatorios {

	private IAlgoritmoStrategy algoritmoStrategy;
	private IFiltroStrategy filtroStrategy;
	private IComparadorProduto comparadorProduto;
	private List<Produto> produtos;
	private String criterio;
	private String argFiltro;

	public GeradorDeRelatorios(List<Produto> produtos, String criterio,
			String argFiltro, IAlgoritmoStrategy algoritmoStrategy, IFiltroStrategy filtroStrategy,
			IComparadorProduto comparadorProduto) {

		this.produtos = new ArrayList<Produto>();

		for (int i = 0; i < produtos.size(); i++) {

			this.produtos.add(produtos.get(i));
		}

		this.criterio = criterio;
		this.argFiltro = argFiltro;
		this.algoritmoStrategy = algoritmoStrategy;
		this.filtroStrategy = filtroStrategy;
		this.comparadorProduto = comparadorProduto;
	}

	private void ordena(int ini, int fim) {

		produtos = algoritmoStrategy.ordenar(produtos, comparadorProduto, ini, fim);

		if (!criterio.equals(CriterioOrdenacao.DESCRICAO_C.toString().toLowerCase())
				&& !criterio.equals(CriterioOrdenacao.PRECO_C.toString().toLowerCase())
				&& !criterio.equals(CriterioOrdenacao.ESTOQUE_C.toString().toLowerCase())) {
			Collections.reverse(produtos);
		}
	}

	public void debug() {

		System.out.println("Gerando relatório para array contendo " + produtos.size() + " produto(s)");
		System.out.println("parametro filtro = '" + argFiltro + "'");
	}

	public void geraRelatorio(String arquivoSaida) throws IOException {

		debug();

		ordena(0, produtos.size() - 1);

		PrintWriter out = new PrintWriter(arquivoSaida);

		out.println("<!DOCTYPE html><html>");
		out.println("<head><title>Relatorio de produtos</title></head>");
		out.println("<body>");
		out.println("Relatorio de Produtos:");
		out.println("<ul>");

		int countador = 0;

		for (int i = 0; i < produtos.size(); i++) {

			Produto p = produtos.get(i);
			boolean selecionado = false;

			if (filtroStrategy.aplicarFiltro(p, argFiltro)) {
				selecionado = true;
			}

			if (selecionado) {
				out.print("<li>");
				out.print(p.formataParaImpressao());
				out.println("</li>");
				countador++;
			}
		}

		out.println("</ul>");
		out.println(countador + " produtos listados, de um total de " + produtos.size() + ".");
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

	public static List<Produto> carregaArquivoDeProdutos() {

		List<Produto> listaDeProdutos = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("produtos.csv"))) {

			String linha;

			// pula o cabeçalho (opcional)
			br.readLine();

			while ((linha = br.readLine()) != null) {

				String[] partes = linha.split(", ");

				int id = Integer.parseInt(partes[0]) + 32;
				String descricao = partes[1];
				String categoria = partes[2];
				int estoque = Integer.parseInt(partes[3]);
				double preco = Double.parseDouble(partes[4]);
				Boolean negrito = Boolean.parseBoolean(partes[5]);
				Boolean italico = Boolean.parseBoolean(partes[6]);
				String color = partes[7];

				Produto p = new DecoradorColoracaoProduto(new ProdutoPadrao(id, descricao, categoria, estoque, preco),
						color);

				if (negrito && italico) {
					p = new DecoradorNegritoProduto(new DecoradorItalicoProduto(p));
				} else if (negrito) {
					p = new DecoradorNegritoProduto(p);
				} else if (italico) {
					p = new DecoradorItalicoProduto(p);
				}

				listaDeProdutos.add(p);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return listaDeProdutos;
	}

	public static List<Produto> carregaProdutos() {

		List<Produto> listaDeProdutos = new ArrayList<>();

		listaDeProdutos.addAll(carregaArquivoDeProdutos());
		listaDeProdutos.add(new DecoradorNegritoProduto(new ProdutoPadrao(1, "O Hobbit", "Livros", 2, 34.90)));
		listaDeProdutos
				.add(new DecoradorItalicoProduto(new ProdutoPadrao(2, "Notebook Core i7", "Informatica", 5, 1999.90)));
		listaDeProdutos.add(new ProdutoPadrao(3, "Resident Evil 4", "Games", 7, 79.90));
		listaDeProdutos.add(new ProdutoPadrao(4, "iPhone", "Telefonia", 8, 4999.90));
		listaDeProdutos.add(new ProdutoPadrao(5, "Calculo I", "Livros", 20, 55.00));
		listaDeProdutos.add(new ProdutoPadrao(6, "Power Glove", "Games", 3, 499.90));
		listaDeProdutos.add(new ProdutoPadrao(7, "Microsoft HoloLens", "Informatica", 1, 19900.00));
		listaDeProdutos.add(new ProdutoPadrao(8, "OpenGL Programming Guide", "Livros", 4, 89.90));
		listaDeProdutos.add(new DecoradorItalicoProduto(new ProdutoPadrao(9, "Vectrex", "Games", 1, 799.90)));
		listaDeProdutos.add(new ProdutoPadrao(10, "Carregador iPhone", "Telefonia", 15, 499.90));
		listaDeProdutos.add(
				new DecoradorNegritoProduto(new ProdutoPadrao(11, "Introduction to Algorithms", "Livros", 7, 315.00)));
		listaDeProdutos.add(new DecoradorItalicoProduto(
				new DecoradorNegritoProduto(new ProdutoPadrao(12, "Daytona USA (Arcade)", "Games", 1, 12000.00))));
		listaDeProdutos.add(new DecoradorItalicoProduto(
				new DecoradorNegritoProduto(new ProdutoPadrao(13, "Neuromancer", "Livros", 5, 45.00))));
		listaDeProdutos.add(new DecoradorItalicoProduto(
				new DecoradorNegritoProduto(new ProdutoPadrao(14, "Nokia 3100", "Telefonia", 4, 249.99))));
		listaDeProdutos.add(new DecoradorNegritoProduto(new ProdutoPadrao(15, "Oculus Rift", "Games", 1, 3600.00)));
		listaDeProdutos.add(new ProdutoPadrao(16, "Trackball Logitech", "Informatica", 1, 250.00));
		listaDeProdutos.add(new ProdutoPadrao(17, "After Burner II (Arcade)", "Games", 2, 8900.0));
		listaDeProdutos.add(new ProdutoPadrao(18, "Assembly for Dummies", "Livros", 30, 129.90));
		listaDeProdutos.add(new ProdutoPadrao(19, "iPhone (usado)", "Telefonia", 3, 3999.90));
		listaDeProdutos.add(new ProdutoPadrao(20, "Game Programming Patterns", "Livros", 1, 299.90));
		listaDeProdutos.add(new ProdutoPadrao(21, "Playstation 2", "Games", 10, 499.90));
		listaDeProdutos
				.add(new DecoradorItalicoProduto(new ProdutoPadrao(22, "Carregador Nokia", "Telefonia", 14, 89.00)));
		listaDeProdutos.add(new DecoradorItalicoProduto(
				new ProdutoPadrao(23, "Placa Aceleradora Voodoo 2", "Informatica", 4, 189.00)));
		listaDeProdutos.add(new DecoradorItalicoProduto(new ProdutoPadrao(24, "Stunts", "Games", 3, 19.90)));
		listaDeProdutos
				.add(new DecoradorItalicoProduto(new ProdutoPadrao(25, "Carregador Generico", "Telefonia", 9, 30.00)));
		listaDeProdutos.add(new ProdutoPadrao(26, "Monitor VGA 14 polegadas", "Informatica", 2, 199.90));
		listaDeProdutos.add(new ProdutoPadrao(27, "Nokia N-Gage", "Telefonia", 9, 699.00));
		listaDeProdutos
				.add(new ProdutoPadrao(28, "Disquetes Maxell 5.25 polegadas (caixa com 10 unidades)", "Informatica", 23,
						49.00));
		listaDeProdutos.add(new ProdutoPadrao(29, "Alone in The Dark", "Games", 11, 59.00));
		listaDeProdutos.add(new ProdutoPadrao(30, "The Art of Computer Programming Vol. 1", "Livros", 3, 240.00));
		listaDeProdutos.add(new ProdutoPadrao(31, "The Art of Computer Programming Vol. 2", "Livros", 2, 200.00));
		listaDeProdutos.add(new ProdutoPadrao(32, "The Art of Computer Programming Vol. 3", "Livros", 4, 270.00));

		return listaDeProdutos;
	}

	public static void main(String[] args) {

		if (args.length < 4) {

			System.out.println("Uso:");
			System.out.println("\tjava " + GeradorDeRelatorios.class.getName()
					+ " <algoritmo> <critério de ordenação> <critério de filtragem> <parâmetro de filtragem> <opções de formatação>");
			System.out.println("Onde:");
			System.out.println("\talgoritmo: 'quick' ou 'insertion'");
			System.out.println(
					"\tcriterio de ordenação: 'preco_c' ou 'descricao_c' ou 'estoque_c' ou 'preco_d' ou 'descricao_d' ou 'estoque_d'");
			System.out.println(
					"\tcriterio de filtragem: 'todos' ou 'estoque_menor_igual' ou 'categoria_igual' ou 'intervalo_preco' ou 'descricao_contem'");
			System.out.println(
					"\tparâmetro de filtragem: argumentos adicionais necessários para a filtragem (intervalo usar formato x-y)");
			System.out.println();
			System.exit(1);
		}

		String opcao_algoritmo = args[0];
		String opcao_criterio_ord = args[1];
		String opcao_criterio_filtro = args[2];
		String opcao_parametro_filtro = args[3];

		IAlgoritmoStrategy algoritmoStrategy;

		if (opcao_algoritmo.equals(AlgoritmoEnum.INSERTION.toString().toLowerCase())) {
			algoritmoStrategy = new AlgoritmoInsertionSortStrategy();
		} else if (opcao_algoritmo.equals(AlgoritmoEnum.QUICK.toString().toLowerCase())) {
			algoritmoStrategy = new AlgoritmoQuickSortStrategy();
		} else {
			throw new RuntimeException("Algoritmo invalido!");
		}

		IFiltroStrategy filtroStrategy;

		if (opcao_criterio_filtro.equals(CriterioFiltro.TODOS.toString().toLowerCase())) {
			filtroStrategy = new FiltroPadraoStrategy();
		} else if (opcao_criterio_filtro.equals(CriterioFiltro.ESTOQUE_MENOR_OU_IQUAL_A.toString().toLowerCase())) {
			filtroStrategy = new FiltroEstoqueMenorOuIgualStrategy();
		} else if (opcao_criterio_filtro.equals(CriterioFiltro.CATEGORIA_IGUAL_A.toString().toLowerCase())) {
			filtroStrategy = new FiltroCategoriaIgualStrategy();
		} else if (opcao_criterio_filtro.equals(CriterioFiltro.INTERVALO_PRECO.toString().toLowerCase())) {
			filtroStrategy = new FiltroIntervaloPrecoStrategy();
		} else if (opcao_criterio_filtro.equals(CriterioFiltro.DESCRICAO_CONTEM.toString().toLowerCase())) {
			filtroStrategy = new FiltroDescricaoContemStrategy();
		} else {
			throw new RuntimeException("Filtro invalido!");
		}

		IComparadorProduto comparadorProduto = null;

		if (opcao_criterio_ord.equals(CriterioOrdenacao.DESCRICAO_C.toString().toLowerCase())
				|| opcao_criterio_ord.equals(CriterioOrdenacao.DESCRICAO_D.toString().toLowerCase())) {
			comparadorProduto = new ComparadorDescricaoProduto();
		} else if (opcao_criterio_ord.equals(CriterioOrdenacao.PRECO_C.toString().toLowerCase())
				|| opcao_criterio_ord.equals(CriterioOrdenacao.PRECO_D.toString().toLowerCase())) {
			comparadorProduto = new ComparadorPrecoProduto();
		} else if (opcao_criterio_ord.equals(CriterioOrdenacao.ESTOQUE_C.toString().toLowerCase())
				|| opcao_criterio_ord.equals(CriterioOrdenacao.ESTOQUE_D.toString().toLowerCase())) {
			comparadorProduto = new ComparadorEstoqueProduto();
		}

		GeradorDeRelatorios gdr = new GeradorDeRelatorios(carregaProdutos(),
				opcao_criterio_ord,
				opcao_parametro_filtro,
				algoritmoStrategy,
				filtroStrategy,
				comparadorProduto);

		try {
			gdr.geraRelatorio("saida.html");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
