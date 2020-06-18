package pt.ulusofona.deisi.aedProj2020;
import java.util.HashMap;

public class Movie {
    int id;
    String titulo;
    HashMap<Integer,Actor> actores=new HashMap<>();
    HashMap<Integer,Director> directors =new HashMap<>();
    HashMap<Integer,Genero> generos=new HashMap<>();
    String dataLancamento;
    int orcamento;
    float duracao;
    float mediaVotos;
    int numeroDeVotos;

    public Movie(int id, String titulo, String dataLancamento, int orcamento, float duracao) {

        this.id = id;
        this.titulo = titulo;
        this.dataLancamento = validaData(dataLancamento)?arrumaData(dataLancamento):"0000-00-00";
        this.orcamento = orcamento;
        this.duracao = duracao;
    }

    public boolean validaData(String data){
        String[] dataArray = data.split("-",3);
        return dataArray.length == 3 && (dataArray[0].length() == 2 &&
                dataArray[1].length() == 2 && dataArray[2].length() == 4);
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

    public int[] numerosActores(HashMap<Integer,Actor> actores){
        int[] numerosActores= {0,0};

        for (Actor actor:actores.values()){
            if (actor.genero=='M'){
                numerosActores[0]++;
            }else if (actor.genero=='F'){
                numerosActores[1]++;
            }
        }
        return numerosActores;
    }

    @Override
    public String toString() {
        int[] numerosActoresPorSexo = numerosActores(actores);
        return  id +" | "+ titulo + " | " + dataLancamento + " | " + generos.size()
                + " | " + directors.size() + " | " + numerosActoresPorSexo[0] + " | " + numerosActoresPorSexo[1];
    }
}
