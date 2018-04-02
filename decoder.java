import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class decoder {

	public static void addNode(Node huffman, int message, String bits){
		if (bits.length()==1){
			Node leaf = new Node(0,message);
			if (bits.charAt(0)=='0')
				huffman.setLeftChild(leaf);
			else
				huffman.setRightChild(leaf);
			return;
		}
		else {
			if (bits.charAt(0)=='0') {
				if (huffman.getLeftChild()==null)
					huffman.setLeftChild(new Node(0,-1));
				addNode(huffman.getLeftChild(),message,bits.substring(1));
			}
			else {
				if (huffman.getRightChild()==null)
					huffman.setRightChild(new Node(0,-1));
				addNode(huffman.getRightChild(), message, bits.substring(1));
			}
		}

	}

	
	
	public static Node huffmanConstruction(String file) throws IOException {
		String line; Node huffman = new Node(0,-1);
		File input = new File(file);
		BufferedReader br = new BufferedReader(new FileReader(input));
		while ((line = br.readLine())!=null){
			int msg = Integer.parseInt(line.split(" ")[0]);
			String code = line.split(" ")[1];
			addNode(huffman,msg,code);
		}
		br.close();
		return huffman;
	}

	
	
	public static void main(String[] args) throws IOException {

		if (args.length < 2){
			System.out.println("Inappropriate inputs");
			return;
		}
		
		Node huffman = huffmanConstruction(args[1]);
		byte[] encodedBin = Files.readAllBytes(Paths.get(args[0]));
		System.out.print(" decoded.txt made  ");;
		File decoder = new File("decoded.txt");
		BufferedWriter output = new BufferedWriter(new FileWriter(decoder));
		Node current = huffman;
		for (int i=0;i<encodedBin.length;i++){
			int j = 0;
			if (encodedBin[i]<0) encodedBin[i] += 256;
			while (j<8){
				if (current.getValue()!=-1){
					output.write(current.getValue()+"\n");
					current = huffman;
				}
				else{
					int next_bit = (encodedBin[i]>>j) & 0x1;
					if (next_bit==1)
						current = current.getRightChild();
					else
						current = current.getLeftChild();
					j++;
				}
			}
			if (i==encodedBin.length-1 && j==8)
				output.write(current.getValue()+"\n");
		}
		output.close();
		
	}
}
