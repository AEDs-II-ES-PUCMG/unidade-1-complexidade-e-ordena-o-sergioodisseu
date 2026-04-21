import java.util.Comparator;

/**
 * Critério A - Valor Final do Pedido (crescente).
 * Desempate 1: Volume Total de Itens (quantProdutos).
 * Desempate 2: Código Identificador do primeiro item do pedido.
 */
public class ComparadorCriterioA implements Comparator<Pedido> {

    @Override
    public int compare(Pedido o1, Pedido o2) {
      
        int compValor = Double.compare(o1.getValorFinal(), o2.getValorFinal());
        if (compValor != 0) {
            return compValor;
        }

        int vol1 = o1.getQuantidadeTotal(); 
        int vol2 = o2.getQuantidadeTotal();
        int compVolume = Integer.compare(vol1, vol2);
        if (compVolume != 0) {
            return compVolume;
        }

        int codItem1 = o1.getItens().get(0).getProduto().getCodigo();
        int codItem2 = o2.getItens().get(0).getProduto().getCodigo();
        
        return Integer.compare(codItem1, codItem2);
    }
}