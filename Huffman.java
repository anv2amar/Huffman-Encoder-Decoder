import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


// Node class

class Node{
	
	//members of the class
	private int frequency = 0;
	private int value = -1;
	private Node parent;
	private Node leftChild=null;
	private Node rightChild=null;
	
	// constructor setting the values for the frequency and value
	
	public Node(int frequency, int value){
		this.frequency = frequency;
		this.value = value;
	}
	
	
	/* getters and setters for all the members */
	
	/* Frequency */
	public void setFrequency(int frequency){
		this.frequency = frequency;
	}
	
	public int getFrequency(){
		return this.frequency;
	}
	
	// Value
	public void setValue(int val){
		this.value = val;
	}
	
	public int getValue(){
		return this.value;
	}
	
	//Parent
	public void setParent(Node parent){
		this.parent = parent;
	}
	
	public Node getParent(){
		return parent;
	}
	
	//Left Child
	public void setLeftChild(Node left){
		this.leftChild = left;
	}
	
	public Node getLeftChild(){
		return this.leftChild;
	}
	
	
	//Right Child
	public void setRightChild(Node right){
		this.rightChild = right;
	}
	
	public Node getRightChild(){
		return this.rightChild;
	}
	
}

/* ******************************************************************* */

class BinaryHeap {


	private Node[] inputs;
	public int heapSize;
	
	// constructor : creates an array of nodes with the given size, and initiates heapSize=0
	public BinaryHeap(int numberOfNodes){
		inputs = new Node[numberOfNodes];
		heapSize = 0;
	}
	
	
	// get index of right child
	private int getIndexRC(int index){
		return (2*index)+2;
	}
	
	// get index of left child
	private int getIndexLC(int index){
		return (2*index)+1;
	}
	
	// get index of parent
	private int getIndexParent(int index){
		return (index-1)/2;
	}
	
	
	// inserting node into a heap
		public void insert(Node node){
			
			// if heap size has reached maximum input capacity, it can't add nodes
			if (heapSize==inputs.length)
				System.out.println("Size Overflow");
			else{
				heapSize++;
				inputs[heapSize-1] = node;
				shiftUpward(heapSize-1);
			}
		}
		
		// deleting the minimum node
		public Node deleteMinimum(){	
			
			// do nothing if the heap is empty
			if (isEmpty())
				return null;
			
			else{
				/* assigning the root value as the minimum node and then replacing 
				the root with the last element of the heap and then heapifying it. */
				Node nodeMinimum = new Node(0,-1);
				nodeMinimum= inputs[0];
				inputs[0] = inputs[heapSize-1];
				heapSize--;
				
				if (heapSize>0)
					shiftDownward(0);
				
				return nodeMinimum;
			}
		}
	
	// to check if heap is empty
	public boolean isEmpty() {

		//heap is empty when it's size is zero
		if(heapSize == 0)
			return true;
		else return false;
	}
	
	
	// to get the root of the heap
	public Node findRoot(){
		
		// returns null if heap is empty, else the value at 0th index
		if (isEmpty())
			return null;
		else
			return inputs[0];
	}
	
	
	//returns the value at a particular index
	public Node getValue(int index){
		
		// check if the heap is empty
		if (isEmpty())
			return null;
		else
			return inputs[index];
	}
	
	
	private void shiftUpward(int index){
		
		int parent;
		Node n = new Node(0,-1);
		
		if (index!=0){
			
			// getting the parent of the index node
			parent = getIndexParent(index);
			
			// heapify
			if (inputs[parent].getFrequency() >= inputs[index].getFrequency()){
				
				n = inputs[parent];
				inputs[parent] = inputs[index];
				inputs[index] = n;
				shiftUpward(parent);
			}
		}
	}
	
	private void shiftDownward(int index){
		int min;
		int leftChild,rightChild;
		
		Node n = new Node(0,-1);
		
		// finding the index of left child of the index node
		leftChild = getIndexLC(index);
		
		// finding the index of right child of the index node
		rightChild = getIndexRC(index);
		
		
		// finding the minimum of the left and right children
		if (rightChild>=heapSize){
			if (leftChild>=heapSize)
				return;
			else
				min = leftChild;
		}
		else {
			if(inputs[leftChild].getFrequency() <= inputs[rightChild].getFrequency())
				min = leftChild;
			else
				min= rightChild;
		}
		
		
		if(inputs[index].getFrequency() > inputs[min].getFrequency()){
			n= inputs[min];
			inputs[min] = inputs[index];
			inputs[index] = n;
			shiftDownward(min);
		}
		
	}
		


}


