package pt.ulusofona.deisi.aedProj2020;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class FunctionsQuery {
    public static int countMoviesMonthYear(int mes,int ano){
        int countMovies=0;
        String mesString,anoString;

        if (mes>12 || mes<1 || ano<1895 || ano>2020){return 0;}

        mesString =(mes<10)? "0"+ mes :Integer.toString(mes);
        anoString =Integer.toString(ano);

        for (Movie movie: Main.dicionarioMovies.values()){
            String[] dataLancamento = movie.dataLancamento.split("-");
            if (dataLancamento[0].equals(anoString) && dataLancamento[1].equals(mesString)){
                countMovies++;
            }
        }

        return countMovies;
    }

    public static String topMonthMovieCount(int ano){

        if (ano<1895 || ano>2020){return "Year invalid. Try Again";}

        int topMonth=1,countMovie= countMoviesMonthYear(1,ano);
        for (int i=2; i<=12; i++){
            int countMoviesMonth = countMoviesMonthYear(i,ano);
            if (countMoviesMonth>countMovie){
                topMonth=i;
                countMovie=countMoviesMonth;
            }
        }
        return topMonth+":"+countMovie;
    }

    public static String getActorsByDirector(int numero,String nomeRealizador){
        HashMap<String,Node> countWorksDoneTogether= new HashMap<>();
        StringBuilder resposta= new StringBuilder();

        if (numero<=0||nomeRealizador==null||nomeRealizador.length()<=0){return "Invalid parameters.Try again.";}

        for (Movie movie: Main.dicionarioMovies.values()){
            if (FunctionsAuxiliary.haveDirector(nomeRealizador,movie.directors)){
                for (Actor actor: movie.actores.values()){
                    if (countWorksDoneTogether.containsKey(actor.nome)){
                        countWorksDoneTogether.get(actor.nome).count++;
                    }else {
                        countWorksDoneTogether.put(actor.nome,new Node(actor.nome,1));
                    }
                }
            }
        }

        for (Node node: countWorksDoneTogether.values()){
            if (node.count>=numero){
                resposta.append(node.nome).append(":").append(node.count).append("\n");
            }
        }
        return resposta.toString();
    }

    public static String topMoviesWithGenderBias(int numero, int ano){
        ArrayList<Node> filmesDoAno = new ArrayList<>();
        StringBuilder resposta = new StringBuilder();

        if (numero<=0||ano<1895 || ano>2020){return "Invalid parameters.Try again.";}

        for(Movie movie: Main.dicionarioMovies.values()){
            String[] split = movie.dataLancamento.split("-");
            if (Integer.parseInt(split[0])==ano && movie.actores.size()>10){
                int[] porcentagem = FunctionsAuxiliary.porcentagem(movie.actores);
                filmesDoAno.add(new Node(movie.titulo,(porcentagem[0]==0)?'F':'M',porcentagem[1]));
            }
        }

        filmesDoAno.sort(Comparator.comparing((Node movie) -> movie.porcentagem));

        for (int i=filmesDoAno.size()-1; i>filmesDoAno.size()-numero-1; i--) {
            resposta.append(filmesDoAno.get(i).nome).append(":").append(filmesDoAno.get(i).genero)
                    .append(":").append(filmesDoAno.get(i).porcentagem).append("\n");
        }

        return resposta.toString();
    }

    public static String insertDirector(int idDirector, String nomeDirector,int idMovie){

        if (idDirector<0 || nomeDirector==null || nomeDirector.equals("") || idMovie<0 ||
                Main.dicionarioMovies.get(idMovie)==null){return "Erro";}

        if (!Main.dicionarioMovies.get(idMovie).directors.containsKey(idDirector)){
            Main.dicionarioMovies.get(idMovie).directors.put(idDirector,new Director(idDirector,nomeDirector.trim()));
            return "OK";
        }
        return "Erro";
    }

    public static String getMovieWithActorContaining(String subEstring){
        ArrayList<String> listNameMovie = new ArrayList<>();
        StringBuilder resposta = new StringBuilder();

        if (subEstring==null || subEstring.equals("")){return "Invalid parameters.Try again.";}

        for (Movie movie: Main.dicionarioMovies.values()){
            for (Actor actor: movie.actores.values()){
                if (actor.nome.contains(subEstring)){
                    listNameMovie.add(movie.titulo);
                }
            }
        }

        Collections.sort(listNameMovie);

        for (String titulo:listNameMovie){
            resposta.append(titulo).append("\n");
        }

        return resposta.toString().trim();
    }
}
