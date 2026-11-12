import java.util.Scanner;

public class ChangeDP {

    private static int getChange(int money) {
        int[] coins = {1, 3, 4};
        int[] minCoins = new int[money + 1];

        for (int m = 1; m <= money; m++) {
            minCoins[m] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (m >= coins[j]) {
                    int numCoins = minCoins[m - coins[j]] + 1;
                    minCoins[m] = Math.min(minCoins[m], numCoins);
                }
            }
        }

        return minCoins[money];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Valor a cambiar: ");
        int money = sc.nextInt();

        System.out.println("Minimo de monedas (1, 3, 4): " + getChange(money));
    }
}