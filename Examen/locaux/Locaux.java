package locaux;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Locaux {
    private static int compteurId = 1; // Compteur pour générer des IDs uniques pour chaque local
    private final int id; // ID unique pour le local
    private String nom; // Nom du local (ex : Salle A, Salle B)
    private int capacite; // Capacité du local (le nombre de places disponibles)
    private List<Reservation> reservations; // Liste des réservations associées à ce local

    /**
     * Constructeur de la classe Locaux.
     * 
     * @param nom Le nom du local (ex : "Salle A").
     * @param capacite La capacité du local (ex : 20, 30, 50 places).
     */
    public Locaux(String nom, int capacite) {
        this.id = compteurId++; // Assigner un ID unique et incrémenter le compteur
        this.nom = nom; // Assigner le nom du local
        this.capacite = capacite; // Assigner la capacité du local
        this.reservations = new ArrayList<>(); // Initialiser la liste des réservations
    }

    // Getter pour l'ID du local
    public int getId() { 
        return id; 
    }

    // Getter pour le nom du local
    public String getNom() { 
        return nom; 
    }

    // Getter pour la capacité du local
    public int getCapacite() { 
        return capacite; 
    }

    // Getter pour la liste des réservations de ce local
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Ajoute une réservation à la liste des réservations de ce local.
     * 
     * @param reservation La réservation à ajouter.
     */
    public void ajouterReservation(Reservation reservation) {
        reservations.add(reservation); // Ajoute la réservation à la liste
    }

    /**
     * Vérifie la disponibilité du local pour une date donnée (un créneau par jour).
     * Si le local est déjà réservé pour cette date, la méthode retourne `false`.
     * 
     * @param date La date à vérifier.
     * @return `true` si le local est disponible, sinon `false`.
     */
    public boolean estDisponible(LocalDate date) {
        for (Reservation r : reservations) {
            if (r.getDateHeure().equals(date)) {
                return false; // Le local est déjà réservé pour ce jour-là
            }
        }
        return true; // Le local est disponible pour ce jour
    }
    
    /**
     * Redéfinition de la méthode `toString()` pour fournir une représentation lisible du local.
     * Affiche le nom du local et sa capacité.
     * 
     * @return Une chaîne contenant le nom et la capacité du local.
     */
    @Override
    public String toString() {
        return nom + " (" + capacite + " places)"; // Ex : "Salle A (30 places)"
    }
}
