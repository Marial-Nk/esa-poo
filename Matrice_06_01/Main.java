import function.Matrice;
import function.Fraction;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        // Création de matrices pour les tests
        Matrice matrix1 = generateRandomMatrix(3, 3);
        Matrice matrix2 = generateRandomMatrix(3, 3);

        // Affichage des matrices
        System.out.println("Matrice 1 :");
        System.out.println(matrix1);

        System.out.println("Matrice 2 :");
        System.out.println(matrix2);

        // Test des opérations sur les matrices
        testMatrixOperations(matrix1, matrix2);
    }

    private static void testMatrixOperations(Matrice matrix1, Matrice matrix2) {
        // Addition des matrices
        System.out.println("Addition des matrices :");
        try {
            System.out.println(matrix1.add(matrix2));
        } catch (IllegalArgumentException e) {
            System.out.printf("Erreur lors de l'addition : %s%n", e.getMessage());
        }

        // Soustraction des matrices
        System.out.println("Soustraction des matrices :");
        try {
            System.out.println(matrix1.subtract(matrix2));
        } catch (IllegalArgumentException e) {
            System.out.printf("Erreur lors de la soustraction : %s%n", e.getMessage());
        }

        // Multiplication des matrices
        System.out.println("Multiplication des matrices :");
        try {
            System.out.println(matrix1.multiply(matrix2));
        } catch (IllegalArgumentException e) {
            System.out.printf("Erreur lors de la multiplication : %s%n", e.getMessage());
        }

        // Multiplication par un scalaire
        System.out.println("Multiplication par un scalaire :");
        try {
            Fraction scalar = new Fraction(2, 1);
            System.out.println(matrix1.multiplyByScalar(scalar));
        } catch (IllegalArgumentException e) {
            System.out.printf("Erreur lors de la multiplication par un scalaire : %s%n", e.getMessage());
        }

        // Transposée
        System.out.println("Transposée de la première matrice :");
        System.out.println(matrix1.transpose());

        // Déterminant
        System.out.println("Déterminant de la première matrice :");
        try {
            System.out.println(matrix1.determinant());
        } catch (IllegalArgumentException e) {
            System.out.printf("Erreur lors du calcul du déterminant : %s%n", e.getMessage());
        }

        // Inverse
        System.out.println("Inverse de la première matrice :");
        try {
            System.out.println(matrix1.inverse());
        } catch (ArithmeticException e) {
            System.out.printf("Erreur lors du calcul de l'inverse : %s%n", e.getMessage());
        }

        System.out.println("***********************************************************");
    }

    private static Matrice   generateRandomMatrix(int rows, int cols) {
        Matrice matrix = new Matrice(rows, cols);
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int numerator = random.nextInt(20) - 10;
                int denominator = random.nextInt(19) + 1;
                if (random.nextBoolean()) { // Mélange d'entiers et de fractions
                    denominator = 1; // Génère un entier
                }
                matrix.setElement(i, j, new Fraction(numerator, denominator));
            }
        }

        return matrix;
    }
}