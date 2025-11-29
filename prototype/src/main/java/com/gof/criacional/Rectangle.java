package com.gof.criacional;

/**
 * Classe Rectangle - Protótipo Concreto de um Retângulo.
 * 
 * Demonstra a implementação do padrão Prototype para objetos específicos.
 */
public class Rectangle extends Shape{
    public int width;
    public int height;

    /**
     * Construtor padrão.
     * Simula um ALTO CUSTO DE CRIAÇÃO através de processamento pesado.
     */
    public Rectangle(){
        // DEMONSTRAÇÃO DIDÁTICA: Simula operações custosas de inicialização
        simulateExpensiveCreation();
    }

    /**
     * Construtor de Cópia - Implementa a clonagem do Rectangle.
     * 
     * Este construtor é RÁPIDO porque apenas copia dados existentes,
     * sem realizar as operações custosas do construtor padrão.
     * 
     * @param target O Rectangle a ser copiado
     */
    public Rectangle(Rectangle target){
        super(target); // Chama o construtor de cópia da classe pai (cópia profunda do Border!)

        if(target != null){
            this.width = target.width;
            this.height = target.height;
        }
        
        // NOTA: NÃO chamamos simulateExpensiveCreation() aqui!
        // A clonagem é instantânea - essa é a VANTAGEM do padrão Prototype!
    }

    /**
     * Simula um processo de criação custoso.
     * Em aplicações reais, isso poderia ser:
     * - Carregamento de recursos gráficos
     * - Validações complexas de geometria
     * - Cálculos de renderização
     * - Inicialização de estruturas de dados pesadas
     */
    private void simulateExpensiveCreation() {
        try {
            System.out.println("⏳ Criando Rectangle... (operação custosa)");
            Thread.sleep(100); // Simula 100ms de processamento pesado
            System.out.println("✓ Rectangle criado com sucesso!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Shape clone(){
        // Usa o construtor de cópia para criar o clone
        return new Rectangle(this);
    }

    @Override
    public void draw(){
        System.out.println("Desenhando Retângulo na posição (" + x +", " +y +" ) com cor " +color+ 
                           " e dimensões " + width + "x" + height + 
                           " | Border: " + border);
    }

}
