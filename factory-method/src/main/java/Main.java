public class Main {
    public static void main(String[] args) {
        System.out.println("=== Demonstração do Padrão Factory Method ===\n");
        
        // Exemplo 1: Criando diferentes veículos através de suas fábricas
        System.out.println("--- Exemplo 1: Criando Veículos ---");
        VeiculoFactory vanFactory = new VanFactory();
        VeiculoFactory onibusFactory = new OnibusFactory();
        
        Veiculo van = vanFactory.criarVeiculo();
        Veiculo onibus = onibusFactory.criarVeiculo();
        
        van.exibirDetalhes();
        onibus.exibirDetalhes();
        
        // Exemplo 2: Usando o Gerenciador de Veículos
        System.out.println("\n--- Exemplo 2: Gerenciador de Veículos ---");
        GerenciadorVeiculo gerenciador = new GerenciadorVeiculo();
        
        gerenciador.adicionarVeiculo(new VanFactory());
        gerenciador.adicionarVeiculo(new OnibusFactory());
        gerenciador.adicionarVeiculo(new VanFactory());
        
        gerenciador.listarVeiculos();
        
        // Exemplo 3: Demonstrando a vantagem do padrão
        System.out.println("\n--- Exemplo 3: Flexibilidade do Padrão ---");
        processarVeiculo(new VanFactory());
        processarVeiculo(new OnibusFactory());
        
        // Exemplo 4: Simulação de escolha dinâmica
        System.out.println("\n--- Exemplo 4: Escolha Dinâmica ---");
        String tipoVeiculo = "onibus"; // Poderia vir de input do usuário
        VeiculoFactory factory = escolherFactory(tipoVeiculo);
        Veiculo veiculoEscolhido = factory.criarVeiculo();
        veiculoEscolhido.exibirDetalhes();
    }
    
    // Método que demonstra polimorfismo com Factory Method
    private static void processarVeiculo(VeiculoFactory factory) {
        System.out.println("Processando novo veículo...");
        Veiculo veiculo = factory.criarVeiculo();
        veiculo.exibirDetalhes();
        System.out.println("Veículo processado com sucesso!\n");
    }
    
    // Método que simula escolha dinâmica de fábrica
    private static VeiculoFactory escolherFactory(String tipo) {
        switch (tipo.toLowerCase()) {
            case "van":
                return new VanFactory();
            case "onibus":
                return new OnibusFactory();
            default:
                System.out.println("Tipo desconhecido, usando Van como padrão");
                return new VanFactory();
        }
    }
}