/* ************************************************************************* */
class PairNode {

	// members of the class
	private Node node;
	private PairNode leftChild;
	private PairNode nextSibling;
	private PairNode previous;
	
	
	//constructor to set the initial values of the members
	public PairNode (Node node) {
		this.node = node;
		this.leftChild = null;
		this.nextSibling = null;
		this.previous = null;
	}
	
	//getters and setters for all members of the class
	public int getFrequency() {
		return this.node.getFrequency();
	}
	
	public void setFrequency(int frequency){
		this.node.setFrequency(frequency);
	}
	
	public int getValue() {
		return this.node.getValue();
	}
	
	public void setValue(int msg){
		this.node.setValue(msg);
	}
	
	public PairNode getLeftChild(){
		return this.leftChild;
	}
	
	public void setLeftChild(PairNode node){
		this.leftChild = node;
	}
	
	public Node getNode(){
		return this.node;
	}
	
	public void setNode(Node node){
		this.node = node;
	}
	
	public PairNode getNextSibling(){
		return this.nextSibling;
	}
	
	
	
	public void setNextSibling(PairNode node){
		this.nextSibling = node;
	}
	
	public PairNode getPrevious(){
		return this.previous;
	}
	
	public void setPrevious(PairNode node){
		this.previous = node;
	}
}


/* ***************************************************************** */

class PairingHeap {

	private PairNode root;
	
	// constructor
	public PairingHeap(){
		root = null;
	}
	
	// checking if the heap is empty
	public boolean isEmpty(){
		
		if(root==null)
			return true;
		
		else return false;
	}
	
	// returns the value of root if heap is not null
	public PairNode rootValue(){
		
		if (!isEmpty())
			return root;
		else
			return null;
	}
	
	
	// inserting a node
	public void insert(Node n){
		PairNode node = new PairNode(n);
		
		// when the new node is the only node in the heap
		if (root==null)
			root = node;
		else 
			root = merge(root,node);
	}
	
	// deleting the minimum node of the heap
	public Node deleteMinimum(){
		
		// cannot delete if the heap is empty
		if (isEmpty())
			return null;
		
		else{
			
			Node node = new Node(0,-1);
			node=root.getNode();
			
			if (root.getLeftChild()==null) {
				root = null;
				return node;
			}
			
			root = root.getLeftChild();
			PairNode nx = new PairNode(new Node(0,-1));
			nx = root.getNextSibling();
			root.setNextSibling(null);
			root.setPrevious(null);
			while(nx!=null){
				PairNode merger = nx;
				nx = nx.getNextSibling();
				merger.setNextSibling(null);
				merger.setPrevious(null);
				root = merge(root,merger);
			}
			
			return node;
		}
	}
	
	// merging 2 heaps together based on the values of root nodes.
	private PairNode merge(PairNode n1, PairNode n2){
		
		if (n1.getFrequency()>=n2.getFrequency()){
			if (n2.getLeftChild()!=null)
				n1.setNextSibling(n2.getLeftChild());
			n2.setLeftChild(n1);
			n1.setPrevious(n2);
			return n2;
		}
		
		else {
			if (n1.getLeftChild()!=null)
				n2.setNextSibling(n1.getLeftChild());
			if (n2.getNextSibling()!=null)
				n2.getNextSibling().setPrevious(n2);
			n1.setLeftChild(n2);
			n2.setPrevious(n1);
			return n1;
		}
	}
}



/* *********************************************************************** */
class DaryHeap {

	private Node[] inputs;
	private int des;
	public int heapSize;
	
	/* Constructor */
	public DaryHeap(int numberOfNodes, int des){
		this.des = des;
		this.heapSize = 3;
		this.inputs = new Node[numberOfNodes+3];
	}
	
