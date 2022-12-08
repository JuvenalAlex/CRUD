package com.crud;

public class Locador {
    String locador;
    String livro;

    public Locador(){
        locador="";
        livro="";
    }

    public Locador(String locador, String livro) {
        this.locador = locador;
        this.livro = livro;
    }

    public String getLocador() {
        return locador;
    }

    public void setLocador(String locador) {
        this.locador = locador;
    }

    public String getLivro() {
        return livro;
    }

    public void setLivro(String livro) {
        this.livro = livro;
    }

    @Override
    public String toString() {
        return "Locador: " + locador;
    }
}
