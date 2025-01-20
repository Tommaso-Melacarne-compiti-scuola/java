package online.polp.models;

import org.graalvm.polyglot.Context;

public class Calculator {
    public static String calculate(String expression) {
        if (!isStringCharacterValid(expression)) {
            return "Invalid expression, it contains invalid characters";
        }

        if (!hasValidParenthesis(expression)) {
            return "Invalid expression, invalid parenthesis";
        }

        expression = expression.replaceAll(" ", "");

        try (Context context = Context.create()) {
            return context.eval("js", expression).toString();
        } catch (Exception e) {
            return "Invalid expression";
        }
    }

    /**
     * This method checks if the given expression is valid.
     * Uses a regex to check if the input only contains numbers, operators and brackets.
     *
     * @param expression the expression to check
     * @return true if the expression is valid, false otherwise
     */
    private static boolean isStringCharacterValid(String expression) {
        return expression.matches("[0-9+\\-*/()]*");
    }

    /**
     * This method checks if the given expression has valid parenthesis.
     *
     * @param expression the expression to check
     * @return true if the expression has valid parenthesis, false otherwise
     */
    private static boolean hasValidParenthesis(String expression) {
        int openingBrackets = 0;
        int closingBrackets = 0;

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                openingBrackets++;
            } else if (expression.charAt(i) == ')') {
                closingBrackets++;
            }
        }

        return openingBrackets == closingBrackets;
    }
}
