package pt.ulusofona.deisi.aedProj2020;
import org.junit.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import static org.junit.Assert.*;

public class TestsMain {

    @Test
    public void testCountIgnoreLines() throws IOException {
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.dicionarioAno = new HashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();

        FunctionsParseFiles.folder = "./test-files/";

        FunctionsParseFiles.readFolderMovie();
        FunctionsParseFiles.readFolderActors();
        FunctionsParseFiles.readFolderDirectors();
        FunctionsParseFiles.readFolderGenres();
        FunctionsParseFiles.readFolderGenresMovies();
        FunctionsParseFiles.readFolderVotes();

        int resultadoReal = Main.countIgnoredLines("deisi_movies.txt");

        //String resultadoReal = filme.toString();
        int resultadoEsperado = 1;
        assertEquals("Erro no countInoreLines do ficheiro movies",resultadoEsperado,resultadoReal);

        FunctionsParseFiles.folder = "";
    }

    @Test
    public void testToStringMovie()throws IOException {
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();
        Main.dicionarioAno = new HashMap<>();

        FunctionsParseFiles.folder = "./test-files/";

        FunctionsParseFiles.readFolderMovie();
        FunctionsParseFiles.readFolderActors();
        FunctionsParseFiles.readFolderDirectors();
        FunctionsParseFiles.readFolderGenres();
        FunctionsParseFiles.readFolderGenresMovies();
        FunctionsParseFiles.readFolderVotes();

        String resultadoReal = Main.dicionarioMovies.get(10428).toString();

        //String resultadoReal = filme.toString();
        String resultadoEsperado = "10428 | Hackers | 1995-09-14 | 3 | 1 | 0 | 1";
        assertEquals("Erro no toString() da class movie",resultadoEsperado,resultadoReal);

        FunctionsParseFiles.folder = "";
    }

    @Test
    public void testToString()throws IOException {
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();
        Main.dicionarioAno = new HashMap<>();

        FunctionsParseFiles.folder = "./test-files/";

        FunctionsParseFiles.readFolderMovie();
        FunctionsParseFiles.readFolderActors();
        FunctionsParseFiles.readFolderDirectors();
        FunctionsParseFiles.readFolderGenres();
        FunctionsParseFiles.readFolderGenresMovies();
        FunctionsParseFiles.readFolderVotes();

        HashMap<Integer,Actor> resultado = Main.dicionarioMovies.get(10428).actores;
        String resultadoReal = resultado.get(11701).toString();

        //String resultadoReal = filme.toString();
        String resultadoEsperado = "11701 | Angelina Jolie | F\n";
        assertEquals("Erro no toString() da class movie",resultadoEsperado,resultadoReal);

        FunctionsParseFiles.folder = "";
    }

    @Test
    public void testCountMoviesMonthYear()throws IOException{
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();
        Main.dicionarioAno = new HashMap<>();

        FunctionsParseFiles.folder = "./test-files/";

        FunctionsParseFiles.readFolderMovie();
        FunctionsParseFiles.readFolderActors();
        FunctionsParseFiles.readFolderDirectors();
        FunctionsParseFiles.readFolderGenres();
        FunctionsParseFiles.readFolderGenresMovies();
        FunctionsParseFiles.readFolderVotes();

        int resultadoReal = FunctionsQuery.countMoviesMonthYear(9,1998);
        int resultadoEsperado = 1;
        assertEquals("Erro contar quantos filmes foram lançados em um certo mes-ano",
                resultadoEsperado,resultadoReal);
      
        FunctionsParseFiles.folder = "";
    }

    @Test
    public void testTopMonthMovieCount()throws IOException{
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();
        Main.dicionarioAno = new HashMap<>();

        FunctionsParseFiles.folder = "./test-files/";

        FunctionsParseFiles.readFolderMovie();
        FunctionsParseFiles.readFolderActors();
        FunctionsParseFiles.readFolderDirectors();
        FunctionsParseFiles.readFolderGenres();
        FunctionsParseFiles.readFolderGenresMovies();
        FunctionsParseFiles.readFolderVotes();

        String resultadoReal = FunctionsQuery.topMonthMovieCount(1998);
        String resultadoEsperado = "9:1";
        assertEquals("Erro procurar o mês com mais filmes em um certo ano",
                resultadoEsperado,resultadoReal);

        FunctionsParseFiles.folder = "";
    }

