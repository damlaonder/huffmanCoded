package huffman;

public class HuffmanNode implements Comparable{
	public String letter;
	public Double frequency;
	public HuffmanNode left, right;
	
	//constructor
	public HuffmanNode(String letter, Double frequency) {
		this.letter = letter;
		this.frequency = frequency;
		this.left = null;
		this.right = null;
		
	}
	//constructor
	public HuffmanNode(HuffmanNode left, HuffmanNode right) {
		this.letter = left.letter + right.letter;
		this.frequency = left.frequency + right.frequency;
		
		
	}
	
	public int compareTo(Object o) {
		//cast object o into huffman node (call it huff) 
		HuffmanNode huff = (HuffmanNode) o;
		return this.frequency.compareTo(huff.frequency);
		
	}
	public String toString() {
		return "<" + this.letter + ", " + this.frequency + ">";
		
	}

}
