package br.com.vidaplena.model;

import br.com.vidaplena.interfaces.Agendavel;

public class Consulta implements Agendavel {
    private Paciente paciente;
    private Profissional profissional;
    private String dataHora;
    private Pagamento pagamento;
    private String status; 

    public Consulta(Paciente paciente, Profissional profissional, String dataHora, Pagamento pagamento) {
        this.paciente = paciente;
        this.profissional = profissional;
        this.dataHora = dataHora;
        this.pagamento = pagamento;
        this.status = "AGENDADA";
    }

    @Override
    public void agendar() throws Exception {
        if (!paciente.isAtivo()) {
            throw new br.com.vidaplena.exception.PacienteInativoException("Não é possível agendar consulta para paciente inativo.");
        }
        this.status = "AGENDADA";
    }

    @Override
    public void cancelar() throws Exception {
        this.status = "CANCELADA";
    }

    @Override
    public void remarcar() throws Exception {
        this.status = "REMARCADA";
    }

    // Getters e Setters
    public Paciente getPaciente() { return paciente; }
    public Profissional getProfissional() { return profissional; }
    public String getDataHora() { return dataHora; }
    public Pagamento getPagamento() { return pagamento; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}