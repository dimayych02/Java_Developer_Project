package chislennieMethody;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;


public class LabTests {

    @Test
    public void getEpsilon() {
        System.out.println(MethodOfGettingEpsilon.getEpsilon(1));
    }


    @Test(dataProvider = "lowMatrixData", dataProviderClass = DataTest.class)
    public void testLowMatrix(double[][] matrix) {
        System.out.println(DeserializeArray.deserializeOneDimension(GetSolution.lowTriangleMatrix(
                MatrixLogic.getVector(matrix),
                MatrixLogic.getMatrix(MatrixLogic.getVector(matrix).length, matrix))));
    }

    @Test(dataProvider = "highMatrixData", dataProviderClass = DataTest.class)
    public void testHighMatrix(double[][] matrix) {
        System.out.println(DeserializeArray.deserializeOneDimension(GetSolution.highTriangleMatrix(
                MatrixLogic.getVector(matrix),
                MatrixLogic.getMatrix(MatrixLogic.getVector(matrix).length, matrix))));
    }

    @Test(dataProvider = "matrixData", dataProviderClass = DataTest.class)
    public void determinant(double[][] matrix) {
        System.out.println(MatrixLogic.getDeterminant(matrix));
    }

    @Test(dataProvider = "matrixData", dataProviderClass = DataTest.class)
    public void reverseMatrix(double[][] matrix) {
        System.out.println(DeserializeArray.deserializeTwoDimension(MatrixLogic.reverseMatrix(matrix)));
    }

    @Test(dataProvider = "dataGauss", dataProviderClass = DataTest.class)
    public void solveGauss(double[][] matrix) {
        double[] actualSolveSystem = {1.0, 2.0, 3.0};//решение всех систем: 1),2),3),4),5)

        Assert.assertEquals(DeserializeArray.deserializeOneDimension(GetSolution.solveLinearSystem(MatrixLogic.getVector(matrix),
                        MatrixLogic.getMatrix(MatrixLogic.getVector(matrix).length, matrix))),
                DeserializeArray.deserializeOneDimension(actualSolveSystem));
        //Assert-проверка,что все решения совпадают с actualSolveSystem
    }

    @Test(dataProvider = "dataGauss", dataProviderClass = DataTest.class)
    public void squareRootMethodSolve(double[][] matrix) throws MatrixIsNotSymetricException {
        System.out.println(DeserializeArray.deserializeOneDimension(GetSolution.squareRootMethod(
                MatrixLogic.getVector(matrix),
                MatrixLogic.getMatrix(MatrixLogic.getVector(matrix).length, matrix))));
    }

    @Test(dataProvider = "dataGauss", dataProviderClass = DataTest.class)
    public void progonka(double[][] matrix) {
       // System.out.println(DeserializeArray.deserializeOneDimension(GetSolution.solve(
         //       MatrixLogic.getVector(matrix),
           //     MatrixLogic.getMatrix(MatrixLogic.getVector(matrix).length, matrix))));
    }
}