package br.com.vidaplena.model;

public class PagamentoConvenio extends Pagamento {

    public PagamentoConvenio(double valorNominal) {
        super(valorNominal);
    }

    @Override
    public double calcularTotal() {
        return 0.0;
    }
}