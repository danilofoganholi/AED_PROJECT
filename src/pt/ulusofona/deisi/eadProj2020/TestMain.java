package pt.ulusofona.deisi.eadProj2020;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;

public class TestMain {

    ////////////////////////////////////////////testes que leem o ficheiro movies///////////////////////////////////////

    @Test
    public void testReadFolderMovies_NotFound() {
        //testando se mostra o erro quando não achar o ficheiro

        //chamando função que le arquivo
        HashMap<Integer,Movie> dicionarioMovies = Main.readFolderMovies("deisi_bla.txt");
    }

    @Test
    public void testReadFolderMovies_NormaCase() {
        //testando o se a leitura do ficheiro movies esta  correta

        //chamando função que le arquivo
        HashMap<Integer,Movie> dicionarioMovies = Main.readFolderMovies("deisi_movies.txt");

        //teste primeiro objeto movie na primeira linha
        String errorMessage="Erro na leitura do ficheiro movie - Normal Case";
        assertEquals(errorMessage,603,dicionarioMovies.get(603).id);
        assertEquals(errorMessage,"The Matrix",dicionarioMovies.get(603).titulo);
        assertEquals(errorMessage,136.0,dicionarioMovies.get(603).duracao,0.01);
        assertEquals(errorMessage,63000000,dicionarioMovies.get(603).orcamento);
        assertEquals(errorMessage,"30-03-1999",dicionarioMovies.get(603).dataLancamento);
    }

    @Test
    public void testReadFolderMovies_MoreSpace() {
        //testando o se a leitura do ficheiro movies esta  correta mesmo contendo espacos a mais

        //chamando função que le arquivo
        HashMap<Integer,Movie> dicionarioMovies = Main.readFolderMovies("deisi_movies.txt");

        //teste primeiro objeto movie na primeira linha
        String errorMessage="Erro na leitura do ficheiro movie com espaços a mais no meio";
        assertEquals(errorMessage,6978,dicionarioMovies.get(6978).id);
        assertEquals(errorMessage,"Big Trouble in Little China",dicionarioMovies.get(6978).titulo);
        assertEquals(errorMessage,99.0,dicionarioMovies.get(6978).duracao,0.01);
        assertEquals(errorMessage,25000000,dicionarioMovies.get(6978).orcamento);
        assertEquals(errorMessage,"30-05-1986",dicionarioMovies.get(6978).dataLancamento);
    }

    @Test
    public void testReadFolderMovies_NegativeID() {
        //testando o se a leitura do ficheiro movies esta  correta mesmo contendo id negativo

        //chamando função que le arquivo
        HashMap<Integer,Movie> dicionarioMovies = Main.readFolderMovies("deisi_movies.txt");

        //teste primeiro objeto movie na primeira linha
        String errorMessage="Erro na leitura do ficheiro movie com id negativos";
        Assert.assertNull(errorMessage,dicionarioMovies.get(-40));
    }

    @Test
    public void testReadFolderMovies_MoreParameters() {
        //testando o se a leitura do ficheiro movies esta correta mesmo contendo mais parametros

        //chamando função que le arquivo
        HashMap<Integer,Movie> dicionarioMovies = Main.readFolderMovies("deisi_movies.txt");

        //teste primeiro objeto movie na primeira linha
        String errorMessage="Erro na leitura do ficheiro movie. Ainda esta lendo linhas com mais que 5 parametros";
        Assert.assertNull(errorMessage,dicionarioMovies.get(999999));
    }

    ///////////////////////////////////////////testes que leem o ficheiro actors ///////////////////////////////////////
    @Test
    public void testReadFolderActors_NormaCase() {
        //testando o se a leitura do ficheiro movies esta  correta

        //chamando função que le arquivo
        ArrayList<Actor> ListActors = Main.readFolderActors("deisi_actors.txt");

        //teste primeiro objeto movie na primeira linha
        String errorMessage="Erro na leitura do ficheiro actors - Normal Case";
        assertEquals(errorMessage,11701,ListActors.get(0).idActor);
        assertEquals(errorMessage,"Angelina Jolie",ListActors.get(0).nome);
        assertEquals(errorMessage,'F',ListActors.get(0).genero);
        assertEquals(errorMessage,1995,ListActors.get(0).idFilmes);
    }

    @Test
    public void testReadFolderActorss_MoreSpace() {
        //testando o se a leitura do ficheiro movies esta  correta mesmo contendo espacos a mais

        //chamando função que le arquivo
        ArrayList<Actor> ListActors = Main.readFolderActors("deisi_actors.txt");

        //teste primeiro objeto movie na primeira linha
        String errorMessage="Erro na leitura do ficheiro com espaços a mais no meio";
        assertEquals(errorMessage,6856,ListActors.get(3).idActor);
        assertEquals(errorMessage,"Kurt Russell",ListActors.get(3).nome);
        assertEquals(errorMessage,'M',ListActors.get(3).genero);
        assertEquals(errorMessage,6978,ListActors.get(3).idFilmes);
    }

    @Test
    public void testReadFolderActors_NegativeID() {
        //testando o se a leitura do ficheiro movies esta  correta mesmo contendo id negativo

        //chamando função que le arquivo
        ArrayList<Actor> ListActors = Main.readFolderActors("deisi_actors.txt");

        //teste primeiro objeto movie na primeira linha
        String errorMessage="Erro na leitura do ficheiro com id negativos";
        Assert.assertEquals(errorMessage,2109,ListActors.get(4).idActor);
    }

    @Test
    public void testReadFolderActors_MoreParameters() {
        //testando o se a leitura do ficheiro movies esta correta mesmo contendo mais parametros

        //chamando função que le arquivo
        ArrayList<Actor> ListActors = Main.readFolderActors("deisi_actors.txt");

        //teste primeiro objeto movie na primeira linha
        String errorMessage="Erro na leitura do ficheiro. Ainda esta lendo linhas com mais que 5 parametros";
        Assert.assertEquals(errorMessage,6856,ListActors.get(3).idActor);
    }
}
