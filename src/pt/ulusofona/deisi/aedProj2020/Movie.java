package pt.ulusofona.deisi.aedProj2020;
import java.util.ArrayList;
import java.util.HashMap;

public class Movie {
    int id,diaLancamento,mesLancamento,anoLancamento,orcamento,numeroDeVotos;
    String titulo;
    HashMap<Integer,Actor> actores=new HashMap<>();
    HashMap<String,Director> directors =new HashMap<>();
    HashMap<Integer,Genero> generos=new HashMap<>();
    String dataString;
    float duracao,mediaVotos;

    public Movie(int id, String titulo, String dataLancamento, int orcamento, float duracao) {
        String[] dataArray = dataLancamento.split("-",3);
        int[] data = arrumaData(dataArray);
        this.id = id;
        this.titulo = titulo;
        this.diaLancamento = data[0];
        this.mesLancamento = data[1];
        this.anoLancamento = data[2];
        this.dataString = dataArray[2]+"-"+dataArray[1]+"-"+dataArray[0];
        this.orcamento = orcamento;
        this.duracao = duracao;
        if (!Main.dicionarioAno.containsKey(data[2])){//coloca o id no ano correspondente
            Main.dicionarioAno.put(data[2],new ArrayList<>());
        }
        Main.dicionarioAno.get(data[2]).add(id);
    }

    public int[] arrumaData(String[] dataArray){
        int[] data = new int[4];
        if (dataArray.length == 3){
            try {
                data[0] = Integer.parseInt(dataArray[0]);
                data[1] = Integer.parseInt(dataArray[1]);
                data[2] = Integer.parseInt(dataArray[2]);
            }catch (NumberFormatException ignored) {data[3]=1;}
        }
        return data;
    }

    public int[] numerosActores(HashMap<Integer,Actor> actores){
        int[] numerosActores= {0,0};

        for (Actor actor:actores.values()){
            if (actor.genero=='M'){
                numerosActores[0]++;
            }else{
                numerosActores[1]++;
            }
        }
        return numerosActores;
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
        return  id +" | "+ titulo + " | " + dataString + " | " + generos.size()
                + " | " + directors.size() + " | " + numerosActoresPorSexo[0] + " | " + numerosActoresPorSexo[1];
    }
}
