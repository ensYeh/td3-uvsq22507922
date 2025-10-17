package fr.uvsq.cprog.collex;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CommandeRechercheIPTest {
    private Dns dns;

    @Before
    public void setUp() {
        dns = new Dns();
    }

    @Test
    public void testRechercheIPExistante() {
        Commande cmd = new CommandeRechercheIP("www.uvsq.fr");
        String result = cmd.execute(dns);
        assertEquals("193.51.31.90", result);
    }

    @Test
    public void testRechercheIPInexistante() {
        Commande cmd = new CommandeRechercheIP("inconnue.uvsq.fr");
        String result = cmd.execute(dns);
        assertTrue(result.contains("Aucune entrée"));
    }
}
