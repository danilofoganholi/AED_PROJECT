package pt.ulusofona.deisi.aedProj2020;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FunctionsParseFiles {
    static String folder = "";

    public static void readFolderMovie() throws IOException{
        String nomeFicheiro = "deisi_movies.txt";

        FileReader ficheiro = new FileReader(folder+nomeFicheiro);

        BufferedReader leitorFicheiro = new BufferedReader(ficheiro);

        String lines;

        while ((lines = leitorFicheiro.readLine())!=null) {

            String[] dadosLine = lines.split(",");//partir a linha no caractere separador

            if (dadosLine.length == 5){//verificando se a linha possui o numero de elementos correto

                FunctionsAuxiliary.tiraAspas(dadosLine);//tira aspas duplas do nome

                try {
                    //tentar passar as strings para inteiros se possivel
                    int idFilme = Integer.parseInt(dadosLine[0].trim());

                    //verificando se já existe esse id
                    if (idFilme>=0 && !Main.dicionarioMovies.containsKey(idFilme)) {
                        //gravar os dados do filme no dicionario
                        Main.dicionarioMovies.put(idFilme,new Movie(idFilme,dadosLine[1].trim(),
                                dadosLine[4].trim(), Integer.parseInt(dadosLine[3].trim()),
                                Float.parseFloat(dadosLine[2].trim())));
                    }
                //caso de errado a linha sera ignorada
                } catch (NumberFormatException ignored) {Main.ignoredLine[0]++;}

            }else{Main.ignoredLine[0]++;}//caso tenha mais elementos ela sera ignorada
        }
        leitorFicheiro.close();
    }

    public static void readFolderActors() throws IOException{
        String nomeFicheiro = "deisi_actors.txt";

        FileReader ficheiro = new FileReader(folder+nomeFicheiro);

        BufferedReader leitorFicheiro = new BufferedReader(ficheiro);

        String lines;

        while ((lines = leitorFicheiro.readLine())!=null) {

            String[] dadosLine = lines.split(",");//partir a linha no caractere separador

            if (dadosLine.length == 4){//verificando se a linha possui o numero de elementos correto

                FunctionsAuxiliary.tiraAspas(dadosLine);//tira aspas duplas do nome se tiver

                try {
                    int idActor = Integer.parseInt(dadosLine[0].trim());
                    Movie filme = Main.dicionarioMovies.get(Integer.parseInt(dadosLine[3].trim()));
                    if (filme!=null && !filme.actores.containsKey(idActor)){
                        filme.actores.put(idActor, new Actor(idActor, dadosLine[1].trim(),
                                (dadosLine[2].trim()).charAt(0)));//gravar actor nos filmes realizados por ele
                    }
                //caso de errado a linha sera ignorada
                } catch (NumberFormatException ignored) {Main.ignoredLine[1]++;}
            }else{Main.ignoredLine[1]++;}//caso tenha mais elementos ela sera ignorada
        }
        leitorFicheiro.close();
    }

    public static void readFolderDirectors() throws IOException{
        String nomeFicheiro = "deisi_directors.txt";

        FileReader ficheiro = new FileReader(folder+nomeFicheiro);

        BufferedReader leitorFicheiro = new BufferedReader(ficheiro);

        String lines;

        while ((lines = leitorFicheiro.readLine())!=null) {

            String[] dadosLine = lines.split(",");//partir a linha no caractere separador

            if (dadosLine.length == 3){//verificando se a linha possui o numero de elementos correto

                try {
                    String nomeDirector = dadosLine[1].trim();
                    Movie filme = Main.dicionarioMovies.get(Integer.parseInt(dadosLine[2].trim()));

                    if (filme!=null && !filme.directors.containsKey(nomeDirector)){
                        //gravar diretor nos filmes realizados por ele
                        filme.directors.put(dadosLine[1].trim(),
                                new Director(Integer.parseInt(dadosLine[0].trim()), nomeDirector));
                    }
                //caso de errado a linha sera ignorada
                } catch (NumberFormatException ignored) {Main.ignoredLine[2]++;}

            }else if(dadosLine.length == 4){

                dadosLine = FunctionsAuxiliary.arrumaStringComVirgula(dadosLine);

                try {
                    String nomeDirector = dadosLine[1].trim();
                    Movie filme = Main.dicionarioMovies.get(Integer.parseInt(dadosLine[2].trim()));

                    if (filme!=null && !filme.directors.containsKey(nomeDirector)){
                        //gravar diretor nos filmes realizados por ele
                        filme.directors.put(dadosLine[1].trim(),
                                new Director(Integer.parseInt(dadosLine[0].trim()), nomeDirector));
                    }
                    //caso de errado a linha sera ignorada
                } catch (NumberFormatException ignored) {Main.ignoredLine[2]++;}

            }else{Main.ignoredLine[2]++;}
        }
        leitorFicheiro.close();
    }

    public static void readFolderGenresMovies()throws IOException{
        String nomeFicheiro = "deisi_genres_movies.txt";

        FileReader ficheiro = new FileReader(folder+nomeFicheiro);

        BufferedReader leitorFicheiro = new BufferedReader(ficheiro);

        String lines;

        while ((lines = leitorFicheiro.readLine())!=null) {

            String[] dadosLine = lines.split(",");//partir a linha no caractere separador

            if (dadosLine.length == 2){//verificando se a linha possui o numero de elementos correto

                try {
                    int idGenero = Integer.parseInt(dadosLine[0].trim());
                    Movie filme = Main.dicionarioMovies.get(Integer.parseInt(dadosLine[1].trim()));

                    if (filme!=null && !filme.generos.containsKey(idGenero)){
                        //gravar actor nos filmes realizados por ele
                       filme.generos.put(idGenero, new Genero(idGenero, Main.legendaGeneros.get(idGenero)));
                    }
                //caso de errado a linha sera ignorada
                } catch (NumberFormatException ignored) {Main.ignoredLine[4]++;}

            }else{Main.ignoredLine[4]++;}//caso tenha mais elementos ela sera ignorada
        }
        leitorFicheiro.close();
    }

    public static void readFolderVotes()throws IOException{
        String nomeFicheiro = "deisi_movie_votes.txt";

        FileReader ficheiro = new FileReader(folder+nomeFicheiro);

        BufferedReader leitorFicheiro = new BufferedReader(ficheiro);

        String lines;

        while ((lines = leitorFicheiro.readLine())!=null) {

            String[] dadosLine = lines.split(",");//partir a linha no caractere separador

            if (dadosLine.length == 3){//verificando se a linha possui o numero de elementos correto

                try {
                    Movie filme = Main.dicionarioMovies.get(Integer.parseInt(dadosLine[0].trim()));
                    if (filme!=null){
                        filme.mediaVotos = Float.parseFloat(dadosLine[1].trim());
                        filme.numeroDeVotos = Integer.parseInt(dadosLine[2].trim());
                    }
                //caso de errado a linha sera ignorada
                }catch (NumberFormatException ignored) {Main.ignoredLine[5]++;}

            }else{Main.ignoredLine[5]++;}//caso tenha mais elementos ela sera ignorada
        }
        leitorFicheiro.close();
    }

    public static void readFolderGenres()throws IOException{
        String nomeFicheiro = "deisi_genres.txt";

        FileReader ficheiro = new FileReader(folder+nomeFicheiro);

        BufferedReader leitorFicheiro = new BufferedReader(ficheiro);

        String lines;

        while ((lines = leitorFicheiro.readLine())!=null) {

            String[] dadosLine = lines.split(",");//partir a linha no caractere separador

            try {
                //colocar genero no dicionario de generos
                Main.legendaGeneros.put(Integer.parseInt(dadosLine[0]),dadosLine[1].trim());
            //caso de errado a linha sera ignorada
            }catch (NumberFormatException ignored) {Main.ignoredLine[3]++;}
        }
        leitorFicheiro.close();
    }
}
