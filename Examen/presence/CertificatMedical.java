package presence;

import java.time.LocalDate;

public class CertificatMedical {
    private String nom; // Le nom du certificat médical (par exemple, un nom de maladie, ou un type de certificat)
    private LocalDate date; // La date à laquelle le certificat a été délivré

    /**
     * Constructeur de la classe CertificatMedical.
     * 
     * @param nom Le nom du certificat médical (ex : "Grippe", "Chirurgie", etc.).
     * @param date La date de délivrance du certificat médical.
     */
    public CertificatMedical(String nom, LocalDate date) {
        this.nom = nom; // Assigner le nom du certificat
        this.date = date; // Assigner la date de délivrance du certificat
    }

    // Getter pour le nom du certificat
    public String getNom() {
        return nom;
    }

    // Getter pour la date de délivrance du certificat
    public LocalDate getDate() {
        return date;
    }

    /**
     * Redéfinition de la méthode toString() pour fournir une représentation lisible du certificat.
     * 
     * @return Une chaîne de caractères qui représente le certificat médical sous la forme :
     * "Certificat de [nom] pour le [date]".
     */
    @Override
    public String toString() {
        return "Certificat de " + nom + " pour le " + date;
    }
}
