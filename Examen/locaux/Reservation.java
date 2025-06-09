package locaux;

import java.time.LocalDate;
import cours.Cours;

public class Reservation {
    private static int compteurId = 1; // Compteur pour générer des IDs uniques pour chaque réservation
    private final int id; // ID unique pour chaque réservation
    private Cours cours; // Le cours associé à la réservation
    private Locaux local; // Le local réservé
    private LocalDate dateHeure; // La date et l'heure de la réservation

    /**
     * Constructeur de la classe Reservation.
     * 
     * @param cours Le cours pour lequel la réservation est faite.
     * @param dateHeure La date et l'heure de la réservation.
     * @param local Le local réservé pour ce cours.
     */
    public Reservation(Cours cours, LocalDate dateHeure, Locaux local) {
        this.id = compteurId++; // Assigner un ID unique et incrémenter le compteur
        this.cours = cours; // Assigner le cours de la réservation
        this.local = local; // Assigner le local réservé
        this.dateHeure = dateHeure; // Assigner la date et l'heure de la réservation
    }

    // Getter pour l'ID de la réservation
    public int getId() {
        return id;
    }

    // Getter pour le cours associé à cette réservation
    public Cours getCours() {
        return cours;
    }

    // Getter pour la date et l'heure de la réservation
    public LocalDate getDateHeure() {
        return dateHeure;
    }

    // Getter pour le local réservé
    public Locaux getLocal() {
        return local;
    }

    /**
     * Retourne le jour de la réservation sous forme de chaîne (par exemple, "MONDAY").
     * 
     * @return Le jour de la réservation.
     */
    public String getJour() {
        return dateHeure.getDayOfWeek().toString(); // Retourne le jour de la semaine en texte (ex : "MONDAY")
    }

    /**
     * Redéfinition de la méthode toString() pour afficher une représentation lisible de la réservation.
     * 
     * @return Une chaîne de caractères contenant les informations de la réservation.
     */
    @Override
    public String toString() {
        return "Réservation du local " + local.getNom() + " pour le cours " + cours.getNom() + " le " + dateHeure;
    }
}
