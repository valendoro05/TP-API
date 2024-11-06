package com.example.demo.views;

public class UnidadView {
    private Integer identificador;
    private int piso;
    private int numero;
    private String habitado; 
    private EdificioView edificio;

    public UnidadView() {}

    public UnidadView(Integer identificador, int piso, int numero, String habitado, EdificioView edificio) {
        this.identificador = identificador;
        this.piso = piso;
        this.numero = numero;
        this.habitado = habitado;
        this.edificio = edificio;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isHabitado() {
        return "H".equals(this.habitado); // More concise and null-safe
    }

    public void setHabitado(String habitado) {
        this.habitado = habitado;
    }

    public EdificioView getEdificio() {
        return edificio;
    }

    public void setEdificio(EdificioView edificio) {
        this.edificio = edificio;
    }

    @Override
    public String toString() {
        return "UnidadView{" +
                "identificador=" + identificador +
                ", piso=" + piso +
                ", numero=" + numero +
                ", habitado='" + habitado + '\'' +
                ", edificio=" + edificio +
                '}';
    }
}


