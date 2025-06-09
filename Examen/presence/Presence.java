package presence;

import java.time.LocalDate;
import eleve.Eleve;
import cours.Cours;

public class Presence {
    private Eleve eleve; // L'élève concerné par la présence
    private Cours cours; // Le cours auquel la présence est enregistrée
    private LocalDate date; // La date de la séance de cours
    private boolean present; // Statut de la présence (true si présent, false si absent)
    private boolean justifiee; // Statut pour savoir si l'absence est justifiée (true si justifiée)

    /**
     * Constructeur de la classe Presence.
     * 
     * @param eleve L'élève dont la présence est enregistrée.
     * @param cours Le cours auquel cette présence appartient.
     * @param date La date de la séance de cours.
     * @param present Le statut de présence de l'élève (true si présent, false si absent).
     * @param justifiee Le statut de justification de l'absence (true si justifiée).
     */
    public Presence(Eleve eleve, Cours cours, LocalDate date, boolean present, boolean justifiee) {
        this.eleve = eleve; // Assigner l'élève
        this.cours = cours; // Assigner le cours
        this.date = date; // Assigner la date de la séance
        this.present = present; // Assigner le statut de présence
        this.justifiee = justifiee; // Assigner le statut de justification
    }

    // Getter pour l'élève concerné par cette présence
    public Eleve getEleve() {
        return eleve;
    }

    // Getter pour le cours concerné par cette présence
    public Cours getCours() {
        return cours;
    }

    // Getter pour la date de la présence
    public LocalDate getDate() {
        return date;
    }

    // Getter pour le statut de présence (présent ou absent)
    public boolean isPresent() {
        return present;
    }

    // Setter pour le statut de présence (modifier la présence de l'élève)
    public void setPresent(boolean present) {
        this.present = present;
    }

    /**
     * Méthode pour marquer une absence justifiée par un certificat médical.
     * Cette méthode marque l'élève comme absent et indique que l'absence est justifiée.
     * 
     * @param certificat Le certificat médical justifiant l'absence.
     */
    public void marquerAbsenceJustifiee(CertificatMedical certificat) {
        this.present = false; // Marque l'élève comme absent
        this.justifiee = true; // Indique que l'absence est justifiée par un certificat
    }

    // Getter pour savoir si l'absence est justifiée
    public boolean isJustifiee() {
        return justifiee;
    }

    // Setter pour modifier le statut de justification de l'absence
    public void setJustifiee(boolean justifiee) {
        this.justifiee = justifiee;
    }

    /**
     * Redéfinition de la méthode toString() pour fournir une représentation lisible de la présence.
     * 
     * @return Une chaîne de caractères contenant les détails de la présence.
     */
    @Override
    public String toString() {
        return "Présence [" + date + "] - " +
               eleve.getNomComplet() + " au cours '" + cours.getNom() + "' : " +
               (present ? "Présent" : "Absent");
    }
}
