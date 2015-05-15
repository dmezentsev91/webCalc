package WebCalc1;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


public class LogicWebProgram {
    private static final HashMap<String, Integer> priorityFunction = new HashMap<String, Integer>();

    static {
        priorityFunction.put("sin(", 0);
        priorityFunction.put("cos(", 0);
        priorityFunction.put("tan(", 0);
        priorityFunction.put("ctan(", 0);
        priorityFunction.put("sqrt(", 0);
        priorityFunction.put("asin(", 0);
        priorityFunction.put("acos(", 0);
        priorityFunction.put("ln(", 0);
        priorityFunction.put("lg(", 0);
        priorityFunction.put("(", 0);
        priorityFunction.put(")", 0);
        priorityFunction.put("+", 1);
        priorityFunction.put("-", 1);
        priorityFunction.put("*", 2);
        priorityFunction.put("/", 2);
        priorityFunction.put("^", 3);
        priorityFunction.put("!", 4);
    }


    ArrayList<String> postExp = new ArrayList<String>();
    Stack<String> stack = new Stack<String>();
    Stack<Double> stackD = new Stack<Double>();

    ArrayList<String> firstListFunct;
    boolean rad;

    // public static HashMap<String, Integer> getPriorityFunction() {
//        return priorityFunction;
//    }

    public String mainMethod(String arg) throws Exception {
        if (arg.equals("nullnull")) return "";
        String result;
        String temp;
        double resultD;
        try {
            temp = liteCorrecting(arg);
            if (temp.length() == 1) return "";
            temp = degOrRad(temp);

            firstListFunct = separationFunctAndNumb(temp);
            methodTransform(firstListFunct);
            resultD = round(10, CalculateResult());

            if ((resultD - (int) resultD) == 0)
                result = Integer.toString((int) resultD);
            else
                result = Double.toString(resultD);

            return additionalRounding(result);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }
    public String nullExpression(String arg) {
        try {
            if (arg.equals(null)) return "";
            return arg;
        } catch (Exception e) {
            return "";
        }
    }

    private String additionalRounding(String a){
        try {
            if (a.substring(a.length() - 10).equals("0000000001"))
                return a.substring(0, a.length() - 11);
        } catch (IndexOutOfBoundsException e){ }
         return a;
    }

    private String degOrRad(String temp) {
        if (temp.indexOf('d') == 0) {
            rad = false;
            temp = temp.substring(1);
        } else if (temp.indexOf('r') == 0) {
            temp = temp.substring(1);
            rad = true;
        } else
            rad = true;
        return temp;
    }

    private double round(int n, double number) {
        double newDouble = new BigDecimal(number).setScale(n, RoundingMode.UP).doubleValue();
        return newDouble;
    }

    private String liteCorrecting(String arg) {
        return arg.replaceAll(" ", "").replaceAll("/,", ".").toLowerCase();
    }

    private ArrayList<String> separationFunctAndNumb(String arg) throws Exception {
        ArrayList<String> listF = new ArrayList<String>();
        String buff = "";
        char[] chars = arg.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            try {
                if (chars[i] == 'p' && chars[i + 1] == 'i') {
                    listF.add(Double.toString(Math.PI));
                    i++;
                    continue;

                }
            } catch (IndexOutOfBoundsException e) {

            }
            if (chars[i] == 'e') {
                listF.add(Double.toString(Math.E));
                continue;

            } else if (chars[i] >= 48 && chars[i] <= 57) {
                buff = searchNumber(i, chars);
                if (i != chars.length - 1)
                    i += buff.length() - 1;
                listF.add(buff);
                continue;

            } else {
                buff = searchFunction(i, arg);
                if (buff != null)
                    i += buff.length() - 1;
            }
            if (buff != null)
                listF.add(buff);
        }

        return listF;
    } //separation line on the number and function

    private String searchFunction(int n, String str) {
        // n - first symbol of function
        for (Map.Entry<String, Integer> pair : priorityFunction.entrySet()) {
            try {
                String maybeFunct = str.substring(n, n + pair.getKey().length());
                if (maybeFunct.equals(pair.getKey())) return maybeFunct;
            } catch (StringIndexOutOfBoundsException e) {
                continue;
            }
        }
        return null;
    }

