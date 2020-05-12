package structures.conjuntistas;

public class FuncionesHash {

    public static int doblamiento(int valor, int n) {
        int g1, g2, g3;
        g1 = valor / 10000000;
        g2 = (valor - (g1 * 10000000)) / 1000;
        g3 = valor - (g1 * 10000000) - (g2 * 1000);
        return (g1 + g2 + g3) % n;
    }

    public static int cuadrado(int valor, int n) {
        int c = valor * valor;
        String cad = "" + c;
        n = n - 1;
        return -1;
    }
}
