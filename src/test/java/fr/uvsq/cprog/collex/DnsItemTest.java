package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class DnsItemTest {

    @Test
    public void testCreationDnsItem() {
        AdresseIP ip = new AdresseIP("193.51.31.90");
        NomMachine nom = new NomMachine("www.uvsq.fr");
        DnsItem item = new DnsItem(ip, nom);
        assertEquals(ip, item.getAdresse());
        assertEquals(nom, item.getNom());
    }

    @Test
    public void testToString() {
        DnsItem item = new DnsItem(new AdresseIP("193.51.31.90"), new NomMachine("www.uvsq.fr"));
        assertTrue(item.toString().contains("193.51.31.90"));
        assertTrue(item.toString().contains("www.uvsq.fr"));
    }

    @Test
    public void testEqualsEtHashCode() {
        DnsItem i1 = new DnsItem(new AdresseIP("1.2.3.4"), new NomMachine("a.b.c"));
        DnsItem i2 = new DnsItem(new AdresseIP("1.2.3.4"), new NomMachine("a.b.c"));
        assertEquals(i1, i2);
        assertEquals(i1.hashCode(), i2.hashCode());
    }
}
