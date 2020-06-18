package pt.ulusofona.deisi.aedProj2020;

public class Actor {
    int idActor;
    String nome;
    char genero;

    public Actor(int idActor, String nome, char genero) {
        this.idActor = idActor;
        this.nome = nome;
        this.genero = Character.toUpperCase(genero) ;
    }

    @Override
    public String toString() {
        return  idActor+" | "+nome+" | " +genero+"\n";
    }
}
