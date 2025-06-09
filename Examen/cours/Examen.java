package cours;

import eleve.Eleve;

public class Examen {
    private Cours cours; // Le cours pour lequel l'examen est passé
    private Eleve eleve; // L'élève qui passe l'examen
    private int note; // La note obtenue par l'élève lors de l'examen
    private String session; // La session d'examen (par exemple, session d'été ou d'hiver)

    /**
     * Constructeur de la classe Examen.
     * 
     * @param cours Le cours pour lequel l'examen est donné.
     * @param eleve L'élève qui passe l'examen.
     * @param note La note obtenue par l'élève.
     * @param session La session d'examen (par exemple, "hiver", "été").
     */
    public Examen(Cours cours, Eleve eleve, int note, String session) {
        this.cours = cours; // Assigner le cours à l'examen
        this.eleve = eleve; // Assigner l'élève à l'examen
        this.note = note;   // Assigner la note obtenue par l'élève
        this.session = session; // Assigner la session de l'examen
    }

    // Getter pour le cours de l'examen
    public Cours getCours() {
        return cours;
    }

    // Setter pour le cours de l'examen
    public void setCours(Cours cours) {
        this.cours = cours;
    }

    // Getter pour l'élève qui passe l'examen
    public Eleve getEleve() {
        return eleve;
    }

    // Setter pour l'élève qui passe l'examen
    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    // Getter pour la note obtenue par l'élève
    public double getNote() {
        return note;
    }

    // Setter pour la note obtenue par l'élève
    public void setNote(int note) {
        this.note = note;
    }

    // Getter pour la session de l'examen
    public String getSession() {
        return session;
    }

    /**
     * Redéfinition de la méthode toString() pour afficher le résultat de l'examen sous une forme lisible.
     * 
     * @return Une chaîne contenant le nom du cours et la note obtenue par l'élève.
     */
    @Override
    public String toString() {
        return cours.getNom() + "  " + note;
    }
}
