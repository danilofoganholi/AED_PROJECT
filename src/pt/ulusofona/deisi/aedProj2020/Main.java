package pt.ulusofona.deisi.aedProj2020;
import java.util.*;

public class Main {
    //array que guarda as linhas ignoradas: (index-referencia)
    //0-Movie  | 1-Actors  | 2-Directors  | 3-Genres  | 4-GenresMovie  | 5-Votes
    static int[] ignoredLine = new int[6];

    //dicionario para guardar os dados dos filmes em ordem
    static LinkedHashMap<Integer,Movie> dicionarioMovies = new LinkedHashMap<>();

    //dicionario para guardar qual genero corresponde a qual id
    static HashMap<Integer,String> legendaGeneros = new HashMap<>();

    public static void parseFiles(){
        //inicializando variaveis que guardaram os retornos das leituras dos ficheiros
        dicionarioMovies = new LinkedHashMap<>();
        ignoredLine = new int[6];
        legendaGeneros = new HashMap<>();

//        //chamando funcoes que leem os ficheiros
//        long incio = System.currentTimeMillis();
//        FunctionsParseFiles.readFolder("","deisi_movies.txt");
//        long fim = System.currentTimeMillis();
//        System.out.println("Movies = "+ (fim-incio));
//
//        incio = System.currentTimeMillis();
//        FunctionsParseFiles.readFolder("","deisi_actors.txt");
//        fim = System.currentTimeMillis();
//        System.out.println("Actors = "+ (fim-incio));
//
//        incio = System.currentTimeMillis();
//        FunctionsParseFiles.readFolder("","deisi_directors.txt");
//        fim = System.currentTimeMillis();
//        System.out.println("Directors = "+ (fim-incio));
//
//        incio = System.currentTimeMillis();
//        FunctionsParseFiles.readFolder("","deisi_genres.txt");
//        fim = System.currentTimeMillis();
//        System.out.println("Genres = "+ (fim-incio));
//
//        incio = System.currentTimeMillis();
//        FunctionsParseFiles.readFolder("","deisi_genres_movies.txt");
//        fim = System.currentTimeMillis();
//        System.out.println("GenresMovie = "+ (fim-incio));
//
//        incio = System.currentTimeMillis();
//        FunctionsParseFiles.readFolder("","deisi_movie_votes.txt");
//        fim = System.currentTimeMillis();
//        System.out.println("Votes = "+ (fim-incio));

        FunctionsParseFiles.readFolder("","deisi_movies.txt");
        FunctionsParseFiles.readFolder("","deisi_actors.txt");
        FunctionsParseFiles.readFolder("","deisi_directors.txt");
        FunctionsParseFiles.readFolder("","deisi_genres.txt");
        FunctionsParseFiles.readFolder("","deisi_genres_movies.txt");
        FunctionsParseFiles.readFolder("","deisi_movie_votes.txt");
    }

    public static ArrayList<Movie> getMovies(){
        parseFiles();
        return new ArrayList<>(dicionarioMovies.values());//devolve lista com os filmes encontrados no ficheiro
    }

    public static int countIgnoredLines(String fileName){
        int countIgonedLines=0;
        switch (fileName) {//verificando qual pasta foi passada
            case "deisi_movies.txt":
                countIgonedLines = ignoredLine[0];
                break;
            case "deisi_actors.txt":
                countIgonedLines = ignoredLine[1];
                break;
            case "deisi_directors.txt":
                countIgonedLines = ignoredLine[2]-1;
                break;
            case "deisi_genres_movies.txt":
                countIgonedLines = ignoredLine[4];
                break;
            case "deisi_genres.txt":
                countIgonedLines = ignoredLine[3];
                break;
            case "deisi_movie_votes.txt":
                countIgonedLines = ignoredLine[5];
                break;
            default:
                break;
        }
        return countIgonedLines;
    }

    public static String askAmbrosio(String query){
        String[] queryArray= query.split(" ");

        switch (queryArray[0].toUpperCase()){
            case "COUNT_MOVIES_MONTH_YEAR":
                try{
                    return Integer.toString(FunctionsQuery.countMoviesMonthYear(
                            Integer.parseInt(queryArray[1].trim()),Integer.parseInt(queryArray[2].trim())));
                }catch (NumberFormatException ignored){
                    return "Invalid parameters. Try again.";
                }
            case "TOP_MONTH_MOVIE_COUNT":
                try{
                    return FunctionsQuery.topMonthMovieCount(Integer.parseInt(queryArray[1].trim()));
                }catch (NumberFormatException ignored){
                    return "Invalid parameters. Try again.";
                }
            case "GET_ACTORS_BY_DIRECTOR":
                try{
                    StringBuilder name = new StringBuilder();
                    for (int i=2;i<queryArray.length;i++){
                        name.append(queryArray[i]).append(" ");
                    }
                    return FunctionsQuery.getActorsByDirector(
                            Integer.parseInt(queryArray[1].trim()),name.toString().trim());
                }catch (NumberFormatException ignored){
                    return "Invalid parameters. Try again.";
                }
            case "TOP_MOVIES_WITH_GENDER_BIAS":
                try{
                    return FunctionsQuery.topMoviesWithGenderBias(Integer.parseInt(queryArray[1].trim()),
                            Integer.parseInt(queryArray[2].trim()));
                }catch (NumberFormatException ignored){
                    return "Invalid parameters. Try again.";
                }
            case "INSERT_DIRECTOR":
                queryArray= query.split(";");
                try{
                    String[] idDirector = queryArray[0].split(" ");
                    return FunctionsQuery.insertDirector(Integer.parseInt(idDirector[1]),
                            queryArray[1],Integer.parseInt(queryArray[2].trim()));
                }catch (NumberFormatException ignored){
                    return "Invalid parameters. Try again.";
                }
            case "GET_MOVIES_WITH_ACTOR_CONTAINING":
                return FunctionsQuery.getMovieWithActorContaining(queryArray[1]);
            default:
                return "Invalid query. Try again.";
        }
    }

    public static String getVideoURL(){
        return "";
    }

    public static void main(String[] args) {

//        long inicio =System.currentTimeMillis();
        getMovies();
//        long fim = System.currentTimeMillis();
//        System.out.println("(demorou " + (fim - inicio) + " ms)");

//        Scanner in = new Scanner(System.in);
//
//        String line = in.nextLine();
//
//        while (line != null && !line.equals("QUIT")) {
//
//            long start = System.currentTimeMillis();
//
//            String result = askAmbrosio(line);
//
//            long end = System.currentTimeMillis();
//
//            System.out.println(result);
//
//            System.out.println("(demorou " + (end - start) + " ms)");
//
//            line = in.nextLine();
//        }
    }
}