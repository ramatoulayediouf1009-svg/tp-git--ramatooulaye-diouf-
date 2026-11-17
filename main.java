public class Main {

    static int addition(int a, int b) {
        return a + b;
    }

    static int soustraction(int a, int b) {
        return a - b;
    }

    static int multiplication(int a, int b) {
        return a * b;
    }

    static double division(double a, double b) {
        if (b == 0) {
            System.out.println("Erreur : Division par zéro");
            return 0;
        }
        return a / b;
    }

    static double puissance(double base, double exposant) {
        return Math.pow(base, exposant);
    }

    static int modulo(int a, int b) {
        return a % b;
    }

    public static void main(String[] args) {
        System.out.println("2 + 3 = " + addition(2, 3));
        System.out.println("5 - 2 = " + soustraction(5, 2));
        System.out.println("4 * 3 = " + multiplication(4, 3));
        System.out.println("10 / 2 = " + division(10, 2));
        System.out.println("2^3 = " + puissance(2, 3));
        System.out.println("5 % 2 = " + modulo(5, 2));
    }
}