package pt.ulusofona.deisi.aedProj2020;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;

public class TestMain {

    @Test
    public void testReadFolderMovies_NegativeID() {
        //testando o se a leitura do ficheiro movies esta  correta mesmo contendo id negativo

        //chamando função que le arquivo
        Main.readFolder("deisi_movies.txt");
        HashMap<Integer,Movie> dicionarioMovies = Main.dicionarioMovies;

        //teste primeiro objeto movie na primeira linha
        String errorMessage="Erro na leitura do ficheiro movie com id negativos";
        Assert.assertNull(errorMessage,dicionarioMovies.get(-40));
    }

    @Test
    public void testReadFolderMovies_MoreParameters() {
        //testando o se a leitura do ficheiro movies esta correta mesmo contendo mais parametros

        //chamando função que le arquivo
        Main.readFolder("deisi_movies.txt");
        HashMap<Integer,Movie> dicionarioMovies = Main.dicionarioMovies;

        //teste primeiro objeto movie na primeira linha
        String errorMessage="Erro na leitura do ficheiro movie. Ainda esta lendo linhas com mais que 5 parametros";
        Assert.assertNull(errorMessage,dicionarioMovies.get(999999));
    }
}
