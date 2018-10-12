import javafx.concurrent.Worker;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;

/**
 * Created by HiThereAndy on 10/10/2018.
 */
public class GameOfLife {

    private int m, n;
    private Cell[][] board;
    private int[][] boardForCells;

    public GameOfLife(int m, int n) {
        this.m = m;
        this.n = n;
        board = new Cell[this.m][this.n];
        boardForCells = new int[this.m][this.n];
    }

    public void main() throws ExecutionException, InterruptedException {

        Random rand = new Random();
        Scanner kb = new Scanner(System.in);
        String input = " ";

        //iterate through grid to instantiate individual cells with correct positions
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){

                boardForCells[i][j] = rand.nextInt(2);
            }
        }

        CyclicBarrier cb = new CyclicBarrier(m*n);



        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){


                board[i][j] = new Cell(i, j, boardForCells[i][j], boardForCells, cb);
                if(board[i][j].val == 0)
                    System.out.print(".");
                else
                    System.out.print("*");
            }
            System.out.println();
        }

        while(input != "q"){
            nextGeneration();
            //print();
            System.out.println("Enter q to quit or anything else to go another generation");
            input = kb.nextLine();
        }


    }

    private void nextGeneration() throws ExecutionException, InterruptedException {

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){

                board[i][j].computeCell();

            }
        }
    }
}
