package pt.ulusofona.deisi.aedProj2020;

public class Node {
    String nome;
    int count;
    char genero;
    int porcentagem;

    public Node(String nome, int count) {
        this.nome = nome;
        this.count = count;
    }

    public Node(String nome, char genero, int porcentagem) {
        this.nome = nome;
        this.genero = genero;
        this.porcentagem = porcentagem;
    }

    @Override
    public String toString() {
        return nome+" | "+genero +" | "+porcentagem;
    }
}
