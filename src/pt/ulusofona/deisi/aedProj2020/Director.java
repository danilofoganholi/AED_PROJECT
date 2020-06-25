package pt.ulusofona.deisi.aedProj2020;

public class Director {
    int idRealizador;
    String nome;

    public Director(int idRealizador, String nome) {
        this.idRealizador = idRealizador;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return   nome+":"+idRealizador;
    }
}
