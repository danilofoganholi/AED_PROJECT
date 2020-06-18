package pt.ulusofona.deisi.aedProj2020;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class FunctionsParseFiles {

    public static void readFolder(String folder ,String nomeFicheiro) {
        try {
            FileReader ficheiro = new FileReader(folder+nomeFicheiro);

            BufferedReader leitorFicheiro = new BufferedReader(ficheiro);

            String lines;

            while ((lines = leitorFicheiro.readLine())!=null) {

                String[] dadosLine = lines.split(",");//partir a linha no caractere separador

                switch (nomeFicheiro) {//verificando qual pasta foi passada
                    case "deisi_movies.txt":
                        if (dadosLine.length == 5){//verificando se a linha possui o numero de elementos correto
                            folderMovie(FunctionsAuxiliary.tiraAspas(dadosLine));//acrecenta filme no dicionario
                        }else{Main.ignoredLine[0]++;}//caso tenha mais elementos ela sera ignorada
                        break;
                    case "deisi_actors.txt":
                        if (dadosLine.length == 4){
                            folderActors(FunctionsAuxiliary.tiraAspas(dadosLine));//acrecenta actors no dicionario
                        }else{Main.ignoredLine[1]++;}
                        break;
                    case "deisi_directors.txt":

                        if (dadosLine.length == 3){//verificando se a linha possui o numero de elementos correto
                            folderDirectors(dadosLine);//acrecenta director no dicionario
                        }else if (dadosLine.length == 4){
                            //acrecenta director no dicionario
                            folderDirectors(FunctionsAuxiliary.arrumaStringComVirgula(dadosLine));
                        }else{Main.ignoredLine[2]++;}
                        break;
                    case "deisi_genres_movies.txt":
                        if (dadosLine.length == 2) {//verificando se a linha possui o numero de elementos correto
                            folderGenresMovies(dadosLine);//acrecenta generos no dicionario
                        }else{Main.ignoredLine[4]++;}
                        break;
                    case "deisi_genres.txt":
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 2) {
                            folderGenres(dadosLine);//acrecenta generos no dicionario generos
                        }else{Main.ignoredLine[3]++;}
                        break;
                    case "deisi_movie_votes.txt":
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 3) {
                            folderVotes(dadosLine);//acrecenta votos no dicionario
                        }else{Main.ignoredLine[5]++;}
                    default:
                        break;
                }
            }
            leitorFicheiro.close();
        }catch (IOException e) {e.printStackTrace();}
    }

    public static void folderMovie(String[] dadosLine){
        try {
            //tentar passar as strings para inteiros se possivel
            int idFilme = Integer.parseInt(dadosLine[0].trim());

            //verificando se já existe esse id
            if ((idFilme >= 0) && (!Main.dicionarioMovies.containsKey(idFilme))) {
                //gravar os dados do filme no dicionario
                Main.dicionarioMovies.put(idFilme,new Movie(idFilme,dadosLine[1].trim(),dadosLine[4].trim(),
                        Integer.parseInt(dadosLine[3].trim()),Float.parseFloat(dadosLine[2].trim())));
            }
        } catch (NumberFormatException ignored) {Main.ignoredLine[0]++;}//caso de errado a linha sera ignorada
    }

    public static void folderActors(String[] dadosLine){
        try {
            if (dadosLine[3]!=null){
                int idFilme = Integer.parseInt(dadosLine[3].trim());
                int idActor = Integer.parseInt(dadosLine[0].trim());

                if (Main.dicionarioMovies.get(idFilme)!=null && !Main.dicionarioMovies.get(idFilme).
                        actores.containsKey(idActor)){
                    Main.dicionarioMovies.get(idFilme).actores.put(idActor, new Actor(idActor, dadosLine[1].trim(),
                            (dadosLine[2].trim()).charAt(0)));//gravar actor nos filmes realizados por ele
                }
            }
        } catch (NumberFormatException ignored) {Main.ignoredLine[1]++;}//caso de errado a linha sera ignorada
    }

    public static void folderDirectors(String[] dadosLine){
        try {
            if (dadosLine[2]!=null){
                int idFilme = Integer.parseInt(dadosLine[2].trim());
                int idDirector = Integer.parseInt(dadosLine[0].trim());

                if (Main.dicionarioMovies.get(idFilme)!=null && !Main.dicionarioMovies.get(idFilme).
                        directors.containsKey(idDirector)){
                    Main.dicionarioMovies.get(idFilme).directors.put(idDirector, new Director(idDirector,
                            dadosLine[1].trim()));//gravar actor nos filmes realizados por ele
                }
            }
        } catch (NumberFormatException ignored) {Main.ignoredLine[2]++;}//caso de errado a linha sera ignorada
    }

    public static void folderGenres(String[] dadosLine){
        try {
            //colocar genero no dicionario de generos
            Main.legendaGeneros.put(Integer.parseInt(dadosLine[0].trim()),dadosLine[1].trim());

        }catch (NumberFormatException ignored) {Main.ignoredLine[3]++;}//caso de errado a linha sera ignorada
    }

    public static void folderGenresMovies(String[] dadosLine){
        try {
            if (dadosLine[1]!=null){
                int idFilme = Integer.parseInt(dadosLine[1].trim());
                int idGenero = Integer.parseInt(dadosLine[0].trim());

                if (Main.dicionarioMovies.get(idFilme)!=null && !Main.dicionarioMovies.get(idFilme).
                        generos.containsKey(idGenero)){
                    Main.dicionarioMovies.get(idFilme).generos.put(idGenero, new Genero(idGenero,
                            Main.legendaGeneros.get(idGenero)));//gravar actor nos filmes realizados por ele
                }
            }
        } catch (NumberFormatException ignored) {Main.ignoredLine[4]++;}//caso de errado a linha sera ignorada
    }

    public static void folderVotes(String[] dadosLine){
        try {
            if (Main.dicionarioMovies.get(Integer.parseInt(dadosLine[0].trim()))!=null){
                //colocar genero no dicionario de generos
                Main.dicionarioMovies.get(Integer.parseInt(dadosLine[0].trim()))
                        .mediaVotos = Float.parseFloat(dadosLine[1].trim());
                Main.dicionarioMovies.get(Integer.parseInt(dadosLine[0].trim()))
                        .numeroDeVotos = Integer.parseInt(dadosLine[2].trim());
            }
        }catch (NumberFormatException ignored) {Main.ignoredLine[5]++;}//caso de errado a linha sera ignorada
    }
}
