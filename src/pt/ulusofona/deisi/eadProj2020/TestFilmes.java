package pt.ulusofona.deisi.eadProj2020;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestFilmes {
    @Test
    public void testToString() {
        //Inicializando valores para test
        Realizador realizadorTest = new Realizador(123,"Rafael");
        Genero generoFilmeTest = new Genero(4001,"Ação");
        Actor actorTest = new Actor(1000,"Matheus barcelos",'M',1010);
        Movie filme = new Movie(1010,"O teste do Danilo",actorTest,realizadorTest,generoFilmeTest,"01-10-2010");
        //testando o toString da class movie
        String resultadoReal = filme.toString();
        String resultadoEsperado = "1010 | O teste do Danilo | 2010-10-01";
        assertEquals("Erro no toString() da class movie",resultadoEsperado,resultadoReal);
        //test do toString da class movie com data invalida
        filme.dataLancamento="10-0-2010";
        String resultadoReal1 = filme.toString();
        resultadoEsperado = "1010 | O teste do Danilo | 0000-00-00";
        assertEquals("Erro no toString() da class movie com data inválida", resultadoReal1, resultadoEsperado);
    }
}
