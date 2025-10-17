package fr.uvsq.cprog.collex;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DnsTUITest {
    private DnsTUI tui;
    private Dns dns;

    @Before
    public void setUp() {
        tui = new DnsTUI();
        dns = new Dns();
    }

    @Test
    public void testCommandeVide() {
        Commande c = tui.nextCommande("");
        assertNotNull(c);
        String result = c.execute(dns);
        assertTrue(result.contains("vide"));
    }

    @Test
    public void testCommandeRechercheIP() {
        Commande c = tui.nextCommande("www.uvsq.fr");
        assertTrue(c instanceof CommandeRechercheIP);
        String result = c.execute(dns);
        assertEquals("193.51.31.90", result);
    }

    @Test
    public void testCommandeRechercheNom() {
        Commande c = tui.nextCommande("193.51.31.90");
        assertTrue(c instanceof CommandeRechercheNom);
        String result = c.execute(dns);
        assertEquals("www.uvsq.fr", result);
    }

    @Test
    public void testCommandeListeDomaine() {
        Commande c = tui.nextCommande("ls uvsq.fr");
        assertTrue(c instanceof CommandeListeDomaine);
        String result = c.execute(dns);
        assertTrue(result.contains("www.uvsq.fr"));
    }

    @Test
    public void testCommandeAdd() {
        Commande c = tui.nextCommande("add 123.51.25.99 fewhost.uvsq.fr");
        assertTrue(c instanceof CommandeAjout);
        String result = c.execute(dns);
        assertTrue(result.contains("Ajout"));
    }

    @Test
    public void testCommandeInconnue() {
        Commande c = tui.nextCommande("inconnue");
        assertNotNull(c);
        String result = c.execute(dns);
        assertTrue(result.contains("non reconnue"));
    }
}
