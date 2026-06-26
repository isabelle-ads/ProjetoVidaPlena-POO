package br.com.vidaplena.model;

public abstract class Profissional extends Pessoa {
    protected String registroProfissional;
    protected double valorConsulta;

    public Profissional(String nome, String cpf, String telefone, String dataNascimento, 
                        String registroProfissional, double valorConsulta) {
        super(nome, cpf, telefone, dataNascimento);
        this.registroProfissional = registroProfissional;
        this.valorConsulta = valorConsulta;
    }

    public String getRegistroProfissional() { return registroProfissional; }
    public double getValorConsulta() { return valorConsulta; }
}