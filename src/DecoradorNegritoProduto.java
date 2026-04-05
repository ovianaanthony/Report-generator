package src;
public class DecoradorNegritoProduto extends DecoradorProduto {

    public DecoradorNegritoProduto(Produto produto) {
        super(produto);
    }

    // metodo que devolve uma String que representa o produto, a ser usada na
    // geração dos relatorios.

    public String formataParaImpressao() {

        return "<span style=\"font-weight:bold\">" + produto.formataParaImpressao() + "</span>";
    }

}