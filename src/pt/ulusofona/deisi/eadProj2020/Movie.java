package pt.ulusofona.deisi.eadProj2020;
import java.util.HashSet;

public class Movie {
    int id;
    String titulo;
    HashSet<Actor> actores=new HashSet<>();
    HashSet<Realizador> realizadores=new HashSet<>();
    HashSet<Genero> generos=new HashSet<>();
    String dataLancamento;
    int orcamento;
    float duracao;
    float mediaVotos;
    int numeroVotos;

    public Movie(int id, String titulo, Actor actor, Realizador realizador, Genero genero, String data) {
        this.id = id;
        this.titulo = titulo;
        this.actores.add(actor);
        this.realizadores.add(realizador);
        this.generos.add(genero);
        this.dataLancamento = validaData(data)?data:"00-00-0000";
    }

    public Movie(int id, String titulo, Actor actor, Realizador realizador, Genero genero,
                  String data, int orcamento,float duracao, float mediaVotos, int numeroVotos) {
        this.id = id;
        this.titulo = titulo;
        this.actores.add(actor);
        this.realizadores.add(realizador);
        this.generos.add(genero);
        this.dataLancamento = validaData(data)?data:"00-00-0000";
        this.orcamento = orcamento;
        this.duracao=duracao;
        this.mediaVotos = mediaVotos;
        this.numeroVotos = numeroVotos;
    }

    public boolean validaData(String data){
        String[] dataArray = data.split("-",3);
        return dataArray.length == 3 && (dataArray[0].length() == 2 && dataArray[1].length() == 2 && dataArray[2].length() == 4);
    }

    public String arrumaData(String data){

        String[] dataArray = data.split("-",3);
        if (dataArray.length == 3){

            if (dataArray[0].length() == 2 && dataArray[1].length() == 2 && dataArray[2].length() == 4){

                return dataArray[2]+"-"+dataArray[1]+"-"+dataArray[0];

            }else if (dataArray[0].length() == 4 && dataArray[1].length() == 2 && dataArray[2].length() == 2){
                return dataArray[0]+"-"+dataArray[1]+"-"+dataArray[2];
            }
        }
        return "0000-00-00";
    }

    @Override
    public String toString() {
        return  id +" | "+ titulo + " | " + arrumaData(dataLancamento);
    }
}
