package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

//representer la base de données DNS
public class Dns {
    private final Map<AdresseIP, NomMachine> ipToName = new HashMap<>();
    private final Map<NomMachine, AdresseIP> nameToIp = new HashMap<>();
    private final Path dataFilePath;

    //constructeur; charge la base DNS à partir du fichier défini (dns.properties)
    public Dns()
    {
        this.dataFilePath = loadFilePath();
        loadDataFromFile();
    }

    //charge le chemin depuis dns.properties
    private Path loadFilePath()
    {
        Properties props = new Properties();
        try {
            Path propPath = Paths.get("src/main/resources/dns.properties");
            props.load(Files.newBufferedReader(propPath));
            String filePath = props.getProperty("dns.file");
            return Paths.get(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Impossible de charger dns.properties", e);
        }
    }

    //charge les entrées du fichier texte dans les maps
    private void loadDataFromFile() {
        try {
            List<String> lines = Files.readAllLines(dataFilePath);
            for (String line : lines) {
                if (line.isBlank()) continue;
                String[] parts = line.trim().split("\\s+");
                if (parts.length != 2) continue;

                NomMachine nom = new NomMachine(parts[0]);
                AdresseIP ip = new AdresseIP(parts[1]);
                ipToName.put(ip, nom);
                nameToIp.put(nom, ip);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du chargement du fichier DNS", e);
        }
    }

    //recupere les entrées a partir d'une adresse IP 
    public DnsItem getItem(AdresseIP ip) {
        NomMachine nom = ipToName.get(ip);
        if (nom == null) {
            return null;
        }
        return new DnsItem(ip, nom);
    }

    //recupere les entrées a partir d'un nom de machine 
    public DnsItem getItem(NomMachine nom) {
        AdresseIP ip = nameToIp.get(nom);
        if (ip == null) {
            return null;
        }
        return new DnsItem(ip, nom);
    }

    //retourner toutes les entrées coresspendant à un domaine donné
    public List<DnsItem> getItems(String domaine) {
        return ipToName.entrySet().stream().filter(e -> e.getValue().getDomaine().equalsIgnoreCase(domaine)).map(e -> new DnsItem(e.getKey(), e.getValue())).sorted(Comparator.comparing(o -> o.getNom().getNomMachine())).collect(Collectors.toList());
    }

    //ajoute une nouvelle entrée dans la base et dans le fichier
    public void addItem(AdresseIP ip, NomMachine nom) 
    {
        if (ipToName.containsKey(ip)) {
            throw new IllegalArgumentException("ERREUR : L'adresse IP existe déjà !");
        }
        if (nameToIp.containsKey(nom)) {
            throw new IllegalArgumentException("ERREUR : Le nom de machine existe déjà !");
        }

        ipToName.put(ip, nom);
        nameToIp.put(nom, ip);

        String line = nom.getNomQual() + " " + ip.getIP() + System.lineSeparator();
        try {
            Files.write(dataFilePath,
                    line.getBytes(),
                    StandardOpenOption.APPEND,
                    StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'écriture dans le fichier DNS", e);
        }
    }
}
