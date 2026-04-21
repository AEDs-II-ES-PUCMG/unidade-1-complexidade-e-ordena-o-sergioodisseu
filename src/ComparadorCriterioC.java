import java.util.Comparator;

/**
 * Critério C - Índice de Economia (decrescente).
 * O índice de economia é a diferença entre o valor de catálogo atual e o valor efetivamente pago.
 * Desempate 1: Valor Final do Pedido (crescente).
 * Desempate 2: Código Identificador do pedido (crescente).
 */
public class ComparadorCriterioC implements Comparator<Pedido> {

    @Override
    public int compare(Pedido o1, Pedido o2) {
        double economia1 = calcularEconomia(o1);
        double economia2 = calcularEconomia(o2);

        int compEco = Double.compare(economia2, economia1);
        if (compEco != 0) {
            return compEco;
        }

    
        int compValor = Double.compare(o1.getValorFinal(), o2.getValorFinal());
        if (compValor != 0) {
            return compValor;
        }

     
        return Integer.compare(o1.getIdPedido(), o2.getIdPedido());
    }

    private double calcularEconomia(Pedido pedido) {
        double valorCatalogoAtual = 0.0;
    
        for (ItemDePedido item : pedido.getItens()) {
        
            double precoAtual = item.getProduto().valorDeVenda();
            valorCatalogoAtual += (precoAtual * item.getQuantidade());
        }
        
       
        return valorCatalogoAtual - pedido.getValorFinal();
    }
}