package com.gof.criacional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Curso com muitos atributos opcionais — caso para usar Builder.
 */
public final class Course {
  // campos obrigatórios
  private final String id;
  private final String title;
  private final String instructor;

  // campos opcionais
  private final String description;
  private final double price;
  private final List<Module> modules;
  private final int estimatedHours;
  private final LocalDate releaseDate;
  private final boolean published;

  private Course(Builder b) {
    this.id = b.id;
    this.title = b.title;
    this.instructor = b.instructor;
    this.description = b.description;
    this.price = b.price;
    this.modules = Collections.unmodifiableList(new ArrayList<>(b.modules));
    this.estimatedHours = b.estimatedHours;
    this.releaseDate = b.releaseDate;
    this.published = b.published;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getInstructor() {
    return instructor;
  }

  public String getDescription() {
    return description;
  }

  public double getPrice() {
    return price;
  }

  public List<Module> getModules() {
    return modules;
  }

  public int getEstimatedHours() {
    return estimatedHours;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public boolean isPublished() {
    return published;
  }

  @Override
  public String toString() {
    return "Course{" +
        "id='" + id + '\'' +
        ", title='" + title + '\'' +
        ", instructor='" + instructor + '\'' +
        ", price=" + price +
        ", modules=" + modules.size() +
        ", estimatedHours=" + estimatedHours +
        ", releaseDate=" + releaseDate +
        ", published=" + published +
        '}';
  }

  // Builder estático interno
  public static class Builder {
    // obrigatórios
    private final String id;
    private final String title;
    private final String instructor;

    // opcionais com defaults
    private String description = "";
    private double price = 0.0;
    private List<Module> modules = new ArrayList<>();
    private int estimatedHours = 0;
    private LocalDate releaseDate = LocalDate.now();
    private boolean published = false;

    public Builder(String id, String title, String instructor) {
      this.id = Objects.requireNonNull(id, "id é obrigatório");
      this.title = Objects.requireNonNull(title, "title é obrigatório");
      this.instructor = Objects.requireNonNull(instructor, "instructor é obrigatório");
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder price(double price) {
      this.price = price;
      return this;
    }

    public Builder addModule(Module module) {
      if (module != null)
        this.modules.add(module);
      return this;
    }

    public Builder estimatedHours(int hours) {
      this.estimatedHours = hours;
      return this;
    }

    public Builder releaseDate(LocalDate date) {
      this.releaseDate = date;
      return this;
    }

    public Builder published(boolean published) {
      this.published = published;
      return this;
    }

    public Course build() {
      // validações de negócio
      if (price < 0)
        throw new IllegalStateException("price não pode ser negativo");
      if (modules.isEmpty()) {
        // permitir curso sem módulos pode ser aceitável; aqui exigimos >=1 para exemplo
        // educativo
        throw new IllegalStateException("Um curso deve ter pelo menos 1 módulo");
      }
      if (estimatedHours <= 0) {
        // estimativa mínima
        throw new IllegalStateException("estimatedHours deve ser maior que 0");
      }
      return new Course(this);
    }
  }
  public String prettyPrint() {
    StringBuilder sb = new StringBuilder();

    sb.append("📘 CURSO: ").append(title).append("\n")
      .append("ID: ").append(id).append("\n")
      .append("Instrutor: ").append(instructor).append("\n")
      .append("Preço: ").append(price == 0 ? "Gratuito" : "R$ " + price).append("\n")
      .append("Horas Estimadas: ").append(estimatedHours).append("h\n")
      .append("Lançamento: ").append(releaseDate.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("\n")
      .append("Status: ").append(published ? "Publicado" : "Rascunho").append("\n\n");

    sb.append("📚 Módulos (").append(modules.size()).append("):\n");

    int i = 1;
    for (Module m : modules) {
        sb.append("----------------------------------------\n")
          .append(i++).append(") ").append(m.getTitle()).append("\n")
          .append("   Duração: ").append(m.getDurationMinutes()).append(" minutos\n")
          .append("   Recursos:\n");

        for (String r : m.getResources()) {
            sb.append("     - ").append(r).append("\n");
        }

        sb.append("\n");
    }

    return sb.toString();
  }
}
