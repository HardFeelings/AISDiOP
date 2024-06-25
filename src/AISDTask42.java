public class AISDTask42 {
    public static int countNumbersInIntervals(int[][] intervals) {
        int count = 0;

        for (int[] interval : intervals) {
            if (interval.length == 2 && interval[0] <= interval[1]) {
                count += (interval[1] - interval[0] + 1);
            } else {
                return -1;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {1, 2},
                {6, 10},
                {11, 15}
        };

        int result = countNumbersInIntervals(intervals);
        System.out.println("Общее количество чисел в интервалах: " + result);

    }
}
