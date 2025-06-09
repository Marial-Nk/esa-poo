package cours;

import eleve.Eleve;
import presence.ListePresence;

public class ExamenService {

    /**
     * Vérifie si un élève peut passer l'examen pour un cours donné.
     * Un élève peut passer l'examen s'il a moins de 11 absences non justifiées
     * et si son taux de présence est supérieur ou égal à 60%.
     * 
     * @param eleve L'élève pour lequel la vérification est effectuée.
     * @param cours Le cours pour lequel la vérification est effectuée.
     * @return true si l'élève peut passer l'examen, false sinon.
     */
    public boolean peutPasserExamen(Eleve eleve, Cours cours) {
        // Vérifier que l'élève a moins de 11 absences non justifiées
        if (eleve.getAbsencesNonJustifiees(cours) >= 11) {
            return false; // Trop d'absences non justifiées, l'élève ne peut pas passer l'examen
        }

        // Vérifier que l'élève a au moins 60% de présences au cours de l'année
        double totalAbsences = cours.getFeuillesPresence().size(); // Nombre total de cours
        double absences = 0; // Compteur pour les absences de l'élève
        
        // Parcours des feuilles de présence pour compter le nombre d'absences de l'élève
        for (ListePresence feuille : cours.getFeuillesPresence()) {
            // Vérifier si l'élève est présent dans la feuille de présence
            for (Eleve e : feuille.getPresencesEleves().keySet()) {
                if (e.equals(eleve) && !feuille.getPresencesEleves().get(e)) {
                    absences++; // Incrémenter le compteur d'absences si l'élève est absent
                }
            }
        }

        // Calculer le taux de présence
        double tauxPresence = (totalAbsences - absences) / totalAbsences;

        // Retourner vrai si le taux de présence est supérieur ou égal à 60%
        return tauxPresence >= 0.60;
    }
}
