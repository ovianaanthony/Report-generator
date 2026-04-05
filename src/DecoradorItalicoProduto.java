package src;
public class DecoradorItalicoProduto extends DecoradorProduto {

    public DecoradorItalicoProduto(Produto produto) {
        super(produto);
    }

    // metodo que devolve uma String que representa o produto, a ser usada na
    // geração dos relatorios.

    public String formataParaImpressao() {

        return "<span style=\"font-style:italic\">" + produto.formataParaImpressao() + "</span>";
    }

}
