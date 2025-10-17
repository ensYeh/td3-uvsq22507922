package fr.uvsq.cprog.collex;

import java.util.Scanner;

/**
 * Interface texte (TUI = Text User Interface) du DNS.
 * Analyse la saisie utilisateur et crée la commande correspondante.
 */
public class DnsTUI {

    private final Scanner scanner;

    public DnsTUI() {
        this.scanner = new Scanner(System.in);
    }

    public Commande nextCommande(String saisie) {
        if (saisie == null || saisie.isBlank()) {
            return (dns) -> "Commande vide.";
        }

        String[] tokens = saisie.trim().split("\\s+");
        String cmd = tokens[0];

        // Commande "ls" : liste les machines d’un domaine
        if (cmd.equalsIgnoreCase("ls")) {
            boolean triParAdresse = false;
            String domaine;

            if (tokens.length == 3 && tokens[1].equals("-a")) {
                triParAdresse = true;
                domaine = tokens[2];
            } else if (tokens.length == 2) {
                domaine = tokens[1];
            } else {
                return (dns) -> "Usage : ls [-a] <domaine>";
            }

            return new CommandeListeDomaine(domaine, triParAdresse);
        }

        // Commande "add" : ajoute une entrée DNS
        if (cmd.equalsIgnoreCase("add")) {
            if (tokens.length != 3) {
                return (dns) -> "Usage : add <ip> <nom.qualifie.machine>";
            }
            String ip = tokens[1];
            String nom = tokens[2];
            return new CommandeAjout(ip, nom);
        }

        // Commande "quit" ou "exit" : quitte le programme
        if (cmd.equalsIgnoreCase("quit") || cmd.equalsIgnoreCase("exit")) {
            return new CommandeQuitter();
        }

        // Si c’est une IP ou un nom qualifié
        if (cmd.contains(".")) {
            if (Character.isDigit(cmd.charAt(0))) {
                return new CommandeRechercheNom(cmd); // cas d'une adresse IP
            } else {
                return new CommandeRechercheIP(cmd); // cas d'un nom qualifié
            }
        }

        // Commande inconnue
        return (dns) -> "Commande non reconnue : " + cmd;
    }

    /**
     * Affiche le résultat d'une commande.
     */
    public void affiche(String resultat) {
        System.out.println(resultat);
    }

    /**
     * Boucle d'interaction manuelle (pour tests rapides).
     */
    public static void main(String[] args) {
        Dns dns = new Dns();
        DnsTUI tui = new DnsTUI();

        System.out.println("=== Serveur DNS (TUI) ===");
        System.out.println("Commandes disponibles :");
        System.out.println("- <nom.qualifie.machine>");
        System.out.println("- <adresse.IP>");
        System.out.println("- ls [-a] <domaine>");
        System.out.println("- add <adresse.IP> <nom.qualifie.machine>");
        System.out.println("- quit / exit");
        System.out.println();

        while (true) {
            System.out.print("> ");
            String saisie = tui.scanner.nextLine();
            Commande commande = tui.nextCommande(saisie);
            String resultat = commande.execute(dns);
            tui.affiche(resultat);
        }
    }
}
