package src;
import java.util.List;

public interface IAlgoritmoStrategy {
    public List<Produto> ordenar(List<Produto> produtos, IComparadorProduto comparador, int ini, int fim);
}
