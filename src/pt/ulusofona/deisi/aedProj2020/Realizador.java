package pt.ulusofona.deisi.aedProj2020;

public class Realizador {
    int idRealizador;
    String nome;

    public Realizador(int idRealizador, String nome) {
        this.idRealizador = idRealizador;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return  idRealizador +" = "+nome;
    }
}
