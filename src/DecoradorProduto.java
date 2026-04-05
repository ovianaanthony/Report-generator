package src;
public class DecoradorProduto implements Produto {
    protected Produto produto;

    public DecoradorProduto(Produto produto) {
        this.produto = produto;
    }

    // setters
    public void setQtdEstoque(int qtdEstoque) {

        produto.setQtdEstoque(qtdEstoque);
    }

    public void setPreco(double preco) {

        produto.setPreco(preco);
    }

    // getters

    public int getId() {

        return produto.getId();
    }

    public String getDescricao() {

        return produto.getDescricao();
    }

    public String getCategoria() {

        return produto.getCategoria();
    }

    public int getQtdEstoque() {

        return produto.getQtdEstoque();
    }

    public double getPreco() {

        return produto.getPreco();
    }

    // metodo que devolve uma String que representa o produto, a ser usada na
    // geração dos relatorios.

    public String formataParaImpressao() {

        return produto.formataParaImpressao();
    }

}