    private String searchNumber(int n, char[] chars) throws Exception {
        // n - first symbol of number
        String buff = "";
        for (int i = n; i < chars.length; i++) {
            if ((chars[i] >= 48 && chars[i] <= 57) || chars[i] == '.') {
                buff += Character.toString(chars[i]);
                continue;
            }
            if (buff.indexOf('.') != buff.lastIndexOf('.')) throw new Exception();
            else return buff;
        }
        return buff;
    }

    private void methodTransform(ArrayList<String> expressionList) {

        String partExp;
        for (int i = 0; i < expressionList.size(); i++) {
            partExp = expressionList.get(i);
            if (isNumber(partExp)) postExp.add(partExp);
            else {

                int a = priorityFunction.get(partExp);
                rulsSelect(partExp, a);
            }
        }
        while (!stack.empty()) {
            postExp.add(stack.pop());
        }
    } //Transform expressionList to postExp

    private void rulsSelect(String arg, int priorityNewElement) {
        int priorityInStack;
        try {
            priorityInStack = priorityFunction.get(stack.peek());
        } catch (EmptyStackException e) {
            stack.push(arg);
            return;
        }
        if (arg.equals(")")) {
            while (priorityInStack != 0) {
                postExp.add(stack.pop());
                priorityInStack = priorityFunction.get(stack.peek());
            }
            if (!stack.peek().equals("(")) postExp.add(stack.pop());
            else stack.pop();
        } else if (stack.isEmpty() || (priorityNewElement == 0))
            stack.push(arg);
        else if (priorityInStack < priorityNewElement)
            stack.add(arg);
        else if (priorityNewElement <= priorityInStack) {
            postExp.add(stack.pop());
            rulsSelect(arg, priorityNewElement);
        }

    } // https://ru.wikipedia.org/wiki/%D0%9E%D0%B1%D1%80%D0%B0%D1%82%D0%BD%D0%B0%D1%8F_%D0%BF%D0%BE%D0%BB%D1%8C%D1%81%D0%BA%D0%B0%D1%8F_%D0%B7%D0%B0%D0%BF%D0%B8%D1%81%D1%8C

    private boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private double CalculateResult() throws Exception {
        String str;
        for (int i = 0; i < postExp.size(); i++) {
            str = postExp.get(i);
            if (isNumber(str)) stackD.push(Double.parseDouble(postExp.get(i)));
            else doFunction(str);
        }
        return stackD.pop();
    } //

    private void doFunction(String str) throws Exception {
        int priorityFunct = priorityFunction.get(str);
        if (priorityFunct == 0 || str.equals("!")) unaryFunct(str);
        else binaryFunct(str);
    } // call method binaryFunct or unaryFunct

    private void binaryFunct(String str) {
        double upperElem = stackD.pop();
        double lowerElem = stackD.pop();
        if (str.equals("+")) stackD.push(CalculationExpression.plus(lowerElem, upperElem));
        if (str.equals("-")) stackD.push(CalculationExpression.minus(lowerElem, upperElem));
        if (str.equals("*")) stackD.push(CalculationExpression.multip(lowerElem, upperElem));
        if (str.equals("/")) stackD.push(CalculationExpression.division(lowerElem, upperElem));
        if (str.equals("^")) stackD.push(CalculationExpression.pow(lowerElem, upperElem));
    }

    private void unaryFunct(String str) throws Exception {
        if (str.equals("sin(")) stackD.push(CalculationExpression.sin(rad, stackD.pop()));
        else if (str.equals("cos(")) stackD.push(CalculationExpression.cos(rad, stackD.pop()));
        else if (str.equals("tan(")) stackD.push(CalculationExpression.tan(rad, stackD.pop()));
        else if (str.equals("ctan(")) stackD.push(CalculationExpression.ctan(rad, stackD.pop()));
        else if (str.equals("sqrt(")) stackD.push(CalculationExpression.sqrt(stackD.pop()));
        else if (str.equals("asin(")) stackD.push(CalculationExpression.aSin(rad, stackD.pop()));
        else if (str.equals("acos(")) stackD.push(CalculationExpression.aCos(rad, stackD.pop()));
        else if (str.equals("ln(")) stackD.push(CalculationExpression.ln(stackD.pop()));
        else if (str.equals("lg(")) stackD.push(CalculationExpression.lg(stackD.pop()));
        else if (str.equals("!")) stackD.push(CalculationExpression.factor(stackD.pop()));
    }
}
