package com.gof.criacional;

/**
 * Classe Circle - Protótipo Concreto de um Círculo.
 * 
 * Demonstra a implementação do padrão Prototype para objetos específicos.
 */
public class Circle extends Shape{
    public int radius;

    /**
     * Construtor padrão.
     * Simula um ALTO CUSTO DE CRIAÇÃO através de processamento pesado.
     */
    public Circle(){
        // DEMONSTRAÇÃO DIDÁTICA: Simula operações custosas de inicialização
        // (ex: carregar recursos, conexões de rede, cálculos complexos)
        simulateExpensiveCreation();
    }

    /**
     * Construtor de Cópia - Implementa a clonagem do Circle.
     * 
     * Este construtor é RÁPIDO porque apenas copia dados existentes,
     * sem realizar as operações custosas do construtor padrão.
     * 
     * @param target O Circle a ser copiado
     */
    public Circle(Circle target){
        super(target); // Chama o construtor de cópia da classe pai (cópia profunda do Border!)

        if(target != null){
            this.radius = target.radius;
        }
        
        // NOTA: NÃO chamamos simulateExpensiveCreation() aqui!
        // A clonagem é instantânea - essa é a VANTAGEM do padrão Prototype!
    }

    /**
     * Simula um processo de criação custoso.
     * Em aplicações reais, isso poderia ser:
     * - Carregamento de texturas/imagens
     * - Conexões com banco de dados
     * - Cálculos matemáticos complexos
     * - Inicialização de componentes pesados
     */
    private void simulateExpensiveCreation() {
        try {
            System.out.println("⏳ Criando Circle... (operação custosa)");
            Thread.sleep(100); // Simula 100ms de processamento pesado
            System.out.println("✓ Circle criado com sucesso!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Shape clone() {
        // Usa o construtor de cópia para criar o clone
        return new Circle(this);
    }

    @Override
    public void draw() {
        System.out.println("Desenhando Círculo na posição (" + x + ", " + y + 
                           ") com cor " + color + " e raio " + radius + 
                           " | Border: " + border);
    }
    
}

