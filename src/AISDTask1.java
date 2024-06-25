import java.util.Arrays;

public class AISDTask1 {
    public static int findNewNumber(int n) {
        char[] digits = String.valueOf(n).toCharArray();
        int length = digits.length;
        int i = length - 2;
        while (i >= 0 && digits[i] >= digits[i + 1]) {
            i--;
        }
        if (i == -1)
            return -1;
        int j = length - 1;
        while (digits[j] <= digits[i]) {
            j--;
        }
        char temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;
        Arrays.sort(digits, i + 1, length);
        long result = Long.parseLong(new String(digits));
        return result <= Integer.MAX_VALUE ? (int) result : -1;
    }

    public static void main(String[] args) {
        System.out.println(findNewNumber(23));
        System.out.println(findNewNumber(523));
        System.out.println(findNewNumber(2018));
        System.out.println(findNewNumber(1));
        System.out.println(findNewNumber(532));
    }
}
