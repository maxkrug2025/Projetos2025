import java.util.Scanner;

public class sistemaBanco {
    static Scanner scanner = new Scanner(System.in);
    // dados da conta
    static boolean contaAberta = false; // controla se a conta já está aberta
    static String nomeCliente = null;
    static double saldoInicial = 0.0;
    static double saldoAtual = 0.0;

    // "mudanças" no financeiro na conta
    static int quantidadeDepositos = 0;
    static double valorTotalDepositos = 0.0;
    static int quantidadeSaques = 0;
    static double valorTotalSaques = 0.0;
    static double valorTotalJuros = 0.0;
    static double saldoMinimo = 0.0;
    static double saldoMaximo = 0.0;

    public static void main(String[] args) {
        int escolha = 0;

        // loop do menu e saida do sistema
        while (escolha != 9) {
            exibirMenu();

            escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    if (contaAberta) {
                        System.out.println("Você já possui uma conta aberta");
                        ;
                    } else {
                        abrirConta();
                    }
                    break;
                case 2:
                    if (contaAberta) {
                        realizarDeposito();
                    } else {
                        System.out.println(" É necessário ter uma conta aberta para realizar depósitos.");
                    }
                    break;
                case 3:
                    if (contaAberta) {
                        realizarSaque();
                    } else {
                        System.out.println(" É necessário ter uma conta aberta para realizar saques.");
                    }
                    break;
                case 4:
                    if (contaAberta) {
                        aplicarJuros();
                    } else {
                        System.out.println(" É necessário ter uma conta aberta para aplicar juros.");
                    }
                    break;
                case 5:
                    if (contaAberta) {
                        simularEmprestimo();
                    } else {
                        System.out.println(" É necessário ter uma conta aberta para simular empréstimo.");
                    }
                    break;
                case 6:
                    if (contaAberta) {
                        mostrarExtrato();
                        ;
                    } else {
                        System.out.println(" É necessário ter uma conta aberta para ter um extrato");
                    }

                    break;
                case 7:
                    System.out.println("-----Integrantes do grupo-------");
                    System.out.println("Guilherme Larssen Barbosa");
                    System.out.println("Maximiliano Krug Fagundes");
                    System.out.println("Matheus Gomes Medeiros");
                    System.out.println("Felipe Cemin");
                    System.out.println("João Antônio Tietbohl Schmidt");
                    System.out.println("-------------------------");
                    break;
                case 8:
                    if (contaAberta) {
                        excluirConta();
                    } else {
                        System.out.println(" Não há uma conta aberta para ser excluida.");
                    }
                    break;
                case 9:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

            // deixar o menu mais bonitinho
            if (escolha != 9) {
                System.out.println("\nPressione ENTER para continuar");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    // MENU
    public static void exibirMenu() {
        System.out.println("\n***********************************");
        System.out.println("      Banco de Pindamoiangaba");
        System.out.println("***********************************");
        System.out.println("1: Abrir Conta" + (contaAberta ? " (Sua conta já está aberta)" : ""));
        System.out.println("2: Realizar Depósito" + (!contaAberta ? " (Requer conta aberta)" : ""));
        System.out.println("3: Realizar saque" + (!contaAberta ? " (Requer conta aberta)" : ""));
        System.out.println("4: Aplicar Juros" + (!contaAberta ? " (Requer conta aberta)" : ""));
        System.out.println("5: Simular empréstimo" + (!contaAberta ? " (Requer conta aberta)" : ""));
        System.out.println("6: Extrato" + (!contaAberta ? " (Requer conta aberta)" : ""));
        System.out.println("7: Integrantes");
        System.out.println("8: Excluir conta" + (!contaAberta ? " (Requer conta aberta)" : ""));
        System.out.println("9: Sair");
        System.out.print("Escolha uma opção (1 a 9): ");
    }

    // ABRIR CONTA
    public static void abrirConta() {
        System.out.println("\n--- Abrindo Nova Conta ---");

        System.out.print("Digite o seu nome completo: ");
        nomeCliente = scanner.nextLine();

        // depósito inicial
        System.out.print("Qual o valor do depósito inicial? R$ ");
        saldoInicial = scanner.nextDouble();
        scanner.nextLine();
     // validação valor acima de 0
        if (saldoInicial <= 0) {
            System.out.println(" O depósito inicial não pode ser negativo ou 0 . Tente novamente.");
            return;
        }
//atualizando dados
        saldoAtual = saldoInicial;
        saldoMinimo = saldoInicial;
        saldoMaximo = saldoInicial;
        contaAberta = true;

        System.out.printf("Conta aberta com sucesso para %s com saldo inicial de R$ %.2f\n", nomeCliente, saldoInicial);

    }

    // DEPOSITO
    public static void realizarDeposito() {
        //realiza o depósito
        System.out.print("Qual é valor do depósito? R$ ");
        double deposito = scanner.nextDouble();
        scanner.nextLine(); 
             // validação valor acima de 0
        if (deposito <= 0) { 
            System.out.println(" O depósito tem que ser maior do que zero. Tente novamente.");
            return;
        }
//atualiza os dados bancarios
        quantidadeDepositos++;
        valorTotalDepositos += deposito;
        saldoAtual += deposito;
        // atualiza saldo maximo
        if (saldoAtual > saldoMaximo) {
            saldoMaximo = saldoAtual;
        }
        System.out.printf("Depósito de R$ %.2f realizado com sucesso.\n", deposito);
        System.out.printf("Seu saldo atual é R$ %.2f\n", saldoAtual);
    }

    // SAQUE
    public static void realizarSaque() {
        System.out.printf("Qual é valor do saque (Saldo atual da conta: R$ %.2f)? R$ ", saldoAtual);
        double saque = scanner.nextDouble();
        scanner.nextLine();

        // validação valor acima de 0
        if (saque <= 0) { 
            System.out.println(" O valor do saque deve ser maior que zero. Tente novamente.");
            return;
        }

        // valida se o saldo é suficiente
        if (saque > saldoAtual) {
            System.out.println(" Saldo insuficiente para realizar o saque de R$ " + saque
                    + ". Seu saldo atual é de apenas R$ " + saldoAtual);
            return;
        }
        double verNota = saque;
        int notas100 = 0;
        int notas50 = 0;
        int notas20 = 0;
        int notas10 = 0;
        int notas5 = 0;
        int notas2 = 0;

        /*
         * CALCULADOR NOTAS (verificando 100 como exemplo)
         * calcula se o valor do saque é maior que 100. Se não for, vai para o proximo.
         * Se for, divide o valor do saque por 100 e armazena a quantidade de notas de
         * 100 na variável notas100 (já que não pode dar numero quebrado nisso, forçamos
         * uma int). Depois subtrai o valor das notas de 100 do valor do verNota, para
         * que na próxima ele verifique se o restante do saque é maior que 50, e assim
         * por diante.Se o valor passar por todas as verificações e ainda sobrar
         * centavos ou 1 real, da erro.
         */
        if (verNota >= 100) {
            notas100 = (int) (verNota / 100);
            verNota = verNota - (notas100 * 100);
        }

        if (verNota >= 50) {
            notas50 = (int) (verNota / 50);
            verNota = verNota - (notas50 * 50);
        }

        if (verNota >= 20) {
            notas20 = (int) (verNota / 20);
            verNota = verNota - (notas20 * 20);
        }

        if (verNota >= 10) {
            notas10 = (int) (verNota / 10);
            verNota = verNota - (notas10 * 10);
        }

        if (verNota >= 5) {
            notas5 = (int) (verNota / 5);
            verNota = verNota - (notas5 * 5);
        }

        if (verNota >= 2) {
            notas2 = (int) (verNota / 2);
            verNota = verNota - (notas2 * 2);
        }
        if (verNota > 0) {
            System.out.printf(
                    "Não é possível sacar o valor de R$ %.2f em dinheiro.\n",
                    saque);
            return;
        }

        // passando pelas validações, realiza o saque
        System.out.println("--- Saque Aprovado ---");
        System.out.println("Notas a serem liberadas:");

        // Mostra a quantidade de cada nota
        if (notas100 > 0) {
            System.out.println(notas100 + " x R$ 100.00");
        }
        if (notas50 > 0) {
            System.out.println(notas50 + " x R$ 50.00");
        }
        if (notas20 > 0) {
            System.out.println(notas20 + " x R$ 20.00");
        }
        if (notas10 > 0) {
            System.out.println(notas10 + " x R$ 10.00");
        }
        if (notas5 > 0) {
            System.out.println(notas5 + " x R$ 5.00");
        }
        if (notas2 > 0) {
            System.out.println(notas2 + " x R$ 2.00");
        }

        // Atualiza os dados
        quantidadeSaques++;
        valorTotalSaques += saque;
        saldoAtual -= saque;

        // atualiza o saldo mínimo
        if (saldoAtual < saldoMinimo) {
            saldoMinimo = saldoAtual;
        }

        // Mostra o resultado
        System.out.printf("Saque de R$ %.2f realizado com sucesso.\n", saque);
        System.out.printf("Seu novo saldo é R$ %.2f\n", saldoAtual);
    }

    // APLICAR JUROS
    public static void aplicarJuros() {
        System.out.print("Digite a taxa de juros percentual: ");
        double taxaJuros = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Taxa de juros de " + taxaJuros + "% aplicada.");
// validação valor acima de 0
        if (taxaJuros <= 0) {
            System.out.println(" A taxa de juros deve ser maior que zero. Tente novamente.");
            return;
        }

        double juros = saldoAtual * (taxaJuros / 100);
        saldoAtual += juros;
        valorTotalJuros += juros;
        // atualiza o saldo maximo
        if (saldoAtual > saldoMaximo) {
            saldoMaximo = saldoAtual;
        }

        System.out.printf("Juros de R$ %.2f aplicados com sucesso.\n", juros);
        System.out.printf("Seu novo saldo é de R$ %.2f\n", saldoAtual);
    }

    // Empréstimo
    public static void simularEmprestimo() {
        System.out.print("Qual é o valor do empréstimo? R$ ");
        double valorEmprestimo = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Qual é a taxa de juros mensal percentual? ");
        double taxaJurosE = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Quantas parcelas até o pagamento total? ");
        int quantParcelas = scanner.nextInt();
        scanner.nextLine();

        if (valorEmprestimo <= 0 || taxaJurosE <= 0 || quantParcelas <= 0) {
            System.out.println(
                    " O valor do empréstimo, taxa de juros ou quantidade de parcelas deve ser maior que zero. Tente novamente.");
            return;
        }
        // juros Simples
        double jurosTotal = valorEmprestimo * (taxaJurosE / 100.0) * quantParcelas;
        //valor total pago
        double valorTotalPago = valorEmprestimo + jurosTotal;
        // valor de cada parcela
        double valorParcela = valorTotalPago / quantParcelas;
        System.out.println("\n--- Resultado da Simulação ---");
        System.out.printf("Valor de cada parcela: R$ %.2f\n", valorParcela);
        System.out.printf("Quantidade total de juros a ser pago: R$ %.2f\n", jurosTotal);
        System.out.printf("Valor total a ser pago (principal + juros): R$ %.2f\n", valorTotalPago);

    }

    // EXTRATO
    public static void mostrarExtrato() {
        System.out.println("\n--- Extrato da Conta ---");
        System.out.println("Cliente: " + nomeCliente);
        System.out.printf("Saldo Inicial: R$ %.2f%n", saldoInicial);
        System.out.printf("Saldo Atual: R$ %.2f%n", saldoAtual);
        System.out.printf("Quantidade de Depósitos: %d%n", quantidadeDepositos);
        System.out.printf("Valor Total de Depósitos: R$ %.2f%n", valorTotalDepositos);
        System.out.printf("Quantidade de Saques: %d%n", quantidadeSaques);
        System.out.printf("Valor Total de Saques: R$ %.2f%n", valorTotalSaques);
        System.out.printf("Valor Total de juros recebidos: R$ %.2f%n", valorTotalJuros);
        System.out.printf("Saldo Mínimo: R$ %.2f%n", saldoMinimo);
        System.out.printf("Saldo Máximo: R$ %.2f%n", saldoMaximo);
        System.out.println("------------------------");
    }

    public static void excluirConta() {
        //confirmação para excluir a conta
        System.out.println("Você realmente deseja excluir sua conta? (s/n)");
        String resposta = scanner.nextLine().toLowerCase();

        if (resposta.equals("s") || resposta.equals("S")) {
            // apaga os dados da conta e muda contaAberta para false
            contaAberta = false;
            nomeCliente = null;
            saldoInicial = 0.0;
            saldoAtual = 0.0;
            quantidadeDepositos = 0;
            valorTotalDepositos = 0.0;
            quantidadeSaques = 0;
            valorTotalSaques = 0.0;
            valorTotalJuros = 0.0;
            saldoMinimo = 0.0;
            saldoMaximo = 0.0;

            System.out.println("Conta excluída com sucesso.");
        } else {
            System.out.println("Exclusão de conta cancelada.");
        }

    }
}