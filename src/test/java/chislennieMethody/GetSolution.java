package chislennieMethody;


import Jama.Matrix;
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

    public static double[] solveYakob(double[][] A, double[] b, double eps) {
        int n = b.length;
        double[] x = new double[n];
        double[] xPrev = new double[n];
        while (true) {
            for (int i = 0; i < n; i++) {
                xPrev[i] = x[i]; //приближенное решение
            }
            for (int i = 0; i < n; i++) {
                double sum = 0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum += A[i][j] * xPrev[j];//Расчетная формула для метода простой итерации
                    }
                }
                x[i] = (b[i] - sum) / A[i][i];
            }
            double norm = 0;//норма матрицы
            for (int i = 0; i < n; i++) {
                norm += Math.pow(x[i] - xPrev[i], 2);
            }
            if (Math.sqrt(norm) < eps) {//критерий метода Якоби
                break;
            }
        }
        return x;
    }

    public static double[] solveRelaxation(double[][] A, double[] b, double w, double eps) {
        int n = b.length;
        double[] x = new double[n];
        double[] xPrev = new double[n];
        while (true) {
            for (int i = 0; i<n; i++) {
                xPrev[i] = x[i];
            }
            for (int i = 0; i< n; i++) {
                double sum = 0;
                for (int j = 0; j< n; j++) {
                    if (j != i) {
                        sum += A[i][j] * x[j];
                    }
                }
                //метод релаксации,w-релаксационный параметр
                x[i] = (1 - w) * xPrev[i] + (w / A[i][i]) * (b[i] - sum);
            }
            double norm = 0;
            for (int i = 0; i < n; i++) {
                norm += Math.pow(x[i] - xPrev[i], 2);
            }
            if (Math.sqrt(norm)<  eps) {
                break;
            }
        }
        return x;
    }


    ///Рекурсия. Вычисляем разделенные разности
    private static double argumentDifference(double x[], int startPos, int finishPos, double[][] h_xFunction) {
        if (startPos == finishPos) {
            return h_xFunction[startPos][1];
        } else {
            double fun1 = argumentDifference(x, startPos, finishPos - 1, h_xFunction);
            double fun2 = argumentDifference(x, startPos + 1, finishPos, h_xFunction);
            return (fun2 - fun1) / (x[finishPos] - x[startPos]);
            //h(x1)-h(x0)/(x1-x0)
        }
    }

    // Метод вычисления  интерполяционного многочлена Ньютона в точке x
    public static double interpolationNewton(double[] x, double[] y, double xValue) {
        int n = x.length;
        double[][] h_x = new double[n][n];
        for (int i = 0; i < n; i++) {
            h_x[i][0] = x[i];//аргумент функции h_x в точке
            h_x[i][1] = y[i];// функция
        }
        for (int i = 2; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                h_x[j][i] = argumentDifference(x, j, j + i - 1, h_x);
            }
        }
        double result = h_x[0][1];//h_o
        double temp = 1; //шаг умножения
        for (int i = 1; i < n; i++) {
            temp *= (xValue - x[i - 1]);
            result += temp * h_x[0][i];// P_n(x)-формула интерполяционного многочлена Лагранжа
        }
        return result;
    }

    public static double[] smoothSolution(double[] x, int degree, double[] coefficients) { //находим F_n(x)
        double[] y = new double[x.length];
        for (int i = 0; i < x.length; i++) {//сглаживание значений x в 2 раза
            double sum = 0;
            for (int j = 0; j <= degree; j++) {
                sum += coefficients[j] * Math.pow(x[i], j);
            }
            y[i] = sum;
        }
        return y;
    }

    public static double[] calculateCoefficients(double[] xValues, double[] yValues, int degree) {
        int n = xValues.length;
        int m = degree + 1;
        double[][] xMatrix = new double[n][m];
        double[][] yMatrix = new double[n][1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                xMatrix[i][j] = Math.pow(xValues[i], j);
            }
            yMatrix[i][0] = yValues[i];
        }
        //вычисление коэффициентов многочлена  через матрицу
        Matrix x = new Matrix(xMatrix);
        Matrix y = new Matrix(yMatrix);
        Matrix transposedX = x.transpose();
        Matrix product = transposedX.times(x);
        Matrix inverse = product.inverse();
        Matrix coefficientsMatrix = inverse.times(transposedX).times(y);
        double[] coefficients = new double[m];
        for (int i = 0; i < m; i++) {
            coefficients[i] = coefficientsMatrix.get(i, 0);
        }
        return coefficients;
    }

    public static double sumSquareError(double[] f_nFunction, double[] y) {
        double sum = 0;
        for (int i = 0; i < f_nFunction.length; i++) {
            sum += Math.pow(f_nFunction[i] - y[i], 2);
        }
        return sum;//Сумма квадратов ошибок
    }
}

