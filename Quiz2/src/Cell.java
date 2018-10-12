import java.util.concurrent.*;


/**
 * Created by HiThereAndy on 10/10/2018.
 */
public class Cell {

    int m, n, val, aliveNeighbors;
    int[][] boardForCells;
    ExecutorService es;
    CyclicBarrier cb;

    public Cell(int m, int n, int i, int[][] boardForCells, CyclicBarrier cb) {
        this.m = m;
        this.n = n;
        this.val = i;
        this.boardForCells = boardForCells;
        es = Executors.newSingleThreadExecutor();
        this.cb = cb;
    }


    public void computeCell() throws InterruptedException, ExecutionException {
        Future result = es.submit(new Worker(m, n, val, boardForCells, cb));
        val = (int) result.get();
    }

    private static class Worker implements Callable {


        int m, n, val, aliveNeighbors;
        int[][] boardForCells;
        CyclicBarrier cb;

        public Worker(int m, int n, int i, int[][] boardForCells, CyclicBarrier cb) {
            this.m = m;
            this.n = n;
            this.val = i;
            this.boardForCells = boardForCells;
            this.cb = cb;

        }

        @Override
        public Integer call() throws Exception {

            try {
                //calculate how many neighbors are alive
                aliveNeighbors = 0;

                for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++)
                        aliveNeighbors += boardForCells[m + i][n + j];

                    //subtract cell from neighbors
                    aliveNeighbors -= boardForCells[m][n];
                    cb.await();

                    // Cell is lonely and dies
                    if ((boardForCells[m][n] == 1) && (aliveNeighbors < 2))
                        boardForCells[m][n] = 0;

                        // Cell dies due to over population
                    else if ((boardForCells[m][n] == 1) && (aliveNeighbors > 3))
                        boardForCells[m][n] = 0;

                        // A new cell is born
                    else if ((boardForCells[m][n] == 0) && (aliveNeighbors == 3))
                        boardForCells[m][n] = 1;

                    // Remains the same

                } catch(InterruptedException e){
                    System.out.println(e);
                } catch(BrokenBarrierException e){
                    System.out.println(e);
                }

                return boardForCells[m][n];
            }
        }



    }

