package presence;

import cours.Cours;
import eleve.Eleve;
import prof.Prof;
import java.util.Map;
import java.time.LocalDate;

public class GestionPresence implements IGestionPresence {

    /**
     * Marque la présence ou l'absence d'un élève pour un cours donné à la date du jour.
     * 
     * @param cours Le cours pour lequel la présence doit être marquée.
     * @param eleve L'élève dont la présence doit être marquée.
     * @param estPresent Un booléen qui indique si l'élève est présent (true) ou absent (false).
     */
    @Override
    public void marquerPresence(Cours cours, Eleve eleve, boolean estPresent) {
        // Parcourt les feuilles de présence du cours pour trouver celle de la date actuelle
        for (ListePresence feuille : cours.getFeuillesPresence()) {
            if (feuille.getDate().equals(LocalDate.now())) {
                // Marque la présence de l'élève pour la date actuelle
                feuille.marquerPresence(eleve, estPresent);
            }
        }
    }

    /**
     * Vérifie si un élève est présent ou absent pour un cours donné à la date du jour.
     * 
     * @param cours Le cours pour lequel vérifier la présence.
     * @param eleve L'élève dont la présence doit être vérifiée.
     * @return `true` si l'élève est présent, sinon `false`.
     */
    @Override
    public boolean estPresent(Cours cours, Eleve eleve) {
        // Parcourt les feuilles de présence du cours pour trouver celle de la date actuelle
        for (ListePresence feuille : cours.getFeuillesPresence()) {
            if (feuille.getDate().equals(LocalDate.now())) {
                // Retourne le statut de présence de l'élève à la date du jour
                return feuille.getPresencesEleves().get(eleve);
            }
        }
        // Si aucune feuille de présence n'est trouvée pour cette date, l'élève est présumé absent
        return false;
    }

    /**
     * Affiche la liste des présences pour un cours donné.
     * Pour chaque feuille de présence, l'affichage montre la date et le statut de présence de chaque élève.
     * 
     * @param cours Le cours pour lequel afficher les présences.
     */
    @Override
    public void afficherPresences(Cours cours) {
        // Parcourt toutes les feuilles de présence du cours
        for (ListePresence feuille : cours.getFeuillesPresence()) {
            System.out.println("Date : " + feuille.getDate());
            // Affiche la présence de chaque élève
            for (Map.Entry<Eleve, Boolean> entry : feuille.getPresencesEleves().entrySet()) {
                Eleve eleve = entry.getKey();
                String statutPresence = entry.getValue() ? "Présent" : "Absent";
                // Affiche le nom de l'élève et son statut de présence
                System.out.println("  - " + eleve.getNomComplet() + " : " + statutPresence);
            }
        }
    }

    /**
     * Marque l'absence du professeur pour un cours donné à la date du jour.
     * 
     * @param cours Le cours pour lequel marquer l'absence du professeur.
     * @param prof Le professeur qui est absent.
     */
    @Override
    public void marquerAbsenceProf(Cours cours, Prof prof) {
        // Parcourt les feuilles de présence du cours pour trouver celle de la date actuelle
        for (ListePresence feuille : cours.getFeuillesPresence()) {
            if (feuille.getDate().equals(LocalDate.now())) {
                // Marque l'absence du professeur à la date actuelle
                feuille.setProfAbsent(true);
            }
        }
    }
}
