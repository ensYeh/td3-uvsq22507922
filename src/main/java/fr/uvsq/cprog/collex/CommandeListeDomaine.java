package fr.uvsq.cprog.collex;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Commande pour lister les machines d'un domaine.

public class CommandeListeDomaine implements Commande {
    private final String domaine;
    private final boolean triParAdresse;

    public CommandeListeDomaine(String domaine, boolean triParAdresse) {
        this.domaine = domaine;
        this.triParAdresse = triParAdresse;
    }

    @Override
    public String execute(Dns dns) {
        List<DnsItem> items = dns.getItems(domaine);

        if (items.isEmpty()) {
            return "Aucune machine trouvée pour le domaine " + domaine;
        }

        if (triParAdresse) {
            items.sort(Comparator.comparing(i -> i.getAdresse().getIP()));
        }

        return items.stream()
                .map(i -> i.getAdresse() + " " + i.getNom())
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
