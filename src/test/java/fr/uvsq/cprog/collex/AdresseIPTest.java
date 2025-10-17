package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class AdresseIPTest {

    @Test
    public void testAdresseValide() {
        AdresseIP ip = new AdresseIP("192.168.0.1");
        assertEquals("192.168.0.1", ip.getIP());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdresseInvalideFormat() {
        new AdresseIP("999.168.0.1");
    }

    @Test
    public void testComparaison() {
        AdresseIP ip1 = new AdresseIP("192.168.0.1");
        AdresseIP ip2 = new AdresseIP("192.168.0.5");
        assertTrue(ip1.compareTo(ip2) < 0);
    }

    @Test
    public void testEqualsEtHashCode() {
        AdresseIP ip1 = new AdresseIP("10.0.0.1");
        AdresseIP ip2 = new AdresseIP("10.0.0.1");
        assertEquals(ip1, ip2);
        assertEquals(ip1.hashCode(), ip2.hashCode());
    }
}

