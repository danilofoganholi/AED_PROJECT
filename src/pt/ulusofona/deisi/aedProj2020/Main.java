package pt.ulusofona.deisi.aedProj2020;
import java.io.*;
import java.util.*;

public class Main {
    //array que guarda as linhas ignoradas: (index-referencia)
    //0-Movie  | 1-Actors  | 2-Directors  | 3-Genres  | 4-GenresMovie  | 5-Votes
    static int[] ignoredLine = new int[6];

    //dicionario para guardar os dados dos filmes
    static HashMap<Integer,Movie> dicionarioMovies = new HashMap<>();

    //dicionario para guardar qual genero corresponde a qual id
    static HashMap<Integer,String> legendaGeneros = new HashMap<>();

    public static void parseFiles(){
        //inicializando variaveis que guardaram os retornos das leituras dos ficheiros
        dicionarioMovies = new HashMap<>();

//        //chamando funcoes que leem os ficheiros
//        long incio = System.currentTimeMillis();
//        readFolder("deisi_movies.txt");
//        long fim = System.currentTimeMillis();
//        System.out.println("Movies = "+ (fim-incio));
//
//        incio = System.currentTimeMillis();
//        readFolder("deisi_actors.txt");
//        fim = System.currentTimeMillis();
//        System.out.println("Actors = "+ (fim-incio));
//
//        incio = System.currentTimeMillis();
//        readFolder("deisi_directors.txt");
//        fim = System.currentTimeMillis();
//        System.out.println("Directors = "+ (fim-incio));
//
//        incio = System.currentTimeMillis();
//        readFolder("deisi_genres.txt");
//        fim = System.currentTimeMillis();
//        System.out.println("Genres = "+ (fim-incio));
//
//        incio = System.currentTimeMillis();
//        readFolder("deisi_genres_movies.txt");
//        fim = System.currentTimeMillis();
//        System.out.println("GenresMovie = "+ (fim-incio));
//
//        incio = System.currentTimeMillis();
//        readFolder("deisi_movie_votes.txt");
//        fim = System.currentTimeMillis();
//        System.out.println("Votes = "+ (fim-incio));

        readFolder("deisi_movies.txt");
        readFolder("deisi_actors.txt");
        readFolder("deisi_directors.txt");
        readFolder("deisi_genres.txt");
        readFolder("deisi_genres_movies.txt");
        readFolder("deisi_movie_votes.txt");
    }

    public static String[] arrumaString(String[] dadosLine){
        if (dadosLine.length>=3 && dadosLine[1].startsWith("\"")){//verificando se possui dois de um elemento
            if (dadosLine[1].endsWith("\"")){
                dadosLine[1]=dadosLine[1].replace("\"\"", "\"");
                dadosLine[1]=dadosLine[1].substring(1,dadosLine[1].length()-1);
                return dadosLine;
            }else{
                String[] stringCorrigida= new String[dadosLine.length-1];
                int count=0;
                for (int i=0; i<dadosLine.length;i++){
                    if (i==1){
                        stringCorrigida[count]=(dadosLine[i]+","+dadosLine[i+1]).replace("\"\"", "\"");
                        i++;
                    }else{
                        stringCorrigida[count]=dadosLine[i];
                    }
                    count++;
                }
                stringCorrigida[1] = stringCorrigida[1].substring(1,stringCorrigida[1].length()-1);
                return stringCorrigida;
            }
        }
        return dadosLine;
    }

