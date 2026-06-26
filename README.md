Markdown
# 🏥 VidaPlena - Sistema de Gestão de Clínica Multidisciplinar
> **Componente Curricular:** Programação Orientada a Objetos (POO)  
> **Etapa de Avaliação:** AV2 — Evolução e Refatoração de Software  
> **Status do Projeto:** Compilável, Executável e Validado 
---

## 📝 1. Descrição do Sistema e Funcionalidades

O **VidaPlena** é um ecossistema de software desenvolvido em Java focado na gestão operacional e financeira de uma clínica de saúde integrada. O projeto representa a evolução incremental do código construído na AV1, agora totalmente refatorado sob os pilares da Programação Orientada a Objetos Avançada, eliminando estruturas estáticas e arrays fixos em prol de uma arquitetura resiliente, escalável e polimórfica.

### 🚀 Funcionalidades Principais Desenvolvidas (AV2):
1. **Controle Automatizado de Fluxo de Caixa por Pagamentos Polimórficos:** Processamento dinâmico de valores de consulta aplicando regras de desconto para espécie (Dinheiro), taxas operacionais fixas para meios eletrônicos (Cartão) ou isenção imediata de coparticipação (Convênio).
2. **Garantia de Unicidade Cadastral (Jornada 28):** Validação em tempo real contra duplicidade de CPFs na base através de estruturas de dispersão de alta performance.
3. **Blindagem e Resiliência contra Falhas (Jornada 30):** Mecanismo centralizado de captura de erros operacionais e de negócio através de uma hierarquia de exceções customizadas, prevenindo o colapso (*crash*) do terminal.
4. **Emissão de Relatório Unificado de Entidades:** Consolidação polimórfica e varredura segura de memória para exibição agregada de dados de profissionais e pacientes.

---

## 🛠️ 2. Orientações para Compilação e Execução

O projeto foi construído utilizando exclusivamente os pacotes nativos `java.util` e `java.lang`, cumprindo a restrição de **não utilizar frameworks externos**.

Para compilar e executar o sistema diretamente através do terminal do seu sistema operacional, certifique-se de que possui o **JDK 11 ou superior** instalado e configurado nas variáveis de ambiente.

Execute a sequência de comandos abaixo a partir da **raiz do projeto** (onde a pasta `src` está localizada):

