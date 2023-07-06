package huffman;

public class HuffmanTree {
	HuffmanNode root;
	
	//constructor - make binary heap of huffman nodes 
	public HuffmanTree(HuffmanNode huff) {
		this.root = huff;
		
	}
	public void printLegend() {
		//calls next method
		 this.printLegend(this.root, "");
		
	}
	
	//next recursive method in instructions 
	private void printLegend(HuffmanNode t, String s){
		if(t.letter.length() > 1 ){
	      this.printLegend(t.left, s+"0");
	      this.printLegend(t.right, s+"1");
	    }
		
	    else{
	      System.out.print(t.letter+"="+s);
	    }
	}
	public static BinaryHeap legendToHeap(String legend) {
		//split the string based on the spaces 
		 String[] legendArray = legend.split(" ");
		 //instantiate binary heap 
		 BinaryHeap binaryheap = new BinaryHeap();
		 //iterate through the array and insert new huffman nodes into the binary heap 
		 for (int i = 0; i < legendArray.length; i++) {
			 String letter = legendArray[i];
			 Double frequency = Double.parseDouble(legendArray[i=1]);
			 binaryheap.insert(new HuffmanNode(letter, frequency));
		 }
		 //method returns the binary heap 
		 return binaryheap;
		
	}
	
	
	public static HuffmanTree createFromHeap(BinaryHeap b) {
		//size of binary heap is more than 1
       while(b.getSize() > 1){
    	   //remove the two nodes with MIN frequency 
    	   HuffmanNode hNode1 = (HuffmanNode) b.deleteMin();
           HuffmanNode hNode2 = (HuffmanNode) b.deleteMin();
           
           //create new huffman node with the previous two nodes as its children 
           HuffmanNode huffNodeParent = new HuffmanNode(hNode1, hNode2);
           huffNodeParent.left=hNode1;
           huffNodeParent.right=hNode2;
           
           //put this new huffman node into the binary heap 
           b.insert(huffNodeParent);
           

        }
       	//instantiate huffman tree w the only element left in the binary heap (aka the root)
        HuffmanTree tree = new HuffmanTree((HuffmanNode)b.deleteMin());
        
        //return the tree 
        return tree;
        
       } 



}
