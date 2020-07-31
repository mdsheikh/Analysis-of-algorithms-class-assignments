public class BF_Search {

    static int brute_force_comparison = 0;

    void printBF_Comparison() {
        System.out.println("Comparisons:" + brute_force_comparison);
    }

    public void brute_force_search(String txt, String pat) {

        int M = pat.length();
        int N = txt.length();

        /* A loop to slide pat one by one */
        for (int i = 0; i <= N - M; i++) {

            int j;

            /*
             * For current index i, check for pattern match
             */
            for (j = 0; j < M; j++) {

                brute_force_comparison++;

                if (txt.charAt(i + j) != pat.charAt(j))
                    break;
            }
            if (j == M) // if pat[0...M-1] = txt[i, i+1, ...i+M-1]
                System.out.println("Pattern found at index " + i);
        }
    }
}