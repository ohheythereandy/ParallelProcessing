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
        String stringToEncode="";

        try {

            FileReader file = new FileReader(inputPath);
            BufferedReader read = new BufferedReader(file);


            //write huffcode to file for decoding
            BufferedWriter initWrite = new BufferedWriter(new FileWriter(outputPath, true));

            for(Map.Entry<Character, String> entry: huffCode.entrySet()) {
                initWrite.write(entry.getKey() + "-" + entry.getValue() + "\n");
            }
            initWrite.close();

            FileOutputStream writer = new FileOutputStream(outputPath, true);


            while((currentLine = read.readLine()) != null) {

                //iterate through characters
                for(char currentChar: currentLine.toCharArray()){

                    //ignore spaces
                    if((currentChar != ' ')) {
                        stringToEncode += huffCode.get(currentChar);

                    }

                }

                //write in new line
                stringToEncode+= huffCode.get('\n');
            }

            BitSet bitSet = new BitSet(stringToEncode.length());
            int bitCounter = 0;

            for(Character c : stringToEncode.toCharArray()) {

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