	// to check if the heap is empty i.e. the initial size that is 3.
	public boolean isEmpty() {
		if(heapSize == 3)
			return true;
		else return false;
	}
	
	
	// finding the value at the root
	public Node rootValue(){
		
		// check if the heap is empty
		if (isEmpty())
			return null;
		else
			//returning the root
			return inputs[3];
	}
	
  //getting the index of the parent
	private int getIndexParent(int index) {
		return ((index-1-3)/des)+3;
	}
	
	
	// getting the index of the kth child
	private int getindexkC(int index, int k) {
		return (des*(index-3))+k+3;
	}
	 
	
	// getting the index of the minimum child
	private int getIndexOfMinChild(int index) {
		int min = getindexkC(index, 1);
		int k = 2;
		int indexOfkChild = getindexkC(index, k);
		while (k<=des && indexOfkChild<heapSize) {
			if (inputs[min].getFrequency()>inputs[indexOfkChild].getFrequency()){
				min = indexOfkChild;
			}
			k++;
			indexOfkChild = getindexkC(index, k);
		}
		return min;
	}
	
	
	// inserting a node in the heap
	public void insert(Node node){
		
		// check if the heap is full
		if (heapSize==inputs.length)
			System.out.println("Size Overflow");
		
		else{
			heapSize++;
			inputs[heapSize-1] = node;
			shiftUpwards(heapSize-1);
		}
	}
	
	
	// deleting the minimum node
	public Node deleteMinimum(){	
		
		// can't delete anything if the heap is empty
		if (isEmpty())
			return null;
		
		else{
			
			Node nodeMinimum = new Node(0,-1);
			nodeMinimum=inputs[3];
			inputs[3] = inputs[heapSize-1];
			heapSize--;
			
			if (heapSize>3)
				shiftDownwards(3);
			
			return nodeMinimum;
		}
	}
	
	// shift upwards to manage heap properties
	private void shiftUpwards(int index) {
		int parent;
		Node temp = new Node(0,-1);
		if (index!=3){
			parent = getIndexParent(index);
			if (inputs[parent].getFrequency() >= inputs[index].getFrequency()){
				temp = inputs[parent];
				inputs[parent] = inputs[index];
				inputs[index] = temp;
				shiftUpwards(parent);
			}
		}
	}

	// shift downwards to manage heap properties
	private void shiftDownwards(int index){
		Node n = new Node(0,-1);
		int min;
		if (getindexkC(index, 1)<heapSize){
			min = getIndexOfMinChild(index);
			if (inputs[index].getFrequency() >= inputs[min].getFrequency()){
				n = inputs[min];
				inputs[min] = inputs[index];
				inputs[index] = n;
				shiftDownwards(min);
			}
		}
	}

}

/* ***************************************************************************** */


public class Huffman {

	public static int count;
	
	// generating huffman tree
	public static void generateHuffman(Node node, String bits){
	
		if (node.getValue() != -1)
			return;
		else{
			generateHuffman(node.getLeftChild(),bits.concat("0"));
			generateHuffman(node.getRightChild(),bits.concat("1"));
		}
	}
	
	// making frequency table
	public static int[] makeFrequencyTable(ArrayList<Integer> inputs){
		
		// making an array of desired length known as frequency table
		int[] frequencyTable = new int[Collections.max(inputs)+1];
		
		// filling everything with zeros, Initialization
		Arrays.fill(frequencyTable, 0);	
	
		for (int i=0;i<inputs.size();i++){
			
			frequencyTable[inputs.get(i)]++;
			
			if (frequencyTable[inputs.get(i)]==1) 
				count++;
		}
		return frequencyTable;
	}
	
	// huffman tree using binary heap
		public static Node huffmanBinaryHeap(int[] frequencyTable) {

			
			BinaryHeap heap = new BinaryHeap(count);
			
			for (int i=0;i<frequencyTable.length;i++){
				if (frequencyTable[i]!=0){
					Node node = new Node(frequencyTable[i], i);
					heap.insert(node);
				}
			}

			//combining single nodes to form huffman tree
			while (heap.heapSize != 1){
				Node node = new Node(0,-1);
				node.setLeftChild(heap.deleteMinimum());
				node.getLeftChild().setParent(node);
				node.setRightChild(heap.deleteMinimum());
				node.getRightChild().setParent(node);
				node.setFrequency(node.getLeftChild().getFrequency()+node.getRightChild().getFrequency());
				
				heap.insert(node);
			}
			String bits = "";
			generateHuffman(heap.findRoot(),bits);
			return heap.findRoot();
		}

	

