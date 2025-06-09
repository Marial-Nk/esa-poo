package cours;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import locaux.GestionLocaux;
import locaux.Locaux;
import locaux.Reservation;

public class GestionCours {
    private List<Cours> cours = new ArrayList<>(); // Liste des cours disponibles

    /**
     * Charge des cours prédéfinis en générant une liste de cours à l'aide de la méthode Create_cours.
     * 
     * @param nb Le nombre de cours à charger.
     */
    public void chargerCoursPredefinis(int nb) {
        List<Cours> predefinis = Create_cours.genererCours(nb); // Génére des cours à l'aide de Create_cours
        cours.addAll(predefinis); // Ajoute ces cours à la liste des cours
    }

    /**
     * Ajoute un cours à la liste des cours.
     * 
     * @param cours Le cours à ajouter à la liste.
     */
    public void ajouterCours(Cours cours) {
        this.cours.add(cours); // Ajoute le cours à la liste des cours
    }

    /**
     * Recherche un cours dans la liste par son ID.
     * 
     * @param id L'ID du cours à rechercher.
     * @return Le cours avec l'ID spécifié, ou null si aucun cours n'est trouvé.
     */
    public Cours rechercherCoursParId(int id) {
        for (Cours c : cours) {
            if (c.getId() == id) {
                return c; // Retourne le cours trouvé
            }
        }
        return null; // Si aucun cours avec cet ID n'est trouvé
    }

    /**
     * Affiche la liste de tous les cours disponibles.
     */
    public void afficherTousLesCours() {
        if (cours.isEmpty()) {
            System.out.println("Aucun cours disponible."); // Si la liste est vide
        } else {
            System.out.println("Liste des cours disponibles :");
            for (Cours c : cours) {
                System.out.println(c); // Affiche chaque cours
            }
        }
    }

    /**
     * Récupère la liste des cours qui ne sont pas réservés pour une date donnée.
     * 
     * @param date La date à vérifier pour les réservations.
     * @param gestionLocaux L'objet de gestion des locaux qui contient les réservations des locaux.
     * @return Une liste de cours non réservés à la date spécifiée.
     */
    public List<Cours> getCoursSansReservationPourDate(LocalDate date, GestionLocaux gestionLocaux) {
        List<Cours> resultat = new ArrayList<>(); // Liste pour stocker les cours sans réservation à la date spécifiée
        for (Cours c : cours) {
            boolean dejaReserve = false; // Drapeau pour vérifier si le cours est déjà réservé
            // Parcours des locaux pour vérifier les réservations
            for (Locaux l : gestionLocaux.getLocaux()) {
                for (Reservation r : l.getReservations()) {
                    if (r.getCours().getId() == c.getId() && r.getDateHeure().equals(date)) {
                        dejaReserve = true; // Le cours est déjà réservé à cette date
                        break;
                    }
                }
                if (dejaReserve) break; // Si le cours est déjà réservé, on arrête la vérification
            }
            if (!dejaReserve) {
                resultat.add(c); // Si le cours n'est pas réservé à cette date, on l'ajoute à la liste résultat
            }
        }
        return resultat; // Retourne la liste des cours non réservés
    }

    /**
     * Getter pour la liste des cours disponibles.
     * 
     * @return La liste des cours.
     */
    public List<Cours> getCours() {
        return cours; // Retourne la liste complète des cours
    }
}
