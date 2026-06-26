package br.com.vidaplena.model;

public class Fisioterapeuta extends Profissional {
    private int sessoesPrevistas;

    public Fisioterapeuta(String nome, String cpf, String telefone, String dataNascimento, 
                          String registroProfissional, double valorConsulta, int sessoesPrevistas) {
       
        super(nome, cpf, telefone, dataNascimento, registroProfissional, valorConsulta);
        this.sessoesPrevistas = sessoesPrevistas;
    }

    public int getSessoesPrevistas() { return sessoesPrevistas; }

    @Override
    public void exibirResumo() {
        System.out.println("Fisioterapeuta: " + this.nome + " | Registro: " + this.registroProfissional + " | Valor: R$ " + this.valorConsulta);
    }

    @Override
    public String exportarDados() {
        return "FISIO;" + this.nome + ";" + this.cpf + ";" + this.registroProfissional;
    }
}