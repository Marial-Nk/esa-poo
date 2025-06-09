package agenda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe EleveAgenda implémente l'interface IAgenda.
 * Elle permet de gérer un agenda pour un élève, incluant l'ajout, l'affichage d'événements
 * et la vérification de la disponibilité de l'élève pour une date donnée.
 */
public class EleveAgenda implements IAgenda {
    private String nom; // Nom de l'élève
    private List<String> evenements; // Liste des événements de l'agenda sous forme de chaînes de caractères

    /**
     * Constructeur de la classe EleveAgenda.
     * Il initialise un agenda pour un élève avec un nom et une liste d'événements vide.
     *
     * @param nom Le nom de l'élève pour l'agenda.
     */
    public EleveAgenda(String nom) {
        this.nom = nom; // Assigne le nom de l'élève
        this.evenements = new ArrayList<>(); // Initialise la liste des événements
    }

    /**
     * Ajoute un événement à l'agenda de l'élève pour une date spécifique.
     * 
     * @param date La date de l'événement à ajouter.
     * @param description La description de l'événement.
     */
    @Override
    public void ajouterEvenement(LocalDate date, String description) {
        // Ajoute l'événement sous forme de chaîne dans la liste d'événements
        evenements.add("Date: " + date + " - " + description);
    }

    /**
     * Supprime un événement spécifique de l'agenda de l'élève.
     *
     * @param date La date de l'événement à supprimer.
     * @param description La description de l'événement à supprimer.
     */
    @Override
    public void supprimerEvenement(LocalDate date, String description) {
        // Supprime l'événement correspondant à la date et à la description
        evenements.removeIf(event -> event.equals("Date: " + date + " - " + description));
    }

    /**
     * Affiche les événements associés à une date donnée.
     * 
     * @param date La date pour laquelle afficher les événements.
     * @return Une liste des événements pour la date spécifiée.
     */
    @Override
    public List<String> afficherEvenements(LocalDate date) {
        List<String> eventsForDate = new ArrayList<>(); // Liste pour stocker les événements pour la date donnée
        
        // Parcours des événements existants pour sélectionner ceux qui correspondent à la date
        for (String event : evenements) {
            if (event.contains(date.toString())) { // Si l'événement correspond à la date
                eventsForDate.add(event); // Ajoute l'événement à la liste
            }
        }
        return eventsForDate; // Retourne la liste des événements pour la date donnée
    }

    /**
     * Vérifie si l'élève est disponible à une date donnée.
     * L'élève est considéré comme disponible si aucun événement n'est prévu à cette date.
     *
     * @param date La date à vérifier pour la disponibilité.
     * @return true si l'élève est disponible (pas d'événement prévu), sinon false.
     */
    @Override
    public boolean estDisponible(LocalDate date) {
        // Vérifie s'il n'y a aucun événement prévu pour la date donnée
        return evenements.stream().noneMatch(event -> event.contains(date.toString()));
    }

    /**
     * Retourne le nom de l'élève associé à cet agenda.
     * 
     * @return Le nom de l'élève.
     */
    public String getNom() {
        return nom; // Retourne le nom de l'élève
    }
}
