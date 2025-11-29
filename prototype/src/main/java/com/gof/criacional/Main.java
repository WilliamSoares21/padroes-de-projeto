package com.gof.criacional;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main( String[] args ){
        List<Shape> shapes = new ArrayList<>();
        List<Shape> shapesCopy = new ArrayList<>();

        // 1. Criação do Objeto Protótipo Base (Objeto caro/complexo de criar)
        Circle circle = new Circle();
        circle.x = 10;
        circle.y = 20;
        circle.radius = 15;
        circle.color = "Vermelho";
        shapes.add(circle);
        
        // 2. Criação de um Clone (Cópia Exata)
        Shape anotherCircle = circle.clone();
        shapesCopy.add(anotherCircle); // Cópia idêntica, mas instância separada!
        
        // 3. Criação de outro Objeto Protótipo
        Rectangle rectangle = new Rectangle();
        rectangle.width = 10;
        rectangle.height = 20;
        rectangle.color = "Azul";
        shapes.add(rectangle);
        
        // 4. Criação de um Clone com Alteração (Variação)
        Shape anotherRectangle = rectangle.clone();
        anotherRectangle.color = "Verde"; // Modifica APENAS o clone
        shapesCopy.add(anotherRectangle);
        
        System.out.println("--- Formas Originais ---");
        for (Shape s : shapes) {
            s.draw();
        }

        System.out.println("\n--- Formas Clonadas ---");
        for (Shape s : shapesCopy) {
            s.draw();
        }
        
        System.out.println("\nCírculo Original == Círculo Clonado (referência)? " + (circle == anotherCircle));
        System.out.println("Retângulo Original == Retângulo Clonado (referência)? " + (rectangle == anotherRectangle));
        
    }
}
