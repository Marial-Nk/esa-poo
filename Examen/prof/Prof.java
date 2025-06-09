package prof;

import java.util.ArrayList;
import java.util.List;
import cours.Cours;
import cours.GestionCours;

public class Prof {
    private static int compteurId = 1; // Compteur pour générer des IDs uniques pour les professeurs
    private final int id; // L'ID unique du professeur
    private String nom; // Le nom du professeur
    private String prenom; // Le prénom du professeur
    private List<Cours> coursAssignes; // Liste des cours assignés au professeur

    /**
     * Constructeur pour créer un professeur avec un nom et un prénom.
     * 
     * @param nom Le nom du professeur.
     * @param prenom Le prénom du professeur.
     */
    public Prof(String nom, String prenom) {
        this.id = compteurId++; // Assigner un ID unique pour ce professeur
        this.nom = nom; // Assigner le nom
        this.prenom = prenom; // Assigner le prénom
        this.coursAssignes = new ArrayList<>(); // Initialiser la liste des cours assignés
    }

    /**
     * Retourne l'ID unique du professeur.
     * 
     * @return L'ID du professeur.
     */
    public int getId() { 
        return id; 
    }

    /**
     * Retourne le nom complet du professeur (prénom + nom).
     * 
     * @return Le nom complet du professeur.
     */
    public String getNomComplet() {
        return prenom + " " + nom; // Combine le prénom et le nom
    }

    /**
     * Assigner un cours à ce professeur en vérifiant si le cours existe.
     * 
     * @param gestionCours L'objet qui gère les cours dans le système.
     * @param idCours L'ID du cours à assigner.
     */
    public void ajouterCours(GestionCours gestionCours, int idCours) {
        // Recherche du cours dans le gestionnaire de cours par ID
        Cours cours = gestionCours.rechercherCoursParId(idCours);
        if (cours != null) { // Si le cours existe
            // Si ce cours n'a pas déjà été assigné à ce professeur
            if (!coursAssignes.contains(cours)) {
                coursAssignes.add(cours); // Ajouter le cours à la liste des cours assignés
                cours.ajouterProf(this); // Ajouter ce professeur au cours également
            } else {
                System.out.println("Le cours est déjà assigné à ce professeur.");
            }
        } else {
            System.out.println("Cours avec l'ID " + idCours + " n'existe pas.");
        }
    }

    /**
     * Retourne la liste des cours assignés à ce professeur.
     * 
     * @return La liste des cours assignés.
     */
    public List<Cours> getCoursAssignes() {
        return coursAssignes;
    }

    /**
     * Redéfinition de la méthode toString() pour fournir une représentation lisible du professeur.
     * 
     * @return Une chaîne représentant le professeur (ID, prénom et nom).
     */
    @Override
    public String toString() {
        return id + " " + prenom + " " + nom; // Retourne l'ID, le prénom et le nom du professeur
    }
}
