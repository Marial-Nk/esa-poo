package agenda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe LocalAgenda implémente l'interface IAgenda et représente un agenda pour un local.
 * Elle permet de gérer les événements associés à un local, incluant l'ajout, la suppression, l'affichage des événements et la vérification de la disponibilité du local.
 */
public class LocalAgenda implements IAgenda {
    private String local; // Le nom du local associé à cet agenda
    private List<String> evenements; // Liste des événements associés à ce local, sous forme de chaînes de caractères

    /**
     * Constructeur de la classe LocalAgenda.
     * 
     * @param local Le nom du local pour lequel l'agenda est créé.
     */
    public LocalAgenda(String local) {
        this.local = local; // Initialisation du nom du local
        this.evenements = new ArrayList<>(); // Initialisation de la liste des événements
    }

    /**
     * Ajoute un événement à l'agenda du local pour une date spécifique.
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
     * Supprime un événement spécifique de l'agenda du local.
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
     * Affiche tous les événements de l'agenda du local pour une date donnée.
     * 
     * @param date La date pour laquelle afficher les événements du local.
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
     * Vérifie si le local est disponible à une date donnée.
     * 
     * @param date La date à vérifier pour la disponibilité.
     * @return true si le local est disponible (aucun événement prévu), sinon false.
     */
    @Override
    public boolean estDisponible(LocalDate date) {
        // Vérifie s'il n'y a aucun événement prévu pour la date donnée
        return evenements.stream().noneMatch(event -> event.contains(date.toString()));
    }

    /**
     * Retourne le nom du local associé à cet agenda.
     * 
     * @return Le nom du local.
     */
    public String getLocal() {
        return local; // Retourne le nom du local
    }
}
