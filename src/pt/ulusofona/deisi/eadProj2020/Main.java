package pt.ulusofona.deisi.eadProj2020;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void parseFiles(){
        Actor actor;
        Realizador realizador;
        Genero genero;
        int mediaVotos;
        int numeroVotos;

        HashMap<Integer,Movie> dicionarioMovies = readFolderMovies("deisi_movies.txt");
        ArrayList<Actor> dicionarioActors = readFolderActors("deisi_actors.txt");
        ArrayList<Realizador> dicionarioDirectors = readFolderDirectors("deisi_directors.txt");
        HashMap<Integer,String> dicionarioGenres = readFolderGenres("deisi_genres.txt");
        HashMap<Integer, Integer> dicionarioGenresMovie = readFolderGenresMovie("deisi_genres_movies.txt");
        HashMap<Integer, Vote> dicionarioMovieVotes = readFolderMovieVotes("deisi_movie_votes.txt");
    }
    public static HashMap<Integer,Vote> readFolderMovieVotes(String nomeFicheiro){
        int idFilme;
        float mediaVotos;
        int numeroDeVotos;

        HashMap<Integer,Vote> dicionarioMovieVotes=new HashMap();

        try {
            File ficheiro = new File(nomeFicheiro);

            Scanner leitorFicheiro = new Scanner(new FileInputStream(ficheiro));

            // enquanto o ficheiro tiver linhas não-lidas
            while (leitorFicheiro.hasNextLine()) {

                // ler uma linha do ficheiro
                String linha = leitorFicheiro.nextLine();

                // partir a linha no caractere separador
                String dadosLine[] = linha.split(",");

                if (dadosLine.length==3){//verificando se a linha possui 5 elementos
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
                    }catch (NumberFormatException ignored){}//ignorar caso nao seja possivel
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
        int idGenero;
        int idFilmes;

        HashMap<Integer,Integer> dicionarioGenresMovie=new HashMap();

        try {
            File ficheiro = new File(nomeFicheiro);

            Scanner leitorFicheiro = new Scanner(new FileInputStream(ficheiro));

            // enquanto o ficheiro tiver linhas não-lidas
            while (leitorFicheiro.hasNextLine()) {

                // ler uma linha do ficheiro
                String linha = leitorFicheiro.nextLine();

                // partir a linha no caractere separador
                String dadosLine[] = linha.split(",");

                if (dadosLine.length==2){//verificando se a linha possui 5 elementos
                    try{
                        //tentar passar as strings para inteiros se possivel
                        idGenero=Integer.parseInt(dadosLine[0].trim());
                        idFilmes=Integer.parseInt(dadosLine[1].trim());

                        //se o id for maior igual a 0
                        if (idGenero>=0 && idFilmes>=0){
                            //colocar ids no dicionario
                            dicionarioGenresMovie.put(idGenero,idFilmes);
                        }

                    }catch (NumberFormatException ignored){}//ignorar caso nao seja possivel
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
        int idGenero;
        String nome;

        HashMap<Integer,String> dicionarioGenres=new HashMap();

        try {
            File ficheiro = new File(nomeFicheiro);

            Scanner leitorFicheiro = new Scanner(new FileInputStream(ficheiro));

            // enquanto o ficheiro tiver linhas não-lidas
            while (leitorFicheiro.hasNextLine()) {

                // ler uma linha do ficheiro
                String linha = leitorFicheiro.nextLine();

                // partir a linha no caractere separador
                String dadosLine[] = linha.split(",");

                if (dadosLine.length==2){//verificando se a linha possui 5 elementos
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

                    }catch (NumberFormatException ignored){}//ignorar caso nao seja possivel
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
        int idActor;
        String nome;
        char genero;
        int idFilmes;

        ArrayList<Actor> listActors=new ArrayList<>();

        try {
            File ficheiro = new File(nomeFicheiro);

            Scanner leitorFicheiro = new Scanner(new FileInputStream(ficheiro));

            // enquanto o ficheiro tiver linhas não-lidas
            while (leitorFicheiro.hasNextLine()) {

                // ler uma linha do ficheiro
                String linha = leitorFicheiro.nextLine();

                // partir a linha no caractere separador
                String dadosLine[] = linha.split(",");

                if (dadosLine.length==4){//verificando se a linha possui 4 elementos
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
                    }catch (NumberFormatException ignored){}//ignorar caso nao seja possivel
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
        int idRealizador;
        String nome;

        ArrayList<Realizador> listDirectors=new ArrayList<>();

        try {
            File ficheiro = new File(nomeFicheiro);

            Scanner leitorFicheiro = new Scanner(new FileInputStream(ficheiro));

            // enquanto o ficheiro tiver linhas não-lidas
            while (leitorFicheiro.hasNextLine()) {

                // ler uma linha do ficheiro
                String linha = leitorFicheiro.nextLine();

                // partir a linha no caractere separador
                String dadosLine[] = linha.split(",");

                if (dadosLine.length==2){//verificando se a linha possui 2 elementos apenas
                    try{
                        //tentar passar as strings para inteiros se possivel
                        idRealizador=Integer.parseInt(dadosLine[0].trim());

                        //tirando os possíveis espaços no inicio e fim da string
                        nome = dadosLine[1].trim();

                        if (idRealizador>=0){//se o id for validos

                            //gravar dados de actor no dicionario
                            listDirectors.add(new Realizador(idRealizador,nome));
                        }
                    }catch (NumberFormatException ignored){}//ignorar caso nao seja possivel
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
        int idFilme;
        String titulo;
        String data;
        int orcamento;
        float duracao;

        HashMap<Integer,Movie> dicionarioFilmes=new HashMap();

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
                            dicionarioFilmes.put(idFilme,new Movie(idFilme,titulo,null,null,
                                    null,data,orcamento,duracao,0,0));
                        }

                    }catch (NumberFormatException ignored){}//ignorar caso nao seja possivel
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
        return new ArrayList<>();
    }

    public static int countIgnoredLines(String fileName){
        return 0;
    }

    public static void main(String[] args) {

    }
}
