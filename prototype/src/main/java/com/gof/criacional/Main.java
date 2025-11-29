package com.gof.criacional;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstração Educacional do Padrão de Projeto Prototype (GoF - Creational)
 * 
 * Este exemplo ilustra DOIS conceitos fundamentais:
 * 
 * 1. MOTIVAÇÃO DO PADRÃO: Por que usar Prototype?
 *    - Criação de objetos pode ser CUSTOSA (tempo, recursos, complexidade)
 *    - Clonar um objeto existente é mais EFICIENTE que criar do zero
 * 
 * 2. CÓPIA PROFUNDA vs CÓPIA RASA:
 *    - Cópia Rasa: Copia referências (objetos compartilhados)
 *    - Cópia Profunda: Cria novas instâncias de objetos aninhados (independência total)
 */
public class Main {
    public static void main( String[] args ){
        System.out.println("========================================");
        System.out.println("  PADRÃO PROTOTYPE - DEMONSTRAÇÃO");
        System.out.println("========================================\n");
        
        // ============================================================
        // PARTE 1: DEMONSTRAÇÃO DO CUSTO DE CRIAÇÃO
        // ============================================================
        System.out.println("--- PARTE 1: Custo de Criação vs Clonagem ---\n");
        
        List<Shape> shapes = new ArrayList<>();
        List<Shape> shapesCopy = new ArrayList<>();

        // 1. Criação do Objeto Protótipo Base (Objeto CARO/COMPLEXO de criar)
        // OBSERVE o tempo que leva para criar o objeto original (100ms + processamento)
        System.out.println("🔨 Criando protótipo original Circle...");
        long startTime = System.currentTimeMillis();
        Circle circle = new Circle();
        circle.x = 10;
        circle.y = 20;
        circle.radius = 15;
        circle.color = "Vermelho";
        
        // Adiciona Border ao Circle
        circle.border = new Border();
        circle.border.thickness = 3;
        circle.border.style = "Sólida";
        
        shapes.add(circle);
        long endTime = System.currentTimeMillis();
        System.out.println("⏱️  Tempo de criação: " + (endTime - startTime) + "ms\n");
        
        // 2. Criação de um Clone (INSTANTÂNEO!)
        // OBSERVE que a clonagem é MUITO mais rápida que a criação original
        System.out.println("⚡ Clonando Circle existente...");
        startTime = System.currentTimeMillis();
        Shape anotherCircle = circle.clone();
        endTime = System.currentTimeMillis();
        System.out.println("⏱️  Tempo de clonagem: " + (endTime - startTime) + "ms");
        System.out.println("💡 A clonagem foi INSTANTÂNEA! Não executou operações custosas.\n");
        shapesCopy.add(anotherCircle);
        
        // 3. Criação de outro Objeto Protótipo (novamente custoso)
        System.out.println("🔨 Criando protótipo original Rectangle...");
        startTime = System.currentTimeMillis();
        Rectangle rectangle = new Rectangle();
        rectangle.width = 10;
        rectangle.height = 20;
        rectangle.color = "Azul";
        
        // Adiciona Border ao Rectangle
        rectangle.border = new Border();
        rectangle.border.thickness = 2;
        rectangle.border.style = "Tracejada";
        
        shapes.add(rectangle);
        endTime = System.currentTimeMillis();
        System.out.println("⏱️  Tempo de criação: " + (endTime - startTime) + "ms\n");
        
        // 4. Criação de um Clone com Alteração (Variação)
        System.out.println("⚡ Clonando Rectangle existente...");
        startTime = System.currentTimeMillis();
        Shape anotherRectangle = rectangle.clone();
        anotherRectangle.color = "Verde"; // Modifica APENAS o clone
        endTime = System.currentTimeMillis();
        System.out.println("⏱️  Tempo de clonagem: " + (endTime - startTime) + "ms");
        System.out.println("💡 Novamente, clonagem instantânea!\n");
        shapesCopy.add(anotherRectangle);
        
        // ============================================================
        // PARTE 2: DEMONSTRAÇÃO DE CÓPIA PROFUNDA (Deep Copy)
        // ============================================================
        System.out.println("\n--- PARTE 2: Cópia Profunda vs Cópia Rasa ---\n");
        
        System.out.println("📋 Estado ANTES da modificação do Border do clone:");
        System.out.println("Circle Original - Border: " + circle.border);
        System.out.println("Circle Clonado  - Border: " + ((Circle)anotherCircle).border);
        
        // Modificando o Border APENAS no clone
        System.out.println("\n🔧 Modificando Border do Circle CLONADO...");
        ((Circle)anotherCircle).border.thickness = 10;
        ((Circle)anotherCircle).border.style = "Pontilhada";
        
        System.out.println("\n📋 Estado DEPOIS da modificação do Border do clone:");
        System.out.println("Circle Original - Border: " + circle.border);
        System.out.println("Circle Clonado  - Border: " + ((Circle)anotherCircle).border);
        
        System.out.println("\n✅ RESULTADO:");
        System.out.println("   O Border do original NÃO foi afetado!");
        System.out.println("   Isso demonstra que fizemos uma CÓPIA PROFUNDA (Deep Copy).");
        System.out.println("   Cada objeto tem sua PRÓPRIA instância de Border.\n");
        
        System.out.println("💡 EXPLICAÇÃO:");
        System.out.println("   Se tivéssemos feito apenas 'this.border = target.border' (cópia rasa),");
        System.out.println("   ambos os objetos compartilhariam a MESMA instância de Border,");
        System.out.println("   e a modificação afetaria os dois!\n");
        
        // ============================================================
        // PARTE 3: VISUALIZAÇÃO GERAL E INDEPENDÊNCIA
        // ============================================================
        System.out.println("\n--- PARTE 3: Visualização e Independência ---\n");
        
        System.out.println("--- Formas Originais ---");
        for (Shape s : shapes) {
            s.draw();
        }

        System.out.println("\n--- Formas Clonadas ---");
        for (Shape s : shapesCopy) {
            s.draw();
        }
        
        System.out.println("\n--- Teste de Independência (Referências Diferentes) ---");
        System.out.println("Círculo Original == Círculo Clonado? " + (circle == anotherCircle) + " (false = objetos independentes)");
        System.out.println("Retângulo Original == Retângulo Clonado? " + (rectangle == anotherRectangle) + " (false = objetos independentes)");
        System.out.println("Border Original == Border Clonado? " + (circle.border == ((Circle)anotherCircle).border) + " (false = cópia profunda!)");
        
        System.out.println("\n========================================");
        System.out.println("  FIM DA DEMONSTRAÇÃO");
        System.out.println("========================================");
    }
}
