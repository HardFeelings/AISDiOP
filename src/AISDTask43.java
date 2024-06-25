public class AISDTask43 {
    public static int findMaxSum(int[][] pyramid) {
        int n = pyramid.length;
        int[] dp = new int[pyramid[n - 1].length];
        for (int j = 0; j < pyramid[n - 1].length; j++) {
            dp[j] = pyramid[n - 1][j];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < pyramid[i].length; j++) {
                dp[j] = pyramid[i][j] + Math.max(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int[][] pyramid = {
                {3},
                {7, 4},
                {2, 4, 6},
                {8, 5, 9, 3}
        };

        int maxSum = findMaxSum(pyramid);
        System.out.println("Максимальная сумма скольжения: " + maxSum);
    }
}
