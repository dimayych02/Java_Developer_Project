package chislennieMethody;

import Jama.Matrix;
import org.apache.commons.lang3.ArrayUtils;


public class MatrixLogic {

    protected static double[] getMatrix(int n, double[][] twoDimensionArray) {
        double oneDimensionArray[] = new double[n];
        for (int j = 0; j < n; j++) {
            oneDimensionArray[j] = twoDimensionArray[twoDimensionArray.length - 1][j];
        }
        return oneDimensionArray;
    }

    protected static double[][] getVector(double[][] matrix) {
        matrix = ArrayUtils.remove(matrix, matrix.length - 1);
        return matrix;
    }

    protected static double getDeterminant(double[][] matrixData) {
        return new Matrix(matrixData).det(); //определитель матрицы
    }

    protected static double[][] reverseMatrix(double[][] matrixData) {//обратная матрица

        if (getDeterminant(matrixData) != 0) {// если определитель не равен нулю
            return new Matrix(matrixData).inverse().getArray();
        }

        double[][] infinityMatrix = {{Double.POSITIVE_INFINITY}};
        return infinityMatrix;
    }
}

