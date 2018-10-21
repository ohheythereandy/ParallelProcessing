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
        try {
            inputMap = f.getFileMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(inputMap);


    }
}
