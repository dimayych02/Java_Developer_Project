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

    @Test(dataProvider = "methodOfYakoby", dataProviderClass = DataTest.class)
    public void getYakobySolution(double[][] matrix){
        System.out.println(DeserializeArray.deserializeOneDimension(GetSolution.solveYakob(
                MatrixLogic.getVector(matrix),
                MatrixLogic.getMatrix(MatrixLogic.getVector(matrix).length,matrix),0.001)));
    }

    @Test(dataProvider = "methodOfRelaxation", dataProviderClass = DataTest.class)
    public void getRelaxationSolution(double[][] matrix){
        System.out.println(DeserializeArray.deserializeOneDimension(GetSolution.solveRelaxation(
                MatrixLogic.getVector(matrix),
                MatrixLogic.getMatrix(MatrixLogic.getVector(matrix).length,matrix),1.25,0.001)));
    }

    @Test(dataProvider = "dataNewton",dataProviderClass = DataTest.class)
    public void getNewtonSolution(double testData){
        double[] x={1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,1.9};
        double[] y={1.000,0.951,0.918,0.897,0.887,0.886,0.893,0.908,0.931,0.961};
        System.out.println(GetSolution.interpolationNewton(x,y,testData));
    }

    @Test
    public void solutionSmooth(){
        double[] x ={2.0, 2.3, 2.5, 3.0, 3.5, 3.8,4.0};
        double[] y ={5.848,6.127,6.300,6.694,7.047,7.243,7.368};
        int degree=2;
        System.out.println(DeserializeArray.deserializeOneDimension(
                GetSolution.smoothSolution(x,degree,GetSolution.calculateCoefficients(x,y,degree))));

        System.out.println(GetSolution.sumSquareError
                (GetSolution.smoothSolution(x,degree,GetSolution.calculateCoefficients(x,y,degree)),y));

    }
}