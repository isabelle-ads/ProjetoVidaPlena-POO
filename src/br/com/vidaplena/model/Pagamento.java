package br.com.vidaplena.model;

public abstract class Pagamento {
    protected double valorNominal;

    public Pagamento(double valorNominal) {
        this.valorNominal = valorNominal;
    }

    public abstract double calcularTotal();
    
    public double getValorNominal() { return valorNominal; }
}