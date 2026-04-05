package src;
public class FiltroDescricaoContemStrategy implements IFiltroStrategy {

    @Override
    public Boolean aplicarFiltro(Produto produto, String argFiltro) {
        if (produto.getDescricao().toLowerCase().contains(argFiltro.toLowerCase())) {
            return true;
        }

        return false;
    }

}
