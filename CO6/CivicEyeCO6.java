import java.util.Arrays;

public class CivicEyeCO6 {

    // 0/1 Knapsack using Dynamic Programming
    static int knapsack(int W, int wt[], int val[], int n) {

        int dp[][] = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {

            for (int w = 0; w <= W; w++) {

                if (i == 0 || w == 0)
                    dp[i][w] = 0;

                else if (wt[i - 1] <= w)

                    dp[i][w] = Math.max(
                            val[i - 1]
                                    + dp[i - 1][w - wt[i - 1]],
                            dp[i - 1][w]
                    );

                else
                    dp[i][w] = dp[i - 1][w];
            }
        }

        return dp[n][W];
    }

    // Longest Common Subsequence
    static int lcs(String X, String Y) {

        int m = X.length();
        int n = Y.length();

        int dp[][] = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {

            for (int j = 0; j <= n; j++) {

                if (i == 0 || j == 0)
                    dp[i][j] = 0;

                else if (X.charAt(i - 1) == Y.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                else
                    dp[i][j] = Math.max(
                            dp[i - 1][j],
                            dp[i][j - 1]
                    );
            }
        }

        return dp[m][n];
    }

    // Matrix Chain Multiplication
    static int matrixChain(int p[], int n) {

        int m[][] = new int[n][n];

        for (int L = 2; L < n; L++) {

            for (int i = 1; i < n - L + 1; i++) {

                int j = i + L - 1;

                m[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {

                    int q =
                            m[i][k]
                                    + m[k + 1][j]
                                    + p[i - 1] * p[k] * p[j];

                    if (q < m[i][j])
                        m[i][j] = q;
                }
            }
        }

        return m[1][n - 1];
    }

    public static void main(String[] args) {

        System.out.println("=== CIVICEYE OPTIMIZATION ANALYSIS ===\n");

        // 0/1 Knapsack
        int values[] = {60, 100, 120};
        int weights[] = {10, 20, 30};
        int capacity = 50;

        int maxValue =
                knapsack(
                        capacity,
                        weights,
                        values,
                        values.length
                );

        System.out.println(
                "Knapsack Maximum Value = "
                        + maxValue
        );

        // LCS
        String city1 = "SMARTCITY";
        String city2 = "CITYPLAN";

        int lcsLength = lcs(city1, city2);

        System.out.println(
                "LCS Length = "
                        + lcsLength
        );

        // Matrix Chain Multiplication
        int dimensions[] = {10, 20, 30, 40};

        int minCost =
                matrixChain(
                        dimensions,
                        dimensions.length
                );

        System.out.println(
                "Matrix Chain Cost = "
                        + minCost
        );

        System.out.println(
                "\n=== CONCLUSION ==="
        );

        System.out.println(
                "Dynamic Programming optimized resource allocation and computation."
        );
    }
}