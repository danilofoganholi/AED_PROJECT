package pt.ulusofona.deisi.eadProj2020;

public class Genero {
    int idGenero;
    String nome;

    public Genero(int idGenero, String nome) {
        this.idGenero = idGenero;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return  idGenero+" = "+ nome;
    }
}