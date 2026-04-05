package src;
public class FiltroCategoriaIgualStrategy implements IFiltroStrategy {

    @Override
    public Boolean aplicarFiltro(Produto produto, String argFiltro) {
        if (produto.getCategoria().equalsIgnoreCase(argFiltro)) {
            return true;
        }

        return false;
    }

}
