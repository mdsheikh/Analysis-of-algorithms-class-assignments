
/**
 * assignment5
 */

public class assignment5 {

    public static void main(String[] args) {

        // String input = "Hello World for everyone, free nation";
        String input_one = "Fourscore and seven years ago our fathers brought forth on this continent a new nation, conceived in liberty and dedicated to the proposition that all men are created equal. Now we are engaged in a great civil war, testing whether that nation, or any nation so conceived and so dedicated, can long endure. We are met on a great battlefield of that war. We have come to dedicate a portion of that field as a final resting-place for those who here gave their lives that that nation might live. It is altogether fitting and proper that we should do this. But, in a larger sense, we cannot dedicate — we cannot consecrate — we cannot hallow — this ground. The brave men, living and dead, who struggled here have consecrated it, far above our poor power to add or detract. The world will little note, nor long remember what we say here, but it can never forget what they did here. It is for us the living, rather, to be dedicated here to the unfinished work which they who fought here have thus far so nobly advanced. It is rather for us to be here dedicated to the great task remaining before us — that from these honored dead we take increased devotion to that cause for which they gave the last full measure of devotion — that we here highly resolve that these dead shall not have died in vain — that this nation shall have a new birth of freedom and that government of the people, by the people, for the people, shall not perish from the earth.";
        String input_two = "Oh, say can you see, by the dawn's early light,What so proudly we hailed at the twilight's last gleaming?Whose broad stripes and brightstars,through the perilous fight,O'er the ramparts we watched, were so gallantly streaming?And the rockets' red glare, the bombs bursting in air,Gave proof through the night that our flag was still there.O say, does that star-spangled banner yet waveO'er the land of the free and the home of the brave?On the shore, dimly seen through the mists of the deep,Wherethe foe's haughty host in dread silence reposes,What isthatwhichthe breeze, o'er the towering steep,As it fitfully blows, now conceals, now discloses?Now it catches the gleam of the morning's first beam,In full glory reflected now shines on the stream:'Tis the starspangled banner! O long may it waveO'er the land of the free and the home of the brave. And where is that band who so vauntingly swore That the havoc of war and the battle's confusionA home and a country should leaveus no more?Their blood has wiped out their foulfootstep'spollution.Norefuge could save the hireling and slaveFrom the terror of flight, or the gloom of the grave:And the star-spangled banner in triumph doth waveO'er theland of the free and the home of the brave.Oh! thus be it ever, when freemen shall stand Between their loved homes and the war's desolation!Blest with victory and peace, may the heaven-rescued land Praise the Power that hath made and preserved us a nation.Then conquer we must, for our cause it is just,And this be our motto: In God is our trust. And the star-spangled banner forever shall waveO'er the land of the free and the home of ";

        String input_upper = input_one.toUpperCase();
        String input_upper2 = input_two.toUpperCase();
        // System.out.println(input_upper);
        String[] pattern = { "FREE", "BRAVE", "NATION" };
        // Brute Force algorithm
        System.out.println();
        System.out.println("**************************** OUTPUT OF FIRST INPUT FILE: *****************************");
        System.out.println();
        System.out.println("\t\t\t\t\tBRUTE FORCE ALGORITHM:");
        System.out.println("=====================================================================================");
        BF_Search bf = new BF_Search();
        long bf_startTime = System.nanoTime();
        for (int i = 0; i < pattern.length; i++) {
            bf.brute_force_search(input_upper, pattern[i]);
        }
        long bf_endTime = System.nanoTime();
        bf.printBF_Comparison();
        System.out.println("It took: " + (bf_endTime - bf_startTime) + " nanoseconds");

        // Knuth Moris Pratt algorithm
        System.out.println("\t\t\t\t\tKNUTH MORIS PRATT ALGORITHM:");
        System.out.println("=====================================================================================");
        KMP_Search kmp = new KMP_Search();
        long kmp_startTime = System.nanoTime();
        for (int i = 0; i < pattern.length; i++) {
            kmp.KMPSearch(pattern[i], input_upper);
            if (i == pattern.length - 1) {
                kmp.printKM_Comparisons();
            }
        }
        long kmp_endTime = System.nanoTime();
        System.out.println("It took: " + (kmp_endTime - kmp_startTime) + " nanoseconds");

        // Boyer Moore algorithm
        System.out.println();
        System.out.println("\t\t\t\t\tBOYER MOORE ALGORITHM:");
        System.out.println("=====================================================================================");
        BM_Search bm = new BM_Search();
        char[] bm_input = input_upper.toCharArray();
        char[] bm_pattern = new char[] {};
        long bm_startTime = System.nanoTime();
        for (String i : pattern) {
            bm_pattern = i.toCharArray();
            bm.bm_search(bm_input, bm_pattern);
            if (pattern[pattern.length - 1] == i) {
                bm.printBM_Comparison();
            }
        }
        long bm_endTime = System.nanoTime();
        System.out.println("It took: " + (bm_endTime - bm_startTime) + " nanoseconds");

        // Rabin Karp algorithm
        System.out.println("\t\t\t\t\tRABIN KARP ALGORITHM:");
        System.out.println("=====================================================================================");
        RKA_Search rk = new RKA_Search();
        int q = 101;
        long rk_startTime = System.nanoTime();
        for (int i = 0; i < pattern.length; i++) {
            rk.rka_search(pattern[i], input_upper, q);
            if (i == pattern.length - 1) {
                rk.printRKA_Comparison();
            }
        }
        long rk_endTime = System.nanoTime();
        System.out.println("It took: " + (rk_endTime - rk_startTime) + " nanoseconds");
        System.out.println();
        System.out.println("************************* OUTPUT OF SECOND INPUT FILE: *******************************");
        // Brute Force algorithm
        System.out.println();
        // System.out.println();
        System.out.println("\t\t\t\t\tBRUTE FORCE ALGORITHM:");
        System.out.println("=====================================================================================");
        BF_Search bf2 = new BF_Search();
        long bf_startTime1 = System.nanoTime();
        for (int i = 0; i < pattern.length; i++) {
            bf.brute_force_search(input_upper2, pattern[i]);
        }
        long bf_endTime2 = System.nanoTime();
        bf2.printBF_Comparison();
        System.out.println("It took: " + (bf_endTime2 - bf_startTime1) + " nanoseconds");

        // Knuth Moris Pratt algorithm
        System.out.println("\t\t\t\t\tKNUTH MORIS PRATT ALGORITHM:");
        System.out.println("=====================================================================================");
        KMP_Search kmp2 = new KMP_Search();
        long kmp_startTime1 = System.nanoTime();
        for (int i = 0; i < pattern.length; i++) {
            kmp2.KMPSearch(pattern[i], input_upper2);
            if (i == pattern.length - 1) {
                kmp2.printKM_Comparisons();
            }
        }
        long kmp_endTime2 = System.nanoTime();
        System.out.println("It took: " + (kmp_endTime2 - kmp_startTime1) + " nanoseconds");

        // Boyer Moore algorithm
        System.out.println("\t\t\t\t\tBOYER MOORE ALGORITHM:");
        System.out.println("=====================================================================================");
        BM_Search bm2 = new BM_Search();
        char[] bm_input2 = input_upper.toCharArray();
        char[] bm_pattern2 = new char[] {};
        long bm_startTime1 = System.nanoTime();
        for (String i : pattern) {
            bm_pattern2 = i.toCharArray();
            bm.bm_search(bm_input2, bm_pattern2);
            if (pattern[pattern.length - 1] == i) {
                bm2.printBM_Comparison();
            }
        }
        long bm_endTime2 = System.nanoTime();
        System.out.println("It took: " + (bm_endTime2 - bm_startTime1) + " nanoseconds");

        // Rabin Karp algorithm
        System.out.println("\t\t\t\t\tRABIN KARP ALGORITHM:");
        System.out.println("=====================================================================================");
        RKA_Search rk2 = new RKA_Search();
        int q2 = 101;
        long rk_startTime1 = System.nanoTime();
        for (int i = 0; i < pattern.length; i++) {
            rk2.rka_search(pattern[i], input_upper2, q2);
            if (i == pattern.length - 1) {
                rk2.printRKA_Comparison();
            }
        }
        long rk_endTime2 = System.nanoTime();
        System.out.println("It took: " + (rk_endTime2 - rk_startTime1) + " nanoseconds");
    }
}