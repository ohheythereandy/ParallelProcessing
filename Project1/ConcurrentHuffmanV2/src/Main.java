import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HiThereAndy on 10/21/2018.
 */
public class Main {

    public static void main(String[] args) throws Exception{

        long progStart = System.currentTimeMillis();

        FileInput f = new FileInput();
        Map<Character, Integer > inputMap = new HashMap<>();
        long startTime;
        long endTime;

        //get frequency count for characters from file
        try {
            inputMap = f.getFileMap();
        } catch (IOException e) {
            e.printStackTrace();
        }



        Huffman huff = new Huffman(inputMap);

        //retrieve map containing character as key and its corresponding huffman encoded value
        Map<Character, String> huffCode = huff.getHuffCode();

        //use map to write to new file
        FileOut o = new FileOut(huffCode);
        startTime = System.currentTimeMillis();
        o.encodeFile();
        endTime = System.currentTimeMillis();
        System.out.println("Time to encode file using huffman coding: " + ((float)(endTime - startTime) / 1000) + " seconds.");
        System.out.println("Time to run entire program: " + ((float)(endTime - progStart) / 1000) + " seconds.");


    }
}
