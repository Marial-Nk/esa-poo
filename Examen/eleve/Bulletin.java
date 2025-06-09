package eleve;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cours.Examen;

public class Bulletin {
    private Eleve eleve; // L'élève auquel le bulletin appartient
    private String anneeScolaire; // Année scolaire à laquelle le bulletin se réfère
    private Map<String, List<Examen>> examensParSession; // Les examens passés par l'élève, regroupés par session

    /**
     * Constructeur de la classe Bulletin.
     * 
     * @param eleve L'élève auquel le bulletin est attribué.
     * @param anneeScolaire L'année scolaire pour laquelle le bulletin est généré.
     * @param examensParSession Les examens que l'élève a passés, organisés par session.
     */
    public Bulletin(Eleve eleve, String anneeScolaire, Map<String, List<Examen>> examensParSession) {
        this.eleve = eleve; // Assigner l'élève
        this.anneeScolaire = anneeScolaire; // Assigner l'année scolaire
        this.examensParSession = examensParSession; // Assigner les examens par session
    }

    // Getter pour l'élève
    public Eleve getEleve() {
        return eleve;
    }

    // Setter pour l'élève
    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    // Getter pour l'année scolaire
    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    // Setter pour l'année scolaire
    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    /**
     * Ajoute un examen dans le bulletin, sous la session spécifiée.
     * Si la session n'existe pas, elle est créée.
     * 
     * @param session La session d'examen (par exemple "hiver", "été").
     * @param examen L'examen à ajouter à la session.
     */
    public void ajouterExamen(String session, Examen examen) {
        // Ajoute l'examen à la session donnée, crée la session si elle n'existe pas
        examensParSession.computeIfAbsent(session, k -> new ArrayList<>()).add(examen);
    }

    /**
     * Redéfinition de la méthode toString() pour afficher une représentation lisible du bulletin.
     * 
     * @return Une chaîne de caractères représentant le bulletin de l'élève pour l'année scolaire.
     */
    @Override
    public String toString() {
        return "Bulletin de " + eleve.getNomComplet() + " pour l'année scolaire " + anneeScolaire + "\n" + examensParSession;
    }
}
