package fr.uvsq.cprog.collex;
import java.util.Objects;

//representer une entrée du DNS : (nom de machine, adresse IP).
public class DnsItem {
   private final AdresseIP adresse;
    private final NomMachine nom;

    public DnsItem(AdresseIP adresse, NomMachine nom) {
        this.adresse = adresse;
        this.nom = nom;
    }

    public AdresseIP getAdresse() {
        return adresse;
    }

    public NomMachine getNom() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DnsItem)) return false;
        DnsItem that = (DnsItem) o;
        return adresse.equals(that.adresse) && nom.equals(that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adresse, nom);
    }

    @Override
    public String toString() {
        return adresse + " " + nom;
    } 
}
