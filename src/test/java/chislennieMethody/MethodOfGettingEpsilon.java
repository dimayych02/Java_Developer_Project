package chislennieMethody;



public class MethodOfGettingEpsilon {
    private static double eps1;

    public static Double getEpsilon(double eps) {
        do {
            eps = 0.5 * eps;
            eps1 = 1 + eps;
        }
        while (eps1 > 1);

       return eps;
    }


}