	//huffman using pairing heap
	public static Node huffmanPairingHeap(int[] frequencyTable) {

		PairingHeap heap = new PairingHeap();
		
		for (int i=0;i<frequencyTable.length;i++){
			if (frequencyTable[i]!=0){
				Node node = new Node(frequencyTable[i], i);
				heap.insert(node);
			}
		}

		//combining single nodes to form huffman tree
		while(heap.rootValue().getLeftChild()!=null){
			Node node = new Node(0,-1);
			node.setLeftChild(heap.deleteMinimum());
			node.getLeftChild().setParent(node);
			node.setRightChild(heap.deleteMinimum());
			node.getRightChild().setParent(node);
			node.setFrequency(node.getLeftChild().getFrequency()+node.getRightChild().getFrequency());
			heap.insert(node);
		}
		String bits = "";
		generateHuffman(heap.rootValue().getNode(),bits);
		return heap.rootValue().getNode();
	}

	
	
	
	// huffman tree using d-ary heap
		public static Node huffman4wayHeap(int[] frequencyTable) {

			// making a 4-way heap
			DaryHeap  heap = new DaryHeap(count,4);
			
			for (int i=0;i<frequencyTable.length;i++){
				if (frequencyTable[i]!=0){
					Node node = new Node(frequencyTable[i], i);
					heap.insert(node);	}}
			while (heap.heapSize != 4){
				Node node = new Node(0,-1);
				node.setLeftChild(heap.deleteMinimum());
				node.getLeftChild().setParent(node);
				node.setRightChild(heap.deleteMinimum());
				node.getRightChild().setParent(node);
				node.setFrequency(node.getLeftChild().getFrequency()+node.getRightChild().getFrequency());
				heap.insert(node);
			}
			String bits = "";
			generateHuffman(heap.rootValue(),bits);
			return heap.rootValue();
		}
	
	
	// Main of the program
	public static void main (String[] args) throws InterruptedException, IOException {

		// Read input; make frequency table 
		
		// input will store the input file
		File input = null;
		
		// check if there is no file as input
		if (args.length == 0){
			System.out.println("No input"); return;
		}
		String str;
		int trials = 10;
		input = new File(args[0]);
		BufferedReader reader = new BufferedReader(new FileReader(input));
		ArrayList<Integer> data = new ArrayList<>();
		while (( str = reader.readLine())!=null)
			if (!str.equals(""))
				data.add(Integer.parseInt(str.toString()));
		int[] frequencyTable = makeFrequencyTable(data);
		long startTime,endTime;
		
		Node huffman = new Node(0,-1);
		
		/* ******************4 Way Heap***************** */
		{
			
			startTime = System.currentTimeMillis();
			for (int i=0;i<trials;i++) {
				huffman = huffman4wayHeap(frequencyTable);
			}
			endTime   = System.currentTimeMillis();
			double totalTime = (endTime - startTime)/trials;
			System.out.println(" 4-way cache optimized heap Time taken: "+totalTime);
		} 
		/* ***************** Binary Heap ***************** */
		{
			
			startTime = System.currentTimeMillis();

			for (int i=0;i<trials;i++) {
				huffman = huffmanBinaryHeap(frequencyTable);
			}
			endTime   = System.currentTimeMillis();
			double totalTime = (endTime - startTime)/trials;
			System.out.println("Binary Heap Time taken: "+totalTime);
		}


		/* ******************Pairing Heap******************* */
		{
			
			startTime = System.currentTimeMillis();
			for (int i=0;i<trials;i++) {
				huffman = huffmanPairingHeap(frequencyTable);
			}
			endTime   = System.currentTimeMillis();
			double totalTime = (endTime - startTime)/trials;
			System.out.println(" Pairing heap Time taken: "+totalTime);
		}


		
	}
}
