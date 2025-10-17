package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class NomMachineTest {

    @Test
    public void testNomEtDomaine() {
        NomMachine n = new NomMachine("www.uvsq.fr");
        assertEquals("www", n.getNomMachine());
        assertEquals("uvsq.fr", n.getDomaine());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNomInvalide() {
        new NomMachine("invalide");
    }

    @Test
    public void testEqualsEtHashCode() {
        NomMachine n1 = new NomMachine("poste.uvsq.fr");
        NomMachine n2 = new NomMachine("poste.uvsq.fr");
        assertEquals(n1, n2);
        assertEquals(n1.hashCode(), n2.hashCode());
    }
}
