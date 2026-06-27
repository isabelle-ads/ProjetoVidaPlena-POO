package br.com.vidaplena.main;

import br.com.vidaplena.model.Fisioterapeuta;
import br.com.vidaplena.model.Paciente;
import br.com.vidaplena.model.Pagamento;
import br.com.vidaplena.model.PagamentoCartao;
import br.com.vidaplena.model.PagamentoConvenio;
import br.com.vidaplena.model.PagamentoDinheiro;
import br.com.vidaplena.service.ClinicaServico;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClinicaServico clinica = new ClinicaServico();
        Scanner scanner = new Scanner(System.in);
        
    

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n========= SISTEMA VIDEPLENA =========");
            System.out.println("1 - Cadastrar Paciente");
            System.out.println("2 - Agendar Consulta");
            System.out.println("3 - Relatório Unificado (Pessoas)");
            System.out.println("4 - Relatório de Faturamento e Pagamentos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                
                switch (opcao) {
                    case 1:
                        System.out.print("Nome do Paciente: ");
                        String nome = scanner.nextLine();
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Telefone: ");
                        String tel = scanner.nextLine();
                        System.out.print("Data de Nascimento: ");
                        String dataNasc = scanner.nextLine();
                        System.out.print("Tipo de Cadastro (MINIMO ou COMPLETO): ");
                        String tipo = scanner.nextLine().toUpperCase();
                        
                        Paciente novoPaciente = new Paciente(nome, cpf, tel, dataNasc, tipo);
                     
                        clinica.cadastrarPaciente(novoPaciente);
                        break;
                        
                    case 2:
                        System.out.print("CPF do Paciente: ");
                        String cpfPac = scanner.nextLine();
                        System.out.print("Registro do Profissional (Ex: CREFITO-123): ");
                        String regProf = scanner.nextLine();
                        System.out.print("Data e Hora (Ex: 28/06 14:00): ");
                        String dataHora = scanner.nextLine();
                        
                        System.out.println("Forma de Pagamento:");
                        System.out.println("1 - Dinheiro (10% Desconto)");
                        System.out.println("2 - Cartão (Taxa R$ 5,00)");
                        System.out.println("3 - Convênio (Cobertura Total)");
                        System.out.print("Opção: ");
                        int opPag = Integer.parseInt(scanner.nextLine());
                        

                        double valorBase = 150.0;
                        for (br.com.vidaplena.model.Profissional p : clinica.getProfissionais()) {
                            if (p.getRegistroProfissional().equals(regProf)) {
                                valorBase = p.getValorConsulta();
                                break;
                            }
                        }
                        
                        Pagamento pagamentoSelected = null;
                        if (opPag == 1) pagamentoSelected = new PagamentoDinheiro(valorBase);
                        else if (opPag == 2) pagamentoSelected = new PagamentoCartao(valorBase);
                        else pagamentoSelected = new PagamentoConvenio(valorBase);
                        
                     
                        clinica.agendarConsulta(cpfPac, regProf, dataHora, pagamentoSelected);
                        break;
                        
                    case 3:
                        clinica.exibirRelatorioGeral();
                        break;
                        
                    case 4:
                        clinica.exibirRelatorioPagamentos();
                        break;
                        
                    case 0:
                        System.out.println("Encerrando o sistema VidaPlena...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, insira um número válido.");
            } catch (br.com.vidaplena.exception.CpfDuplicadoException e) {
                System.out.println(e.getMessage());
            } catch (br.com.vidaplena.exception.PacienteInativoException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void inicializarDados(ClinicaServico clinica) {
        try {

            Fisioterapeuta fisio = new Fisioterapeuta("Dr. Carlos Silva", "111.222.333-44", "9999-8888", "10/05/1985", "CREFITO-123", 150.0, 10);
            clinica.cadastrarProfissional(fisio);

            Paciente pac = new Paciente("Ana Maria", "123.456.789-00", "8888-7777", "20/12/1992", "COMPLETO");
            clinica.cadastrarPaciente(pac);
        } catch (Exception e) {
            System.out.println("Erro na carga inicial.");
        }
    }
}