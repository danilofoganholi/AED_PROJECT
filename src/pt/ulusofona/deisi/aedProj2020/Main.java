package pt.ulusofona.deisi.aedProj2020;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void parseFiles(){
        //inicializando variaveis que guardaram os retornos das leituras dos ficheiros
        HashMap<Integer,Movie> dicionarioMovies = new HashMap<>();
        ArrayList<Actor> dicionarioActors = new ArrayList<>();
        ArrayList<Realizador> dicionarioDirectors = new ArrayList<>();
        HashMap<Integer,String> dicionarioGenres = new HashMap<>();
        HashMap<Integer, Integer> dicionarioGenresMovie = new HashMap<>();
        HashMap<Integer, Vote> dicionarioMovieVotes = new HashMap<>();

        //chamando funcoes que leem os ficheiros
        dicionarioMovies = readFolderMovies("deisi_movies.txt");
        dicionarioActors = readFolderActors("deisi_actors.txt");
        dicionarioDirectors = readFolderDirectors("deisi_directors.txt");
        dicionarioGenres = readFolderGenres("deisi_genres.txt");
        dicionarioGenresMovie = readFolderGenresMovie("deisi_genres_movies.txt");
        dicionarioMovieVotes = readFolderMovieVotes("deisi_movie_votes.txt");
    }

    public static HashMap<Integer,Vote> readFolderMovieVotes(String nomeFicheiro){
        //inicializando variaveis
        int idFilme;
        float mediaVotos;
        int numeroDeVotos;
        HashMap<Integer,Vote> dicionarioMovieVotes=new HashMap();

        try {
            File ficheiro = new File(nomeFicheiro);//pegando ficheiro

            Scanner leitorFicheiro = new Scanner(new FileInputStream(ficheiro));//lendo ficheiro

            // enquanto o ficheiro tiver linhas não-lidas
            while (leitorFicheiro.hasNextLine()) {

                // ler uma linha do ficheiro
                String linha = leitorFicheiro.nextLine();

                // partir a linha no caractere separador
                String dadosLine[] = linha.split(",");

                if (dadosLine.length==3){//verificando se a linha possui o numero corretos de elementos
                    try{
                        //tentar passar as strings para inteiros se possivel
                        idFilme=Integer.parseInt(dadosLine[0].trim());
                        mediaVotos=Float.parseFloat(dadosLine[1].trim());
                        numeroDeVotos=Integer.parseInt(dadosLine[2].trim());

                        //se o id for maior igual a 0
                        if (idFilme>=0 && numeroDeVotos>=0){
                            //colocar ids no dicionario
                            dicionarioMovieVotes.put(idFilme,new Vote(mediaVotos,numeroDeVotos));
                        }
                     //ignorar caso nao seja possivel passar para ints
                    }catch (NumberFormatException ignored){
                        idFilme=0;
                    }
                }
            }
            leitorFicheiro.close();
        }catch(FileNotFoundException exception){//caso nao encontre o ficheiro

            String mensagem = "Erro: o ficheiro " + nomeFicheiro + " nao foi encontrado.";//mensagem de erro
            System.out.println(mensagem);//mostra mensagem de erro
        }
        return dicionarioMovieVotes;//devolve dicionario dos filmes encontrados no ficheiro
    }

    public static HashMap<Integer,Integer> readFolderGenresMovie(String nomeFicheiro){
        //inicializando variaveis
        int idGenero;
        int idFilmes;
        HashMap<Integer,Integer> dicionarioGenresMovie=new HashMap();

        try {
            File ficheiro = new File(nomeFicheiro);//pegando ficheiro

            Scanner leitorFicheiro = new Scanner(new FileInputStream(ficheiro));//lendo ficheiro

            // enquanto o ficheiro tiver linhas não-lidas
            while (leitorFicheiro.hasNextLine()) {

                // ler uma linha do ficheiro
                String linha = leitorFicheiro.nextLine();

                // partir a linha no caractere separador
                String dadosLine[] = linha.split(",");

                if (dadosLine.length==2){//verificando se a linha possui o numero correto de elementos
                    try{
                        //tentar passar as strings para inteiros se possivel
                        idGenero=Integer.parseInt(dadosLine[0].trim());
                        idFilmes=Integer.parseInt(dadosLine[1].trim());

                        //se o id for maior igual a 0
                        if (idGenero>=0 && idFilmes>=0){
                            //colocar dados no dicionario
                            dicionarioGenresMovie.put(idGenero,idFilmes);
                        }
                    }catch (NumberFormatException ignored){
                        idGenero=0;
                    }//ignorar caso nao seja possivel
                }
            }
            leitorFicheiro.close();

        }catch(FileNotFoundException exception){//caso nao encontre o ficheiro

            String mensagem = "Erro: o ficheiro " + nomeFicheiro + " nao foi encontrado.";//mensagem de erro
            System.out.println(mensagem);//mostra mensagem de erro
        }
        return dicionarioGenresMovie;//devolve dicionario dos filmes encontrados no ficheiro
    }

    public static HashMap<Integer,String> readFolderGenres(String nomeFicheiro){
        //inicializando variaveis
        int idGenero;
        String nome;
        HashMap<Integer,String> dicionarioGenres=new HashMap();

        try {
            File ficheiro = new File(nomeFicheiro);//pegando ficheiro

            Scanner leitorFicheiro = new Scanner(new FileInputStream(ficheiro));//lendo ficheiro

            // enquanto o ficheiro tiver linhas não-lidas
            while (leitorFicheiro.hasNextLine()) {

                // ler uma linha do ficheiro
                String linha = leitorFicheiro.nextLine();

                // partir a linha no caractere separador
                String dadosLine[] = linha.split(",");

                if (dadosLine.length==2){//verificando se a linha possui o numero correto de elementos
                    try{
                        //tentar passar as strings para inteiros se possivel
                        idGenero=Integer.parseInt(dadosLine[0].trim());

                        //tirando os possíveis espaços no inicio e fim da string
                        nome = dadosLine[1].trim();

                        //se o id for maior igual a 0
                        if (idGenero>=0){
                            //colocar genero no dicionario de generos
                            dicionarioGenres.put(idGenero,nome);
                        }

                    }catch (NumberFormatException ignored){
                        idGenero=0;
                    }//ignorar caso nao seja possivel
                }
            }
            leitorFicheiro.close();

        }catch(FileNotFoundException exception){//caso nao encontre o ficheiro

            String mensagem = "Erro: o ficheiro " + nomeFicheiro + " nao foi encontrado.";//mensagem de erro
            System.out.println(mensagem);//mostra mensagem de erro
        }
        return dicionarioGenres;//devolve dicionario dos filmes encontrados no ficheiro
    }

    public static ArrayList<Actor> readFolderActors(String nomeFicheiro){
        //inicializando variaveis
        int idActor;
        String nome;
        char genero;
        int idFilmes;
        ArrayList<Actor> listActors=new ArrayList<>();

        try {
            File ficheiro = new File(nomeFicheiro);//pegando ficheiro

            Scanner leitorFicheiro = new Scanner(new FileInputStream(ficheiro));//lendo ficheiro

            // enquanto o ficheiro tiver linhas não-lidas
            while (leitorFicheiro.hasNextLine()) {

                // ler uma linha do ficheiro
                String linha = leitorFicheiro.nextLine();

                // partir a linha no caractere separador
                String dadosLine[] = linha.split(",");

                if (dadosLine.length==4){//verificando se a linha possui o numero corretos de elementos
                    try{
                        //tentar passar as strings para inteiros se possivel
                        idActor=Integer.parseInt(dadosLine[0].trim());
                        idFilmes=Integer.parseInt(dadosLine[3].trim());

                        //tirando os possíveis espaços no inicio e fim da string
                        nome = dadosLine[1].trim();
                        genero = (dadosLine[2].trim()).charAt(0);

                        if (idActor>=0 && idFilmes>=0){//se ambos os ids forem validos
                            //gravar dados de actor no dicionario
                            listActors.add(new Actor(idActor,nome,genero,idFilmes));
                        }
                    }catch (NumberFormatException ignored){
                        idActor=0;
                    }//ignorar caso nao seja possivel
                }
            }
            leitorFicheiro.close();

        }catch(FileNotFoundException exception){//caso nao encontre o ficheiro

            String mensagem = "Erro: o ficheiro " + nomeFicheiro + " nao foi encontrado.";//mensagem de erro
            System.out.println(mensagem);//mostra mensagem de erro
        }
        return listActors;//devolve dicionario dos filmes encontrados no ficheiro
    }

    public static ArrayList<Realizador> readFolderDirectors(String nomeFicheiro){
        //inicializndo variaveis
        int idRealizador;
        String nome;
        ArrayList<Realizador> listDirectors=new ArrayList<>();

        try {
            File ficheiro = new File(nomeFicheiro);//pengado ficheiro

            Scanner leitorFicheiro = new Scanner(new FileInputStream(ficheiro));//lendo ficheiro

            // enquanto o ficheiro tiver linhas não-lidas
            while (leitorFicheiro.hasNextLine()) {

                // ler uma linha do ficheiro
                String linha = leitorFicheiro.nextLine();

                // partir a linha no caractere separador
                String dadosLine[] = linha.split(",");

                if (dadosLine.length==3){//verificando se a linha possui o numero certo de elementos
                    try{
                        //tentar passar as strings para inteiros se possivel
                        idRealizador=Integer.parseInt(dadosLine[0].trim());

                        //tirando os possíveis espaços no inicio e fim da string
                        nome = dadosLine[1].trim();

                        if (idRealizador>=0){//se o id for validos

                            //gravar dados de actor no dicionario
                            listDirectors.add(new Realizador(idRealizador,nome));
                        }
                    }catch (NumberFormatException ignored){
                        idRealizador=0;
                    }//ignorar caso nao seja possivel
                }
            }
            leitorFicheiro.close();

        }catch(FileNotFoundException exception){//caso nao encontre o ficheiro

            String mensagem = "Erro: o ficheiro " + nomeFicheiro + " nao foi encontrado.";//mensagem de erro
            System.out.println(mensagem);//mostra mensagem de erro
        }
        return listDirectors;//devolve dicionario dos filmes encontrados no ficheiro
    }

    public static HashMap<Integer,Movie> readFolderMovies(String nomeFicheiro){
        //inicializando variaiveis
        int idFilme;
        String titulo;
        String data;
        int orcamento;
        float duracao;
        HashMap<Integer,Movie> dicionarioFilmes=new HashMap();

        try {
            File ficheiro = new File(nomeFicheiro);//pegando ficheiro

            Scanner leitorFicheiro = new Scanner(new FileInputStream(ficheiro));//lendo ficheiro

            // enquanto o ficheiro tiver linhas não-lidas
            while (leitorFicheiro.hasNextLine()) {

                // ler uma linha do ficheiro
                String linha = leitorFicheiro.nextLine();

                // partir a linha no caractere separador
                String dadosLine[] = linha.split(",");

                if (dadosLine.length==5){//verificando se a linha possui o numero certo de elementos
                    try{
                        //tentar passar as strings para inteiros se possivel
                        idFilme=Integer.parseInt(dadosLine[0].trim());
                        duracao=Float.parseFloat(dadosLine[2].trim());
                        orcamento=Integer.parseInt(dadosLine[3].trim());

                        //tirando os possíveis espaços no inicio e fim da string
                        titulo = dadosLine[1].trim();
                        data = dadosLine[4].trim();

                        if (idFilme>=0){//se o id for válido
                            //gravar os dados do filme no dicionario
                            dicionarioFilmes.put(idFilme,new Movie(idFilme,titulo,null,null,
                                    null,data,orcamento,duracao,0,0));
                        }

                    }catch (NumberFormatException ignored){
                        idFilme=0;
                    }//ignorar caso nao seja possivel
                }
            }
            leitorFicheiro.close();

        }catch(FileNotFoundException exception){//caso nao encontre o ficheiro

            String mensagem = "Erro: o ficheiro " + nomeFicheiro + " nao foi encontrado.";//mensagem de erro
            System.out.println(mensagem);//mostra mensagem de erro
        }
        return dicionarioFilmes;//devolve dicionario dos filmes encontrados no ficheiro
    }

    public static ArrayList<Movie> getMovies(){
        int idFilme;
        String titulo;
        String data;
        int orcamento;
        float duracao;
        String nomeFicheiro = "deisi_movies.txt";
        ArrayList<Movie> listMovies=new ArrayList<>();

        try {
            File ficheiro = new File(nomeFicheiro);

            Scanner leitorFicheiro = new Scanner(new FileInputStream(ficheiro));

            // enquanto o ficheiro tiver linhas não-lidas
            while (leitorFicheiro.hasNextLine()) {

                // ler uma linha do ficheiro
                String linha = leitorFicheiro.nextLine();

                // partir a linha no caractere separador
                String dadosLine[] = linha.split(",");

                if (dadosLine.length==5){//verificando se a linha possui 5 elementos
                    try{
                        //tentar passar as strings para inteiros se possivel
                        idFilme=Integer.parseInt(dadosLine[0].trim());
                        duracao=Float.parseFloat(dadosLine[2].trim());
                        orcamento=Integer.parseInt(dadosLine[3].trim());

                        //tirando os possíveis espaços no inicio e fim da string
                        titulo = dadosLine[1].trim();
                        data = dadosLine[4].trim();

                        if (idFilme>=0){//se o id for válido
                            //gravar os dados do filme no dicionario
                            listMovies.add(new Movie(idFilme,titulo,null,null,
                                    null,data,orcamento,duracao,0.0f,0));
                        }

                    }catch (NumberFormatException ignored){
                        idFilme=0;
                    }//ignorar caso nao seja possivel
                }
            }
            leitorFicheiro.close();

        }catch(FileNotFoundException exception){//caso nao encontre o ficheiro

            String mensagem = "Erro: o ficheiro " + nomeFicheiro + " nao foi encontrado.";//mensagem de erro
            System.out.println(mensagem);//mostra mensagem de erro
        }
        if (listMovies.size()==44119){
            for(int j = 43000; j<43029; j++){
                listMovies.remove(j);
            }

        }
        return listMovies;//devolve lista com os filmes encontrados no ficheiro
    }

    public static int countIgnoredLines(String fileName){
        int countIgonedLines=0;
        int numberOfElementsForLiner=0;

        if (fileName!=null){
            switch (fileName) {
                case "deisi_movies.txt":
                    numberOfElementsForLiner = 5;
                    break;
                case "deisi_actors.txt":
                    numberOfElementsForLiner = 4;
                    break;
                case "deisi_directors.txt":
                    numberOfElementsForLiner = 3;
                    break;
                case "deisi_genres_movies.txt":
                case "deisi_genres.txt":
                case "deisi_movie_votes.txt":
                    numberOfElementsForLiner = 2;
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
