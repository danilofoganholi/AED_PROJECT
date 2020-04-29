package pt.ulusofona.deisi.eadProj2020;

public class Actor {
    int idActor;
    String nome;
    char genero;
    int idFilmes;

    public Actor(int idActor, String nome, char genero, int idFilmes) {
        this.idActor = idActor;
        this.nome = nome;
        this.genero = genero;
        this.idFilmes = idFilmes;
    }

    @Override
    public String toString() {
        return  "("+idActor+" | "+nome+" | " +genero+" | "+idFilmes+")";
    }
}