    @Test
    public void testGetActorsByDirector()throws IOException{
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();
        Main.dicionarioAno = new HashMap<>();

        FunctionsParseFiles.folder = "./test-files/";

        FunctionsParseFiles.readFolderMovie();
        FunctionsParseFiles.readFolderActors();
        FunctionsParseFiles.readFolderDirectors();
        FunctionsParseFiles.readFolderGenres();
        FunctionsParseFiles.readFolderGenresMovies();
        FunctionsParseFiles.readFolderVotes();

        String resultadoReal = FunctionsQuery.getActorsByDirector(2, "Danilo Diretor");
        String resultadoEsperado = "";
        assertEquals("Erro ao procurar os atores que trabalharam pelo menos N vezes com o diretor X",
                resultadoEsperado,resultadoReal);

        FunctionsParseFiles.folder = "";
    }

    @Test
    public void testTopMoviesWithGenderBias()throws IOException{
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();
        Main.dicionarioAno = new HashMap<>();

        FunctionsParseFiles.folder = "./test-files/";

        FunctionsParseFiles.readFolderMovie();
        FunctionsParseFiles.readFolderActors();
        FunctionsParseFiles.readFolderDirectors();
        FunctionsParseFiles.readFolderGenres();
        FunctionsParseFiles.readFolderGenresMovies();
        FunctionsParseFiles.readFolderVotes();

        String resultadoReal = FunctionsQuery.topMoviesWithGenderBias(0, 1998);
        String resultadoEsperado = "";
        assertEquals("Erro ao calcular a discrepancia percentual entre os generos",
                resultadoEsperado,resultadoReal);

        FunctionsParseFiles.folder = "";
    }

    @Test
    public void testInsertDirector()throws IOException{
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();
        Main.dicionarioAno = new HashMap<>();

        FunctionsParseFiles.folder = "./test-files/";

        FunctionsParseFiles.readFolderMovie();
        FunctionsParseFiles.readFolderActors();
        FunctionsParseFiles.readFolderDirectors();
        FunctionsParseFiles.readFolderGenres();
        FunctionsParseFiles.readFolderGenresMovies();
        FunctionsParseFiles.readFolderVotes();


        String resultadoReal = FunctionsQuery.insertDirector(1212, "Foganholi",3000);
        String resultadoEsperado = "OK";
        assertEquals("Erro ao procurar os atores que trabalharam pelo menos N vezes com o diretor X",
                resultadoEsperado,resultadoReal);

        resultadoReal = Main.dicionarioMovies.get(3000).directors.get("Foganholi").nome;
        resultadoEsperado="Foganholi";
        assertEquals("Erro ao procurar o diretor inserido",resultadoEsperado,resultadoReal);

        FunctionsParseFiles.folder = "";
    }

    @Test
    public void testGetMovieWithActorContaining()throws IOException{
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();
        Main.dicionarioAno = new HashMap<>();

        FunctionsParseFiles.folder = "./test-files/";

        FunctionsParseFiles.readFolderMovie();
        FunctionsParseFiles.readFolderActors();
        FunctionsParseFiles.readFolderDirectors();
        FunctionsParseFiles.readFolderGenres();
        FunctionsParseFiles.readFolderGenresMovies();
        FunctionsParseFiles.readFolderVotes();

        String resultadoReal = FunctionsQuery.getMovieWithActorContaining( "Kim");
        String resultadoEsperado = "Big Trouble in Little China";
        assertEquals("Erro ao procurar os filmes que possuem pelos menos um " +
                "ator cujo nome inclui uma sub-string X", resultadoEsperado,resultadoReal);

        FunctionsParseFiles.folder = "";
    }

    @Test
    public void top6DirectorsWithFamily()throws IOException{
        Main.dicionarioMovies = new LinkedHashMap<>();
        Main.ignoredLine = new int[6];
        Main.legendaGeneros = new HashMap<>();
        Main.dicionarioAno = new HashMap<>();

        FunctionsParseFiles.folder = "./test-files/";

        FunctionsParseFiles.readFolderMovie();
        FunctionsParseFiles.readFolderActors();
        FunctionsParseFiles.readFolderDirectors();
        FunctionsParseFiles.readFolderGenres();
        FunctionsParseFiles.readFolderGenresMovies();
        FunctionsParseFiles.readFolderVotes();

        String resultadoReal = FunctionsQuery.top6DirectorsWithFamily( 2000,2005);
        String resultadoEsperado = "\n\n\n\n\n\n";
        assertEquals("Erro ao procurar diretores que realizaram trabalhos juntos com" +
                "familiares", resultadoEsperado,resultadoReal);

        FunctionsParseFiles.folder = "";
    }
}
