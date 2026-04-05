package src;
import java.util.List;

public class AlgoritmoQuickSortStrategy implements IAlgoritmoStrategy {

    @Override
    public List<Produto> ordenar(List<Produto> produtos, IComparadorProduto comparador, int ini, int fim) {

        if (ini < fim) {

            int q = particionar(produtos, comparador, ini, fim);

            ordenar(produtos, comparador, ini, q);
            ordenar(produtos, comparador, q + 1, fim);
        }

        return produtos;
    }

    public int particionar(List<Produto> produtos, IComparadorProduto comparador, int ini, int fim) {
        Produto x = produtos.get(ini);

        int i = (ini - 1);
        int j = (fim + 1);

        while (true) {

            do {
                j--;

            } while (comparador.comparar(x, produtos.get(j)));

            do {
                i++;

            } while (comparador.comparar(produtos.get(i), x));

            if (i < j) {
                Produto temp = produtos.get(i);
                produtos.set(i, produtos.get(j));
                produtos.set(j, temp);
            } else
                return j;
        }
    }

}
