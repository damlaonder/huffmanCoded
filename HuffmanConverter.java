package huffman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanConverter {
		// The # of chars in the ACII table dictates the size of the count[] and code[] arrays     
		public static final int NUMBER_OF_CHARACTERS = 256;
		// the contents of our message...     
		private static String contents;
		// the tree created from the message     
		private HuffmanTree huffmanTree;
		// tracks how often each character occurs    
		private int count[];
		// the huffman code for each character     
		private String code[];
		// stores the # of unique characters in content     
		private int uniqueChars = 0;
		//constructor   
		public HuffmanConverter(String input){
		this.contents = input;
		this.count = new int[NUMBER_OF_CHARACTERS];
		this.code = new String[NUMBER_OF_CHARACTERS];
		}
		
		public void recordFrequencies() throws FileNotFoundException {
			//for loop that goes through all the characters in the array 
		
		    for(char ch:contents.toCharArray()){
		    	
		    	count[ch]++;
		    }
		    String charArray = "";
		    for(int i=0;i<256;i++){
		        if(count[i]!=0) {
		        	charArray += (char)i;
		        	charArray += " ";
		        	charArray+=count[i];
		        	charArray += " ";
		            
		            System.out.print("<" + (char)i + ", " + count[i] + "> ");
		        }
		    }
		   
		    
		}
		    
		
		
		public void frequenciesToTree() {
			
			try {
				int num = 0;
				//iterate through the array 
				for (int i=0; i<count.length; i++) {
					//get all frequencies that are not zero 
					if (count[i]!=0) {
						num++;
					}
				}
					//create huffman node with the characters that show up in the text 
					HuffmanNode[] huffs = new HuffmanNode[num];
					int huffCount=0;
					for (int x=0; x<count.length; x++) {
						//if freq does not equal zero 
						if (count[x]!=0) {
							//create the charater 
							String c = Character.toString((char) x);
							//create new huffman node , char and frequency 
							huffs[huffCount] = new HuffmanNode(c, (double)count[x]);
							huffCount++;
						}
					}
					BinaryHeap heap = new BinaryHeap(huffs);
					heap.printHeap();
					this.huffmanTree = HuffmanTree.createFromHeap(heap);
				
			}
			catch (UnderflowException e) {
				e.printStackTrace();
			}
		
		}
		
		public void treeToCode() {
			for (int i=0; i<code.length; i++) {
				code[i] = "";
			}
			
		treeToCode(huffmanTree.root,"");
		}
		
		private void treeToCode(HuffmanNode t, String s) {
			if (t.left !=null && t.right != null) {
				treeToCode(t.left, s + "1");
				treeToCode(t.right, s + "0");
			}
			else {
				//print the huffmancode for output
				code[(int)t.letter.charAt(0)]= s;
				System.out.print("'"+t.letter+"'" + "=" + s + " ");
				
			}
	
		}
		
		public String encodeMessage() {
			//create huffman code with all character codes 
			String huffmanEncoded = "";
			for (int i= 0; i< contents.length(); i++) {
				huffmanEncoded += code[(int) contents.charAt(i)];
			}
			return huffmanEncoded;
		}
		
		public static String readContents(String filename) throws IOException {
			//use buffered reader instead of new File(pathname)
			BufferedReader reader = new BufferedReader(new FileReader(filename));
		    String line = null;
		    StringBuilder  content = new StringBuilder();
		    String newLine = System.getProperty("line.separator");

		        while((line = reader.readLine()) != null) {
		            content.append(line);
		            content.append(newLine);
		        }

		        return content.toString();

			
		}
		
		public String decodeMessage(String encodedStr) {
			//instantiate string variable decoded 
			String decoded = "";
			HuffmanNode base = huffmanTree.root;
			//while loop = encoded is not empty, it has contents
			while (!encodedStr.isEmpty()) {
				if (encodedStr.charAt(0)=='0') {
					base = base.right;
					encodedStr = encodedStr.substring(1);
				}
				else {
					base = base.left;
					encodedStr = encodedStr.substring(1);
				}
				if (base.left == null && base.right == null) {
					decoded += base.letter;
					base = huffmanTree.root;
				}
			}
			System.out.println();
			return decoded;
		}
		
		
		public static void main(String args[]) throws IOException {
			String message = HuffmanConverter.readContents(args[0]);
			HuffmanConverter huffmanConverter = new HuffmanConverter(message);
			huffmanConverter.recordFrequencies();
			huffmanConverter.frequenciesToTree();
			huffmanConverter.treeToCode();
			String encoded = huffmanConverter.encodeMessage();
			System.out.println();
			System.out.println();
			System.out.println("Huffman encoding:" + "\n" + encoded);
			System.out.println();
			System.out.println("Message size in ASCII encoding:"+ contents.length()*8);
			System.out.println("Message size in Huffman encoding:"+ encoded.length());
			System.out.println();
			System.out.println(huffmanConverter.decodeMessage(encoded));
			
		}
		
		
}
	
		
		