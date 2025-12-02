package com.gof.criacional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

class MainTest {

  @Test
  @DisplayName("Deve criar um módulo com builder")
  void shouldCreateModuleWithBuilder() {
    Module module = new Module.Builder("Java Básico")
        .content("Introdução ao Java")
        .durationMinutes(30)
        .addResource("slides.pdf")
        .build();

    assertNotNull(module);
    assertEquals("Java Básico", module.getTitle());
    assertEquals("Introdução ao Java", module.getContent());
    assertEquals(30, module.getDurationMinutes());
    assertEquals(1, module.getResources().size());
    assertTrue(module.getResources().contains("slides.pdf"));
  }

  @Test
  @DisplayName("Deve criar curso completo com builder")
  void shouldCreateCourseWithBuilder() {
    Module m1 = new Module.Builder("Módulo 1")
        .content("Conteúdo 1")
        .durationMinutes(45)
        .build();

    Course course = new Course.Builder("curso-1", "Curso de Java", "Prof. João")
        .description("Curso completo de Java")
        .price(BigDecimal.valueOf(199.90))
        .addModule(m1)
        .estimatedHours(10)
        .releaseDate(LocalDate.of(2025, 1, 1))
        .published(true)
        .build();

    assertNotNull(course);
    assertEquals("curso-1", course.getId());
    assertEquals("Curso de Java", course.getTitle());
    assertEquals("Prof. João", course.getInstructor());
    assertEquals(0, BigDecimal.valueOf(199.90).compareTo(course.getPrice()));
    assertEquals(1, course.getModules().size());
    assertEquals(10, course.getEstimatedHours());
    assertTrue(course.isPublished());
  }

  @Test
  @DisplayName("Deve lançar exceção ao criar curso com preço negativo")
  void shouldThrowExceptionForNegativePrice() {
    Module m1 = new Module.Builder("Módulo 1")
        .durationMinutes(30)
        .build();

    Course.Builder builder = new Course.Builder("curso-1", "Curso Teste", "Instrutor")
        .price(BigDecimal.valueOf(-10.0))
        .addModule(m1)
        .estimatedHours(5);

    assertThrows(IllegalStateException.class, builder::build);
  }

  @Test
  @DisplayName("Deve lançar exceção ao criar curso sem módulos")
  void shouldThrowExceptionForCourseWithoutModules() {
    Course.Builder builder = new Course.Builder("curso-1", "Curso Teste", "Instrutor")
        .price(BigDecimal.valueOf(100.0))
        .estimatedHours(5);

    assertThrows(IllegalStateException.class, builder::build);
  }

  @Test
  @DisplayName("Deve lançar exceção ao criar curso com horas estimadas inválidas")
  void shouldThrowExceptionForInvalidEstimatedHours() {
    Module m1 = new Module.Builder("Módulo 1")
        .durationMinutes(30)
        .build();

    Course.Builder builder = new Course.Builder("curso-1", "Curso Teste", "Instrutor")
        .addModule(m1)
        .estimatedHours(0);

    assertThrows(IllegalStateException.class, builder::build);
  }

  @Test
  @DisplayName("Deve lançar exceção ao criar módulo com duração negativa")
  void shouldThrowExceptionForNegativeDuration() {
    Module.Builder builder = new Module.Builder("Módulo Teste")
        .durationMinutes(-10);

    assertThrows(IllegalStateException.class, builder::build);
  }

  @Test
  @DisplayName("Deve criar curso gratuito com valores padrão")
  void shouldCreateFreeCourseWithDefaults() {
    Module m1 = new Module.Builder("Módulo 1")
        .durationMinutes(30)
        .build();

    Course course = new Course.Builder("curso-free", "Curso Gratuito", "Prof. Maria")
        .addModule(m1)
        .estimatedHours(2)
        .build();

    assertEquals(0, BigDecimal.ZERO.compareTo(course.getPrice()));
    assertFalse(course.isPublished());
    assertEquals(LocalDate.now(), course.getReleaseDate());
  }

  @Test
  @DisplayName("Módulos devem ser imutáveis após criação do curso")
  void courseModulesShouldBeImmutable() {
    Module m1 = new Module.Builder("Módulo 1")
        .durationMinutes(30)
        .build();

    Course course = new Course.Builder("curso-1", "Curso", "Instrutor")
        .addModule(m1)
        .estimatedHours(2)
        .build();

    assertThrows(UnsupportedOperationException.class,
        () -> course.getModules().clear());
  }

  @Test
  @DisplayName("Deve adicionar múltiplos recursos ao módulo")
  void shouldAddMultipleResourcesToModule() {
    Module module = new Module.Builder("Módulo Completo")
        .addResource("slides.pdf")
        .addResource("exercicios.zip")
        .addResource("video.mp4")
        .durationMinutes(60)
        .build();

    assertEquals(3, module.getResources().size());
  }

  @Test
  @DisplayName("Deve ignorar recursos vazios ou nulos")
  void shouldIgnoreEmptyOrNullResources() {
    Module module = new Module.Builder("Módulo")
        .addResource("")
        .addResource("  ")
        .addResource(null)
        .addResource("valid.pdf")
        .durationMinutes(30)
        .build();

    assertEquals(1, module.getResources().size());
    assertEquals("valid.pdf", module.getResources().get(0));
  }
}
