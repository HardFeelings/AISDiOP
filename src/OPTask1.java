import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class OPTask1 {
    private static final String HISTORY_FILE = "calculator_history.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        readHistory();
        while (true) {
            System.out.print("Введите выражение (или 'exit' для выхода): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                double result = evaluateExpression(input);
                System.out.println("Результат: " + result);
                saveHistory(input + " = " + result);
            } catch (Exception e) {
                System.out.println("Ошибка в выражении: " + e.getMessage());
            }
        }
        scanner.close();
    }



    private static double evaluateExpression(String expression) {
        String formattedExpression = formatExpression(expression);
        return evaluate(formattedExpression);
    }

    private static String formatExpression(String expression) {
        return expression.replaceAll("\\s+", "");
    }

    private static double evaluate(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c >= '0' && c <= '9') {
                StringBuilder sbuf = new StringBuilder();
                while (i < expression.length() && (expression.charAt(i) >= '0' && expression.charAt(i) <= '9' || expression.charAt(i) == '.')) {
                    sbuf.append(expression.charAt(i++));
                }
                i--;
                values.push(Double.parseDouble(sbuf.toString()));
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (ops.peek() != '(') {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!ops.isEmpty() && hasPrecedence(c, ops.peek())) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(c);
            }
        }

        while (!ops.isEmpty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    private static double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Деление на ноль");
                }
                return a / b;
        }
        return 0;
    }

    private static void saveHistory(String record) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE, true))) {
            writer.write(record);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Ошибка записи истории: " + e.getMessage());
        }
    }

    private static void readHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HISTORY_FILE))) {
            String line;
            System.out.println("История вычислений:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения истории: " + e.getMessage());
        }
    }
}
