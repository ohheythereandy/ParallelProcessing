import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Andy on 10/7/18.
 */
public class Main {

    private static int numThreads = 1;
    private static final ExecutorService threadpool = Executors.newFixedThreadPool(numThreads);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int m, n, p;


        Scanner keyboard = new Scanner(System.in);
        System.out.println("We are going to multiply Matrix m x n by Matrix n x p to obtain Matrix m x p.");
        System.out.println("What value of m do you wish to use?");
        m = keyboard.nextInt();
        System.out.println("What value of n do you wish to use?");
        n = keyboard.nextInt();
        System.out.println("What value of p do you wish to use?");
        p = keyboard.nextInt();

        MatrixMult multiply = new MatrixMult();

        MatrixSet task = new MatrixSet(m,p);
        long startTime = System.currentTimeMillis();
        Future future = threadpool.submit(task);

        while(!future.isDone()) {
            //System.out.println("Waiting to initialize 2d matrix");
        }


        float[][] firstMatrix = new float[m][n];
        float[][] secondMatrix = new float[n][p];
        float[][] resultantMatrix = (float[][]) future.get();

        populateMatrix(firstMatrix);
        populateMatrix(secondMatrix);

        multiply.matmult(firstMatrix, secondMatrix, resultantMatrix, m, n, p);
        long endTime = System.currentTimeMillis();

        long totalTime = endTime - startTime;

        System.out.println("Time with " + numThreads + " thread:" + ((float)totalTime / 1000) + " sec");

        threadpool.shutdown();


    }

    private static void populateMatrix(float[][] firstMatrix) {

        Random rand = new Random();

        for(int i = 0; i < firstMatrix.length; i++){
            for(int j =0 ; j < firstMatrix[i].length; j++){
                firstMatrix[i][j] = rand.nextInt(100);
            }
        }
    }

    private static class MatrixSet implements Callable {

        float[][] initialMatrix;
        int m, p;

        public MatrixSet(int m, int p) {
            initialMatrix = new float[m][p];
            this.m = m;
            this.p = p;
        }

        public float[][] call()  {

                IntStream.range(0, m-1).parallel()
                        .forEach(i -> IntStream.range(0, p-1).forEach(j -> initialMatrix[i][j]=0));


            return initialMatrix;
        }
    }
}
