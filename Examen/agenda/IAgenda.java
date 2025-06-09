package agenda;

import java.time.LocalDate;
import java.util.List;

/**
 * L'interface IAgenda définit les méthodes nécessaires pour gérer un agenda.
 * Elle est destinée à être implémentée par des classes qui souhaitent gérer des événements pour un utilisateur spécifique (professeur, élève, local, etc.).
 */
public interface IAgenda {

    /**
     * Ajoute un événement à l'agenda pour une date donnée.
     * 
     * @param date La date à laquelle l'événement doit être ajouté.
     * @param description La description de l'événement.
     */
    void ajouterEvenement(LocalDate date, String description);

    /**
     * Supprime un événement de l'agenda pour une date et une description données.
     * 
     * @param date La date de l'événement à supprimer.
     * @param description La description de l'événement à supprimer.
     */
    void supprimerEvenement(LocalDate date, String description);

    /**
     * Affiche les événements de l'agenda pour une date donnée.
     * 
     * @param date La date pour laquelle les événements doivent être affichés.
     * @return Une liste de chaînes de caractères contenant les événements de cette date.
     */
    List<String> afficherEvenements(LocalDate date);

    /**
     * Vérifie si l'agenda est disponible pour une date donnée.
     * 
     * @param date La date à vérifier.
     * @return true si l'agenda est disponible pour cette date (aucun événement n'est prévu pour cette date), sinon false.
     */
    boolean estDisponible(LocalDate date);
}
