package WebCalc1;


import java.util.*;


public class LogicWebProgram {
    private static final HashMap<String, Integer> priorityFunction = new HashMap<String, Integer>();
    static {
        priorityFunction.put("sin(", 0);
        priorityFunction.put("cos(", 0);
        priorityFunction.put("tan(", 0);
        priorityFunction.put("ctan(", 0);
        priorityFunction.put("sqrt(", 0);
        priorityFunction.put("(", 0);
        priorityFunction.put(")", 0);
        priorityFunction.put("+", 1);
        priorityFunction.put("-", 1);
        priorityFunction.put("*", 2);
        priorityFunction.put("/", 2);
        priorityFunction.put("^", 3);
        priorityFunction.put("!", 3);
    }


    ArrayList<String> postExp = new ArrayList<String>();
    Stack<String> stack = new Stack<String>();
    Stack<Double> stackD = new Stack<Double>();

    ArrayList<String> firstListFunct ;


   // public static HashMap<String, Integer> getPriorityFunction() {
//        return priorityFunction;
//    }

    public String mainMethod(String arg) {
        String result;
        try {
            firstListFunct = divFunctAndNumb(liteCorrecting(arg));
        } catch (Exception e) {
            e.printStackTrace();
            return "errr";
        }
        methodTransform(firstListFunct);


        result = Double.toString(CalculateResult());
        return result;
    }

    public String liteCorrecting(String arg) {
        return arg.replaceAll(" ", "").replaceAll("/,", ".").toLowerCase();
    }

    public String nullExpression(String arg) {
        try {
            if (arg.equals(null)) return "";
            return arg;
        } catch (Exception e) {
            return "";
        }
    }

    protected ArrayList<String> divFunctAndNumb(String arg) {
        ArrayList<String> listF = new ArrayList<String>();
        String buff = "";
        char[] chars = arg.toCharArray();
        for (int i = 0; i < chars.length; i++) {
           if( chars[i]>=48 && chars[i] <=57 ) {
              buff= searchNumber(i, chars);
               if (i!= chars.length-1)
               i+=buff.length()-1;

           } else {
               buff = searchFunction(i, arg);
               i+=buff.length()-1;
           }
            listF.add(buff);
        }

        return listF;
    }

    protected String searchFunction(int n, String str) {
        for (Map.Entry<String,Integer> pair : priorityFunction.entrySet()){
            try {
                String maybeFunct = str.substring(n, n + pair.getKey().length());
                if (maybeFunct.equals(pair.getKey())) return maybeFunct;
            } catch ( StringIndexOutOfBoundsException e){
                continue;
            }
        }
        return null;
    }

    protected String searchNumber(int n, char[] chars) {
        String buff  = "";
        for (int i = n; i < chars.length; i++) {
            if((chars[i]>=48 && chars[i] <=57)||chars[i] == '.' ){
                buff +=Character.toString(chars[i]);
                continue;
            }
            else return buff; //doit case with two point and more
        }
        return buff;
    }

    protected void methodTransform(ArrayList<String> expressionList) {

        String partExp;
        for (int i = 0; i < expressionList.size(); i++) {
            partExp = expressionList.get(i);
            if (isNumber(partExp)) postExp.add(partExp);
            else {

                int a = priorityFunction.get(partExp);
                rulsSelect(partExp, a);
            }
        }
        while(!stack.empty()){
            postExp.add(stack.pop());
        }
    }

    public void rulsSelect(String arg, int priorityNewElement) {
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

}

    private boolean isNumber(String s) {
        try {
            double d = Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private double CalculateResult() {
        String str ;
        for (int i = 0; i < postExp.size(); i++) {
            str = postExp.get(i);
            if (isNumber(str)) stackD.push(Double.parseDouble(postExp.get(i)));
            else doFunction(str);
        }
        return stackD.pop();
    }

    private void doFunction(String str) {
        int priorityFunct = priorityFunction.get(str);
        if (priorityFunct == 0 || str.equals("!")) unaryFunct(str);
        else bynaryFunct(str);
    }

    private void bynaryFunct(String str) {
        double upperElem = stackD.pop();
        double lowerElem = stackD.pop();
        if (str.equals("+")) stackD.push(CalculationExpression.plus(lowerElem, upperElem));
        if (str.equals("-")) stackD.push(CalculationExpression.minus(lowerElem, upperElem));
        if (str.equals("*")) stackD.push(CalculationExpression.multip(lowerElem, upperElem));
        if (str.equals("/")) stackD.push(CalculationExpression.division(lowerElem, upperElem));
        if (str.equals("^")) stackD.push(CalculationExpression.pow(lowerElem, upperElem));
    }

    private void unaryFunct(String str) {
        if (str.equals("sin(")) stackD.push(CalculationExpression.sin(stackD.pop()));
        else if (str.equals("cos(")) stackD.push(CalculationExpression.cos(stackD.pop()));
        else if (str.equals("tan(")) stackD.push(CalculationExpression.tan(stackD.pop()));
        else if (str.equals("ctan(")) stackD.push(CalculationExpression.ctan(stackD.pop()));
        else if (str.equals("sqrt(")) stackD.push(CalculationExpression.sqrt(stackD.pop()));
        else if (str.equals("!")) stackD.push(CalculationExpression.factor(stackD.pop()));

    }

}
