/**
 * assignment3
 */
public class assignment3 {

    public static int comparisons =  0;
    public static int comparisonsDP = 0;

    //this function is used to clean up the string
    public static String cleanString(String input){
        String newString = "";
        newString = input.replaceAll("[^a-zA-Z]", "");  
        newString = newString.toUpperCase();
        return newString;

    }

    public static int helperMax(int x , int y){
        return x > y ? x:y;
    }
  // O(n^2) time and O(n^2) space
  public static int lps_length(String seq) 
  { 
  int n = seq.length(); 
  int i, j, cl; 
  // Create a table to store results of subproblems 
  int L[][] = new int[n][n];  
    
  // Strings of length 1 are palindrome of lentgh 1 
  for (i = 0; i < n; i++) 
      L[i][i] = 1; 
            
      // Build the table. Note that the lower  
      // diagonal values of table are 
      // useless and not filled in the process.  
      // The values are filled in a manner similar 
      //  to Matrix Chain Multiplication DP solution (See 
      // cl is length of substring 
      for (cl=2; cl<=n; cl++) 
      { 
          for (i=0; i<n-cl+1; i++) 
          { 
              j = i+cl-1; 
              if (seq.charAt(i) == seq.charAt(j) && cl == 2) 
              L[i][j] = 2; 
              else if (seq.charAt(i) == seq.charAt(j)) 
              L[i][j] = L[i+1][j-1] + 2; 
              else
              L[i][j] = helperMax(L[i][j-1], L[i+1][j]); 
          } 
      } 
            
      return L[0][n-1]; 
  } 
   

    // Returns the length of the longest  
    // palindromic subsequence in str 
    // space optimization using dynamic programming
    public static int lps_DP(String s) 
    { 
        int n = s.length(); 
  
    // a[i] is going to store length 
    // of longest palindromic subsequence 
    // of substring s[0..i] 
        int a[] = new int[n]; 
  
        // Pick starting point 
        for (int i = n - 1; i >= 0; i--){  
            
            int back_up = 0; 
  
    // Pick ending points and see if s[i] 
    // increases length of longest common 
    // subsequence ending with s[j]. 
    for (int j = i; j < n; j++) { 
  
    // similar to 2D array L[i][j] == 1 
    // i.e., handling substrings of length 
    // one. 
    comparisonsDP += 3;
    if (j == i) {

        a[j] = 1; 
    }   
  
    // Similar to 2D array L[i][j] = L[i+1][j-1]+2 
    // i.e., handling case when corner characters 
    // are same. 
  
    
    else if (s.charAt(i) == s.charAt(j)) 

        { 
        int temp = a[j]; 
        a[j] = back_up + 2; 
        back_up = temp; 
        } 
 
    // similar to 2D array L[i][j] = max(L[i][j-1], 
    // a[i+1][j]) 
      else   
            { 
                back_up = a[j]; 
                a[j] = Math.max(a[j - 1], a[j]); 
            } 
          } 
    } 
    return a[n - 1]; 
    }  

//this is for printing LCS string
    public static String lcs(String a, String b) { 
        int m = a.length(); 
        int n = b.length(); 
        char X[] = a.toCharArray(); 
        char Y[] = b.toCharArray(); 
  
        int L[][] = new int[m + 1][n + 1]; 
  
        /* Following steps build L[m+1][n+1] in bottom  
        up fashion. Note that L[i][j] contains  
        length of LCS of X[0..i-1] and Y[0..j-1] */
        for (int i = 0; i <= m; i++) { 
            for (int j = 0; j <= n; j++) { 
                if (i == 0 || j == 0) { 
                    L[i][j] = 0; 
                } else if (X[i - 1] == Y[j - 1]) { 
                    L[i][j] = L[i - 1][j - 1] + 1; 
                } else { 
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]); 
                } 
            } 
        } 
  
        // Following code is used to print LCS  
        int index = L[m][n]; 
  
        // Create a String length index+1 and  
        // fill it with \0  
        char[] lcs = new char[index + 1]; 
  
        // Start from the right-most-bottom-most  
        // corner and one by one store characters  
        // in lcs[]  
        int i = m, j = n; 
        while (i > 0 && j > 0) { 
            // If current character in X[] and Y  
            // are same, then current character  
            // is part of LCS  
            if (X[i - 1] == Y[j - 1]) { 
                // Put current character in result  
                lcs[index - 1] = X[i - 1]; 
                i--; 
                j--; 
  
                // reduce values of i, j and index  
                index--; 
            } // If not same, then find the larger of  
            // two and go in the direction of larger  
            // value  
            else if (L[i - 1][j] > L[i][j - 1]) { 
                i--; 
            } else { 
                j--; 
            } 
        } 
        String ans = ""; 
        for (int x = 0; x < lcs.length; x++) { 
            ans += lcs[x]; 
        } 
        return ans; 
    } 
  

