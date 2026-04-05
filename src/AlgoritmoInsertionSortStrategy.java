package src;
import java.util.List;

public class AlgoritmoInsertionSortStrategy implements IAlgoritmoStrategy {

    @Override
    public List<Produto> ordenar(List<Produto> produtos, IComparadorProduto comparador, int ini, int fim) {

        for (int i = ini; i <= fim; i++) {

            Produto x = produtos.get(i);
            int j = (i - 1);

            while (j >= ini) {

                if (comparador.comparar(x, produtos.get(j))) {
                    produtos.set(j + 1, produtos.get(j));
                    j--;
                } else
                    break;

            }

            produtos.set(j + 1, x);
        }

        return produtos;
    }
}
