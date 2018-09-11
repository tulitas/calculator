package calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0.0
 */
class Calculator {

    String calculate(String[] expression) {
        List<String> tmp = new ArrayList<>(List.of(expression));
        //ishem skobki i peredaem virazenija v nih metodu calculate
        int brOpenIdx = -1;
        do {
            brOpenIdx = -1;
            for (int i = 0; i < tmp.size(); i++) {
                String a = tmp.get(i);
                if (a.equals("(")) {
                    brOpenIdx = i;
                } else if (a.equals(")")) {
                    var inBr = tmp.subList(brOpenIdx + 1, i);
                    var result = calculate(inBr);
                    tmp.subList(brOpenIdx, i + 1).clear();
                    tmp.add(brOpenIdx, result);
                }
            }
        } while (brOpenIdx != -1);

        return calculate(tmp);
    }

    String calculate(List<String> expression) {

        List<String> tmp = new ArrayList<>();

        double a = Double.parseDouble(expression.get(0));
        for (int i = 1; i < expression.size(); i += 2) {
            String op = expression.get(i);
            double b = Double.parseDouble(expression.get(i + 1));
            switch (op) {
                case "+":
                case "-":
                    tmp.add(String.valueOf(a));
                    tmp.add(op);
                    a = b;
                    break;
                case "*":
                    a *= b;
                    break;
                case "/":
                    a /= b;
                    break;
            }
        }
        //ghg
        tmp.add(String.valueOf(a));
        System.out.println(tmp);


        double result = Double.parseDouble(tmp.get(0));
        for (int i = 1; i < tmp.size(); i += 2) {
            String op = tmp.get(i);
            double b = Double.parseDouble(tmp.get(i + 1));
            switch (op) {
                case "+":
                    result += b;
                    break;
                case "-":
                    result -= b;
                    break;
                default:
                    return "ERROR";
            }
        }

        return String.valueOf(result);

    }


}
