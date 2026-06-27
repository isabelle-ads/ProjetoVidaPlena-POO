package br.com.vidaplena.service;

import br.com.vidaplena.exception.CpfDuplicadoException;
import br.com.vidaplena.exception.PacienteInativoException;
import br.com.vidaplena.model.Consulta;
import br.com.vidaplena.model.Paciente;
import br.com.vidaplena.model.Profissional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClinicaServico {

    private List<Consulta> consultas = new ArrayList<>();

    private Set<String> cpfsCadastrados = new HashSet<>();

    private Map<String, Paciente> mapaPacientes = new HashMap<>();
    
    private List<Profissional> profissionais = new ArrayList<>();

    public void cadastrarPaciente(Paciente paciente) throws CpfDuplicadoException {
    
        if (cpfsCadastrados.contains(paciente.getCpf())) {
            throw new CpfDuplicadoException("Erro: Já existe um paciente cadastrado com o CPF: " + paciente.getCpf());
        }
        
        mapaPacientes.put(paciente.getCpf(), paciente);
        System.out.println("Paciente " + paciente.getNome() + " cadastrado com sucesso!");
    }

    public void cadastrarProfissional(Profissional prof) {
        profissionais.add(prof);
        System.out.println("Profissional " + prof.getNome() + " cadastrado com sucesso!");
    }

    public void agendarConsulta(String cpfPaciente, String registroProfissional, String dataHora, br.com.vidaplena.model.Pagamento pagamento) throws Exception {
    
        Paciente paciente = mapaPacientes.get(cpfPaciente);
        if (paciente == null) {
            throw new IllegalArgumentException("Erro: Paciente não encontrado.");
        }

        if (!paciente.isAtivo()) {
            throw new PacienteInativoException("Erro: Não é possível agendar para um paciente inativo.");
        }

        Profissional profissional = null;
        for (Profissional p : profissionais) {
            if (p.getRegistroProfissional().equals(registroProfissional)) {
                profissional = p;
                break;
            }
        }

        if (profissional == null) {
            throw new IllegalArgumentException("Erro: Profissional não encontrado.");
        }

        Consulta novaConsulta = new Consulta(paciente, profissional, dataHora, pagamento);
        novaConsulta.agendar();
        
        consultas.add(novaConsulta);
        System.out.println("Consulta agendada com sucesso para " + dataHora);
    }

    public void exibirRelatorioGeral() {
        System.out.println("\n--- RELATÓRIO UNIFICADO DA CLÍNICA ---");
        
        List<br.com.vidaplena.model.Pessoa> todasAsPessoas = new ArrayList<>();
        todasAsPessoas.addAll(mapaPacientes.values());
        todasAsPessoas.addAll(profissionais);

        for (br.com.vidaplena.model.Pessoa p : todasAsPessoas) {
            p.exibirResumo();
            
            if (p instanceof Paciente) {
                Paciente pac = (Paciente) p;
                System.out.println(" -> Tipo de Cadastro: " + pac.getTipoCadastro());
            }
        }
    }

    public void exibirRelatorioPagamentos() {
        System.out.println("\n--- RELATÓRIO DE PAGAMENTOS ---");
        double faturamentoTotal = 0;

        for (Consulta c : consultas) {
            double valorFinal = c.getPagamento().calcularTotal();
            faturamentoTotal += valorFinal;
            System.out.println("Paciente: " + c.getPaciente().getNome() + 
                            " | Profissional: " + c.getProfissional().getNome() + 
                            " | Valor Pago: R$ " + valorFinal + 
                            " | Status: " + c.getStatus());
        }
        System.out.println("Faturamento Total Líquido da Clínica: R$ " + faturamentoTotal);
    }


    public List<Profissional> getProfissionais() { return profissionais; }
    public Map<String, Paciente> getMapaPacientes() { return mapaPacientes; }
}