package com.gof.criacional;

public class Rectangle extends Shape{
    public int width;
    public int height;

    public Rectangle(){}

    public Rectangle(Rectangle target){
        super(target);

        if(target != null){
            this.width = target.width;
            this.height = target.height;
        }
    }

    @Override
    public Shape clone(){
        return new Rectangle(this);
    }

    @Override
    public void draw(){
        System.out.println("Desenhando Rentângulo na posição (" + x +", " +y +" ) com cor " +color+ " e dimensões " + width + "x" + height);
    }

}
