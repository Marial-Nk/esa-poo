package cours;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Create_cours {
    // Liste des noms de cours possibles
    private static final String[] NOMS_COURS = {
        "Mathématiques", "Français", "Sciences", "Réseaux", "POO",
        "Informatique", "GEBD", "Physique", "Chimie", "Anglais"
    };

    /**
     * Génère une liste de cours avec un nombre spécifié de cours.
     * Les cours sont créés avec des noms aléatoires tirés de la liste NOMS_COURS.
     * 
     * @param nb Le nombre de cours à générer.
     * @return Une liste contenant les cours générés.
     */
    public static List<Cours> genererCours(int nb) {
        List<Cours> coursList = new ArrayList<>(); // Liste pour stocker les cours générés
        Random random = new Random(); // Générateur de nombres aléatoires pour choisir un nom de cours

        // Boucle pour générer le nombre de cours spécifié
        for (int i = 0; i < nb; i++) {
            // Sélectionne un nom de cours aléatoire dans la liste NOMS_COURS
            String nomCours = NOMS_COURS[random.nextInt(NOMS_COURS.length)];
            // Crée un cours avec le nom sélectionné et l'ajoute à la liste
            coursList.add(new Cours(nomCours));  
        }
        
        return coursList; // Retourne la liste des cours générés
    }
}
