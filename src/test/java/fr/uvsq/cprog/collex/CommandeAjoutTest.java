package fr.uvsq.cprog.collex;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CommandeAjoutTest {
    private Dns dns;

    @Before
    public void setUp() {
        dns = new Dns();
    }

    @Test
    public void testAjoutValide() {
        Commande cmd = new CommandeAjout("193.52.25.55", "qewhost.uvsq.fr");
        String result = cmd.execute(dns);
        assertTrue(result.contains("Ajout réussi"));
    }

    @Test
    public void testAjoutNomExistant() {
        Commande cmd = new CommandeAjout("193.51.25.99", "www.uvsq.fr");
        String result = cmd.execute(dns);
        assertTrue(result.contains("existe déjà"));
    }
}
