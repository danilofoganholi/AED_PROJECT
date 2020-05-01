package pt.ulusofona.deisi.aedProj2020;
import java.io.*;
import java.util.*;

public class Main {
    static int ignoredLineMovie, ignoredLineActors, ignoredLineDirectors;
    static int ignoredLineGenres, ignoredLineGenresMovie, ignoredLineVotes;
    static LinkedHashMap<Integer,Movie> dicionarioMovies = new LinkedHashMap<>();
    static ArrayList<Actor> listActors = new ArrayList<>();
    static ArrayList<Realizador> listDirectors = new ArrayList<>();
    static HashMap<Integer,String> dicionarioGenres = new HashMap<>();
    static HashMap<Integer, Integer> dicionarioGenresMovie = new HashMap<>();
    static HashMap<Integer, Vote> dicionarioMovieVotes = new HashMap<>();

    public static void parseFiles(){
        //inicializando variaveis que guardaram os retornos das leituras dos ficheiros
        dicionarioMovies = new LinkedHashMap<>();
        listActors = new ArrayList<>();
        listDirectors = new ArrayList<>();
        dicionarioGenres = new HashMap<>();
        dicionarioGenresMovie = new HashMap<>();
        dicionarioMovieVotes = new HashMap<>();

        //chamando funcoes que leem os ficheiros
        readFolder("deisi_movies.txt");
        readFolder("deisi_actors.txt");
        readFolder("deisi_directors.txt");
        readFolder("deisi_genres.txt");
        readFolder("deisi_genres_movies.txt");
        readFolder("deisi_movie_votes.txt");
    }

