package prof;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Create_prof {
    // Liste des prénoms possibles pour les professeurs
    private static final String[] PRENOMS = {
        "Jean", "Claire", "Ali", "Marie", "Sébastien", "Amina", "Luc", "Fatou", "Olivier", "Nina"
    };

    // Liste des noms de famille possibles pour les professeurs
    private static final String[] NOMS = {
        "Nkondi", "Mojuye", "Lopez", "Durand", "Bernard", "Morel", "Roux", "Fabre", "Cissé", "Bourdon"
    };

    /**
     * Génère une liste de professeurs aléatoires avec des prénoms et noms issus des listes définies.
     * 
     * @param nb Le nombre de professeurs à générer.
     * @return Une liste de professeurs générés aléatoirement.
     */
    public static List<Prof> genererProfesseurs(int nb) {
        List<Prof> professeurs = new ArrayList<>(); // Liste pour stocker les professeurs générés
        Random random = new Random(); // Instance de Random pour générer des valeurs aléatoires

        // Boucle pour générer 'nb' professeurs
        for (int i = 1; i <= nb; i++) {
            // Sélectionner un prénom aléatoire dans la liste PRENOMS
            String prenom = PRENOMS[random.nextInt(PRENOMS.length)];
            // Sélectionner un nom aléatoire dans la liste NOMS
            String nom = NOMS[random.nextInt(NOMS.length)];
            // Créer un nouveau professeur avec le prénom et le nom sélectionnés
            professeurs.add(new Prof(nom, prenom));
        }

        // Retourner la liste des professeurs générés
        return professeurs;
    }
}
