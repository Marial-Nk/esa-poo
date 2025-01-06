package function;

// Classe représentant une matrice

public class Matrice {

    private Fraction[][] elements;

    public Matrice(int rows, int cols) {
        elements = new Fraction[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                elements[i][j] = new Fraction(0, 1); // Initialise chaque élément à 0/1
            }
        }
    }

    public void setElement(int row, int col, Fraction value) {
        elements[row][col] = value;
    }

    public Fraction getElement(int row, int col) {
        return elements[row][col];
    }

    public int getRowCount() {
        return elements.length;
    }

    public int getColCount() {
        return elements[0].length;
    }

    public Matrice add(Matrice other) {
        // Vérification si les matrices ont la même taille
        if (this.elements.length != other.elements.length || this.elements[0].length != other.elements[0].length) {
            throw new IllegalArgumentException("Les matrices doivent avoir la même taille pour l'addition.");
        }

        // Création de la matrice résultat avec les mêmes dimensions
        Matrice result = new Matrice(this.elements.length, this.elements[0].length);

        // Addition des éléments des deux matrices
        for (int i = 0; i < this.elements.length; i++) {
            for (int j = 0; j < this.elements[0].length; j++) {
                // Addition des fractions
                result.elements[i][j] = this.elements[i][j].add(other.elements[i][j]);
            }
        }

        return result;
    }


    public Matrice subtract(Matrice other) {
        // Vérification si les matrices ont la même taille
        if (this.elements.length != other.elements.length || this.elements[0].length != other.elements[0].length) {
            throw new IllegalArgumentException("Les matrices doivent avoir la même taille pour la soustraction.");
        }

        // Création de la matrice résultat avec les mêmes dimensions
        Matrice result = new Matrice(this.elements.length, this.elements[0].length);

        // Soustraction des éléments des deux matrices
        for (int i = 0; i < this.elements.length; i++) {
            for (int j = 0; j < this.elements[0].length; j++) {
                // Soustraction des fractions
                result.elements[i][j] = this.elements[i][j].subtract(other.elements[i][j]);
            }
        }

        return result;
    }


    public Matrice multiply(Matrice other) {
        // Vérification si la multiplication est possible
        if (this.elements[0].length != other.elements.length) {
            throw new IllegalArgumentException("Le nombre de colonnes de la première matrice doit être égal au nombre de lignes de la deuxième matrice.");
        }

        // Création de la matrice résultat avec les dimensions appropriées
        Matrice result = new Matrice(this.elements.length, other.elements[0].length);

        // Multiplication des matrices
        for (int i = 0; i < this.elements.length; i++) {
            for (int j = 0; j < other.elements[0].length; j++) {
                Fraction sum = new Fraction(0, 1); // Initialise la somme à 0
                for (int k = 0; k < this.elements[0].length; k++) {
                    sum = sum.add(this.elements[i][k].multiply(other.elements[k][j]));
                }
                result.elements[i][j] = sum;
            }
        }

        return result;
    }


    public Matrice multiplyByScalar(Fraction scalar) {
        // Création de la matrice résultat avec les mêmes dimensions
        Matrice result = new Matrice(this.elements.length, this.elements[0].length);

        // Multiplication de chaque élément de la matrice par le scalaire
        for (int i = 0; i < this.elements.length; i++) {
            for (int j = 0; j < this.elements[0].length; j++) {
                // Multiplication de l'élément par le scalaire
                result.elements[i][j] = this.elements[i][j].multiply(scalar);
            }
        }

        return result;
    }


    public Matrice transpose() {
        // Création de la matrice transposée avec les dimensions inversées
        Matrice result = new Matrice(this.elements[0].length, this.elements.length);

        // Transposition de la matrice
        for (int i = 0; i < this.elements.length; i++) {
            for (int j = 0; j < this.elements[0].length; j++) {
                // L'élément (i, j) de la matrice d'origine devient (j, i) dans la matrice transposée
                result.elements[j][i] = this.elements[i][j];
            }
        }

        return result;
    }


    public Fraction determinant() {
        if (getRowCount() != getColCount()) {
            throw new IllegalArgumentException("La matrice doit être carrée pour calculer le déterminant.");
        }
        return calculateDeterminant(this);
    }

    private Fraction calculateDeterminant(Matrice matrix) {
        int size = matrix.elements.length;

        // Si la matrice est de taille 1x1, son déterminant est l'élément lui-même
        if (size == 1) {
            return matrix.elements[0][0];
        }

        // Si la matrice est de taille 2x2, on applique la formule classique
        if (size == 2) {
            return matrix.elements[0][0].multiply(matrix.elements[1][1])
                    .subtract(matrix.elements[0][1].multiply(matrix.elements[1][0]));
        }

        // Calcul du déterminant pour les matrices de taille supérieure
        Fraction determinant = new Fraction(0, 1);
        for (int col = 0; col < size; col++) {
            // Calcul du mineur en supprimant la première ligne et la colonne `col`
            Matrice minor = minor(matrix, 0, col);
            // Ajouter ou soustraire selon la règle des signes
            Fraction minorDet = calculateDeterminant(minor);
            Fraction sign = new Fraction(col % 2 == 0 ? 1 : -1, 1);
            determinant = determinant.add(matrix.elements[0][col].multiply(sign).multiply(minorDet));
        }
        return determinant;
    }

    // Méthode pour obtenir le mineur de la matrice (en excluant la ligne i et la colonne j)
    private Matrice minor(Matrice matrix, int row, int col) {
        // Création de la matrice mineure
        Matrice minor = new Matrice(matrix.elements.length - 1, matrix.elements[0].length - 1);
        int r = 0, c;
        for (int i = 0; i < matrix.elements.length; i++) {
            if (i == row) continue; // Ignorer la ligne `row`
            c = 0;
            for (int j = 0; j < matrix.elements[0].length; j++) {
                if (j == col) continue; // Ignorer la colonne `col`
                minor.elements[r][c] = matrix.elements[i][j];
                c++;
            }
            r++;
        }
        return minor;
    }


    public Matrice inverse() {
        Fraction det = determinant();
        if (det.toDouble() == 0) {
            throw new ArithmeticException("La matrice est singulière et ne peut pas être inversée.");
        }

        int size = getRowCount();
        Matrice adjugate = new Matrice(size, size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Matrice minor = minor(this, i, j);
                Fraction cofactor = new Fraction((i + j) % 2 == 0 ? 1 : -1, 1).multiply(minor.determinant());
                adjugate.setElement(j, i, cofactor); // Transposer
            }
        }
        return adjugate.multiplyByScalar(new Fraction(1, det.numerator));  // Correction du calcul de l'inverse
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Parcours de chaque ligne de la matrice
        for (Fraction[] row : elements) {
            // Parcours de chaque élément dans la ligne
            for (Fraction value : row) {
                sb.append(value).append("\t");  // Ajout de l'élément et d'un tabulateur
            }
            sb.append("\n");  // Ajout d'un saut de ligne après chaque ligne de la matrice
        }

        return sb.toString();  // Retourne la chaîne construite
    }

}
