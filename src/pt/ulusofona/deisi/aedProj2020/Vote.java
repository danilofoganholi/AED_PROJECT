package pt.ulusofona.deisi.aedProj2020;

public class Vote {
    int idFilme;
    float mediaVotos;
    int numeroDeVotos;

    public Vote( float mediaVotos, int numeroDeVotos) {
        this.mediaVotos = mediaVotos;
        this.numeroDeVotos = numeroDeVotos;
    }

    @Override
    public String toString() {
        return "Media de Votos= "+mediaVotos+"Numero de Votos="+ numeroDeVotos;
    }
}
