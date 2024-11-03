package Exo_02_11.Exo_02_11;

import java.util.Arrays;
import java.util.Optional;

/**
 * La classe Stack représente une pile (Last In, First Out - LIFO) extensible et rétractable.
 * Elle hérite de SQStructure, ce qui lui permet de redimensionner dynamiquement son tableau interne.
 */
public class Stack extends SQStructure {

    /**
     * Constructeur de la classe Stack.
     * Appelle le constructeur de la classe parente SQStructure pour initialiser la pile.
     */

    public Stack() {
        super();
    }

    /**
     * Retire l'élément au sommet de la pile et le retourne sous forme d'Optional.
     * Si la pile est vide, retourne Optional.empty().
     *
     * @return L'élément retiré du sommet de la pile encapsulé dans un Optional, ou Optional.empty() si la pile est vide.
     */
    public Optional<Integer> Pop () {

        // Vérifie si la pile est vide en testant si tete est inférieur à 0.
        if (this.tete < 0) {
            return Optional.empty();
        }

        // Récupère la valeur au sommet de la pile.
        int valeur = this.montableau[this.tete];  // Récupère la valeur au sommet de la pile.
        this.tete = this.tete - 1;  // Décrémente `tete` pour retirer l'élément.

        // Redimensionnement conditionnel : réduit la taille de la pile de 5 cases si
        // il y a 5 cases inutilisées après le retrait et que la pile n'est pas vide.
        if ((this.Size() - this.tete - 1 == 5)  && (this.tete >= 0)) {
            int newSize = Math.max(this.montableau.length - 5, 1);  // La taille minimale est 1.
            this.montableau = Arrays.copyOf(montableau, newSize);
        }

        // Retourne l'élément retiré encapsulé dans un Optional.
        return Optional.of(valeur) ;
    }

    /**
     * Compte et retourne le nombre d'éléments dans la pile.
     * Le nombre d'éléments correspond à tete + 1.
     *
     * @return Le nombre d'éléments présents dans la pile.
     */
    public int Count () {

        return this.tete+1 ;
    }

    /**
     * Affiche les éléments de la pile du bas vers le sommet.
     * Si la pile est vide, affiche un message indiquant qu'elle est vide.
     */
    public  void Affiche() {

        // Vérifie si la pile est vide.
        if (this.tete < 0) {
            System.out.println("La pile est vide");
            return;
        }

        // Parcourt et affiche les éléments de la pile, de l'index 0 jusqu'à tete.
        for (int i = 0; i <= this.tete; i++) {
            System.out.println(this.montableau[i] + " | ");
        }
    }

    public void Clear() {
        super.Clear();
    }
}
