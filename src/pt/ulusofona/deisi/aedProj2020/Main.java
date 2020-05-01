package pt.ulusofona.deisi.aedProj2020;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
    public static void parseFiles(){
        //inicializando variaveis que guardaram os retornos das leituras dos ficheiros
        LinkedHashMap<Integer,Movie> dicionarioMovies = new LinkedHashMap<>();
        ArrayList<Actor> listActors = new ArrayList<>();
        ArrayList<Realizador> listDirectors = new ArrayList<>();
        HashMap<Integer,String> dicionarioGenres = new HashMap<>();
        HashMap<Integer, Integer> dicionarioGenresMovie = new HashMap<>();
        HashMap<Integer, Vote> dicionarioMovieVotes = new HashMap<>();

        //chamando funcoes que leem os ficheiros
        dicionarioMovies = readFolder("deisi_movies.txt").dicionarioMovies;
        listActors = readFolder("deisi_actors.txt").listActors;
        listDirectors = readFolder("deisi_directors.txt").listDirectors;
        dicionarioGenres = readFolder("deisi_genres.txt").dicionarioGenres;
        dicionarioGenresMovie = readFolder("deisi_genres_movies.txt").dicionarioGenresMovie;
        dicionarioMovieVotes = readFolder("deisi_movie_votes.txt").dicionarioMovieVotes;
    }

    public static Folder readFolder(String nomeFicheiro) {
        //inicializando variavel que guardará a leitura do ficheiro
        Folder containsFolder = new Folder(new LinkedHashMap<>(),new ArrayList<>(),
                new ArrayList<>(),new HashMap<>(),new HashMap<>(),
                new HashMap<>());
        try {
            File ficheiro = new File(nomeFicheiro);//pegando ficheiro

            Scanner leitorFicheiro = new Scanner(new FileInputStream(ficheiro));//lendo ficheiro

            while (leitorFicheiro.hasNextLine()) {// enquanto o ficheiro tiver linhas não-lidas

                String linha = leitorFicheiro.nextLine();// ler uma linha do ficheiro

                String dadosLine[] = linha.split(",");// partir a linha no caractere separador

                switch (nomeFicheiro) {//verificando qual pasta foi passada
                    case "deisi_movies.txt":
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 5) {
                            //chamando função que coloca dados no containsFolder
                            folderMovie(dadosLine,containsFolder);
                        }
                        break;
                    case "deisi_actors.txt":
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 4) {
                            //chamando função que coloca dados no containsFolder
                            folderActors(dadosLine,containsFolder);
                        }
                        break;
                    case "deisi_directors.txt":
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 3) {
                            //chamando função que coloca dados no containsFolder
                            folderDirectors(dadosLine,containsFolder);
                        }
                        break;
                    case "deisi_genres_movies.txt":
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 2) {
                            //chamando função que coloca dados no containsFolder
                            folderGenresMovies(dadosLine,containsFolder);
                        }
                        break;
                    case "deisi_genres.txt":
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 2) {
                            //chamando função que coloca dados no containsFolder
                            folderGenres(dadosLine,containsFolder);
                        }
                        break;
                    case "deisi_movie_votes.txt":
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 2) {
                            //chamando função que coloca dados no containsFolder
                            folderVotes(dadosLine,containsFolder);
                        }
                        break;
                    default:
                        System.out.println("Folder not recognized by the system");
                        break;
                }
            }
            leitorFicheiro.close();
        }catch(FileNotFoundException exception){//caso nao encontre o ficheiro
            String mensagem = "Erro: o ficheiro " + nomeFicheiro + " nao foi encontrado.";//mensagem de erro
            System.out.println(mensagem);//mostra mensagem de erro
        }
        return containsFolder;
    }

    public static void folderMovie(String dadosLine[],Folder containsFolder){
        int idFilme,orcamento;
        String titulo,data;
        float duracao;
        try {
            //tentar passar as strings para inteiros se possivel
            idFilme = Integer.parseInt(dadosLine[0].trim());

            //verificando se já existe esse id
            if (!(containsFolder.dicionarioMovies.containsKey(idFilme))) {

                //tentando passar para float e int respectivamente
                duracao = Float.parseFloat(dadosLine[2].trim());
                orcamento = Integer.parseInt(dadosLine[3].trim());

                //tirando os possíveis espaços no inicio e fim da string
                titulo = dadosLine[1].trim();
                data = dadosLine[4].trim();

                if (idFilme >= 0) {//se o id for válido

                    //gravar os dados do filme no dicionario
                    containsFolder.dicionarioMovies.put(idFilme, new Movie(idFilme,
                            titulo, null, null,null, data,
                            orcamento, duracao, 0.0f, 0));
                }
            }
        } catch (NumberFormatException ignored) {
            idFilme = 0;
        }//ignorar caso nao seja possivel
    }

    public static void folderActors(String dadosLine[],Folder containsFolder){
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

            if (idActor>=0 && idFilme>=0){//se ambos os ids forem validos
                //gravar dados de actor no dicionario
                containsFolder.listActors.add(new Actor(idActor,nome,genero,idFilme));
            }
        } catch (NumberFormatException ignored) {
            idFilme = 0;
        }//ignorar caso nao seja possivel
    }

    public static void folderDirectors(String dadosLine[],Folder containsFolder){
        int idRealizador;
        String nome;
        try {
            //tentar passar as strings para inteiros se possivel
            idRealizador=Integer.parseInt(dadosLine[0].trim());

            //tirando os possíveis espaços no inicio e fim da string
            nome = dadosLine[1].trim();

            if (idRealizador>=0){//se o id for validos

                //gravar dados de actor no dicionario
                containsFolder.listDirectors.add(new Realizador(idRealizador,nome));
            }
        } catch (NumberFormatException ignored) {
            idRealizador = 0;
        }//ignorar caso nao seja possivel

    }

    public static void folderGenresMovies(String dadosLine[],Folder containsFolder){
        int idFilme,idGenero;
        try {
            //tentar passar as strings para inteiros se possivel
            idGenero=Integer.parseInt(dadosLine[0].trim());
            idFilme=Integer.parseInt(dadosLine[1].trim());

            //se o id for maior igual a 0
            if (idGenero>=0 && idFilme>=0){
                //colocar dados no dicionario
                containsFolder.dicionarioGenresMovie.put(idGenero,idFilme);
            }
        } catch (NumberFormatException ignored) {
            idGenero = 0;
        }//ignorar caso nao seja possivel
    }

    public static void folderGenres(String dadosLine[],Folder containsFolder){
        int idGenero;
        String nome;
        try {
            //tentar passar as strings para inteiros se possivel
            idGenero=Integer.parseInt(dadosLine[0].trim());

            //tirando os possíveis espaços no inicio e fim da string
            nome = dadosLine[1].trim();

            //se o id for maior igual a 0
            if (idGenero>=0){
                //colocar genero no dicionario de generos
                containsFolder.dicionarioGenres.put(idGenero,nome);
            }
        } catch (NumberFormatException ignored) {
            idGenero = 0;
        }//ignorar caso nao seja possivel
    }

    public static void folderVotes(String dadosLine[],Folder containsFolder){
        int idFilme,numeroDeVotos;
        float mediaVotos;
        try {
            //tentar passar as strings para inteiros se possivel
            idFilme=Integer.parseInt(dadosLine[0].trim());
            mediaVotos=Float.parseFloat(dadosLine[1].trim());
            numeroDeVotos=Integer.parseInt(dadosLine[2].trim());

            //se o id for maior igual a 0
            if (idFilme>=0 && numeroDeVotos>=0){
                //colocar ids no dicionario
                containsFolder.dicionarioMovieVotes.put(idFilme,new Vote(mediaVotos,numeroDeVotos));
            }
        } catch (NumberFormatException ignored) {
            idFilme = 0;
        }//ignorar caso nao seja possivel
    }

    public static ArrayList<Movie> getMovies(){
        //Inicializando valiaveis
        String nomeFicheiro = "deisi_movies.txt";
        ArrayList<Movie> listMovies = new ArrayList<>();
        Folder folder= readFolder(nomeFicheiro);

        //passando dicionario para list
        for(Movie values: folder.dicionarioMovies.values()){
            listMovies.add(values);
        }

        return listMovies;//devolve lista com os filmes encontrados no ficheiro
    }

    public static int countIgnoredLines(String fileName){
        int countIgonedLines=0;
        int numberOfElementsForLiner=0;

        //verificando se foi passado uma pasta
        if (fileName!=null){
            switch (fileName) {//verificando qual pasta foi passada
                case "deisi_movies.txt":
                    numberOfElementsForLiner = 5;//ficheiro filmes possui 5 colunas
                    break;
                case "deisi_actors.txt":
                    numberOfElementsForLiner = 4;//ficheiro actors possui 4 colunas
                    break;
                case "deisi_directors.txt":
                    numberOfElementsForLiner = 3;//ficheiro directors possui 3 colunas
                    break;
                case "deisi_genres_movies.txt":
                case "deisi_genres.txt":
                case "deisi_movie_votes.txt":
                    numberOfElementsForLiner = 2;//ficheiro generos e votos possui 2 colunas
                    break;
                default:
                    System.out.println("Folder not recognized by the system");
                    break;
            }
            try {
                File ficheiro = new File(fileName);

                Scanner leitorFicheiro = new Scanner(new FileInputStream(ficheiro));

                // enquanto o ficheiro tiver linhas não-lidas
                while (leitorFicheiro.hasNextLine()) {

                    // ler uma linha do ficheiro
                    String linha = leitorFicheiro.nextLine();

                    // partir a linha no caractere separador
                    String dadosLine[] = linha.split(",");

                    //verificando se a linha possui o numero certo de elementos
                    if (dadosLine.length!=numberOfElementsForLiner){
                        countIgonedLines++;
                    }
                }
                leitorFicheiro.close();

            }catch(FileNotFoundException exception){//caso nao encontre o ficheiro

                String mensagem = "Erro: o ficheiro " + fileName + " nao foi encontrado.";//mensagem de erro
                System.out.println(mensagem);//mostra mensagem de erro
            }
        }
        return countIgonedLines;
    }

    public static void main(String[] args) {
    }
}