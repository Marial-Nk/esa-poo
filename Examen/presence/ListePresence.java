package presence;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cours.Cours;
import eleve.Eleve;
import prof.Prof;

public class ListePresence {
    private Cours cours; // Le cours auquel la feuille de présence est associée
    private LocalDate date; // La date de la séance de cours
    private List<Prof> profsDuJour; // Liste des professeurs présents ce jour-là
    private boolean profAbsent; // Statut d'absence du professeur (true si absent)
    private Map<Eleve, Boolean> presencesEleves; // Map des élèves et leur statut de présence (true si présent, false si absent)

    /**
     * Constructeur de la classe ListePresence.
     * 
     * @param cours Le cours auquel cette feuille de présence appartient.
     * @param date La date de la séance de cours.
     */
    public ListePresence(Cours cours, LocalDate date) {
        this.cours = cours; // Assigner le cours à la feuille de présence
        this.date = date; // Assigner la date de la séance
        this.profsDuJour = cours.getProfsAssignes(); // Récupérer la liste des professeurs assignés à ce cours
        this.presencesEleves = new HashMap<>(); // Initialiser la map des présences des élèves
    }

    /**
     * Marque la présence ou l'absence d'un élève pour cette feuille de présence.
     * 
     * @param eleve L'élève dont la présence est marquée.
     * @param present Un booléen indiquant si l'élève est présent (true) ou absent (false).
     */
    public void marquerPresence(Eleve eleve, boolean present) {
        presencesEleves.put(eleve, present); // Marque la présence de l'élève dans la map
    }

    // Getter pour savoir si le professeur est absent
    public boolean isProfAbsent() {
        return profAbsent;
    }

    // Setter pour marquer l'absence du professeur
    public void setProfAbsent(boolean absent) {
        this.profAbsent = absent; // Marque si le professeur est absent ou non
    }

    // Getter pour le cours associé à cette feuille de présence
    public Cours getCours() {
        return cours;
    }

    // Getter pour la date de la séance
    public LocalDate getDate() {
        return date;
    }

    // Getter pour la map des présences des élèves
    public Map<Eleve, Boolean> getPresencesEleves() {
        return presencesEleves;
    }

    // Getter pour la liste des professeurs assignés à ce cours
    public List<Prof> getProfsDuJour() {
        return profsDuJour;
    }

    /**
     * Redéfinition de la méthode toString() pour fournir une représentation lisible
     * de la feuille de présence. Affiche les informations sur le cours, la date,
     * les professeurs et les présences des élèves.
     * 
     * @return Une chaîne contenant les informations de la feuille de présence.
     */
    @Override
    public String toString() {
        StringBuilder presenceDetails = new StringBuilder();
        presenceDetails.append("Feuille de présence pour le cours '").append(cours.getNom())
                       .append("' - Date: ").append(date).append("\n");

        // Affichage des professeurs et de leur statut de présence
        presenceDetails.append("Professeur(s) : ");
        for (Prof prof : profsDuJour) {
            presenceDetails.append(prof.getNomComplet())
                           .append(profAbsent ? " (Absent)" : " (Présent)") // Si le professeur est absent, afficher "Absent"
                           .append(", ");
        }
        presenceDetails.append("\n");

        // Affichage des élèves et de leur statut de présence
        presenceDetails.append("Présences des élèves : ");
        for (Map.Entry<Eleve, Boolean> entry : presencesEleves.entrySet()) {
            Eleve eleve = entry.getKey();
            Boolean present = entry.getValue();
            presenceDetails.append(eleve.getNomComplet()).append(": ")
                           .append(present ? "Présent" : "Absent") // Si l'élève est présent, afficher "Présent", sinon "Absent"
                           .append(", ");
        }

        return presenceDetails.toString(); // Retourner les informations formatées sous forme de chaîne
    }
}
