package fr.uvsq.cprog.collex;
import java.util.Objects;

//presentation d'un nom qualifié de machine (ex www.uvsq.fr)

public class NomMachine {

    public final String nomQual;

    public NomMachine(String nomQual)
    {
        if(nomQual == null || !nomQual.contains("."))
        {
            throw new IllegalArgumentException("Nom qualifié invalide : " + nomQual);
        }
        this.nomQual = nomQual;
    }

    public String getNomQual()
    {
        return nomQual;
    }

    public String getNomMachine()
    {
        return nomQual.split("\\.")[0];
    }

    public String getDomaine()
    {
        return nomQual.substring(nomQual.indexOf('.') + 1);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof NomMachine)) return false;
        NomMachine that = (NomMachine) o;
        return nomQual.equals(that.nomQual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomQual);
    }

    @Override
    public String toString() {
        return nomQual;
    }
}
