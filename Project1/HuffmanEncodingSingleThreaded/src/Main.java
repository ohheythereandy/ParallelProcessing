import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HiThereAndy on 10/21/2018.
 */
public class Main {

    public static void main(String[] args) {

        FileInput f = new FileInput();
        Map<Character, Integer > inputMap = new HashMap<>();
        long startTime=0;
        long endTime=0;

        try {
            startTime = System.currentTimeMillis();
            inputMap = f.getFileMap();
            endTime = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Time to create huffman tree: " + ((float)(endTime - startTime) / 1000) + " seconds.");
        //System.out.println(inputMap);

        Huffman huff = new Huffman(inputMap);
        Map<Character, String> huffCode = huff.getHuffCode();

        FileOut o = new FileOut(huffCode);
        startTime = System.currentTimeMillis();
        o.encodeFile();
        endTime = System.currentTimeMillis();
        System.out.println("Time to encode file using huffman coding: " + ((float)(endTime - startTime) / 1000) + " seconds.");
        System.out.println("File successfully encoded.");


    }
}
