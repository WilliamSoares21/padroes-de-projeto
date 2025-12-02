package com.gof.criacional;

/**
 * Classe abstrata Shape - Protótipo base para formas geométricas.
 * 
 * Esta classe implementa Cloneable e define o contrato para o padrão Prototype.
 * Incluímos um objeto Border para demonstrar a CÓPIA PROFUNDA (Deep Copy).
 */
public abstract class Shape implements Cloneable {
    public int x;
    public int y;
    public String color;
    
    // OBJETO MUTÁVEL - Usado para demonstrar Cópia Profunda vs Cópia Rasa
    public Border border;

    public Shape(){}

    /**
     * Construtor de Cópia - Mecanismo PRINCIPAL do Padrão Prototype.
     * 
     * IMPORTANTE: Este construtor realiza uma CÓPIA PROFUNDA (Deep Copy):
     * - Copia valores primitivos diretamente (x, y)
     * - Copia referências imutáveis diretamente (String color)
     * - Para objetos mutáveis (Border), cria uma NOVA INSTÂNCIA usando
     *   o construtor de cópia de Border, garantindo independência total.
     * 
     * Se fizéssemos apenas "this.border = target.border" (cópia rasa),
     * o clone compartilharia a MESMA instância de Border com o original,
     * e alterações em um afetariam o outro!
     * 
     * @param target O objeto Shape a ser copiado
     */
    public Shape(Shape target){
        if(target != null){
            this.x = target.x;
            this.y = target.y;
            this.color = target.color;
            
            // CÓPIA PROFUNDA: Cria uma NOVA instância de Border
            // ao invés de copiar apenas a referência
            this.border = (target.border != null) ? new Border(target.border) : null;
        } 
    }

    public abstract Shape clone();

    public abstract void draw();
}