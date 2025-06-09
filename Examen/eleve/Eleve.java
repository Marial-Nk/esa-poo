package eleve;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import cours.Cours;
import cours.Examen;
import cours.GestionCours;
import cours.Session;
import presence.CertificatMedical;
import presence.ListePresence;

public class Eleve {
    private static int compteurId = 1; // Compteur pour générer des IDs uniques pour chaque élève
    private final int id; // ID unique de l'élève
    private String nom; // Nom de l'élève
    private String prenom; // Prénom de l'élève
    private List<Cours> coursAssignes; // Liste des cours suivis par l'élève
    private List<CertificatMedical> certificats = new ArrayList<>(); // Liste des certificats médicaux de l'élève
    private List<Bulletin> bulletins; // Liste des bulletins de l'élève
    private List<Session> sessions; // Liste des sessions d'examen auxquelles l'élève participe
    private Map<Cours, Integer> absencesNonJustifiees = new HashMap<>(); // Map des absences non justifiées par cours
    private List<Examen> examens = new ArrayList<>(); // Liste des examens passés par l'élève

    /**
     * Constructeur de la classe Eleve.
     * 
     * @param nom Le nom de l'élève.
     * @param prenom Le prénom de l'élève.
     */
    public Eleve(String nom, String prenom) {
        this.id = compteurId++; // Génère un ID unique pour l'élève
        this.nom = nom; // Initialisation du nom de l'élève
        this.prenom = prenom; // Initialisation du prénom de l'élève
        this.coursAssignes = new ArrayList<>(); // Initialisation de la liste des cours assignés
        this.bulletins = new ArrayList<>(); // Initialisation de la liste des bulletins
        this.sessions = new ArrayList<>(); // Initialisation de la liste des sessions
    }

    // Getter pour l'ID de l'élève
    public int getId() {
         return id;
    }

    // Getter pour le nom complet de l'élève (prenom + nom)
    public String getNomComplet() {
        return prenom + " " + nom;
    }

    // Getter pour la liste des examens passés par l'élève
    public List<Examen> getExamens() {
        return examens;
    }

    /**
     * Ajoute un examen à la liste des examens de l'élève.
     * 
     * @param examen L'examen à ajouter à la liste.
     */
    public void ajouterExamen(Examen examen) {
        examens.add(examen);  // Ajoute l'examen à la liste des examens de l'élève
    }

    /**
     * Ajoute une absence non justifiée à un cours spécifique.
     * 
     * @param cours Le cours pour lequel l'absence est ajoutée.
     */
    public void ajouterAbsenceNonJustifiee(Cours cours) {
        absencesNonJustifiees.put(cours, absencesNonJustifiees.getOrDefault(cours, 0) + 1);
    }

    /**
     * Retourne le nombre d'absences non justifiées pour un cours spécifique.
     * 
     * @param cours Le cours pour lequel on veut savoir le nombre d'absences non justifiées.
     * @return Le nombre d'absences non justifiées pour ce cours.
     */
    public int getAbsencesNonJustifiees(Cours cours) {
        return absencesNonJustifiees.getOrDefault(cours, 0);
    }

    /**
     * Assigne un cours à l'élève en vérifiant s'il existe dans le gestionnaire de cours.
     * 
     * @param gestionCours L'objet qui gère les cours.
     * @param idCours L'ID du cours à assigner à l'élève.
     */
    public void ajouterCours(GestionCours gestionCours, int idCours) {
        Cours cours = gestionCours.rechercherCoursParId(idCours); // Recherche le cours par ID
        if (cours != null) {
            this.coursAssignes.add(cours);  // Ajouter le cours à la liste des cours assignés
            cours.inscrireEleve(this);       // Inscrire l'élève dans le cours
        }
    }

    // Getter pour la liste des cours assignés à l'élève
    public List<Cours> getCoursAssignes() {
        return coursAssignes;
    }

    /**
     * Ajoute un certificat médical à l'élève.
     * 
     * @param certif Le certificat médical à ajouter.
     */
    public void ajouterCertificat(CertificatMedical certif) {
        certificats.add(certif);
    }

    /**
     * Vérifie si l'élève possède un certificat médical pour une date donnée.
     * 
     * @param date La date à vérifier pour un certificat médical.
     * @return true si un certificat médical existe pour la date donnée, sinon false.
     */
    public boolean aCertificatPourDate(LocalDate date) {
        return certificats.stream().anyMatch(c -> c.getDate().equals(date));
    }

