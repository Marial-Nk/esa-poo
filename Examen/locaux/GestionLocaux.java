package locaux;

import cours.Cours;
import presence.ListePresence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionLocaux {

    private final List<Locaux> locaux = new ArrayList<>(); // Liste des locaux disponibles

    /**
     * Initialise les locaux automatiquement en générant 5 locaux via la classe Create_locaux.
     */
    public void initialiserLocaux() {
        List<Locaux> initiaux = Create_locaux.genererLocaux(5); // Génère 5 locaux
        this.locaux.addAll(initiaux); // Ajoute ces locaux à la liste des locaux
    }

    /**
     * Enregistre un local dans la gestion des locaux.
     * 
     * @param local Le local à enregistrer.
     */
    public void enregistrerLocal(Locaux local) {
        locaux.add(local); // Ajoute le local à la liste des locaux
    }

    /**
     * Recherche un local par son ID.
     * 
     * @param id L'ID du local à rechercher.
     * @return Le local correspondant à l'ID, ou null si non trouvé.
     */
    public Locaux rechercherParId(int id) {
        for (Locaux l : locaux) {
            if (l.getId() == id) return l; // Retourne le local si l'ID correspond
        }
        return null; // Retourne null si le local n'est pas trouvé
    }

    /**
     * Affiche les locaux disponibles pour une date donnée.
     * 
     * @param date La date à vérifier pour la disponibilité des locaux.
     */
    public void afficherLocauxDisponibles(LocalDate date) {
        System.out.println("Locaux disponibles pour le " + date + " :");
        for (Locaux local : locaux) {
            if (local.estDisponible(date)) {
                System.out.println(local); // Affiche le local s'il est disponible pour la date
            }
        }
    }

    /**
     * Réserve un local pour un cours à une date donnée.
     * 
     * @param cours Le cours pour lequel réserver le local.
     * @param local Le local à réserver.
     * @param date La date de la réservation.
     */
    public static void reserverLocalPourCours(Cours cours, Locaux local, LocalDate date) {
        if (!local.estDisponible(date)) { // Vérifie si le local est disponible pour la date
            System.out.println("Le local " + local.getNom() + " n'est pas disponible pour la date " + date + ".");
            return; // Si le local n'est pas disponible, on arrête l'exécution
        }

        // Créer la réservation pour le local
        Reservation reservation = new Reservation(cours, date, local);
        
        // Ajouter la réservation au local
        local.ajouterReservation(reservation);

        // Créer une nouvelle feuille de présence pour le cours à cette date
        ListePresence feuillePresence = new ListePresence(cours, date);
        cours.ajouterFeuillePresence(feuillePresence);  // Ajouter la feuille de présence au cours

        // Ajouter la réservation au cours si nécessaire (vérifier la logique de votre application)
        cours.ajouterReservation(reservation);  // Ajouter la réservation au cours (assurez-vous que cette méthode existe dans Cours)
    }

    /**
     * Affiche les réservations pour un cours donné, y compris les locaux et dates des réservations.
     * 
     * @param cours Le cours pour lequel afficher les réservations.
     * @return Une chaîne contenant les informations des réservations ou un message si aucune réservation n'est trouvée.
     */
    public String afficherReservationsParCours(Cours cours) {
        StringBuilder result = new StringBuilder(); // Utilisation de StringBuilder pour construire le résultat efficacement
        boolean trouve = false; // Drapeau pour savoir si des réservations ont été trouvées
        
        // Parcours de tous les locaux
        for (Locaux local : locaux) {
            for (Reservation reservation : local.getReservations()) {
                // Vérifie si la réservation concerne le cours
                if (reservation.getCours().getId() == cours.getId()) {
                    result.append("Local : ").append(local.getNom())
                        .append(" | Réservation le : ").append(reservation.getDateHeure())
                        .append("\n");
                    trouve = true; // Si une réservation est trouvée, on met le drapeau à true
                }
            }
        }

        // Si aucune réservation n'est trouvée pour ce cours
        if (!trouve) {
            result.append("Aucune réservation trouvée pour ce cours.");
        }

        return result.toString();  // Retourne la chaîne contenant les informations des réservations
    }

    /**
     * Récupère la liste des locaux disponibles pour une date donnée.
     * 
     * @param date La date pour laquelle vérifier la disponibilité des locaux.
     * @return Une liste des locaux disponibles pour la date spécifiée.
     */
    public List<Locaux> getLocauxDisponibles(LocalDate date) {
        List<Locaux> disponibles = new ArrayList<>(); // Liste pour stocker les locaux disponibles
        for (Locaux l : locaux) {
            if (l.estDisponible(date)) {
                disponibles.add(l); // Ajoute le local à la liste s'il est disponible
            }
        }
        return disponibles; // Retourne la liste des locaux disponibles
    }

    // Getter pour la liste des locaux
    public List<Locaux> getLocaux() {
        return locaux;
    }
}
