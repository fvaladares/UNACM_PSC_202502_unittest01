package com.example.biblioteca;

import java.util.Objects;

public class Livro {

    private final String titulo;
    private boolean disponivel = true;

    public Livro(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título do livro não pode ser vazio");
        }
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void marcarIndisponivel() {
        if (!disponivel) throw new IllegalStateException("Livro já está emprestado");
        this.disponivel = false;
    }

    public void marcarDisponivel() {
        this.disponivel = true;
    }

    @Override
    public String toString() {
        return "Livro{titulo='" + titulo + "', disponivel=" + disponivel + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Livro)) return false;
        Livro livro = (Livro) o;
        return titulo.equalsIgnoreCase(livro.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo.toLowerCase());
    }
}