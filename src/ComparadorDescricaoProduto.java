package src;
public class ComparadorDescricaoProduto implements IComparadorProduto {

    @Override
    public Boolean comparar(Produto p1, Produto p2) {
        return p1.getDescricao().compareToIgnoreCase(p2.getDescricao()) < 0;
    }
}