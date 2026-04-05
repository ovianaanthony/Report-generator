package src;
public class DecoradorColoracaoProduto extends DecoradorProduto {

    private String color;

    public DecoradorColoracaoProduto(Produto produto, String color) {
        super(produto);
        this.color = color;
    }

    @Override
    public String formataParaImpressao() {
        return "<span style=\"color: " + color + ";\">" + produto.formataParaImpressao() + "</span>";
    }

}
