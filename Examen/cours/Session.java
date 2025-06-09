package cours;

import java.util.List;

public class Session {
    private String nom; // Nom de la session (par exemple : juin, août)
    private List<Examen> examens; // Liste des examens dans cette session

    /**
     * Constructeur de la classe Session.
     * 
     * @param nom Le nom de la session (par exemple, "juin", "août").
     * @param examens La liste des examens associés à cette session.
     */
    public Session(String nom, List<Examen> examens) {
        this.nom = nom; // Assigner le nom de la session
        this.examens = examens; // Assigner la liste des examens
    }

    // Getter pour le nom de la session
    public String getNom() {
        return nom;
    }

    // Setter pour le nom de la session
    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter pour la liste des examens de la session
    public List<Examen> getExamens() {
        return examens;
    }

    // Setter pour la liste des examens de la session
    public void setExamens(List<Examen> examens) {
        this.examens = examens;
    }

    /**
     * Redéfinition de la méthode toString() pour afficher une représentation lisible de l'objet Session.
     * 
     * @return Une chaîne de caractères représentant la session avec ses examens.
     */
    @Override
    public String toString() {
        return "Session{" +
               "nom='" + nom + '\'' +
               ", examens=" + examens +
               '}';
    }
}
