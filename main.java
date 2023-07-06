package huffman;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//hard code
		String legend="A 20 E 24 G 3 H 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2";
		
		BinaryHeap bheap = HuffmanTree.legendToHeap(legend);
		
		bheap.printHeap();
		
		HuffmanTree htree = HuffmanTree.createFromHeap(bheap);
		htree.printLegend();

	}

}
