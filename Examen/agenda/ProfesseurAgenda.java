package agenda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe ProfesseurAgenda implémente l'interface IAgenda et représente un agenda pour un professeur.
 * Elle permet de gérer les événements associés à un professeur, incluant l'ajout, la suppression, l'affichage des événements et la vérification de la disponibilité du professeur.
 */
public class ProfesseurAgenda implements IAgenda {
    private String nom; // Le nom du professeur associé à cet agenda
    private List<String> evenements; // Liste des événements associés à ce professeur, sous forme de chaînes de caractères

    /**
     * Constructeur de la classe ProfesseurAgenda.
     * 
     * @param nom Le nom du professeur pour lequel l'agenda est créé.
     */
    public ProfesseurAgenda(String nom) {
        this.nom = nom; // Initialisation du nom du professeur
        this.evenements = new ArrayList<>(); // Initialisation de la liste des événements
    }

    /**
     * Ajoute un événement à l'agenda du professeur pour une date spécifique.
     * 
     * @param date La date à laquelle l'événement doit être ajouté.
     * @param description La description de l'événement.
     */
    @Override
    public void ajouterEvenement(LocalDate date, String description) {
        // Ajoute l'événement à la liste sous forme de chaîne de caractères
        evenements.add("Date: " + date + " - " + description);
    }

    /**
     * Supprime un événement spécifique de l'agenda du professeur.
     * 
     * @param date La date de l'événement à supprimer.
     * @param description La description de l'événement à supprimer.
     */
    @Override
    public void supprimerEvenement(LocalDate date, String description) {
        // Retire l'événement de la liste des événements s'il correspond à la date et à la description données
        evenements.removeIf(event -> event.equals("Date: " + date + " - " + description));
    }

    /**
     * Affiche tous les événements de l'agenda du professeur pour une date donnée.
     * 
     * @param date La date pour laquelle afficher les événements du professeur.
     * @return Une liste contenant les événements pour la date spécifiée.
     */
    @Override
    public List<String> afficherEvenements(LocalDate date) {
        List<String> eventsForDate = new ArrayList<>(); // Liste pour stocker les événements pour la date donnée
        
        // Parcours des événements pour ajouter ceux qui correspondent à la date spécifiée
        for (String event : evenements) {
            if (event.contains(date.toString())) { // Si l'événement concerne la date donnée
                eventsForDate.add(event); // Ajouter l'événement à la liste
            }
        }
        return eventsForDate; // Retourner la liste des événements pour la date donnée
    }

    /**
     * Vérifie si le professeur est disponible à une date donnée.
     * 
     * @param date La date à vérifier pour la disponibilité.
     * @return true si le professeur est disponible (aucun événement prévu), sinon false.
     */
    @Override
    public boolean estDisponible(LocalDate date) {
        // Vérifie s'il n'y a aucun événement prévu pour la date donnée
        return evenements.stream().noneMatch(event -> event.contains(date.toString()));
    }

    /**
     * Retourne le nom du professeur associé à cet agenda.
     * 
     * @return Le nom du professeur.
     */
    public String getNom() {
        return nom; // Retourne le nom du professeur
    }
}
