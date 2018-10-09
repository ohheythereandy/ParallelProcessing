import java.util.Arrays;
import java.util.Vector;

/**
 * Created by Andy on 10/7/18.
 */
public class MatrixMult {


    public MatrixMult() {

    }

    public void matmult(float[][] firstMatrix, float[][] secondMatrix, float[][] resultantMatrix, int m, int n, int p) {

        for(int i = 0; i < m; i++){

            for(int j = 0; j < p; j++){
                for(int k =0; k < n; k++){
                    resultantMatrix[i][j] = resultantMatrix[i][j] + (firstMatrix[i][k] * secondMatrix[k][j]);
                }
            }

        }
    }


}
