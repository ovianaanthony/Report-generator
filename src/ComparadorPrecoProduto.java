package src;
public class ComparadorPrecoProduto implements IComparadorProduto {

    @Override
    public Boolean comparar(Produto p1, Produto p2) {
        return p1.getPreco() < p2.getPreco();
    }

}