package com.gof.criacional;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
  public static void main(String[] args) {
    Module introductionModule = new Module.Builder("Introducao ao Java")
        .content("Conceitos basicos: JDK, JRE, JVM, tipos")
        .durationMinutes(30)
        .addResource("slides-intro.pdf")
        .build();

    Module controlModule = new Module.Builder("Variaveis e Controle")
        .content("if, for, while, tipos primitivos")
        .durationMinutes(45)
        .addResource("exercicios-variaveis.zip")
        .build();

    Course course = new Course.Builder(
            "curso-java-basico",
            "Java Basico - Projeto Educacional",
            "Prof. Maria")
        .description("Curso introdutorio de Java para estudantes iniciantes.")
        .price(BigDecimal.ZERO)
        .addModule(introductionModule)
        .addModule(controlModule)
        .estimatedHours(4)
        .releaseDate(LocalDate.of(2025, 3, 1))
        .published(true)
        .build();

    System.out.println(course.prettyPrint());

    course.getModules().forEach(System.out::println);
  }
}