// Returns longest palindromic subsequence  of string
       public static String longestPalSubseqOutput(String str) { 
        // Find reverse of str  
        String rev = str; 
        rev = reverse(rev); 
  
        // Return LCS of str and its reverse  
        return lcs(str, rev); 
    } 
  
    public static String reverse(String str) { 
        String ans = ""; 
        // convert String to character array  
        // by using toCharArray  
        char[] try1 = str.toCharArray(); 
  
        for (int i = try1.length - 1; i >= 0; i--) { 
            ans += try1[i]; 
        } 
        return ans; 
    } 
  

    public static void main(String[] args) {
        System.out.println();
        System.out.println("====================================================================================");
        System.err.println("*********************************** MD SHEIKH **************************************");
        System.out.println("====================================================================================");
        // input string
        String input_one = "Fourscore and seven years ago our fathers brought forth on this continent a new nation, conceived in liberty and dedicated to the proposition that all men are created equal. Now we are engaged in a great civil war, testing whether that nation, or any nation so conceived and so dedicated, can long endure. We are met on a great battlefield of that war. We have come to dedicate a portion of that field as a final resting-place for those who here gave their lives that that nation might live. It is altogether fitting and proper that we should do this. But, in a larger sense, we cannot dedicate — we cannot consecrate — we cannot hallow — this ground. The brave men, living and dead, who struggled here have consecrated it, far above our poor power to add or detract. The world will little note, nor long remember what we say here, but it can never forget what they did here. It is for us the living, rather, to be dedicated here to the unfinished work which they who fought here have thus far so nobly advanced. It is rather for us to be here dedicated to the great task remaining before us — that from these honored dead we take increased devotion to that cause for which they gave the last full measure of devotion — that we here highly resolve that these dead shall not have died in vain — that this nation shall have a new birth of freedom and that government of the people, by the people, for the people, shall not perish from the earth.";
        String input_two = "Oh, say can you see, by the dawn's early light,What so proudly we hailed at the twilight's last gleaming?Whose broad stripes and brightstars,through the perilous fight,O'er the ramparts we watched, were so gallantly streaming?And the rockets' red glare, the bombs bursting in air,Gave proof through the night that our flag was still there.O say, does that star-spangled banner yet waveO'er the land of the free and the home of the brave?On the shore, dimly seen through the mists of the deep,Wherethe foe's haughty host in dread silence reposes,What isthatwhichthe breeze, o'er the towering steep,As it fitfully blows, now conceals, now discloses?Now it catches the gleam of the morning's first beam,In full glory reflected now shines on the stream:'Tis the starspangled banner! O long may it waveO'er the land of the free and the home of the brave. And where is that band who so vauntingly swore That the havoc of war and the battle's confusionA home and a country should leaveus no more?Their blood has wiped out their foulfootstep'spollution.Norefuge could save the hireling and slaveFrom the terror of flight, or the gloom of the grave:And the star-spangled banner in triumph doth waveO'er theland of the free and the home of the brave.Oh! thus be it ever, when freemen shall stand Between their loved homes and the war's desolation!Blest with victory and peace, may the heaven-rescued land Praise the Power that hath made and preserved us a nation.Then conquer we must, for our cause it is just,And this be our motto: In God is our trust. And the star-spangled banner forever shall waveO'er the land of the free and the home of ";
        String cleanedString_one = cleanString(input_one); // cleaning string
        System.out.println("\t\t\t\t\t\t\t OUTPUT OF REMAINING STRING : ");
        System.out.println("New remaining String: " + cleanedString_one);
        System.out.println();
        System.out.println("Length of original String : " + input_one.length()); // length of original string   
        System.out.println("Length of remaining String: " + cleanedString_one.length()); // length of new cleaned string

        System.out.print("LPS length is: ");
        long starTime = System.nanoTime();
        System.out.println(lps_length(cleanedString_one));
        System.out.println("LPS String is: "); // printing LPS string
        System.out.println(longestPalSubseqOutput(cleanedString_one));
        long endTime = System.nanoTime();
        //System.out.println("It took: " + comparisons +  " Comparisons");
        System.out.println("It took : " + (endTime - starTime) + " nanoseconds");
        System.out.println();
        // second input starts here 
        System.out.println("\t\t\t\t\t\t\tOUTPUT OF SECOND REMAINING STRING:");
        String clenedString_two = cleanString(input_two);
        System.out.println("New String is: " + clenedString_two);
        System.out.println("Length of original String: " + input_two.length());
        System.out.println("Length of remaining String: " + clenedString_two.length());
        System.out.print("LPS is: ");
        long startTimeDP = System.nanoTime();
        System.out.print(lps_DP(clenedString_two));
        System.out.println();
        System.out.println("LPS String is: ");
        System.out.println(longestPalSubseqOutput(clenedString_two));
        long endTimeDP = System.nanoTime();
        System.out.println();
        System.out.println("It took using DP: " + comparisonsDP + " Comparisons");
        System.out.println("It took " + (endTimeDP - startTimeDP) + " nanoseconds");
             
        
    }
} 