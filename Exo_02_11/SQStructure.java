package Exo_02_11.Exo_02_11;

import java.util.Arrays;
import java.util.Optional;

/**
 * La classe abstraite SQStructure sert de base pour des structures de données
 * dynamiques, telles que les piles et les files, avec un tableau redimensionnable.
 * Elle définit des attributs et des méthodes de base pour la gestion de l'ajout
 * et du retrait d'éléments.
 */
public abstract class SQStructure {

    // Tableau interne pour stocker les éléments de la structure.
    int[] montableau;

    // Taille actuelle du tableau `montableau`.
    int taille;

    // Indice indiquant la position du dernier élément ajouté.
    int tete;

    /**
     * Constructeur de la classe SQStructure.
     * Initialise le tableau montableau avec une taille de 1
     * et place tete à -1 pour indiquer que la structure est vide.
     */
    SQStructure() {
        this.taille =1;
        this.montableau = new int[taille];
        this.tete = -1;
    }

    /**
     * Ajoute un élément à la fin de la structure.
     * Si le tableau est plein, la taille est augmentée de 5 cases pour accommoder
     * de nouveaux éléments.
     *
     * @param value La valeur entière à ajouter.
     */
    public  void Push (int value) {

        // Vérifie si le tableau est plein et le redimensionne si nécessaire.
        if (this.tete >= montableau.length - 1) {
            int newSize = montableau.length + 5;  // Augmente la capacité de 5 cases.
            montableau = Arrays.copyOf(montableau, newSize);
        }

        // Incrémente tete et ajoute la nouvelle valeur.
        this.tete += 1 ;
        this.montableau[this.tete] = value;

    }

    /**
     * Méthode abstraite pour retirer un élément de la structure.
     * Cette méthode doit être implémentée dans les sous-classes.
     *
     * @return Un Optional contenant l'élément retiré, ou Optional.empty() si la structure est vide.
     */
    public abstract Optional<Integer> Pop () ;

    /**
     * Méthode abstraite pour compter le nombre d'éléments dans la structure.
     * Cette méthode doit être implémentée dans les sous-classes.
     *
     * @return Le nombre d'éléments dans la structure.
     */
    public abstract int Count () ;

    /**
     * Retourne la taille du tableau montableau.
     * La taille représente le nombre de cases actuellement allouées pour la structure,
     * et non nécessairement le nombre d'éléments qu'elle contient.
     *
     * @return La taille du tableau interne.
     */
    public int Size () {
        return this.montableau.length;
    }

    public void Clear() {
        this.tete = -1;  // Réinitialise la tête pour vider la structure.
    }
}
