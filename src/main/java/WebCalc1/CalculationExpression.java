package WebCalc1;

import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by Виталий on 07.05.2015.
 */
public class CalculationExpression {
    public static   double sin(double a) {
        return Math.sin(a);
    }
    public static double cos(double a) {
        return Math.cos(a);
    }
    public static  double tan(double a) {
        return Math.tan(a);
    }
    public static double ctan(double a) {
        return 1/Math.tan(a);
    }
    public static double sqrt(double a) {
        return Math.sqrt(a);
    }
    public static double aSin(double a) {
        return Math.asin(a);
    }
    public static double aCos(double a) {
        return Math.acos(a);
    }
    public static double ln(double a) {
        return Math.log(a);
    }
    public static double lg(double a) {
        return Math.log10(a);
    }
    public static double factor(double a) throws Exception {
        if  (a%1 != 0) throw new Exception();
        if  (a==0) return 0;
        if (a == 1) return 1;
        return a*factor(a - 1);
    }
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
