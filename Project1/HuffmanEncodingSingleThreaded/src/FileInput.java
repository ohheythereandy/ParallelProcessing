import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HiThereAndy on 10/21/2018.
 */
public class FileInput {

    private String inputPath = "../USConst.txt";
    private String currentLine;

    public FileInput() {

    }

    public HashMap<Character,Integer> getFileMap() throws IOException{

        currentLine = " ";
        HashMap<Character, Integer> retMap = new HashMap<>();

        try {
            FileReader file = new FileReader(inputPath);
            BufferedReader read = new BufferedReader(file);

            //add new line character to map
            retMap.put('\n', 0);

            while((currentLine = read.readLine()) != null) {

                //read line from file
                //currentLine = read.readLine();

               //if(currentLine!=null) {

                   //iterate through characters in line and do stuff to map
                   for(char currentChar: currentLine.toCharArray()){

                        //ignore spaces
                       if((currentChar != ' ')) {
                           //if not  in map, add it
                           if(!retMap.containsKey(currentChar))
                               retMap.put(currentChar, 1);
                           else { //increment map value by one
                               int freq = retMap.get(currentChar);
                               retMap.put(currentChar, freq + 1);
                           }
                       }

                   }

                   //increment new line character
                   retMap.put('\n', retMap.get('\n') + 1);
               //}
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't open file");
        } catch (IOException i) {
            System.out.println("Can't read file");
        }

        return retMap;
    }
}
