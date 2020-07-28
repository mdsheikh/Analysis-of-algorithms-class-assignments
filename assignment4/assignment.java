import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.*;

class assignment {
	// traverse the Huffman Tree and store Huffman Codes in a map.
	public static void encode(Node root, String str, Map<Character, String> huffmanCode) {
		if (root == null)
			return;

		// found a leaf node
		if (root.left == null && root.right == null) {
			huffmanCode.put(root.ch, str);
		}

		encode(root.left, str + '0', huffmanCode);
		encode(root.right, str + '1', huffmanCode);
	}

	// traverse the Huffman Tree and decode the encoded string
	public static int decode(Node root, int index, StringBuilder sb) {
		if (root == null)
			return index;

		// found a leaf node
		if (root.left == null && root.right == null) {
			System.out.print(root.ch);
			return index;
		}

		index++;

		if (sb.charAt(index) == '0')
			index = decode(root.left, index, sb);
		else
			index = decode(root.right, index, sb);

		return index;
	}

	// Builds Huffman Tree and huffmanCode and decode given input text
	public static void buildHuffmanTree(String text) {
		// count frequency of appearance of each character
		// and store it in a map
		Map<Character, Integer> freq = new HashMap<>();
		for (char c : text.toCharArray()) {
			freq.put(c, freq.getOrDefault(c, 0) + 1);
		}

		// Create a priority queue to store live nodes of Huffman tree
		// Notice that highest priority item has lowest frequency
		PriorityQueue<Node> pq;
		pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.freq));

		// Create a leaf node for each character and add it
		// to the priority queue.
		for (var entry : freq.entrySet()) {
			pq.add(new Node(entry.getKey(), entry.getValue()));
		}

		// do till there is more than one node in the queue
		while (pq.size() != 1) {
			// Remove the two nodes of highest priority
			// (lowest frequency) from the queue
			Node left = pq.poll();
			Node right = pq.poll();

			// Create a new internal node with these two nodes as children
			// and with frequency equal to the sum of the two nodes
			// frequencies. Add the new node to the priority queue.
			int sum = left.freq + right.freq;
			pq.add(new Node('\0', sum, left, right));
		}

		// root stores pointer to root of Huffman Tree
		Node root = pq.peek();

		// traverse the Huffman tree and store the Huffman codes in a map

		Map<Character, String> huffmanCode = new HashMap<>();
		long st = System.nanoTime();
		encode(root, "", huffmanCode);
		long et = System.nanoTime();

		// print the Huffman codes
		System.out.println("Huffman Codes are : " + huffmanCode);
		System.out.println("Original string was : " + text);

		// print encoded string
		StringBuilder sb = new StringBuilder();
		for (char c : text.toCharArray()) {
			sb.append(huffmanCode.get(c));
		}
		System.out.println("Encoded string is : " + sb);
		// System.out.println("Length of compression string: " + sb.length());

		// traverse the Huffman Tree again and this time
		// decode the encoded string
		int index = -1;
		System.out.print("Decoded string is: ");
		long ds = System.nanoTime();
		while (index < sb.length() - 2) {
			index = decode(root, index, sb);
		}
		long de = System.nanoTime();
		System.out.println();
		System.out.println("Length of compression string: " + sb.length());
		System.out.println("Time Taken for compresssion: " + (et - st) + " nanosecods");
		System.err.println("Time taken for decompression:" + (de - ds) + " nanoseconds");
		float ratio = (float) text.length() / (float) sb.length();
		System.out.println("Compression time: " + ratio);
		System.out.println("Storage Size of original string: " + text.length() * 8 + "  bits");
		System.out.println("Storage Size of compressed string: " + sb.length() * 8 + " bits");

	}

	public static void main(String[] args) {
		System.err.println();
		System.out.println("******************************** MD SHEIKH ******************************************");
		System.out.println("\t\t\t\t\t\t*****OUTPUT OF FIRST INPUT FILE:*****");
		lzw l = new lzw();
		RLE rle = new RLE();
		String input_one = "Hello world";
		String input_two = "Oh, say can you see, by the dawn's early light,What so proudly we hailed at the twilight's last gleaming?Whose broad stripes and brightstars,through the perilous fight,O'er the ramparts we watched, were so gallantly streaming?And the rockets' red glare, the bombs bursting in air,Gave proof through the night that our flag was still there.O say, does that star-spangled banner yet waveO'er the land of the free and the home of the brave?On the shore, dimly seen through the mists of the deep,Wherethe foe's haughty host in dread silence reposes,What isthatwhichthe breeze, o'er the towering steep,As it fitfully blows, now conceals, now discloses?Now it catches the gleam of the morning's first beam,In full glory reflected now shines on the stream:'Tis the starspangled banner! O long may it waveO'er the land of the free and the home of the brave. And where is that band who so vauntingly swore That the havoc of war and the battle's confusionA home and a country should leaveus no more?Their blood has wiped out their foulfootstep'spollution.Norefuge could save the hireling and slaveFrom the terror of flight, or the gloom of the grave:And the star-spangled banner in triumph doth waveO'er theland of the free and the home of the brave.Oh! thus be it ever, when freemen shall stand Between their loved homes and the war's desolation!Blest with victory and peace, may the heaven-rescued land Praise the Power that hath made and preserved us a nation.Then conquer we must, for our cause it is just,And this be our motto: In God is our trust. And the star-spangled banner forever shall waveO'er the land of the free and the home of ";
		System.out.println("Lampel Ziv Welch:");
		long st = System.nanoTime();
		List<Integer> compressed = l.compress(input_one);
		long et = System.nanoTime();
		System.out.println(compressed);
		System.out.println("Compression Time: " + (et - st) + " nanoseconds");
		long st1 = System.nanoTime();
		String decompressed = l.decompress(compressed);
		long et1 = System.nanoTime();
		System.out.println(decompressed);
		System.out.println("Decompression Time: " + (et1 - st1) + " nanoseconds");
		System.out.println("Lenght of original string: " + input_one.length());
		System.out.println("Length after compression: " + compressed.size());
		float ratio = (float) decompressed.length() / (float) compressed.size();
		System.out.println("Compressed ratio: " + ratio);
		System.out.println("Storage Size for original string: " + input_one.length() * 8 + " bits");
		System.out.println("Sorage Sizr for compressed string: " + compressed.size() * 8 + " bits");
		System.out.println();
		// Run length encoding
		System.out.println("Run Length Encoading:");
		long re1 = System.nanoTime();
		String rle_encode = rle.RLE_Encoding(input_one);
		long re2 = System.nanoTime();
		System.out.println(rle_encode);
		System.out.println("Encoading TIme: " + (re2 - re1) + " nanoseconds");
		long re3 = System.nanoTime();
		String rle_deencode = rle.RLE_DeEncoding(rle_encode);
		long re4 = System.nanoTime();
		System.out.println(rle_deencode);
		System.out.println("Deencoding Time: " + (re4 - re3) + " nanoseconds");
		System.out.println("Length of original string: " + input_one.length());
		System.out.println("Length after compression: " + rle_encode.length());
		float ratio2 = (float) rle_deencode.length() / (float) rle_encode.length();
		System.out.println("Compression ratio: " + ratio2);
		System.out.println("Storage Size for original string : " + input_one.length() * 8 + " bits");
		System.out.println("Storage Size for compressed string: " + rle_encode.length() * 8 + " bits");
		System.out.println();
		// huffman coding
		System.out.println("Huffman coding:");
		buildHuffmanTree(input_one);
		System.out.println();
		System.out.println("Length of original string: " + input_one.length());

		// output of Second input file System.out.println();
		System.out.println("\t\t\t\t\t\t\t *****OUTPUT OF SECOND INPUT FILE:***** ");
		System.out.println("Lampel Ziv Welch:");
		long lw1 = System.nanoTime();
		List<Integer> compressed_second_file = l.compress(input_two);
		long lw2 = System.nanoTime();
		System.out.println();
		System.out.println(compressed_second_file);
		System.out.println("Compression Time: " + (lw2 - lw1) + " nanoseconds");
		long st3 = System.nanoTime();
		String decompressed_second_file = l.decompress(compressed_second_file);
		long et4 = System.nanoTime();
		System.out.println();
		System.out.println(decompressed_second_file);
		System.out.println();
		System.out.println("Decompression Time: " + (et4 - st3) + " nanoseconds");
		System.out.println("Length of original string: " + input_two.length());
		System.out.println("Length after compression: " + compressed_second_file.size());
		System.out.println("Storage Size of original string: " + input_two.length() * 8 + " bits");
		System.out.println("Storage Size of compressed string: " + compressed_second_file.size() * 8 + " bits");
		System.out.println();
		// output of run length encoding
		System.out.println("Run Length Encoading:");
		long res1 = System.nanoTime();
		System.out.println();
		String rle_encode_second_file = rle.RLE_Encoding(input_two);
		System.out.println();
		long res2 = System.nanoTime();
		System.out.println(rle_encode_second_file);
		System.out.println("Encoading TIme: " + (res2 - res1) + " nanoseconds");
		long res3 = System.nanoTime();
		String rle_deencode_second_file = rle.RLE_DeEncoding(rle_encode_second_file);
		long res4 = System.nanoTime();
		System.out.println();
		System.out.println(rle_deencode_second_file);
		System.out.println();
		System.out.println("Deencoding Time: " + (res4 - res3) + " nanoseconds");
		System.out.println("Length of original string: " + input_two.length());
		System.out.println("Length after compression: " + rle_encode_second_file.length());
		System.out.println("Storage Size of original string: " + input_two.length() * 8 + " bits");
		System.out.println("Storage Size of compressed string: " + rle_encode_second_file.length() * 8 + " bits");
		System.out.println();
		// output of huffman coding
		System.out.println("Huffman coding:");
		buildHuffmanTree(input_two);
		System.out.println();
		System.out.println("Length of original string: " + input_two.length());

	}

}
