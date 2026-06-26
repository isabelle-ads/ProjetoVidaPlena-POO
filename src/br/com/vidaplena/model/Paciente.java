package br.com.vidaplena.model;

public class Paciente extends Pessoa {
    private boolean ativo;
    private String tipoCadastro; 

    public Paciente(String nome, String cpf, String telefone, String dataNascimento, String tipoCadastro) {
        super(nome, cpf, telefone, dataNascimento);
        this.ativo = true;
        this.tipoCadastro = tipoCadastro;
    }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public String getTipoCadastro() { return tipoCadastro; }

    @Override
    public void exibirResumo() {
        System.out.println("Paciente: " + this.nome + " | CPF: " + this.cpf + " | Status: " + (this.ativo ? "ATIVO" : "INATIVO"));
    }

    @Override
    public String exportarDados() {
        return "PACIENTE;" + this.nome + ";" + this.cpf + ";" + (this.ativo ? "ATIVO" : "INATIVO");
    }
}