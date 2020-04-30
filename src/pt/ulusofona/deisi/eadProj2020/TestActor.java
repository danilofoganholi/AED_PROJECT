package pt.ulusofona.deisi.eadProj2020;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestActor {
    @Test
    public void testToString() {
        //Inicializando valores para test
        Actor actorTest = new Actor(1000,"Matheus barcelos",'M',1010);

        //testando o toString da class Actor
        String resultadoReal = actorTest.toString();
        String resultadoEsperado = "(1000 | Matheus barcelos | M | 1010)";
        assertEquals("Erro no toString() da class Actor",resultadoEsperado,resultadoReal);
    }
}
