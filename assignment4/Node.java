import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// A Tree node
public class Node {
	char ch;
	int freq;
	Node left = null, right = null;

	Node(char ch, int freq) {
		this.ch = ch;
		this.freq = freq;
	}

	public Node(char ch, int freq, Node left, Node right) {
		this.ch = ch;
		this.freq = freq;
		this.left = left;
		this.right = right;
	}
}
