all: Node.class PairNode.class BinaryHeap.class PairingHeap.class DaryHeap.class Huffman.class encoder.class decoder.class

Node.class: Huffman.java
	javac -d . -classpath . Huffman.java

PairNode.class: Huffman.java
	javac -d . -classpath . Huffman.java

BinaryHeap.class: Huffman.java
	javac -d . -classpath . Huffman.java

PairingHeap.class: Huffman.java
	javac -d . -classpath . Huffman.java

DaryHeap.class: Huffman.java
	javac -d . -classpath . Huffman.java

Huffman.class: Huffman.java
	javac -d . -classpath . Huffman.java

encoder.class: encoder.java
	javac -d . -classpath . encoder.java

decoder.class: decoder.java
	javac -d . -classpath . decoder.java

clean:
	rm -rf ./*.class
	rm encoded.bin decoded.txt code_table.txt
