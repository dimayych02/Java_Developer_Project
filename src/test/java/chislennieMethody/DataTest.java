package chislennieMethody;

import org.testng.annotations.DataProvider;


public class DataTest {

    @DataProvider
    public static Object[][] highMatrixData() {
        return new Object[][]{
                new double[][]{{6, -3, 15}, {0, 12, -10}, {0, 0, 2}, {48, 38, 14}},
                new double[][]{{1, 2, 4}, {0, 4, 5}, {0, 0, 1}, {4, 5, 8}},
                new double[][]{{1, 5, 4, 4}, {0, 5, 7, 6}, {0, 0, 3, 6}, {0, 0, 0, 6}, {1, 2, 3, 6}}
        };
    }

    @DataProvider
    public static Object[][] lowMatrixData() {
        return new Object[][]{
                new double[][]{{1, 0, 0}, {2, 1, 0}, {1, 3, 7}, {-1, 3, 14}},
                new double[][]{{1, 0, 0}, {2, 3, 0}, {4, 5, 6}, {1, 2, 3}},
                new double[][]{{-4, 0, 0, 0}, {2, 3, 0, 0}, {12, 5, 6, 0}, {1, 2, -3, -4}, {4, 7, 8, -1}}
        };
    }

    @DataProvider
    public static Object[][] matrixData() {
        return new Object[][]{
                new double[][]{{-1, 0}, {2, 1}},
                new double[][]{{1, -2, 0}, {3, 4, 2}, {-1, 3, 1}},
                new double[][]{{2, 3, 0}, {-4, -7, 0}, {9, 11, 0}}, //здесь определитель равен нулю
        };
    }

    @DataProvider
    public static Object[][] dataGauss() {
        return new Object[][]{
                new double[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}, {1, 2, 3}},
                new double[][]{{-1, 0, 0}, {0, -1, 0}, {0, 0, -1}, {-1, -2, -3}},
                new double[][]{{3, 1, 1}, {1, 3, 1}, {1, 1, 3}, {8, 10, 12}},
                new double[][]{{1.63, 1.27, -0.84}, {1.27, 0.65, 1.27}, {-0.84, 1.27, -1.21}, {1.51, -0.63, 2.15}},
                new double[][]{{1, 2, 3}, {2, 1, 0}, {0, 0, 1}, {14, 4, 3}}
        };
    }

    @DataProvider
    public static Object[][] methodOfYakoby() {
        return new Object[][]{
                new double[][]{{1, 2, 1}, {-1, -2, 2}, {0, 1, 1}, {1, 1, 2}},
                new double[][]{{10, 1, -1}, {1, 10, -1}, {-1, 1, 10}, {11, 10, 10}}
        };
    }

    @DataProvider
    public static Object[][] methodOfRelaxation() {
        return new Object[][]{
                new double[][]{{10, 1, 1}, {2, 10, 1}, {2, 2, 10}, {12, 13, 14}},
                new double[][]{{1, 0.1, 0.1}, {0.2, -1, 0.1}, {0.2, 0.2, -0.1}, {0.6, 0.7, 0.8}}
        };
    }

    @DataProvider
    public static Object[] dataNewton() {
        return new Object[]{0.471, 0.475, 0.483, 0.486};
    }
}