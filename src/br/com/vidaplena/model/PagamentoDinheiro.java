package br.com.vidaplena.model;

public class PagamentoDinheiro extends Pagamento {

    public PagamentoDinheiro(double valorNominal) {
        super(valorNominal);
    }

    @Override
    public double calcularTotal() {
        return this.valorNominal * 0.9;
    }
}