package com.example.biblioteca;

import java.time.LocalDate;
import java.util.Objects;

public class Emprestimo {

    private final Usuario usuario;
    private final Livro livro;
    private final LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean ativo = true;

    public Emprestimo(Usuario usuario, Livro livro) {
        this(usuario, livro, LocalDate.now());
    }

    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo) {
        if (usuario == null || livro == null) {
            throw new IllegalArgumentException("Usuário e livro são obrigatórios");
        }
        if (!livro.isDisponivel()) {
            throw new IllegalStateException("Livro indisponível para empréstimo");
        }
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = Objects.requireNonNull(dataEmprestimo, "Data de empréstimo é obrigatória");
        livro.marcarIndisponivel();
    }

    public void devolver() {
        if (!ativo) return;
        this.ativo = false;
        this.dataDevolucao = LocalDate.now();
        livro.marcarDisponivel();
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
}