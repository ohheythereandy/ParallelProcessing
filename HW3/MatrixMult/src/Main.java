import java.util.Random;
import java.util.Scanner;

/**
 * Created by Andy on 10/7/18.
 */
public class Main {



    public static void main(String[] args){

        int m, n, p;
        MatrixMult multiply = new MatrixMult();

        Scanner keyboard = new Scanner(System.in);
        System.out.println("We are going to multiply Matrix m x n by Matrix n x p to obtain Matrix m x p.");
        System.out.println("What value of m do you wish to use?");
        m = keyboard.nextInt();
        System.out.println("What value of n do you wish to use?");
        n = keyboard.nextInt();
        System.out.println("What value of p do you wish to use?");
        p = keyboard.nextInt();

        float[][] firstMatrix = new float[m][n];
        float[][] secondMatrix = new float[n][p];
        float[][] resultantMatrix = new float[m][p];

        populateMatrix(firstMatrix);
        populateMatrix(secondMatrix);

        long startTime = System.currentTimeMillis();
        multiply.matmult(firstMatrix, secondMatrix, resultantMatrix, m, n, p);
        long endTime = System.currentTimeMillis();


    }

    private static void populateMatrix(float[][] firstMatrix) {

        Random rand = new Random();

        for(int i = 0; i < firstMatrix.length; i++){
            for(int j =0 ; j < firstMatrix[i].length; j++){
                firstMatrix[i][j] = rand.nextInt(100);
            }
        }
    }
}
