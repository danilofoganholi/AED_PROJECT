package pt.ulusofona.deisi.aedProj2020;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestMovie {
    //Inicializando valores para test
    Realizador realizadorTest = new Realizador(123,"Rafael");
    Genero generoFilmeTest = new Genero(4001,"Ação");
    Actor actorTest = new Actor(1000,"Matheus barcelos",'M',1010);
    Movie filme = new Movie(1010,"O teste do Danilo",actorTest,realizadorTest,generoFilmeTest,"01-10-2010");

    @Test
    public void testToStringMovie() {
        //testando o toString da class movie
        String resultadoReal = filme.toString();
        String resultadoEsperado = "1010 | O teste do Danilo | 2010-10-01";
        assertEquals("Erro no toString() da class movie",resultadoEsperado,resultadoReal);
    }

    @Test
    public void testToStringMovieDataInvalida() {
        //test do toString da class movie com data invalida
        filme.dataLancamento="10-0-2010";
        String resultadoReal = filme.toString();
        String resultadoEsperado = "1010 | O teste do Danilo | 0000-00-00";
        assertEquals("Erro no toString() da class movie com data inválida", resultadoReal, resultadoEsperado);
    }

    @Test
    public void testToStringMovieDataInvertida() {
        //test do toString da class movie com data já invertida
        filme.dataLancamento="2010-10-15";
        String resultadoReal = filme.toString();
        String resultadoEsperado = "1010 | O teste do Danilo | 2010-10-15";
        assertEquals("Erro no toString() da class movie com data já invertida", resultadoReal, resultadoEsperado);
    }
}
