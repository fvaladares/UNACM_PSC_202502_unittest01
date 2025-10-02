package com.example.biblioteca;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Usuario {

    public static final int LIMITE_EMPRESTIMOS = 3;

    private final String nome;
    private final List<Emprestimo> emprestimosAtivos = new ArrayList<>();

    public Usuario(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do usuário é obrigatório");
        }
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public List<Emprestimo> getEmprestimosAtivos() {
        return List.copyOf(emprestimosAtivos);
    }

    public Emprestimo adicionarEmprestimo(Livro livro) {
        Objects.requireNonNull(livro, "Livro é obrigatório");
        if (emprestimosAtivos.size() >= LIMITE_EMPRESTIMOS) {
            throw new IllegalStateException("Usuário já possui " + LIMITE_EMPRESTIMOS + " empréstimos ativos");
        }
        Emprestimo e = new Emprestimo(this, livro);
        emprestimosAtivos.add(e);
        return e;
    }

    public void devolver(Livro livro) {
        Objects.requireNonNull(livro, "Livro é obrigatório");
        boolean devolvido = false;
        Iterator<Emprestimo> it = emprestimosAtivos.iterator();
        while (it.hasNext()) {
            Emprestimo e = it.next();
            if (e.getLivro().equals(livro) && e.isAtivo()) {
                e.devolver();
                it.remove();
                devolvido = true;
                break;
            }
        }
        if (!devolvido) {
            throw new IllegalStateException("Nenhum empréstimo ativo encontrado para este livro");
        }
    }
}