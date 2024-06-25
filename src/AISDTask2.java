import java.util.ArrayList;
import java.util.List;

public class AISDTask2 {
    public static String findRanges(int[] numbers) {
        if (numbers == null || numbers.length == 0)
            return "";
        List<String> ranges = new ArrayList<>();
        int start = numbers[0];
        int end = numbers[0];

        for (int i = 1; i < numbers.length; ++i) {
            if (numbers[i] == end + 1) {
                end = numbers[i];
            } else {
                if (start == end)
                    ranges.add(String.valueOf(start));
                else
                    ranges.add(start + "-" + end);
                start = numbers[i];
                end = numbers[i];
            }
        }
        if (start == end) {
            ranges.add(String.valueOf(start));
        } else {
            ranges.add(start + "-" + end);
        }
        return String.join(",", ranges);
    }

    public static void main(String[] args) {
        int[] inputArray = {-10, -9, -8, -6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20};
        System.out.println(findRanges(inputArray));
    }
}
