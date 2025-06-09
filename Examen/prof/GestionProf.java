package prof;

import java.util.ArrayList;
import java.util.List;

import cours.Cours;
import cours.GestionCours;

public class GestionProf {
    private List<Prof> professeurs = new ArrayList<>(); // Liste des professeurs enregistrés

    /**
     * Méthode pour enregistrer un professeur dans le système.
     * 
     * @param professeur Le professeur à enregistrer.
     */
    public void enregistrerProfesseur(Prof professeur) {
        professeurs.add(professeur); // Ajoute le professeur à la liste des professeurs
    }

    /**
     * Méthode pour assigner un cours à un professeur. Le professeur et le cours sont recherchés
     * par leurs IDs respectifs.
     * 
     * @param idProf L'ID du professeur auquel le cours doit être assigné.
     * @param idCours L'ID du cours à assigner au professeur.
     * @param gestionCours L'objet qui permet de rechercher le cours par son ID.
     */
    public void assignerCoursAProfesseur(int idProf, int idCours, GestionCours gestionCours) {
        // Recherche le professeur avec l'ID spécifié
        Prof professeur = rechercherProfesseurParId(idProf);
        if (professeur != null) { // Si le professeur existe
            // Recherche le cours avec l'ID spécifié
            Cours cours = gestionCours.rechercherCoursParId(idCours); 
            if (cours != null) { // Si le cours existe
                // Assigner le cours au professeur et ajouter le professeur au cours
                professeur.ajouterCours(gestionCours, idCours);
                cours.ajouterProf(professeur); 
            } else {
                System.out.println("Cours avec l'ID " + idCours + " n'existe pas.");
            }
        } else {
            System.out.println("Professeur avec ID " + idProf + " non trouvé.");
        }
    }

    /**
     * Méthode pour rechercher un professeur par son ID.
     * 
     * @param id L'ID du professeur à rechercher.
     * @return Le professeur correspondant à l'ID, ou null si aucun professeur n'est trouvé.
     */
    public Prof rechercherProfesseurParId(int id) {
        for (Prof p : professeurs) {
            if (p.getId() == id) {
                return p; // Retourne le professeur si son ID correspond
            }
        }
        return null; // Retourne null si aucun professeur n'est trouvé avec cet ID
    }

    /**
     * Méthode pour afficher la liste de tous les professeurs enregistrés.
     */
    public void afficherTousLesProfesseurs() {
        if (professeurs.isEmpty()) {
            System.out.println("Aucun professeur enregistré."); // Si aucun professeur n'est enregistré
        } else {
            System.out.println("\nListe des professeurs enregistrés :");
            for (Prof p : professeurs) {
                System.out.println(p); // Afficher chaque professeur
            }
        }
    }

    /**
     * Méthode pour obtenir la liste complète des professeurs enregistrés.
     * 
     * @return La liste des professeurs.
     */
    public List<Prof> getProfesseurs() {
        return professeurs; // Retourne la liste des professeurs
    }
}
