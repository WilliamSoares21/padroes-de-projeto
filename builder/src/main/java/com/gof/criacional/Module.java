package com.gof.criacional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Module {
    private final String title;
    private final String content;
    private final int durationMinutes;
    private final List<String> resources;

    private Module(Builder builder) {
        this.title = builder.title;
        this.content = builder.content;
        this.durationMinutes = builder.durationMinutes;
        this.resources = List.copyOf(builder.resources);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public List<String> getResources() {
        return resources;
    }

    @Override
    public String toString() {
        return String.format("Module{title='%s', durationMinutes=%d, resources=%s}",
                title, durationMinutes, resources);
    }

    public static class Builder {
        private final String title;
        private String content = "";
        private int durationMinutes = 0;
        private List<String> resources = new ArrayList<>();

        public Builder(String title) {
            this.title = validateRequired(title, "title");
        }

        public Builder content(String content) {
            this.content = content != null ? content : "";
            return this;
        }

        public Builder durationMinutes(int minutes) {
            this.durationMinutes = minutes;
            return this;
        }

        public Builder addResource(String resource) {
            if (resource != null && !resource.isBlank()) {
                resources.add(resource);
            }
            return this;
        }

        public Module build() {
            validate();
            return new Module(this);
        }

        private void validate() {
            if (durationMinutes < 0) {
                throw new IllegalStateException("durationMinutes não pode ser negativo");
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
}

