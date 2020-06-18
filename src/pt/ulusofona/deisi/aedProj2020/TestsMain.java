package pt.ulusofona.deisi.aedProj2020;

import org.junit.Test;
import java.util.HashMap;
import java.util.LinkedHashMap;
import static org.junit.Assert.*;

public class TestsMain {

    @Test
    public void testCountIgnoreLines() {
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();

        FunctionsParseFiles.readFolder("./test-files/", "deisi_movies.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_actors.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_directors.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_genres.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_genres_movies.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_movie_votes.txt");

        int resultadoReal = Main.countIgnoredLines("deisi_movies.txt");
        System.out.println(resultadoReal);

        //String resultadoReal = filme.toString();
        int resultadoEsperado = 1;
        assertEquals("Erro no countInoreLines do ficheiro movies",resultadoEsperado,resultadoReal);
    }

    @Test
    public void testToStringMovie() {
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();

        FunctionsParseFiles.readFolder("./test-files/", "deisi_movies.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_actors.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_directors.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_genres.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_genres_movies.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_movie_votes.txt");

        String resultadoReal = Main.dicionarioMovies.get(10428).toString();

        //String resultadoReal = filme.toString();
        String resultadoEsperado = "10428 | Hackers | 1995-09-14 | 3 | 1 | 0 | 1";
        assertEquals("Erro no toString() da class movie",resultadoEsperado,resultadoReal);
    }

    @Test
    public void testToString() {
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();

        FunctionsParseFiles.readFolder("./test-files/", "deisi_movies.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_actors.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_directors.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_genres.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_genres_movies.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_movie_votes.txt");

        HashMap<Integer,Actor> resultado = Main.dicionarioMovies.get(10428).actores;
        String resultadoReal = resultado.get(11701).toString();
        System.out.println(resultadoReal);

        //String resultadoReal = filme.toString();
        String resultadoEsperado = "11701 | Angelina Jolie | F\n";
        assertEquals("Erro no toString() da class movie",resultadoEsperado,resultadoReal);
    }

    @Test
    public void testCountMoviesMonthYear(){
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();

        FunctionsParseFiles.readFolder("./test-files/", "deisi_movies.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_actors.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_directors.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_genres.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_genres_movies.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_movie_votes.txt");

        int resultadoReal = FunctionsQuery.countMoviesMonthYear(9,1998);
        int resultadoEsperado = 1;
        assertEquals("Erro contar quantos filmes foram lançados em um certo mes-ano",
                resultadoEsperado,resultadoReal);
    }

    @Test
    public void testTopMonthMovieCount(){
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();

        FunctionsParseFiles.readFolder("./test-files/", "deisi_movies.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_actors.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_directors.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_genres.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_genres_movies.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_movie_votes.txt");

        String resultadoReal = FunctionsQuery.topMonthMovieCount(1998);
        String resultadoEsperado = "9:1";
        assertEquals("Erro procurar o mês com mais filmes em um certo ano",
                resultadoEsperado,resultadoReal);
    }

    @Test
    public void testGetActorsByDirector(){
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();

        FunctionsParseFiles.readFolder("./test-files/", "deisi_movies.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_actors.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_directors.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_genres.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_genres_movies.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_movie_votes.txt");

        String resultadoReal = FunctionsQuery.getActorsByDirector(2, "Danilo Diretor");
        String resultadoEsperado = "";
        assertEquals("Erro ao procurar os atores que trabalharam pelo menos N vezes com o diretor X",
                resultadoEsperado,resultadoReal);
    }

    @Test
    public void testTopMoviesWithGenderBias(){
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();

        FunctionsParseFiles.readFolder("./test-files/", "deisi_movies.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_actors.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_directors.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_genres.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_genres_movies.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_movie_votes.txt");

        String resultadoReal = FunctionsQuery.topMoviesWithGenderBias(0, 1998);
        String resultadoEsperado = "Invalid parameters.Try again.";
        assertEquals("Erro ao calcular a discrepancia percentual entre os generos",
                resultadoEsperado,resultadoReal);
    }

    @Test
    public void testInsertDirector(){
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();

        FunctionsParseFiles.readFolder("./test-files/", "deisi_movies.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_actors.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_directors.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_genres.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_genres_movies.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_movie_votes.txt");

        String resultadoReal = FunctionsQuery.insertDirector(1212, "Foganholi",3000);
        String resultadoEsperado = "OK";
        assertEquals("Erro ao procurar os atores que trabalharam pelo menos N vezes com o diretor X",
                resultadoEsperado,resultadoReal);
        resultadoReal = Main.dicionarioMovies.get(3000).directors.get(1212).nome;
        resultadoEsperado="Foganholi";
        assertEquals("Erro ao procurar o diretor inserido",resultadoEsperado,resultadoReal);
    }

    @Test
    public void testGetMovieWithActorContaining(){
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();

        FunctionsParseFiles.readFolder("./test-files/", "deisi_movies.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_actors.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_directors.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_genres.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_genres_movies.txt");
        FunctionsParseFiles.readFolder("./test-files/","deisi_movie_votes.txt");

        String resultadoReal = FunctionsQuery.getMovieWithActorContaining( "Kim");
        String resultadoEsperado = "Big Trouble in Little China";
        assertEquals("Erro ao procurar os filmes que possuem pelos menos um " +
                "ator cujo nome inclui uma sub-string X", resultadoEsperado,resultadoReal);
    }
}