    public static void readFolder(String nomeFicheiro) {
        try {
            FileReader ficheiro = new FileReader(nomeFicheiro);//pegando ficheiro
            BufferedReader leitorFicheiro = new BufferedReader(ficheiro);//colocando ficheiro no buffere
            String lines;//variavel para as linhas
            while ((lines = leitorFicheiro.readLine())!=null) {
                String[] dadosLine = lines.split(",");// partir a linha no caractere separador
                switch (nomeFicheiro) {//verificando qual pasta foi passada
                    case "deisi_movies.txt":
                        dadosLine = arrumaString(dadosLine);//verifica se tem aspas ou , no nome e arruma
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 5) {
                            folderMovie(dadosLine);//coloca dados na variavel global adequada
                        }else{ignoredLine[0]++;}
                        break;
                    case "deisi_actors.txt":
                        dadosLine = arrumaString(dadosLine);//verifica se tem aspas ou , no nome e arruma
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 4) {
                            folderActors(dadosLine);//coloca dados na variavel global adequada
                        }else{ignoredLine[1]++;}
                        break;
                    case "deisi_directors.txt":
                        dadosLine = arrumaString(dadosLine);//verifica se tem aspas ou , no nome e arruma
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 3) {
                            folderDirectors(dadosLine);//coloca dados na variavel global adequada
                        }else{ignoredLine[2]++;}
                        break;
                    case "deisi_genres_movies.txt":
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 2) {
                            folderGenresMovies(dadosLine);//coloca dados na variavel global adequada
                        }else{ignoredLine[4]++;}
                        break;
                    case "deisi_genres.txt":
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 2) {
                            folderGenres(dadosLine);//coloca dados na variavel global adequada
                        }else{ignoredLine[3]++;}
                        break;
                    case "deisi_movie_votes.txt":
                        //verificando se a linha possui o numero de elementos correto
                        if (dadosLine.length == 3) {
                            folderVotes(dadosLine);//coloca dados na variavel global adequada
                        }else{
                            ignoredLine[5]++;}
                    default:
                        break;
                }
            }
            leitorFicheiro.close();
        }catch (IOException e) {e.printStackTrace();}
    }

    public static void folderMovie(String dadosLine[]){
        try {
            //tentar passar as strings para inteiros se possivel
            int idFilme = Integer.parseInt(dadosLine[0].trim());

            //verificando se já existe esse id
            if ((idFilme >= 0) && (!dicionarioMovies.containsKey(idFilme))) {
                //gravar os dados do filme no dicionario
                dicionarioMovies.put(idFilme,new Movie(idFilme,dadosLine[1].trim(),dadosLine[4].trim(),
                        Integer.parseInt(dadosLine[3].trim()),Float.parseFloat(dadosLine[2].trim())));
            }
        } catch (NumberFormatException ignored) {ignoredLine[0]++;}//caso de errado a linha sera ignorada
    }

    public static void folderActors(String dadosLine[]){
        try {
            if (dadosLine[3]!=null && dicionarioMovies.get(Integer.parseInt(dadosLine[3].trim()))!=null){
                dicionarioMovies.get(Integer.parseInt(dadosLine[3].trim())).actores.add(
                        new Actor(Integer.parseInt(dadosLine[0].trim()), dadosLine[1].trim(),
                        (dadosLine[2].trim()).charAt(0)));//gravar actor nos filmes realizados por ele
            }
        } catch (NumberFormatException ignored) {ignoredLine[1]++;}//caso de errado a linha sera ignorada
    }

    public static void folderDirectors(String dadosLine[]){
        try {
            if (dadosLine[2]!=null && dicionarioMovies.get(Integer.parseInt(dadosLine[2].trim()))!=null){
                dicionarioMovies.get(Integer.parseInt(dadosLine[2].trim())).directors.add(
                        new Director(Integer.parseInt(dadosLine[0].trim()),
                                dadosLine[1].trim()));//gravar diretor nos filmes realizados por ele
            }
        } catch (NumberFormatException ignored) {ignoredLine[2]++;}//caso de errado a linha sera ignorada

    }

    public static void folderGenres(String dadosLine[]){
        try {
            //colocar genero no dicionario de generos
            legendaGeneros.put(Integer.parseInt(dadosLine[0].trim()),dadosLine[1].trim());

        }catch (NumberFormatException ignored) {ignoredLine[3]++;}//caso de errado a linha sera ignorada
    }

    public static void folderGenresMovies(String dadosLine[]){
        try {//gravar generos nos filmes
            if (dadosLine[1]!=null && dicionarioMovies.get(Integer.parseInt(dadosLine[1].trim()))!=null){
                dicionarioMovies.get(Integer.parseInt(dadosLine[1].trim())).generos.add(
                        new Genero(Integer.parseInt(dadosLine[0].trim()),
                                legendaGeneros.get(Integer.parseInt(dadosLine[0].trim()))));
            }
        } catch (NumberFormatException ignored) {ignoredLine[4]++;}//caso de errado a linha sera ignorada

    }

    public static void folderVotes(String dadosLine[]){
        try {
            if (dicionarioMovies.get(Integer.parseInt(dadosLine[0].trim()))!=null){
                //colocar genero no dicionario de generos
                dicionarioMovies.get(Integer.parseInt(dadosLine[0].trim()))
                        .mediaVotos = Float.parseFloat(dadosLine[1].trim());
                dicionarioMovies.get(Integer.parseInt(dadosLine[0].trim()))
                        .numeroDeVotos = Integer.parseInt(dadosLine[2].trim());
            }
        }catch (NumberFormatException ignored) {ignoredLine[5]++;}//caso de errado a linha sera ignorada
    }

    public static ArrayList<Movie> getMovies(){
        //Inicializando valiaveis
        ArrayList<Movie> listMovies = new ArrayList<>();
        LinkedHashMap<Integer,Movie> dicionarioMoviesOrdenado = new LinkedHashMap<>();
        try {
            FileReader ficheiro = new FileReader("deisi_movies.txt");//pegando ficheiro
            BufferedReader leitorFicheiro = new BufferedReader(ficheiro);//colocando ficheiro no buffere
            String lines;//variavel para as linhas

            while ((lines = leitorFicheiro.readLine())!=null) {
                String[] dadosLine = lines.split(",");// partir a linha no caractere separador
                dadosLine = arrumaString(dadosLine);//verifica se tem aspas ou , no nome e arruma
                //verificando se a linha possui o numero de elementos correto
                if (dadosLine.length == 5) {
                    try {
                        //tentar passar as strings para inteiros se possivel
                        int idFilme = Integer.parseInt(dadosLine[0].trim());

                        //verificando se já existe esse id
                        if ((idFilme >= 0) && (!dicionarioMoviesOrdenado.containsKey(idFilme))) {
                            //gravar os dados do filme no dicionario
                            dicionarioMoviesOrdenado.put(idFilme,
                                    new Movie(idFilme,dadosLine[1].trim(),dadosLine[4].trim(),
                                    Integer.parseInt(dadosLine[3].trim()),
                                            Float.parseFloat(dadosLine[2].trim())));
                        }
                    } catch (NumberFormatException ignored) {
                        listMovies=new ArrayList<>();
                    }
                }

                listMovies.addAll(dicionarioMoviesOrdenado.values());

                return listMovies;
            }
            leitorFicheiro.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return listMovies;//devolve lista com os filmes encontrados no ficheiro
    }

    public static int countIgnoredLines(String fileName){
        int countIgonedLines=0;
        switch (fileName) {//verificando qual pasta foi passada
            case "deisi_movies.txt":
                if (ignoredLine[0]>0){
                    countIgonedLines = ignoredLine[0];
                }else{
                    readFolder("deisi_movies.txt");
                    countIgonedLines = ignoredLine[0];
                }
                break;
            case "deisi_actors.txt":
                if (ignoredLine[1]>0){
                    countIgonedLines = ignoredLine[1];
                }else{
                    readFolder("deisi_actors.txt");
                    countIgonedLines = ignoredLine[1];
                }
                break;
            case "deisi_directors.txt":
                if (ignoredLine[2]>0){
                    countIgonedLines = ignoredLine[2]-1;
                }else{
                    readFolder("deisi_directors.txt");
                    countIgonedLines = ignoredLine[2]-1;
                }
                break;
            case "deisi_genres_movies.txt":
                if (ignoredLine[4]>0){
                    countIgonedLines = ignoredLine[4];
                }else{
                    readFolder("deisi_genres_movies.txt");
                    countIgonedLines = ignoredLine[4];
                }
                break;
            case "deisi_genres.txt":
                if (ignoredLine[3]>0){
                    countIgonedLines = ignoredLine[3];
                }else{
                    readFolder("deisi_genres.txt");
                    countIgonedLines = ignoredLine[3];
                }
                break;
            case "deisi_movie_votes.txt":
                if (ignoredLine[5]>0){
                    countIgonedLines = ignoredLine[5];
                }else{
                    readFolder("deisi_movie_votes.txt");
                    countIgonedLines = ignoredLine[5];
                }
                break;
            default:
                break;
        }
        return countIgonedLines;
    }

    public static String askAmbrosio(String query){
        return "";
    }

    public static String getVideoURL(){
        return "";
    }

    public static int countMoviesMonthYear(int mes,int ano){
        int countMovies=0;
        String mesString,anoString;

        if (mes>12 || mes<1 || ano<1895 || ano>2020){return 0;}

        mesString =(mes<10)? "0"+Integer.toString(mes):Integer.toString(mes);
        anoString =Integer.toString(ano);

        for (Movie movie: dicionarioMovies.values()){
            String[] dataLancamento = movie.dataLancamento.split("-");
            if (dataLancamento[0].equals(anoString) && dataLancamento[1].equals(mesString)){
                countMovies++;
            }
        }

        return countMovies;
    }

    public static boolean haveDirector(String name, HashSet<Director> directors){

        for (Director director : directors){
            if (director.nome.equals(name)){return true;}
        }
        return false;
    }

    public static String topMonthMovieCount(int ano){
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

        for (Movie movie: dicionarioMovies.values()){
            if (haveDirector(nomeRealizador,movie.directors)){
                for (Actor actor: movie.actores){
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
                resposta.append(node.nomeActor).append(":").append(node.count).append("\n");
            }
        }
        return resposta.substring(0,resposta.length()-1);
    }

    public static void main(String[] args) {
//        long incio = System.currentTimeMillis();
        parseFiles();
//        System.out.println(getActorsByDirector(3,"Steven Spielberg"));
//        System.out.println(topMonthMovieCount(1998));
//        System.out.println(countIgnoredLines("deisi_movies.txt"));
//        System.out.println(countIgnoredLines("deisi_actors.txt"));
//        System.out.println(countIgnoredLines("deisi_directors.txt"));
//        System.out.println(countIgnoredLines("deisi_genres_movies.txt"));
//        System.out.println(countIgnoredLines("deisi_genres.txt"));
//        System.out.println(countIgnoredLines("deisi_movie_votes.txt"));
//        long fim = System.currentTimeMillis();
//        System.out.println(fim-incio);
    }
}