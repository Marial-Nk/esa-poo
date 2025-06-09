package presence;

import cours.Cours;
import eleve.Eleve;
import prof.Prof;

public interface IGestionPresence {

    /**
     * Marque la présence ou l'absence d'un élève pour un cours donné.
     * 
     * @param cours Le cours pour lequel la présence doit être marquée.
     * @param eleve L'élève dont la présence doit être marquée.
     * @param estPresent Un booléen qui indique si l'élève est présent (true) ou absent (false).
     */
    void marquerPresence(Cours cours, Eleve eleve, boolean estPresent);

    /**
     * Vérifie si un élève est présent ou absent pour un cours donné.
     * 
     * @param cours Le cours pour lequel vérifier la présence.
     * @param eleve L'élève dont la présence doit être vérifiée.
     * @return `true` si l'élève est présent, sinon `false`.
     */
    boolean estPresent(Cours cours, Eleve eleve);

    /**
     * Affiche la liste des présences pour un cours donné.
     * Affiche pour chaque élève la date de présence et son statut (présent ou absent).
     * 
     * @param cours Le cours pour lequel afficher les présences.
     */
    void afficherPresences(Cours cours);

    /**
     * Marque l'absence du professeur pour un cours donné.
     * 
     * @param cours Le cours pour lequel marquer l'absence du professeur.
     * @param prof Le professeur qui est absent.
     */
    void marquerAbsenceProf(Cours cours, Prof prof);
}
