package com.gof.criacional;

import java.time.LocalDate;

public class Main {
  public static void main(String[] args) {
    // construir módulos usando o builder do Module
    Module m1 = new Module.Builder("Introdução ao Java")
        .content("Conceitos básicos: JDK, JRE, JVM, tipos")
        .durationMinutes(30)
        .addResource("slides-intro.pdf")
        .build();

    Module m2 = new Module.Builder("Variáveis e Controle")
        .content("if, for, while, tipos primitivos")
        .durationMinutes(45)
        .addResource("exercicios-variaveis.zip")
        .build();

    // construir curso usando Course.Builder
    Course course = new Course.Builder("curso-java-basico", "Java Básico - Projeto Educacional", "Prof. Maria")
        .description("Curso introdutório de Java para estudantes iniciantes.")
        .price(0.0) // gratuito
        .addModule(m1)
        .addModule(m2)
        .estimatedHours(4)
        .releaseDate(LocalDate.of(2025, 3, 1))
        .published(true)
        .build();

    System.out.println(course.prettyPrint());

    // saída simples para ver detalhes:
    course.getModules().forEach(System.out::println);
  }
}
