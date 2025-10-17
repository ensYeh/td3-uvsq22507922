package fr.uvsq.cprog.collex;

// Commande pour quitter l'application DNS.

public class CommandeQuitter implements Commande {

    @Override
    public String execute(Dns dns) {
        System.out.println("Fermeture de l'application...");
        System.exit(0);
        return ""; // ne sera jamais atteint
    }
}