    public static void readFolder(String nomeFicheiro) {
        //reset variaveis que contam quantas linhas foram puladas em cada arquivo
        switch (nomeFicheiro) {//verificando qual pasta foi passada
            case "deisi_movies.txt":
                ignoredLineMovie=0;
                break;
            case "deisi_actors.txt":
                ignoredLineActors=0;
                break;
            case "deisi_directors.txt":
                ignoredLineDirectors=0;
                break;
            case "deisi_genres_movies.txt":
                ignoredLineGenresMovie=0;
                break;
            case "deisi_genres.txt":
                ignoredLineGenres=0;
                break;
            case "deisi_movie_votes.txt":
                ignoredLineVotes=0;
                break;
            default:
                break;
        }

        try {
            FileReader ficheiro = new FileReader(nomeFicheiro);//pegando ficheiro
            BufferedReader leitorFicheiro = new BufferedReader(ficheiro);//colocando ficheiro no buffere
            String lines;

            while (((lines = leitorFicheiro.readLine()) != null)) {
                String dadosLine[] = lines.split(",");// partir a linha no caractere separador
                switch (nomeFicheiro) {//verificando qual pasta foi passada
                    case "deisi_movies.txt":
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 5) {
                            //chamando função que coloca dados no containsFolder
                            folderMovie(dadosLine);
                        }else{ignoredLineMovie++;}
                        break;
                    case "deisi_actors.txt":
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 4) {
                            //chamando função que coloca dados no containsFolder
                            folderActors(dadosLine);
                        }else{ignoredLineActors++;}
                        break;
                    case "deisi_directors.txt":
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 3) {
                            //chamando função que coloca dados no containsFolder
                            folderDirectors(dadosLine);
                        }else{ignoredLineDirectors++;}
                        break;
                    case "deisi_genres_movies.txt":
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 2) {
                            //chamando função que coloca dados no containsFolder
                            folderGenresMovies(dadosLine);
                        }else{ignoredLineGenresMovie++;}
                        break;
                    case "deisi_genres.txt":
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 2) {
                            //chamando função que coloca dados no containsFolder
                            folderGenres(dadosLine);
                        }else{ignoredLineGenres++;}
                        break;
                    case "deisi_movie_votes.txt":
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 2) {
                            //chamando função que coloca dados no containsFolder
                            folderVotes(dadosLine);
                        }else{ignoredLineVotes++;}
                        break;
                    default:
                        break;
                }
            }
            leitorFicheiro.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void folderMovie(String dadosLine[]){
        int idFilme,orcamento;
        String titulo,data;
        float duracao;
        try {
            //tentar passar as strings para inteiros se possivel
            idFilme = Integer.parseInt(dadosLine[0].trim());

            //verificando se já existe esse id
            if (idFilme >= 0 && (!(dicionarioMovies.containsKey(idFilme)))) {

                //tentando passar para float e int respectivamente
                duracao = Float.parseFloat(dadosLine[2].trim());
                orcamento = Integer.parseInt(dadosLine[3].trim());

                //tirando os possíveis espaços no inicio e fim da string
                titulo = dadosLine[1].trim();
                data = dadosLine[4].trim();

                //gravar os dados do filme no dicionario
                dicionarioMovies.put(idFilme, new Movie(idFilme,
                        titulo, null, null,null, data,
                        orcamento, duracao, 0.0f, 0));
            }
        } catch (NumberFormatException ignored) {
            idFilme = 0;
        }//ignorar caso nao seja possivel
    }

    public static void folderActors(String dadosLine[]){
        int idFilme,idActor;
        String nome;
        char genero;
        try {
            //tentar passar as strings para inteiros se possivel
            idActor=Integer.parseInt(dadosLine[0].trim());
            idFilme=Integer.parseInt(dadosLine[3].trim());

            //tirando os possíveis espaços no inicio e fim da string
            nome = dadosLine[1].trim();
            genero = (dadosLine[2].trim()).charAt(0);

            //gravar dados de actor no dicionario
            listActors.add(new Actor(idActor,nome,genero,idFilme));

        } catch (NumberFormatException ignored) {
            idFilme = 0;
        }//ignorar caso nao seja possivel
    }

    public static void folderDirectors(String dadosLine[]){
        int idRealizador;
        String nome;
        try {
            //tentar passar as strings para inteiros se possivel
            idRealizador=Integer.parseInt(dadosLine[0].trim());

            //tirando os possíveis espaços no inicio e fim da string
            nome = dadosLine[1].trim();

            //gravar dados de actor no dicionario
            listDirectors.add(new Realizador(idRealizador,nome));

        } catch (NumberFormatException ignored) {
            idRealizador = 0;
        }//ignorar caso nao seja possivel

    }

    public static void folderGenresMovies(String dadosLine[]){
        int idFilme,idGenero;
        try {
            //tentar passar as strings para inteiros se possivel
            idGenero=Integer.parseInt(dadosLine[0].trim());
            idFilme=Integer.parseInt(dadosLine[1].trim());

            //colocar dados no dicionario
            dicionarioGenresMovie.put(idGenero,idFilme);

        } catch (NumberFormatException ignored) {
            idGenero = 0;
        }//ignorar caso nao seja possivel
    }

    public static void folderGenres(String dadosLine[]){
        int idGenero;
        String nome;
        try {
            //tentar passar as strings para inteiros se possivel
            idGenero=Integer.parseInt(dadosLine[0].trim());

            //tirando os possíveis espaços no inicio e fim da string
            nome = dadosLine[1].trim();

            //colocar genero no dicionario de generos
            dicionarioGenres.put(idGenero,nome);

        } catch (NumberFormatException ignored) {
            idGenero = 0;
        }//ignorar caso nao seja possivel
    }

    public static void folderVotes(String dadosLine[]){
        int idFilme,numeroDeVotos;
        float mediaVotos;
        try {
            //tentar passar as strings para inteiros se possivel
            idFilme=Integer.parseInt(dadosLine[0].trim());
            mediaVotos=Float.parseFloat(dadosLine[1].trim());
            numeroDeVotos=Integer.parseInt(dadosLine[2].trim());

            //colocar ids no dicionario
            dicionarioMovieVotes.put(idFilme,new Vote(mediaVotos,numeroDeVotos));

        } catch (NumberFormatException ignored) {
            idFilme = 0;
        }//ignorar caso nao seja possivel
    }

    public static ArrayList<Movie> getMovies(){
        //Inicializando valiaveis
        ArrayList<Movie> listMovies = new ArrayList<>();

        //passando dicionario para list
        if (dicionarioMovies.size()>0){
            listMovies.addAll(dicionarioMovies.values());
        }else{
            readFolder("deisi_movies.txt");
            listMovies.addAll(dicionarioMovies.values());
        }
        return listMovies;//devolve lista com os filmes encontrados no ficheiro
    }

    public static int countIgnoredLines(String fileName){
        int countIgonedLines=0;
        switch (fileName) {//verificando qual pasta foi passada
            case "deisi_movies.txt":
                if (ignoredLineMovie>0){
                    countIgonedLines = ignoredLineMovie;
                }else{
                    readFolder("deisi_movies.txt");
                    countIgonedLines = ignoredLineMovie;
                }
                break;
            case "deisi_actors.txt":
                if (ignoredLineActors>0){
                    countIgonedLines = ignoredLineActors;
                }else{
                    readFolder("deisi_actors.txt");
                    countIgonedLines = ignoredLineActors;
                }
                break;
            case "deisi_directors.txt":
                if (ignoredLineDirectors>0){
                    countIgonedLines = ignoredLineDirectors;
                }else{
                    readFolder("deisi_directors.txt");
                    countIgonedLines = ignoredLineDirectors;
                }
                break;
            case "deisi_genres_movies.txt":
                if (ignoredLineGenresMovie>0){
                    countIgonedLines = ignoredLineGenresMovie;
                }else{
                    readFolder("deisi_genres_movies.txt");
                    countIgonedLines = ignoredLineGenresMovie;
                }
                break;
            case "deisi_genres.txt":
                if (ignoredLineGenres>0){
                    countIgonedLines = ignoredLineGenres;
                }else{
                    readFolder("deisi_genres.txt");
                    countIgonedLines = ignoredLineGenres;
                }
                break;
            case "deisi_movie_votes.txt":
                if (ignoredLineVotes>0){
                    countIgonedLines = ignoredLineVotes;
                }else{
                    readFolder("deisi_movie_votes.txt");
                    countIgonedLines = ignoredLineVotes;
                }
                break;
            default:
                break;
        }
        return countIgonedLines;
    }

    public static void main(String[] args) {
        long incio = System.currentTimeMillis();
        parseFiles();
        getMovies();
        countIgnoredLines("deisi_movies.txt");
        long fim = System.currentTimeMillis();
        System.out.println(fim-incio);
    }
}