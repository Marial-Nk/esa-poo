package cours;

import java.util.ArrayList;
import java.util.List;
import presence.ListePresence;
import prof.Prof;
import eleve.Eleve;
import locaux.Reservation;

public class Cours {
    private static int compteurId = 1; // Compteur pour générer des IDs uniques pour chaque cours
    private final int id; // L'ID unique du cours
    private String nom; // Le nom du cours
    private List<Reservation> reservations; // Liste des réservations associées à ce cours

    // Liste des feuilles de présence associées à ce cours
    private List<ListePresence> feuillesPresence = new ArrayList<>();

    // Liste des professeurs assignés à ce cours
    private List<Prof> profsAssignes = new ArrayList<>();

    // Liste des élèves inscrits à ce cours (optionnel mais utile)
    private List<Eleve> elevesInscrits = new ArrayList<>();

    /**
     * Constructeur de la classe Cours.
     * 
     * @param nom Le nom du cours à créer.
     */
    public Cours(String nom) {
        this.id = compteurId++; // Assigne un ID unique au cours et incrémente le compteur
        this.nom = nom; // Initialisation du nom du cours
        this.reservations = new ArrayList<>(); // Initialisation de la liste des réservations
    }

    // Getter pour l'ID du cours
    public int getId() { 
        return id; 
    }

    // Getter pour le nom du cours
    public String getNom() {
        return nom; 
    }

    // Getter pour la liste des feuilles de présence associées au cours
    public List<ListePresence> getFeuillesPresence() {
        return feuillesPresence;
    }

    /**
     * Ajoute une feuille de présence au cours.
     * 
     * @param feuille La feuille de présence à ajouter.
     */
    public void ajouterFeuillePresence(ListePresence feuille) {
        feuillesPresence.add(feuille);
    }
    
    /**
     * Assigne un professeur à ce cours.
     * 
     * @param prof Le professeur à ajouter à la liste des professeurs assignés.
     */
    public void ajouterProf(Prof prof) {
        if (!profsAssignes.contains(prof)) { // Vérifie que le professeur n'est pas déjà assigné
            profsAssignes.add(prof); // Ajoute le professeur à la liste
        }
    }

    // Getter pour la liste des professeurs assignés à ce cours
    public List<Prof> getProfsAssignes() {
        return profsAssignes;
    }

    /**
     * Inscrit un élève à ce cours.
     * 
     * @param eleve L'élève à inscrire au cours.
     */
    public void inscrireEleve(Eleve eleve) {
        if (!elevesInscrits.contains(eleve)) { // Vérifie que l'élève n'est pas déjà inscrit
            elevesInscrits.add(eleve); // Ajoute l'élève à la liste des inscrits
        }
    }

    // Getter pour la liste des élèves inscrits à ce cours
    public List<Eleve> getElevesInscrits() {
        return elevesInscrits;
    }

    /**
     * Ajoute une réservation pour ce cours.
     * 
     * @param reservation La réservation à ajouter.
     */
    public void ajouterReservation(Reservation reservation) {
        reservations.add(reservation); // Ajoute la réservation à la liste des réservations
    }

    /**
     * Méthode pour récupérer le jour de la première réservation pour ce cours.
     * 
     * @return Le jour de la première réservation ou "Pas de réservation" si aucune réservation.
     */
    public String getJour() {
        if (reservations.isEmpty()) {
            return "Pas de réservation"; // Si aucune réservation n'est associée
        }
        // Retourne le jour de la première réservation
        return reservations.get(0).getJour(); 
    }

    /**
     * Affiche toutes les réservations associées à ce cours.
     */
    public void afficherReservations() {
        if (reservations.isEmpty()) {
            System.out.println("Pas de réservation pour ce cours.");
        } else {
            System.out.println("Réservations pour le cours " + nom + " :");
            for (Reservation reservation : reservations) {
                System.out.println("  - " + reservation); // Affiche chaque réservation
            }
        }
    }

    // Getter pour la liste des réservations du cours
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du cours (ID et nom).
     * 
     * @return La chaîne représentant le cours sous le format "ID nom".
     */
    @Override
    public String toString() {
        return id + " " + nom;
    }
}
