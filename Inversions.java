import java.util.Scanner;

public class Inversions {

    private static long getNumberOfInversionsNaive(int[] a) {
        long numberOfInversions = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    numberOfInversions++;
                }
            }
        }
        return numberOfInversions;
    }

    private static long count(int[] a, int[] aux, int left, int mid, int right) {
        long numberOfInversions = 0;

        for (int k = left; k <= right; k++)
            aux[k] = a[k];

        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > right) a[k] = aux[i++];
            else if (aux[i] <= aux[j]) a[k] = aux[i++];
            else {
                a[k] = aux[j++];
                numberOfInversions += (mid - i + 1);
            }
        }
        return numberOfInversions;
    }

    private static long getNumberOfInversions(int[] a, int[] aux, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left) {
            return numberOfInversions;
        }
        int mid = left + (right - left) / 2;
        numberOfInversions += getNumberOfInversions(a, aux, left, mid);
        numberOfInversions += getNumberOfInversions(a, aux, mid + 1, right);
        numberOfInversions += count(a, aux, left, mid, right);
        return numberOfInversions;
    }

    private static long getNumberOfInversions(int[] a) {
        int[] aux = new int[a.length];
        return getNumberOfInversions(a, aux, 0, a.length - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Cantidad de numeros: ");
        int n = sc.nextInt();
        int[] a = new int[n];

        System.out.print("Escriba los numeros separados por espacio: ");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        long lento = getNumberOfInversionsNaive(a);
        long rapido = getNumberOfInversions(a);

        System.out.println("Inversiones (naive): " + lento);
        System.out.println("Inversiones (divide y venceras): " + rapido);
    }
}