```bash
# 1. Criação do diretório de binários e compilação de todas as camadas do sistema
javac -d bin src/br/com/vidaplena/interfaces/*.java src/br/com/vidaplena/exception/*.java src/br/com/vidaplena/model/*.java src/br/com/vidaplena/service/*.java src/br/com/vidaplena/main/*.java

# 2. Execução do ponto de entrada principal (Main Bootstrapper)
java -cp bin br.com.vidaplena.main.Main
🕹️ 3. Guia Operacional: Como Realizar as Operações
Ao iniciar o sistema, uma Carga Inicial de Dados Automática (Seed) é injetada na memória para viabilizar testes imediatos pelo avaliador, contendo:

Profissional cadastrado: Dr. Carlos Silva | Registro: CREFITO-123 | Valor base: R$ 150.00

Paciente cadastrado: Ana Maria | CPF: 123.456.789-00 | Status: Ativo

Passos de Teste Sugeridos para Validação das Regras:
Operação 1 — Cadastro de Paciente e Teste de Duplicidade (Jornada 28):
Tente cadastrar um novo paciente utilizando o CPF 123.456.789-00. O sistema interceptará a ação imediatamente, exibindo a mensagem tratada da exceção customizada CpfDuplicadoException e retornando com segurança ao menu.

Operação 2 — Agendamento de Consulta e Cálculo Polimórfico:
Selecione a opção 2. Insira o CPF da paciente padrão (123.456.789-00) e o Registro do profissional (CREFITO-123). O sistema solicitará a forma de pagamento. Escolha 1 (Dinheiro) para testar o desconto ou 2 (Cartão) para testar o acréscimo da taxa.

Operação 3 — Relatório Unificado (Polimorfismo e Downcasting):
Selecione a opção 3 para visualizar uma única lista agregadora (List<Pessoa>) imprimindo dinamicamente os dados específicos de médicos e pacientes através de ligação dinâmica e checagem via instanceof.

Operação 4 — Relatório Financeiro:
Visualize o faturamento consolidado da clínica com o valor líquido exato de cada transação processada pelas regras de pagamento.

🗺️ 4. Mapa de Aplicação dos Conceitos de POO
Diretriz / Conceito	Arquivo de Implementação no Projeto	Descrição e Justificativa Pedagógica
Encapsulamento Restritivo (R1)	Pessoa.java	Atributos definidos com visibilidade protegida/privada. O método setCpf(String cpf) atua como guardião invariante, impedindo estados nulos ou vazios no domínio.
Atributos Protegidos (R2)	Pessoa.java e Profissional.java	Uso estratégico do modificador protected nos atributos como nome e registroProfissional, garantindo visibilidade direta e otimização de acesso pelas classes derivadas sem expor os dados publicamente.
Hierarquia de Herança Tri-Nível (R3)	Pessoa → Profissional → Fisioterapeuta	Cumprimento estrito da profundidade de herança mínima exigida. Reuso de comportamento estrutural e semântico vertical absoluto.
Sobrescrita & Ligação Dinâmica (R4)	Paciente.java e Fisioterapeuta.java	O método exibirResumo() foi submetido à anotação @Override. A resolução de qual método executar ocorre em tempo de execução (runtime), baseada no objeto real instanciado.
Classes Abstratas de Molde (R6)	Pessoa.java, Profissional.java, Pagamento.java	Classes marcadas como abstract. Elas impedem a instanciação direta via operador new, funcionando puramente como contratos de generalização e moldes conceituais.
Interfaces Polimórficas (R7)	Agendavel.java e Exportavel.java	Contratos puros aplicados a classes semanticamente distintas. Consulta assina Agendavel, enquanto Pessoa estabelece o comportamento para Exportavel.
Arquitetura de Exceções Robustas (R9)	Pasta br.com.vidaplena.exception/	Divisão clara entre Exceções Verificadas (CpfDuplicadoException, PacienteInativoException que estendem Exception e exigem tratamento obrigatório em tempo de compilação) e Exceções Não-Verificadas (ValorConsultaInvalidoException que herda de RuntimeException).
Java Collections Framework (R10)	ClinicaServico.java	
Substituição completa de arrays fixos:


• List (ArrayList): Mantém o histórico cronológico e ordem de inserção das consultas.


• Set (HashSet): Otimiza a busca e validação de unicidade de CPF com complexidade O(1) através de tabela de espalhamento.


• Map (HashMap): Indexação de alta performance mapeando Chave (CPF) -> Valor (Objeto Paciente).

Polimorfismo Coercitivo e Casting (R14)	ClinicaServico.java (método exibirRelatorioGeral)	Varredura de múltiplos objetos distintos sob a referência abstrata de List<Pessoa>. Uso do operador unário instanceof para validação de tipo em tempo de execução antes de realizar o Downcasting seguro dos dados do Paciente.
📊 5. Diagrama de Classes (Abstração Arquitetural)
O fluxo estrutural de dependências, heranças e contratos de interfaces do sistema está representado através do mapeamento abaixo:

Snippet de código
classDiagram
    class Exportavel {
        <<interface>>
        +exportarDados() String
    }

    class Agendavel {
        <<interface>>
        +agendar() void
        +cancelar() void
        +remarcar() void
    }

    class Pessoa {
        <<abstract>>
        #String nome
        #String cpf
        #String telefone
        #String dataNascimento
        +exibirResumo()* void
    }

    class Paciente {
        -boolean ativo
        -String tipoCadastro
        +exibirResumo() void
        +exportarDados() String
    }

    class Profissional {
        <<abstract>>
        #String registroProfissional
        #double valorConsulta
    }

    class Fisioterapeuta {
        -int sessoesPrevistas
        +exibirResumo() void
        +exportarDados() String
    }

    class Pagamento {
        <<abstract>>
        #double valorNominal
        +calcularTotal()* double
    }

    class PagamentoDinheiro {
        +calcularTotal() double
    }
    class PagamentoCartao {
        +calcularTotal() double
    }
    class PagamentoConvenio {
        +calcularTotal() double
    }

    class Consulta {
        -Paciente paciente
        -Profissional profissional
        -String dataHora
        -Pagamento pagamento
        -String status
    }

    Exportavel <|.. Pessoa
    Pessoa <|-- Paciente
    Pessoa <|-- Profissional
    Profissional <|-- Fisioterapeuta
    
    Agendavel <|.. Consulta
    
    Pagamento <|-- PagamentoDinheiro
    Pagamento <|-- PagamentoCartao
    Pagamento <|-- PagamentoConvenio
    
    Consulta --> Paciente : Contem
    Consulta --> Profissional : Contem
    Consulta --> Pagamento : Associa
