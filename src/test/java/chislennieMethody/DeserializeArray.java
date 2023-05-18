package chislennieMethody;

import java.util.Arrays;

public class DeserializeArray {
    protected static String deserializeOneDimension(double[] array) {
        return Arrays.toString(array);
    }

    protected static String deserializeTwoDimension(double[][] array) {
        return Arrays.deepToString(array);
    }
}