    // Getter pour la liste des certificats médicaux de l'élève
    public List<CertificatMedical> getCertificats() {
        return certificats;
    }

    /**
     * Compte les absences non justifiées pour un cours donné.
     * 
     * @param cours Le cours pour lequel compter les absences non justifiées.
     * @return Le nombre d'absences non justifiées pour le cours.
     */
    public long compterAbsencesNonJustifieesPourCours(Cours cours) {
        return cours.getFeuillesPresence().stream()
            .filter(f -> !f.isProfAbsent())  // Exclure les séances où le professeur est absent
            .filter(f -> {
                Boolean present = f.getPresencesEleves().get(this);
                return present != null && !present; // L'élève est absent
            })
            .filter(f -> !aCertificatPourDate(f.getDate())) // Vérifier que l'absence n'est pas justifiée par un certificat
            .count();
    }

    /**
     * Vérifie si l'élève peut présenter l'examen d'un cours.
     * L'élève doit avoir moins de 11 absences non justifiées et un taux de présence supérieur ou égal à 60%.
     * 
     * @param cours Le cours pour lequel l'élève souhaite passer l'examen.
     * @return true si l'élève peut passer l'examen, sinon false.
     */
    public boolean peutPresenterExamen(Cours cours) {
        List<ListePresence> feuilles = cours.getFeuillesPresence().stream()
            .filter(f -> !f.isProfAbsent())  // Exclure les séances où le professeur est absent
            .filter(f -> f.getPresencesEleves().containsKey(this))  // L'élève doit être inscrit dans la feuille de présence
            .toList();

        long totalSeances = feuilles.size();
        if (totalSeances == 0) return false; // Aucune séance donc pas d'examen possible

        long absencesNonJustifiees = feuilles.stream()
            .filter(f -> !f.getPresencesEleves().get(this)) // L'élève est absent
            .filter(f -> !aCertificatPourDate(f.getDate())) // L'absence n'est pas justifiée
            .count();

        long presences = totalSeances - absencesNonJustifiees;
        double tauxPresence = (double) presences / totalSeances;

        return absencesNonJustifiees < 11 && tauxPresence >= 0.6;
    }

    /**
     * Affiche l'état des absences et de la possibilité de passer les examens pour chaque cours assigné.
     */
    public void afficherEtatParCours() {
        StringBuilder result = new StringBuilder(); // Utilisation de StringBuilder pour une construction efficace du texte

        for (Cours cours : coursAssignes) {
            boolean admissible = peutPresenterExamen(cours);
            result.append("Cours : ").append(cours.getNom())
                .append(" → ").append(admissible ? "Peut présenter l'examen" : "Ne peut pas présenter l'examen")
                .append("\n");

            // Vous pouvez également afficher des détails supplémentaires, comme le taux de présence et le nombre d'absences
            long absencesNonJustifiees = compterAbsencesNonJustifieesPourCours(cours);
            result.append("   Absences non justifiées : ").append(absencesNonJustifiees)
                .append(" / ").append(cours.getFeuillesPresence().size()).append(" séances\n");

            // Ajoutez un espace pour séparer les informations entre les cours
            result.append("\n");
        }

        // Afficher le résultat dans la console
        System.out.println(result.toString());
    }

    // Getter pour la liste des bulletins de l'élève
    public List<Bulletin> getBulletins() {
        return bulletins;
    }

    /**
     * Ajoute un bulletin à la liste des bulletins de l'élève.
     * 
     * @param bulletin Le bulletin à ajouter.
     */
    public void ajouterBulletin(Bulletin bulletin) {
        bulletins.add(bulletin);
    }

    // Getter pour la liste des sessions de l'élève
    public List<Session> getSessions() {
        return sessions;
    }

    /**
     * Ajoute une session d'examen à l'élève.
     * 
     * @param session La session d'examen à ajouter.
     */
    public void ajouterSession(Session session) {
        sessions.add(session);
    }

    /**
     * Redéfinition de la méthode toString() pour afficher une représentation lisible de l'élève.
     * 
     * @return Une chaîne représentant l'élève sous le format "id prénom nom".
     */
    @Override
    public String toString() {
        return id + " " + prenom + " " + nom;
    }
}
