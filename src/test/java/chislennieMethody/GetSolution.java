package chislennieMethody;


import org.testng.annotations.Test;

import java.util.Arrays;

public class GetSolution {

    public static double[] highTriangleMatrix(double[][] matrix, double[] b) {
        int n = b.length;
        double[] x = new double[b.length];
        x[n - 1] = b[n - 1] / matrix[n - 1][n - 1]; //находим значение x_n
        for (int i = n - 2; i >= 0; i--) {
            double sum = b[i];
            for (int j = i + 1; j < n; j++) {
                sum -= matrix[i][j] * x[j];
            }
            x[i] = sum / matrix[i][i];
        }
        return x;
    }

    public static double[] lowTriangleMatrix(double[][] matrix, double[] b) {
        int n = b.length;
        double[] alpha = new double[n];
        double[] beta = new double[n];
        double[] x = new double[n];
        beta[0] = b[0] / matrix[0][0];
        x[0] = beta[0];
        for (int i = 1; i < n; i++) {
            double m = matrix[i][i] + matrix[i][i - 1] * alpha[i - 1];
            alpha[i] = -matrix[i][i] / m;
            beta[i] = (b[i] - matrix[i][i - 1] * beta[i - 1]) / m;
        }
        for (int i = 1; i < n; i++) {
            x[i] = alpha[i] * x[i] + beta[i];
        }
        return x;
    }

    public static double[] solveLinearSystem(double[][] matrix, double[] vector) { //метод  исключения Гаусса
        double[] solution = new double[matrix.length];
        for (int k = 0; k < matrix.length; k++) {
            int maxRow = k;
            for (int i = k + 1; i < matrix.length; i++) {
                if (Math.abs(matrix[i][k]) > Math.abs(matrix[maxRow][k])) {
                    maxRow = i;
                }
            }
            double[] tmp = matrix[k];
            matrix[k] = matrix[maxRow];
            matrix[maxRow] = tmp;
            double temp = vector[k];
            vector[k] = vector[maxRow];
            vector[maxRow] = temp;
            for (int i = k + 1; i < matrix.length; i++) {
                double factor = matrix[i][k] / matrix[k][k];
                vector[i] -= factor * vector[k];
                for (int j = k; j < matrix.length; j++) {
                    matrix[i][j] -= factor * matrix[k][j];
                }
            }
        }
        for (int i = matrix.length - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < matrix.length; j++) {
                sum += matrix[i][j] * solution[j];
            }
            solution[i] = (vector[i] - sum) / matrix[i][i];
        }
        return solution;
    }

    public static double[] squareRootMethod(double[][] A, double[] b) throws MatrixIsNotSymetricException {
        int n = A.length;
        double[][] g = new double[n][n];
        double[] y = new double[n];
        // Проверка симметричности  матрицы A
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (A[i][j] != A[j][i]) {
                    throw new MatrixIsNotSymetricException("Матрица не является симметричной!");
                }
            }
        }
        // Матрица A в виде A = G * G^T
        //Приводим матрицу к верхней треугольной матрице
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int k = 0; k < i; k++) {
                sum += g[i][k] * g[i][k];
            }
            g[i][i] = Math.sqrt(A[i][i] - sum);
            for (int j = i + 1; j < n; j++) {
                sum = 0;
                for (int k = 0; k < i; k++) {
                    sum += g[j][k] * g[i][k];
                }
                g[j][i] = (A[j][i] - sum) / g[i][i];
            }
        }
        // Решение системы Ly = b методом прямого хода
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++) {
                sum += g[i][j] * y[j];
            }
            y[i] = (b[i] - sum) / g[i][i];
        }
        return y;
    }

    public static double[] solve(double[] a, double[] b, double[] c, double[] d) {
        int n = d.length;
        double[] alpha = new double[n];
        double[] beta = new double[n];
        alpha[1] = -b[0] / c[0];
        beta[1] = d[0] / c[0];
        // Вычисление коэффициентов прогонки
        for (int i = 1; i < n - 1; i++) {
            double m = c[i] + a[i] * alpha[i];
            alpha[i + 1] = -b[i] / m;
            beta[i + 1] = (d[i] - a[i] * beta[i]) / m;
        }
        // Решение системы уравнений методом прямого хода
        double[] x = new double[n];
        x[n - 1] = (d[n - 1] - a[n - 1] * beta[n - 1]) / (c[n - 1] + a[n - 1] * alpha[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            x[i] = alpha[i + 1] * x[i + 1] + beta[i + 1];
        }
        return x;
    }

    public static double[] solve(double[][] A, double[] b, double eps) {
        int n = b.length;
        double[] x = new double[n];
        double[] xPrev = new double[n];
        Arrays.fill(x, 0);
        while (true) {
            for (int i = 0; i < n; i++) {
                xPrev[i] = x[i];
            }
            for (int i = 0; i < n; i++) {
                double sum = 0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum += A[i][j] * xPrev[j];
                    }
                }
                x[i] = (b[i] - sum) / A[i][i];
            }
            double norm = 0;
            for (int i = 0; i < n; i++) {
                norm += Math.pow(x[i] - xPrev[i], 2);
            }
            if (Math.sqrt(norm) < eps) {
                break;
            }
        }
        return x;
    }

    public static void main(String[] args) {
        double[][] A = {{1, 2, 1}, {-1, -2, 2}, {0, 1, 1}};
        double[] b = {1, 1, 2};
        double eps = 0.0000000000001;
        double[] x = solve(A, b, eps);
        System.out.println(Arrays.toString(x));
    }

}

