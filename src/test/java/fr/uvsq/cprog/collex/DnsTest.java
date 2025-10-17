package fr.uvsq.cprog.collex;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class DnsTest {
    private Dns dns;

    @Before
    public void setUp() {
        dns = new Dns();
    }

    @Test
    public void testGetItemParNom() {
        DnsItem item = dns.getItem(new NomMachine("www.uvsq.fr"));
        assertNotNull(item);
        assertEquals("www.uvsq.fr", item.getNom().getNomQual());
    }

    @Test
    public void testGetItemParAdresse() {
        DnsItem item = dns.getItem(new AdresseIP("193.51.31.90"));
        assertNotNull(item);
        assertEquals("193.51.31.90", item.getAdresse().getIP());
    }

    @Test
    public void testGetItemsParDomaine() {
        List<DnsItem> items = dns.getItems("uvsq.fr");
        assertFalse(items.isEmpty());
        assertTrue(items.stream().allMatch(i -> i.getNom().getDomaine().equals("uvsq.fr")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemNomDejaExistant() {
        dns.addItem(new AdresseIP("193.51.31.91"), new NomMachine("www.uvsq.fr"));
    }
}
