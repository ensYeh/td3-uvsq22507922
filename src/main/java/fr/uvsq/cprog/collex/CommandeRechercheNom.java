package fr.uvsq.cprog.collex;

//Commande pour rechercher un nom de machine à partir d'une adresse IP.

public class CommandeRechercheNom implements Commande {
    private final String ip;

    public CommandeRechercheNom(String ip) {
        this.ip = ip;
    }

    @Override
    public String execute(Dns dns) {
        DnsItem item = dns.getItem(new AdresseIP(ip));
        if (item == null) {
            return "Aucune entrée trouvée pour " + ip;
        }
        return item.getNom().toString();
    }
}
