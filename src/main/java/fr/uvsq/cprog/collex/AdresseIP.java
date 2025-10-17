package fr.uvsq.cprog.collex;
import java.util.Objects;

//presentation d'une adresse IPv4
public class AdresseIP implements Comparable<AdresseIP> {
    private final String ip;
    public AdresseIP(String ip){
        if(!isValid(ip))
        {
            throw new IllegalArgumentException("Adresse IP non valide : "+ip);
        }
        this.ip = ip;
    }

    //verification de la validité de l'adresse ip 
    private boolean isValid(String ip)
    {
        if(ip == null || ip.isEmpty()) {return false;}
        String[] parties = ip.split("\\.");
        if(parties.length != 4) {return false;} //verifier qu'on a 4 champs X.X.X.X
        try{
            for(String partie : parties){
                int n = Integer.parseInt(partie);
                if(n < 0 || n > 255){return false;} //verifier que tout les champs sont entre 0 et 255
            }
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public String getIP(){return ip;}
    
    @Override 
    public int compareTo(AdresseIP b) 
    {
        String[] p1 = this.ip.split("\\."); //adresse actuelle
        String[] p2 = b.ip.split("\\."); //adresse a comparer
        for(int i = 0;i < 4;++i) //on compare chaque champ avec son correspendant et on retourne le plus grand d'entre eux 
        {
            int n1 = Integer.parseInt(p1[i]);
            int n2 = Integer.parseInt(p2[i]);
            if(n1 != n2){return Integer.compare(n1,n2);}
        }
        return 0;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o){return true;}
        if(!(o instanceof AdresseIP)){return false;}
        AdresseIP ca = (AdresseIP) o;
        return ip.equals(ca.ip);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(ip);
    }

    @Override
    public String toString()
    {
        return ip;
    }
}
