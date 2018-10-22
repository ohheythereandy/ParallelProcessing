import java.io.*;
import java.util.BitSet;
import java.util.Map;

/**
 * Created by HiThereAndy on 10/21/2018.
 */
public class FileOut {

    private Map<Character, String> huffCode;
    private String inputPath = "../USConst.txt";
    private String currentLine;
    private String outputPath = "../USConstCompressed.txt";

    public FileOut(Map<Character, String> huffCode) {
        this.huffCode = huffCode;
    }

    public void encodeFile()  {

        currentLine = " ";
        String encodedString="";

        try {

            FileReader file = new FileReader(inputPath);
            BufferedReader read = new BufferedReader(file);

            //BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath, true));
            FileOutputStream writer = new FileOutputStream(outputPath, true);

            while((currentLine = read.readLine()) != null) {

                //iterate through characters
                for(char currentChar: currentLine.toCharArray()){

                    //ignore spaces
                    if((currentChar != ' ')) {
                        encodedString += huffCode.get(currentChar);
                        //writer.write(replacementStr);

                    }

                }

                //write in new line
                //writer.write(huffCode.get('\n'));
                encodedString+= huffCode.get('\n');
            }

            BitSet bitSet = new BitSet(encodedString.length());
            int bitCounter = 0;

            for(Character c : encodedString.toCharArray()) {

                if(c.equals('1'))
                    bitSet.set(bitCounter);
                bitCounter++;
            }

            writer.write(bitSet.toByteArray());
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Could not read from file");
        }
    }
}
