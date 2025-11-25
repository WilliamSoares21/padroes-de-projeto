package com.gof.criacional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Módulo de um curso (pequena unidade: aula, vídeo, quiz).
 * Aqui usamos um builder simples também para mostrar composição.
 */
public final class Module {
    private final String title;
    private final String content; // resumo do conteúdo
    private final int durationMinutes; // duração aproximada
    private final List<String> resources; // links ou nomes de arquivos

    private Module(Builder b) {
        this.title = b.title;
        this.content = b.content;
        this.durationMinutes = b.durationMinutes;
        this.resources = Collections.unmodifiableList(new ArrayList<>(b.resources));
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public int getDurationMinutes() { return durationMinutes; }
    public List<String> getResources() { return resources; }

    @Override
    public String toString() {
        return "Module{" +
                "title='" + title + '\'' +
                ", durationMinutes=" + durationMinutes +
                ", resources=" + resources +
                '}';
    }

    // Builder para Module (opcional)
    public static class Builder {
        private final String title;
        private String content = "";
        private int durationMinutes = 0;
        private List<String> resources = new ArrayList<>();

        public Builder(String title) {
            this.title = Objects.requireNonNull(title, "title é obrigatório");
        }

        public Builder content(String content) { this.content = content; return this; }
        public Builder durationMinutes(int minutes) { this.durationMinutes = minutes; return this; }
        public Builder addResource(String resource) {
            if (resource != null && !resource.isBlank()) resources.add(resource);
            return this;
        }

        public Module build() {
            if (durationMinutes < 0) throw new IllegalStateException("durationMinutes não pode ser negativo");
            return new Module(this);
        }
    }
}

