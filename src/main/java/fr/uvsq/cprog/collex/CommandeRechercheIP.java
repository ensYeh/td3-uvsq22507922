package fr.uvsq.cprog.collex;

//Commande pour rechercher une adresse IP à partir d'un nom de machine.

public class CommandeRechercheIP implements Commande {
    private final String nomMachine;

    public CommandeRechercheIP(String nomMachine) {
        this.nomMachine = nomMachine;
    }

    @Override
    public String execute(Dns dns) {
        DnsItem item = dns.getItem(new NomMachine(nomMachine));
        if (item == null) {
            return "Aucune entrée trouvée pour " + nomMachine;
        }
        return item.getAdresse().toString();
    }
}
