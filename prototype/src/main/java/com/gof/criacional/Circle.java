package com.gof.criacional;

public class Circle extends Shape{
    public int radius;

    public Circle(){}

    public Circle( Circle target){
        super(target);

        if(target != null){
            this.radius = target.radius;
        }
    }

    @Override
    public Shape clone() {
        return new Circle(this);
    }

    @Override
    public void draw() {
        System.out.println("Desenhando Círculo na posição (" + x + ", " + y + 
                           ") com cor " + color + " e raio " + radius);
    }
    
}

