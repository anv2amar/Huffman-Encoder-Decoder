import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;

public class encoder {
	
public static void main (String[] args) throws InterruptedException, IOException {
		
		File ipFile = null;String str;
		Node huffman = new Node(0,-1);

		// if no input file is provided 
		if (args.length == 0){
			System.out.println("No Input");
			return;
		}
		ipFile = new File(args[0]);
		BufferedReader reader = new BufferedReader(new FileReader(ipFile));
		ArrayList<Integer> data = new ArrayList<>();
		while ((str = reader.readLine())!=null) {
			if (!str.equals("")) {
				data.add(Integer.parseInt(str.toString()));
			}
		}
		int[] frequencyTable = Huffman.makeFrequencyTable(data);
		huffman = Huffman.huffman4wayHeap(frequencyTable);
		
		// declaring a code table as a string array of appropriate size
		String[] codeTable = new String[Collections.max(data)+1];
		// filling all values with -1
		Arrays.fill(codeTable, "-1");
		// output file containing code table
		File ouputFile = new File("code_table.txt");
		BufferedWriter output = new BufferedWriter(new FileWriter(ouputFile));
		fillCodeTable(huffman,"",codeTable,output);
		output.close();
		// initializing  encoded.bin
		File out = new File("encoded.bin");
		FileOutputStream fileStream = new FileOutputStream(out); 
		fileStream.write(getBinary(data,codeTable));
		System.out.print("made encoded.bin.\n");
		fileStream.close();

	}



	private static void fillCodeTable(Node huffman, String bits, String[] codeTable, BufferedWriter output) throws IOException {
		if (huffman.getValue() != -1){
			codeTable[huffman.getValue()] = bits;
			output.write(huffman.getValue()+" "+bits+"\n");
			return;
		}
		else{
			fillCodeTable(huffman.getLeftChild(),bits.concat("0"),codeTable,output);
			fillCodeTable(huffman.getRightChild(),bits.concat("1"),codeTable,output);
		}
	}
	
	
	
	private static byte[] getBinary(ArrayList<Integer> data, String[] codeTable) {
		BitSet set = new BitSet(); int index = 0;
		for (int i=0;i<data.size();i++){
			String str = codeTable[data.get(i)];
			for (int j=0;j<str.length();j++){
				set.set(index);
				if (str.charAt(j) == '0')
					set.clear(index);
				index++;
			}
		}
		return set.toByteArray();
	}
}