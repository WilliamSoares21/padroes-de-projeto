package com.gof.criacional;

/**
 * Classe Border - Representa a borda de uma forma geométrica.
 * 
 * Esta classe é utilizada para demonstrar o conceito de CÓPIA PROFUNDA (Deep Copy)
 * no padrão Prototype. Como Border é um objeto mutável, precisamos garantir que
 * ao clonar uma Shape, uma nova instância de Border seja criada, evitando que
 * alterações no clone afetem o objeto original.
 */
public class Border {
    public int thickness;
    public String style;

    public Border() {}

    /**
     * Construtor de cópia para Border.
     * 
     * Este construtor é ESSENCIAL para implementar a cópia profunda.
     * Ele cria uma NOVA instância de Border com os mesmos valores,
     * garantindo independência entre o original e o clone.
     * 
     * @param target O objeto Border a ser copiado
     */
    public Border(Border target) {
        if (target != null) {
            this.thickness = target.thickness;
            this.style = target.style;
        }
    }

    @Override
    public String toString() {
        return "Border{thickness=" + thickness + ", style='" + style + "'}";
    }
}
