package Calc.Calc;
package com.company;
import java.util.Scanner;
import java.util.Stack;

public class Calc {
    /**
     * выполнение действий с операторами (+,-,*,/)
     */
    public static double operation(double a, double b, char sign) {
        return switch (sign) {
            case '*' -> a * b;
            case '/' -> b / a;
            case '+' -> a + b;
            case '-' -> b - a;
            default -> -1;
        };
    }

    /**
     * установка приоритета
     */
    public static int priority(char sign) {
        return switch (sign) {
            case '(', ')' -> 1;
            case '-', '+' -> 2;
            case '*', '/' -> 3;
            default -> -1;
        };
    }
    /** постфиксная запись выражения
     * @return postfixX
     * (не уверена, что я правильно поняла как этой штукой c java.doc пользоваться) */
    public static String postfix(String expression) {
        Stack <Character> signs = new Stack<>();
        StringBuilder postfixX = new StringBuilder();
        for (char symbol : expression.toCharArray()) {
            if ((symbol >= '0' && symbol <= '9')) {
                postfixX.append(symbol);
            }
            else
            {
                postfixX.append(" ");
                if (priority(symbol) == -1) return("ошибка");
                else {
                    if (signs.isEmpty() || priority(symbol) > priority(signs.peek())) signs.push(symbol);
                    else {
                        postfixX.append(" ");
                        switch (symbol) {
                            case '(' -> signs.push(symbol);
                            case ')' -> {
                                while (signs.peek() != '(') {
                                    postfixX.append(signs.peek().toString());
                                    signs.pop();
                                    if (signs.isEmpty()) return ("ошибка");
                                }
                                signs.pop();
                            }
                            default -> {
                                while (!signs.isEmpty() && priority(signs.peek()) >= priority(symbol)) {
                                    postfixX.append(signs.peek().toString());
                                    signs.pop();
                                }
                                signs.push(symbol);
                            }
                        }
                        postfixX.append(" ");
                    }
                }
            }
        }
        postfixX.append(" ");
        while (!signs.isEmpty())
        {
            postfixX.append(signs.peek().toString());
            signs.pop();
        }
        return postfixX.toString();
    }

    /** считаем значения выражения, записанного в префиксной записи, и записываем результат в answer
     * @return answer - если удалось посчитать */
    public static void calculate(String expression) {
        Stack<Double> numbers = new Stack<>();
        double t = 0;
        boolean flag = true;
        for (char symbol : expression.toCharArray()) {
            if (symbol >= '0' && symbol <= '9') {
                t = t * 10 + Character.getNumericValue(symbol);
                flag = false;
            } else {
                if (symbol == ' ') {
                    if (!flag) {
                        numbers.push(t);
                        t = 0;
                        flag = true;
                    }
                }
                else {
                    if (numbers.isEmpty()) {
                        System.out.println("ошибка");
                        return;
                    }
                    double t1 = numbers.peek();
                    numbers.pop();
                    if (numbers.isEmpty()) {
                        System.out.println("ошибка");
                        return;
                    }
                    double t2 = numbers.peek();
                    numbers.pop();
                    numbers.push(operation(t1, t2, symbol));
                }
            }
        }
        double answer = numbers.peek();
        numbers.pop();
        if (!numbers.isEmpty()) {
            System.out.println("ошибка");
            return;
        }
        System.out.println(answer);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("введите выражение ");
        String expression = scan.nextLine();
        System.out.print(expression+"=");
        String post =postfix(expression);

        if (post.equals("ошибка"))
            System.out.println("ошибка");
        else {
            calculate(post);
            scan.close();
            //System.out.println(post);
        }
    }
}
