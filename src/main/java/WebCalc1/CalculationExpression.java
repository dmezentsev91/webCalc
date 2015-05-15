package WebCalc1;

import java.util.ArrayList;
import java.util.Queue;


public class CalculationExpression {
    public static   double sin(boolean rad, double a) {
        if (rad == true)
            return Math.sin(a);
        else return Math.sin(a*Math.PI/180);
    }
    public static double cos(boolean rad, double a) {
        if (rad == true)
        return Math.cos(a);
        else return Math.cos(a*Math.PI/180);
    }
    public static  double tan(boolean rad, double a) {
        if (rad == true) {

            return Math.tan(a);
        }
        else return  Math.tan(a*Math.PI/180);
    }
    public static double ctan(boolean rad, double a) {

        if (rad == true)
            return 1/Math.tan(a);
        else return  1/Math.tan(a*Math.PI/180);
    }
    public static double sqrt(double a) {
        return Math.sqrt(a);
    }
    public static double aSin(boolean rad, double a) {
        if (rad == true)
        return Math.asin(a);
        else return (Math.asin(a)*180/Math.PI);
    }
    public static double aCos(boolean rad, double a) {
        if (rad == true)
            return Math.acos(a);
        else return (Math.acos(a)*180/Math.PI);
    }
    public static double ln(double a) {
        return Math.log(a);
    }
    public static double lg(double a) {
        return Math.log10(a);
    }
    public static double factor(double a) throws Exception {
        if  (a%1 != 0) throw new Exception(); //decimalFactor(a);
        if  (a==0) return 0;
        if (a == 1) return 1;
        return a*factor(a - 1);
    }

//    private static double decimalFactor(double a) throws Exception {
//        double result;
//        int intPart = (int) a;
//        double decimalPart = a%1;
//        result = Math.log(CalculationExpression.factor(intPart)) + decimalPart * Math.log(intPart + 1);
//        result = Math.pow(Math.E, result);
//        return result;
//    }

    public static double plus(double a,double b) {
        return a + b;
    }
    public static double minus(double a,double b) {
            return a - b;
    }
    public static double multip(double a,double b) {
        return a * b;
    }
    public static double division(double a,double b) {
        return a / b;
    }
    public static double pow(double a,double b) {
        return Math.pow(a, b);
    }

}
