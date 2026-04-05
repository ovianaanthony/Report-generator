package src;
public class FiltroPadraoStrategy implements IFiltroStrategy {

    public Boolean aplicarFiltro(Produto produto, String argFiltro) {
        return true;
    }
}
