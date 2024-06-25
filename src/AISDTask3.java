public class AISDTask3 {
    private static final int SECONDS_IN_A_MINUTE = 60;
    private static final int SECONDS_IN_AN_HOUR = 3600;
    private static final int SECONDS_IN_A_DAY = 86400;
    private static final int SECONDS_IN_A_YEAR = 31536000;

    public static String formatTime(int totalSeconds) {
        if (totalSeconds < 0) {
            return "";
        }

        int years = totalSeconds / SECONDS_IN_A_YEAR;
        totalSeconds %= SECONDS_IN_A_YEAR;

        int days = totalSeconds / SECONDS_IN_A_DAY;
        totalSeconds %= SECONDS_IN_A_DAY;

        int hours = totalSeconds / SECONDS_IN_AN_HOUR;
        totalSeconds %= SECONDS_IN_AN_HOUR;

        int minutes = totalSeconds / SECONDS_IN_A_MINUTE;
        int seconds = totalSeconds % SECONDS_IN_A_MINUTE;

        StringBuilder result = new StringBuilder();
        appendTimeUnit(result, years, "год", "года", "лет");
        appendTimeUnit(result, days, "день", "дня", "дней");
        appendTimeUnit(result, hours, "час", "часа", "часов");
        appendTimeUnit(result, minutes, "минута", "минуты", "минут");
        appendTimeUnit(result, seconds, "секунда", "секунды", "секунд");

        if (result.length() == 0)
            return "0 секунд";
        return formatResult(result);
    }

    private static void appendTimeUnit(StringBuilder result, int value, String singular, String dual, String plural) {
        if (value > 0) {
            if (result.length() > 0) {
                result.append(", ");
            }
            result.append(value).append(" ");
            if (value % 10 == 1 && value % 100 != 11)
                result.append(singular);
            else if (value % 10 > 1 && value % 10 < 5 && (value % 100 < 10 || value % 100 >= 20))
                result.append(dual);
            else
                result.append(plural);
        }
    }

    private static String formatResult(StringBuilder result) {
        int lastComma = result.lastIndexOf(", ");
        if (lastComma != -1) {
            result.replace(lastComma, lastComma + 2, " и ");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(formatTime(62));
        System.out.println(formatTime(3662));
        System.out.println(formatTime(31536062));
        System.out.println(formatTime(604800 + 86400 + 3600 + 60 + 1));
    }
}
