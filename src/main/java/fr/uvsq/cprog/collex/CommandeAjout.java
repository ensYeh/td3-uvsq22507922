package fr.uvsq.cprog.collex;


//Commande pour ajouter un item à la base DNS.
 
public class CommandeAjout implements Commande {
    private final String ip;
    private final String nom;

    public CommandeAjout(String ip, String nom) {
        this.ip = ip;
        this.nom = nom;
    }

    @Override
    public String execute(Dns dns) {
        try {
            dns.addItem(new AdresseIP(ip), new NomMachine(nom));
            return "Ajout réussi : " + nom + " -> " + ip;
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
