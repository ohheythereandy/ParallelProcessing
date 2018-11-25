import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by HiThereAndy on 10/21/2018.
 */
public class FileOut {

    private Map<Character, String> huffCode;
    private String inputPath = "../USConst.txt";
    private String currentLine;
    private String outputPath = "../USConstCompressed.txt";
    private File file;

    public FileOut(Map<Character, String> huffCode) {
        this.huffCode = huffCode;
        this.file = new File(outputPath);
    }

    public void encodeFile() throws Exception {

        currentLine = " ";
        String stringToEncode="";
        List<Future<Vector<Character>>> fileRead = new ArrayList<>();

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
//
//
//            while((currentLine = read.readLine()) != null) {
//
//                //iterate through characters
//                for(char currentChar: currentLine.toCharArray()){
//
//                    //ignore spaces
//                    if((currentChar != ' ')) {
//                        stringToEncode += huffCode.get(currentChar);
//
//                    }
//
//                }
//
//                //write in new line
//                stringToEncode+= huffCode.get('\n');
//            }



            //get string from file
            fileRead = processFile(8, 1000);


            //
            Vector<Character> accumulatedVector = new Vector<>();


            for(Future<Vector<Character>> byteVector : fileRead) {
                accumulatedVector.addAll(byteVector.get());
            }


            stringToEncode = new String(accumulatedVector.toString());
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

    private Vector<Character> merge(Vector<Character> accumulatedVector, Vector<Character> byteVector) {

        Vector<Character> ret = new Vector<>();
        ret.addAll(accumulatedVector);
        ret.addAll(byteVector);
        return ret;
    }

    private List<Future<Vector<Character>>> processFile(int numThreads, int chunkSize) throws Exception{

        int count = (int) ((file.length()+ chunkSize - 1)/chunkSize);
        List<Callable<Vector<Character>>> tasks = new ArrayList<>(count);
        ExecutorService es = Executors.newFixedThreadPool(numThreads);
        SplitFile s = new SplitFile(file);

        for(int i = 0; i < count; i++) {
            tasks.add(s.processHelper(i * chunkSize, Math.min(file.length(), (i + 1) * chunkSize)));
        }

        List<Future<Vector<Character>>> ret = es.invokeAll(tasks);
        es.shutdown();

        return ret;


    }


}

class SplitFile {

    private File file;

    public SplitFile(File file) {
        this.file = file;
    }

    public Callable<Vector<Character>> processHelper(long start, long end) {
        return new Callable<Vector<Character>>() {
            @Override
            public Vector<Character> call() throws Exception {
                return processPart(start,end);
            }
        };
    }

    private Vector<Character> processPart(long start, long end) throws  Exception {

        InputStream in = new FileInputStream(file);
        in.skip(start);
        int counter = 0;
        Vector<Character> ret = new Vector<>();

        while(counter < (end - start)) {
            ret.add((char)in.read());
            counter++;
        }

        in.close();

        return ret;

    }

}
