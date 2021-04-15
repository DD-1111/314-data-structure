/*  Student information for assignment:
 *
 *  On our honor, Diyuan Dai and Yinglei Fang, this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *  Number of slip days used:2
 *
 *  Student 1 (Student whose turnin account is being used)
 *  UTEID:dd33653
 *  email address:daidiyuan@126.com
 *  Grader name:
 *
 *  Student 2 Yinglei Fang
 *  UTEID:yf3675
 *  email address:
 *
 */

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class SimpleHuffProcessor implements IHuffProcessor {


    private HashMap<Integer, String> compressedResult = new HashMap<>();
    private HashMap<String, Integer> decompressedResult = new HashMap<>();
    private TreeNode root;
    private IHuffViewer myViewer;
    private int originalBitsNum = 0;
    private int compressedBitsNum = 0;
    private int headerFormat;
    int[] rawFrequency;




    /**
     * Preprocess data so that compression is possible ---
     * count characters/create tree/store state so that
     * a subsequent call to compress will work. The InputStream
     * is <em>not</em> a BitInputStream, so wrap it int one as needed.
     * @param in is the stream which could be subsequently compressed
     * @param headerFormat a constant from IHuffProcessor that determines what kind of
     * header to use, standard count format, standard tree format, or
     * possibly some format added in the future.
     * @return number of bits saved by compression or some other measure
     * Note, to determine the number of
     * bits saved, the number of bits written includes
     * ALL bits that will be written including the
     * magic number, the header format number, the header to
     * reproduce the tree, AND the actual data.
     * @throws IOException if an arror occurs while reading from the input file.
     */


    public int preprocessCompress(InputStream in, int headerFormat) throws IOException {
        originalBitsNum = 0;
        //Array to count frequency for each value in ASCII
        //standard count format
        this.headerFormat = headerFormat;
        BitInputStream input = new BitInputStream(in);
        // bitsPerWord = 8
        int bitsPerWord = IHuffConstants.BITS_PER_WORD;
        // read the first char
        int charTemp = input.readBits(bitsPerWord);
        rawFrequency = new int[ALPH_SIZE];
        while (charTemp != -1) {
            rawFrequency[charTemp]++;
            originalBitsNum += bitsPerWord;
            charTemp = input.readBits(BITS_PER_WORD);
        }
        input.close();
        doHuffTree(rawFrequency, true);
        myViewer.update("Original bits: " + originalBitsNum);
        return originalBitsNum;
    }

    private void doHuffTree(int[] tally, boolean compress) {
        ArrayList<TreeNode> nodeList = new ArrayList<>();
        for (int i = 0; i < ALPH_SIZE; i++) {
            if (tally[i] != 0) {
                nodeList.add(new TreeNode(i, tally[i]));
            }
        }
        nodeList.add(new TreeNode(PSEUDO_EOF, 1));
        //Collections sort is stable.
        Collections.sort(nodeList);
        combineNodes(nodeList);
        // here lefts the root
        root = nodeList.get(0);
        nodeList.remove(0);
        if (compress) {
            mapHelperCompress(root, "");
        } else {
            mapHelperDecompress(root, "");
        }
    }

    private void combineNodes(ArrayList<TreeNode> nodeList) {
        while (nodeList.size() > 1) {
            TreeNode leftNode = nodeList.get(0);
            TreeNode rightNode = nodeList.get(1);
            TreeNode hfTree = new TreeNode(leftNode, 0, rightNode);
            //add the little tree at the end
            nodeList.add(hfTree);
            //remove the first two
            nodeList.remove(0);
            nodeList.remove(0);
            Collections.sort(nodeList);
        }
    }

    private void mapHelperCompress(TreeNode root, String encoded) {
        // Base case
        if (root == null) {
            //no-op
            return;
        } else if (root.getLeft() == null && root.getRight() == null) {
            // we get a leaf at bottom
            // add it to the result
            compressedResult.put(root.getValue(), encoded);
        } else {
            // common case
            mapHelperCompress(root.getLeft(), encoded + "0");
            mapHelperCompress(root.getRight(), encoded + "1");
        }
    }

    private void mapHelperDecompress(TreeNode root, String encoded) {
        // Base case
        if (root == null) {
            return;
        } else if (root.getLeft() == null && root.getRight() == null) {
            decompressedResult.put(encoded, root.getValue());
        } else {
            // common case
            mapHelperDecompress(root.getLeft(), encoded + "0");
            mapHelperDecompress(root.getRight(), encoded + "1");
        }
    }

    /**
     * Compresses input to output, where the same InputStream has
     * previously been pre-processed via <code>preprocessCompress</code>
     * storing state used by this call.
     * <br> pre: <code>preprocessCompress</code> must be called before this method
     * @param in is the stream being compressed (NOT a BitInputStream)
     * @param out is bound to a file/stream to which bits are written
     * for the compressed file (not a BitOutputStream)
     * @param force if this is true create the output file even if it is larger than the input file.
     * If this is false do not create the output file if it is larger than the input file.
     * @return the number of bits written.
     * @throws IOException if an error occurs while reading from the input file or
     * writing to the output file.
     */
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
        BitInputStream input = new BitInputStream(in);
        BitOutputStream output = new BitOutputStream(out);
        compressedBitsNum = 0;
        // Write MAGIC_NUMBER to the header to indicate this is a valid hf
        output.writeBits(BITS_PER_INT, MAGIC_NUMBER);
        compressedBitsNum += BITS_PER_INT;
        // Write the standard count format to the header
        if (headerFormat == STORE_COUNTS) {
            storeCountsFormat(output);
        } else {
            //write the output for standardTree Format
            storeTreeFormat(output);
        }
        processOGInput(input, output);
        // Write EOF string to the output stream
        String EOFResult = compressedResult.get(PSEUDO_EOF);
        writeBitsOneByOne(EOFResult, output);
        // only continue if the user has selected the option "Force Compression"
        int bitsDifference = compressedBitsNum - originalBitsNum;
        if (bitsDifference > 0) {
            if (force) {
                myViewer.update("Forcing compression");
            } else {
                myViewer.showError("Compressed file has " + bitsDifference
                        + " more bits than uncompressed file."
                        + " Select force option to compress.");
                return 0;
            }
        }
        // Close input and output streams
        input.close();
        output.close();
        myViewer.update("Total compressed bits: " + compressedBitsNum);
        myViewer.showMessage("Compressed bits: " + compressedBitsNum);
        return compressedBitsNum;
    }

    private void storeCountsFormat(BitOutputStream output) {
        output.writeBits(BITS_PER_INT, STORE_COUNTS);
        compressedBitsNum += BITS_PER_INT;
        // Write the character frequency table to the header
        // standard count format output
        for (int k = 0; k < ALPH_SIZE; k++) {
            output.writeBits(BITS_PER_INT, rawFrequency[k]);
            compressedBitsNum += BITS_PER_INT;
        }
    }

    private void storeTreeFormat(BitOutputStream output) {
        output.writeBits(BITS_PER_INT, STORE_TREE);
        compressedBitsNum += BITS_PER_INT;
        int size = countSize(root);
        output.writeBits(BITS_PER_INT, size);
        compressedBitsNum += BITS_PER_INT;
        inOrderTraversal(root, output);
    }

    private int countSize(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        } else {
            return countSize(treeNode.getLeft()) + countSize(treeNode.getRight()) + 1;
        }
    }

    private void inOrderTraversal(TreeNode treeNode, BitOutputStream output) {
        if (treeNode == null) {
            return;
        }
        if (treeNode.getValue() == 0) {
            output.writeBits(1, 0);
            compressedBitsNum += 1;
        } else {
            output.writeBits(1, 1);
            output.writeBits(BITS_PER_WORD, treeNode.getValue());
            compressedBitsNum += 1 + BITS_PER_INT;
        }
        inOrderTraversal(treeNode.getLeft(), output);
        inOrderTraversal(treeNode.getRight(), output);
    }

    private void processOGInput(BitInputStream input, BitOutputStream output) throws IOException {
        // Read in the first character
        int charTemp = input.readBits(BITS_PER_WORD);
        String currentStringResult;
        // Keep reading from file until you've reached the end
        while (charTemp != -1) {
            // Look up character in the compression hashmap to get encoded value
            currentStringResult = compressedResult.get(charTemp);
            // Write bits one at a time to get the correct encoded value
            writeBitsOneByOne(currentStringResult, output);
            // Read in next word
            charTemp = input.readBits(BITS_PER_WORD);
        }
    }

    private void writeBitsOneByOne(String tgt, BitOutputStream output) {
        for (int i = 0; i < tgt.length(); i++) {
            output.writeBits(1, tgt.charAt(i));
            compressedBitsNum++;
        }
    }

    private void writeBitsOneByOne(int val, BitInputStream input, BitOutputStream output) throws IOException {
        String encodedResult = "";
        // Read to the end of the file
        int curChar = input.readBits(1);
        while (val != PSEUDO_EOF && curChar != -1) {
            encodedResult += curChar;
            // Check to see if we've hit a mapped encoding one bit at a time
            if (decompressedResult.get(encodedResult) != null) {
                val = decompressedResult.get(encodedResult);
                output.write(val);
                //reset the space
                encodedResult = "";
                originalBitsNum++;
            }
            curChar = input.readBits(1);
        }
        if (curChar == -1) {
            throw new IOException("No pseudo eof char");
        }
    }


    /**
     * Uncompress a previously compressed stream in, writing the
     * uncompressed bits/data to out.
     * @param in is the previously compressed data (not a BitInputStream)
     * @param out is the uncompressed file/stream
     * @return the number of bits written to the uncompressed file/stream
     * @throws IOException if an error occurs while reading from the input file or
     * writing to the output file.
     */
    public int uncompress(InputStream in, OutputStream out) throws IOException {
        BitInputStream input = new BitInputStream(in);
        BitOutputStream output = new BitOutputStream(out);
        int[] tally = new int[ALPH_SIZE];
        originalBitsNum = 0;
        // If first input doesn't match HF file header
        int header = input.readBits(BITS_PER_INT);
        if (header != MAGIC_NUMBER) {
            myViewer.update("File did not start with the huff magic number");
        }
        // check what format is it
        header = input.readBits(BITS_PER_INT);
        if (header == STORE_COUNTS) {
            buildHFTree(input, tally);
            //build the tree from standard tree format
            writeBitsOneByOne(0, input, output);
        } else if (header == STORE_TREE) {
            root = new TreeNode(0, 0);
            buildHFTree(input, root);
            // jump the first empty root to format the tree
            root = root.getLeft();
            mapHelperDecompress(root, "");
            writeBitsOneByOne(0, input, output);
        } else {
            myViewer.showMessage("File did not start with the huff magic header");
        }
        input.close();
        output.close();
        myViewer.showMessage("Decompressed.");
        return originalBitsNum;
    }

    //for standard count format
    private void buildHFTree(BitInputStream input, int[] tally) throws IOException {
        // Read the frequency table from the header
        for (int i = 0; i < ALPH_SIZE; i++) {
            int bits = input.readBits(BITS_PER_INT);
            tally[i] = bits;
        }
        // Rebuild the map from the header information
        doHuffTree(tally, false);
    }



    private void buildHFTree(BitInputStream input, TreeNode root) throws IOException {
        int size = input.readBits(BITS_PER_INT);
            Stack<TreeNode> nodeStack = new Stack<>();
            nodeStack.push(root);
            int index = 1;
            while (index <= size) {
                int leafOrInternal = input.readBits(1);
                if (leafOrInternal == 0) {
                    TreeNode nextInternal = new TreeNode(0, 0);
                    nodeStack.push(nextInternal);
                    if (root.getLeft() == null) {
                        root.setLeft(nextInternal);
                        root = nextInternal;
                    } else if (root.getRight() == null) {
                        root.setRight(nextInternal);
                        root = nextInternal;
                    } else {
                        throw new IOException("poped problems");
                    }
                } else {
                    //got 1 for input, leaf case
                    int value = input.readBits(BITS_PER_WORD);
                    if (value == 0) {
                        value = PSEUDO_EOF;
                    }
                    TreeNode nextLeaf = new TreeNode(value, 0);
                    if (root.getLeft() == null) {
                        // root is one empty internal node
                        root.setLeft(nextLeaf);
                    } else {
                        // root has one child
                        root.setRight(nextLeaf);
                        TreeNode popped = nodeStack.pop();
                        if (!nodeStack.empty()) {
                            root = nodeStack.peek();
                        }
                        while (nodeStack.size() >= 1 && root.getRight() == popped) {
                            popped = nodeStack.pop();
                            root = nodeStack.peek();
                        }
                    }
                }
                //--------------------------------------------
                index++;
            }
    }


    public void setViewer(IHuffViewer viewer) {
        myViewer = viewer;
    }

    private void showString(String s) {
        if (myViewer != null) {
            myViewer.update(s);
        }
    }
}
