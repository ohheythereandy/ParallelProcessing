import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by HiThereAndy on 10/21/2018.
 */
public class Huffman {

    private Map<Character, Integer> inputMap;
    private Map<Character, String> huffCode;
    private HuffmanNode root;


    public Huffman(Map<Character, Integer> inputMap) {
        this.inputMap = inputMap;
        huffCode = new HashMap<>();
        buildTree();
        populateHuffCode(root, "");
    }

    //Recursive function to determine each character's huffman code equivalent
    //Once traversal to a character(leaf node) is complete then we write string to map
    private void populateHuffCode(HuffmanNode root, String s) {

        //base case
        if(root.left == null && root.right == null ) {
            huffCode.put(root.c, s);
            return;
        }

        //go left, add 0 to string
        populateHuffCode(root.left, s + "0");
        //go right, add 1 to string
        populateHuffCode(root.right, s + "1");
    }


    private void buildTree() {

        int numberOfCharacters = inputMap.size();

        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(numberOfCharacters, new MyComparator());

        //create huffmannode for each character with freq as value and add it to queue
        for(Map.Entry<Character, Integer> entry : inputMap.entrySet()){

            HuffmanNode hn = new HuffmanNode();
            hn.c = entry.getKey();
            hn.data = entry.getValue();

            hn.left = null;
            hn.right = null;

            queue.add(hn);

        }

        //root node
        root = null;

        //extract leaf nodes with smallest frequencies and build new subtree with parent
        //node equal to sum of both leaf nodes value
        while(queue.size() > 1) {

            HuffmanNode x = queue.peek();
            queue.poll();

            HuffmanNode y = queue.peek();
            queue.poll();

            //new parent node
            HuffmanNode p = new HuffmanNode();

            //value equals sum of lead nodes
            p.data = x.data + y.data;
            p.c = '-';

            //node with lower freq count is left leaf, other is right
            p.left = x;
            p.right = y;

            //mark root as new parent
            root = p;

            queue.add(p);
        }

    }

    public Map<Character,String> getHuffCode() {
        return huffCode;
    }


    class HuffmanNode {

        int data;
        char c;

        HuffmanNode left;
        HuffmanNode right;

    }
    class MyComparator implements Comparator<HuffmanNode> {

        public int compare(HuffmanNode x, HuffmanNode y) {
            return x.data - y.data;
        }
    }

}
