package fr.uvsq.cprog.collex;

//Interface du modèle Commande.
@FunctionalInterface //L’annotation @FunctionalInterface permet même d’utiliser des lambdas pour les commandes simples.
public interface Commande {
    //Exécute la commande sur la base DNS.
    String execute(Dns dns);
}
