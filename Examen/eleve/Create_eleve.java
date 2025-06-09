package eleve;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Create_eleve {
    // Liste des prénoms possibles pour les élèves
    private static final String[] PRENOMS = {
        "Alice", "Karim", "Emma", "Nathan", "Leïla", "Lucas", "Maya", "Sophie", "Yassine", "Liam"
    };

    // Liste des noms possibles pour les élèves
    private static final String[] NOMS = {
        "Durand", "Benali", "Martin", "Nguyen", "Dupont", "Moreau", "Fernandez", "Boulanger", "Gauthier", "Petit"
    };

    /**
     * Génère une liste d'élèves en attribuant à chacun un prénom et un nom aléatoire.
     * 
     * @param nb Le nombre d'élèves à générer.
     * @return Une liste contenant les élèves générés.
     */
    public static List<Eleve> genererEleves(int nb) {
        List<Eleve> eleves = new ArrayList<>(); // Liste pour stocker les élèves générés
        Random random = new Random(); // Objet Random pour générer des indices aléatoires

        // Générer les élèves
        for (int i = 1; i <= nb; i++) {
            // Sélectionner un prénom et un nom aléatoires dans les tableaux PRENOMS et NOMS
            String prenom = PRENOMS[random.nextInt(PRENOMS.length)];
            String nom = NOMS[random.nextInt(NOMS.length)];

            // Créer un nouvel objet Eleve avec le nom et le prénom générés
            Eleve e = new Eleve(nom, prenom);

            // Ajouter l'élève à la liste
            eleves.add(e);
        }

        return eleves; // Retourner la liste des élèves générés
    }
}
