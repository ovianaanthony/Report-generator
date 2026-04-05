package src;
import java.util.Arrays;
import java.util.List;

public class FiltroIntervaloPrecoStrategy implements IFiltroStrategy {

    @Override
    public Boolean aplicarFiltro(Produto produto, String argFiltro) {
        List<String> precos = Arrays.asList(argFiltro.split("-"));
        double precoMin = Double.parseDouble(precos.get(0));
        double precoMax = Double.parseDouble(precos.get(1));

        if (produto.getPreco() >= precoMin && produto.getPreco() <= precoMax) {
            return true;
        }

        return false;
    }

}
