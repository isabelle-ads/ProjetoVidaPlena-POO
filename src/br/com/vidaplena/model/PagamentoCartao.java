package br.com.vidaplena.model;

public class PagamentoCartao extends Pagamento {

    public PagamentoCartao(double valorNominal) {
        super(valorNominal);
    }

    @Override
    public double calcularTotal() {
        return this.valorNominal + 5.0;
    }
}