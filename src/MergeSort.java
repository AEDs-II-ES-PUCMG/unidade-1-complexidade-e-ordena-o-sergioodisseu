public class MergeSort <T extends Comparable<T>> implements IOrdenador<T> {
    private int comparacoes;
    private int movimentacoes;
    private double tempoOrdenacao;
    private double inicio;

    private double nanoToMilli = 1.0/1_000_000;

    @Override
    public int getComparacoes() {
        return comparacoes;
    }

    @Override
    public int getMovimentacoes() {
        return movimentacoes;
    }

    @Override
    public double getTempoOrdenacao() {
        return tempoOrdenacao;
    }

    private void iniciar(){
        this.comparacoes = 0;
        this.movimentacoes = 0;
        this.inicio = System.nanoTime();
    }

    private void terminar(){
        this.tempoOrdenacao = (System.nanoTime() - this.inicio) * nanoToMilli;
    }

    private void swap(int x, int y, T[] vetor) {
        T temp = vetor[x];
        vetor[x] = vetor[y];
        vetor[y] = temp;
        movimentacoes+=3;
    }

    private T[] mergeSort(T[] vetor){
        if(vetor.length <= 1) return vetor;

        int meio = vetor.length / 2;
        T[] esquerda = Array.coopyOfRange(vetor, 0, meio);
        T[] direita  = Array.coopyOfRange(vetor, meio, vetor.length);

        esquerda = mergeSort(esquerda);
        direita = mergeSort(direita);

        T[] resultado = Array.copyOf(vetor, vetor.length);

        int i = 0; 
        int j = 0;
        int k = 0;

        while(i < esquerda.length && j < direita.length ){
            comparacoes++;
            if(esquerda[i].compareTo(direita[j]) <= 0){
                resultado[k] = esquerda[i];
                i++;
            }else{
                resultado[k] = direita[j];
                j++;
            }
            movimentacoes++;
            k++;
        }
        while(i < esquerda.length){
            resultado[k] = esquerda[i];
            movimentacoes++;
            i++;
            k++;
        }
        while(j < direita.length){
            resultado[k] = direita[j];
            movimentacoes++;
            j++;
            k++;
        }
        return resultado;
    }

    @Override
    public T[] ordenar(T[] dados){
        T[] dadosOrdenados = Array.copyOf(dados, dados.length);
        iniciar();
        dadosOrdenados = mergeSort(dadosOrdenados);
        terminar();
        return dadosOrdenados;
    }

}
