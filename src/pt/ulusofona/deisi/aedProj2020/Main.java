package pt.ulusofona.deisi.aedProj2020;
import java.io.IOException;
import java.util.*;

public class Main {
    //array que guarda as linhas ignoradas: (index-referencia)
    //0-Movie  | 1-Actors  | 2-Directors  | 3-Genres  | 4-GenresMovie  | 5-Votes
    static int[] ignoredLine = new int[6];

    //dicionario para guardar os dados dos filmes em ordem
    static LinkedHashMap<Integer,Movie> dicionarioMovies = new LinkedHashMap<>();

    //garda os ids dos filmes nos respectivos anos (ano->id's)
    static HashMap<Integer,ArrayList<Integer>> dicionarioAno = new HashMap<>();

    //dicionario para guardar qual genero cinematográfico corresponde a qual id
    static HashMap<Integer,String> legendaGeneros = new HashMap<>();

    public static void parseFiles()throws IOException {
        //reset variaveis que guardaram os retornos das leituras dos ficheiros
        dicionarioMovies = new LinkedHashMap<>();
        ignoredLine = new int[6];
        legendaGeneros = new HashMap<>();
        dicionarioAno = new HashMap<>();

        //chamando funcoes que leem os ficheiros
        FunctionsParseFiles.readFolderMovie();
        FunctionsParseFiles.readFolderActors();
        FunctionsParseFiles.readFolderDirectors();
        FunctionsParseFiles.readFolderGenres();
        FunctionsParseFiles.readFolderGenresMovies();
        FunctionsParseFiles.readFolderVotes();
    }

    public static ArrayList<Movie> getMovies()throws IOException{
        parseFiles();//le todos os ficheiros e monta o dicionario
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

    public static String askAmbrosio(String query) throws NumberFormatException{
        String[] queryArray= query.split(" ");

        switch (queryArray[0].toUpperCase()){//testando a query
            case "COUNT_MOVIES_MONTH_YEAR":
                //passando os parametros para a função countMoviesMonthYear
                return Integer.toString(FunctionsQuery.countMoviesMonthYear(
                        Integer.parseInt(queryArray[1].trim()),Integer.parseInt(queryArray[2].trim())));

            case "TOP_MONTH_MOVIE_COUNT":
                //passando os parametros para a função topMonthMovieCount
                return FunctionsQuery.topMonthMovieCount(Integer.parseInt(queryArray[1].trim()));

            case "GET_ACTORS_BY_DIRECTOR":
                //montando o nome
                StringBuilder name = new StringBuilder();
                for (int i=2;i<queryArray.length;i++){
                    name.append(queryArray[i]).append(" ");
                }
                //passando os parametros para a função getActorsByDirector
                return FunctionsQuery.getActorsByDirector(
                        Integer.parseInt(queryArray[1].trim()),name.toString().trim());

            case "TOP_MOVIES_WITH_GENDER_BIAS":
                //passando os parametros para a função topMoviesWithGenderBias
                return FunctionsQuery.topMoviesWithGenderBias(Integer.parseInt(queryArray[1].trim()),
                        Integer.parseInt(queryArray[2].trim()));

            case "INSERT_DIRECTOR":
                //dividindo os paramtros no ';'
                queryArray= query.split(";");
                String[] idDirector = queryArray[0].split(" ");
                //passando os parametros para a função insertDirector
                return FunctionsQuery.insertDirector(Integer.parseInt(idDirector[1]),
                        queryArray[1],Integer.parseInt(queryArray[2].trim()));

            case "GET_MOVIES_WITH_ACTOR_CONTAINING":
                //passando os parametros para a função getMovieWithActorContaining
                return FunctionsQuery.getMovieWithActorContaining(queryArray[1]);

            case "TOP_6_DIRECTORS_WITHIN_FAMILY":
                //passando os parametros para a função top6DirectorsWithFamily
                return FunctionsQuery.top6DirectorsWithFamily(Integer.parseInt(queryArray[1]),
                        Integer.parseInt(queryArray[2]));

            default:
                return "Invalid query. Try again.";
        }
    }

    public static String getVideoURL(){
        return "";
    }

    public static void main(String[] args)throws IOException {

//        long inicio =System.currentTimeMillis();
        getMovies();
//        long fim = System.currentTimeMillis();
//        System.out.println("(demorou " + (fim - inicio) + " ms)");
//
//        FunctionsQuery.countMoviesMonthYear(5,2017);
//        FunctionsQuery.topMonthMovieCount(2017);
//        FunctionsQuery.getActorsByDirector(4,"Quentin Tarantino");
//        FunctionsQuery.topMoviesWithGenderBias(14,1998);
//        FunctionsQuery.insertDirector(2190, "Danilo Foganholi",395982 );
//        FunctionsQuery.getMovieWithActorContaining("James");
//        FunctionsQuery.top6DirectorsWithFamily(2000,2005);
//
//        fim = System.currentTimeMillis();
//        System.out.println("(demorou " + (fim - inicio) + " ms)");

        Scanner in = new Scanner(System.in);

        String line = in.nextLine();

        while (line != null && !line.equals("QUIT")) {

            long start = System.currentTimeMillis();

            String result = askAmbrosio(line);

            long end = System.currentTimeMillis();

            System.out.println(result);

            System.out.println("(demorou " + (end - start) + " ms)");

            line = in.nextLine();
        }
    }
}