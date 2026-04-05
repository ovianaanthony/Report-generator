package src;
public class ComparadorEstoqueProduto implements IComparadorProduto {

    @Override
    public Boolean comparar(Produto p1, Produto p2) {
        return p1.getQtdEstoque() < p2.getQtdEstoque();
    }
}