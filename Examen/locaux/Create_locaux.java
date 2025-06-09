package locaux;

import java.util.ArrayList;
import java.util.List;

public class Create_locaux {

    /**
     * Génère une liste de locaux avec des noms et des capacités uniques.
     * Les noms des locaux suivent un format de "Salle A", "Salle B", "Salle C", etc.
     * La capacité des locaux augmente de 5 par rapport à la précédente, commençant à 25.
     * 
     * @param nb Le nombre de locaux à générer.
     * @return Une liste contenant les locaux générés.
     */
    public static List<Locaux> genererLocaux(int nb) {
        List<Locaux> locaux = new ArrayList<>(); // Liste pour stocker les locaux générés
        for (int i = 1; i <= nb; i++) {
            // Crée un nom de local basé sur un format "Salle A", "Salle B", etc.
            String nom = "Salle " + (char) ('A' + (i - 1));
            // Calcule la capacité de chaque local, qui augmente de 5 à chaque itération
            int capacite = 20 + (i * 5); // Ex : 25, 30, 35...
            // Ajoute un nouveau local à la liste
            locaux.add(new Locaux(nom, capacite));
        }
        return locaux; // Retourne la liste des locaux générés
    }

    /**
     * Affiche les détails des locaux dans la console.
     * 
     * @param locaux La liste des locaux à afficher.
     */
    public static void afficherLocaux(List<Locaux> locaux) {
        for (Locaux l : locaux) {
            System.out.println(l); // Affiche chaque local
        }
    }
}
