package com.gof.criacional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Course {
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static final BigDecimal ZERO = BigDecimal.ZERO;
  
  private final String id;
  private final String title;
  private final String instructor;
  private final String description;
  private final BigDecimal price;
  private final List<Module> modules;
  private final int estimatedHours;
  private final LocalDate releaseDate;
  private final boolean published;

  private Course(Builder builder) {
    this.id = builder.id;
    this.title = builder.title;
    this.instructor = builder.instructor;
    this.description = builder.description;
    this.price = builder.price;
    this.modules = List.copyOf(builder.modules);
    this.estimatedHours = builder.estimatedHours;
    this.releaseDate = builder.releaseDate;
    this.published = builder.published;
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

  public BigDecimal getPrice() {
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
    return String.format("Course{id='%s', title='%s', instructor='%s', price=%s, modules=%d, estimatedHours=%d, releaseDate=%s, published=%b}",
        id, title, instructor, price, modules.size(), estimatedHours, releaseDate, published);
  }

  public static class Builder {
    private static final int MIN_ESTIMATED_HOURS = 1;
    
    private final String id;
    private final String title;
    private final String instructor;
    private String description = "";
    private BigDecimal price = ZERO;
    private List<Module> modules = new ArrayList<>();
    private int estimatedHours = 0;
    private LocalDate releaseDate = LocalDate.now();
    private boolean published = false;

    public Builder(String id, String title, String instructor) {
      this.id = validateRequired(id, "id");
      this.title = validateRequired(title, "title");
      this.instructor = validateRequired(instructor, "instructor");
    }

    public Builder description(String description) {
      this.description = description != null ? description : "";
      return this;
    }

    public Builder price(double price) {
      this.price = BigDecimal.valueOf(price);
      return this;
    }
    
    public Builder price(BigDecimal price) {
      this.price = price != null ? price : ZERO;
      return this;
    }

    public Builder addModule(Module module) {
      if (module != null) {
        this.modules.add(module);
      }
      return this;
    }

    public Builder estimatedHours(int hours) {
      this.estimatedHours = hours;
      return this;
    }

    public Builder releaseDate(LocalDate date) {
      this.releaseDate = date != null ? date : LocalDate.now();
      return this;
    }

    public Builder published(boolean published) {
      this.published = published;
      return this;
    }

    public Course build() {
      validate();
      return new Course(this);
    }
    
    private void validate() {
      if (price.compareTo(ZERO) < 0) {
        throw new IllegalStateException("price não pode ser negativo");
      }
      if (modules.isEmpty()) {
        throw new IllegalStateException("Um curso deve ter pelo menos 1 módulo");
      }
      if (estimatedHours < MIN_ESTIMATED_HOURS) {
        throw new IllegalStateException("estimatedHours deve ser maior que 0");
      }
    }
    
    private static String validateRequired(String value, String fieldName) {
      Objects.requireNonNull(value, fieldName + " é obrigatório");
      if (value.isBlank()) {
        throw new IllegalArgumentException(fieldName + " não pode ser vazio");
      }
      return value;
    }
  }
  public String prettyPrint() {
    StringBuilder sb = new StringBuilder(500);

    appendCourseHeader(sb);
    appendModulesList(sb);

    return sb.toString();
  }
  
  private void appendCourseHeader(StringBuilder sb) {
    sb.append("CURSO: ").append(title).append("\n")
      .append("ID: ").append(id).append("\n")
      .append("Instrutor: ").append(instructor).append("\n")
      .append("Preco: ").append(formatPrice()).append("\n")
      .append("Horas Estimadas: ").append(estimatedHours).append("h\n")
      .append("Lancamento: ").append(releaseDate.format(DATE_FORMATTER)).append("\n")
      .append("Status: ").append(published ? "Publicado" : "Rascunho").append("\n\n");
  }
  
  private void appendModulesList(StringBuilder sb) {
    sb.append("Modulos (").append(modules.size()).append("):\n");
    
    for (int i = 0; i < modules.size(); i++) {
      Module module = modules.get(i);
      sb.append("----------------------------------------\n")
        .append(i + 1).append(") ").append(module.getTitle()).append("\n")
        .append("   Duracao: ").append(module.getDurationMinutes()).append(" minutos\n")
        .append("   Recursos:\n");

      module.getResources().forEach(resource -> 
        sb.append("     - ").append(resource).append("\n")
      );
      
      sb.append("\n");
    }
  }
  
  private String formatPrice() {
    return price.compareTo(ZERO) == 0 ? "Gratuito" : "R$ " + price;
  }
}
