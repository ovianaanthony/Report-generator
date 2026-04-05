package src;
public class FiltroEstoqueMenorOuIgualStrategy implements IFiltroStrategy {

    @Override
    public Boolean aplicarFiltro(Produto produto, String argFiltro) {
        if (produto.getQtdEstoque() <= Integer.parseInt(argFiltro)) {
            return true;
        }

        return false;
    }

}
