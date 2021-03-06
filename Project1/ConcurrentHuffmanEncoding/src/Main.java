import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HiThereAndy on 10/21/2018.
 */
public class Main {

    public static void main(String[] args) {

        //start timer for entire program
        long progStart = System.currentTimeMillis();

        FileInput f = new FileInput();
        Map<Character, Integer > inputMap = new HashMap<>();
        long startTime=0;
        long endTime=0;

        //get frequency count for characters from file
        try {
            inputMap = f.getFileMap();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //create huffman object that will create a tree given the map indicating characters in file and their frequency
        Huffman huff = new Huffman(inputMap);

        //retrieve map containing character as key and its corresponding huffman encoded value
        Map<Character, String> huffCode = huff.getHuffCode();

        FileOut o = new FileOut(huffCode);
        startTime = System.currentTimeMillis();
        o.encodeFile();
        endTime = System.currentTimeMillis();
        System.out.println("Time to encode file using huffman coding: " + ((float)(endTime - startTime) / 1000) + " seconds.");
        System.out.println("Time to run entire program: " + ((float)(endTime - progStart) / 1000) + " seconds.");


    }
}
