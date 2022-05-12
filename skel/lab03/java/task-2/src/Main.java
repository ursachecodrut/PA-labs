import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Main {
    static class Result {
        int len; // rezultat pentru cerinta 1
        ArrayList<Integer> subsequence; // rezultat pentru cerinta 2

        public Result() {
            len = 0;
            subsequence = new ArrayList<>();
        }
    }

    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n, m;
        int[] v;
        int[] w;

        public void solve() {
            readInput();
            writeOutput(getResult());
        }

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                m = sc.nextInt();

                v = new int[n + 1]; // Adaugare element fictiv - indexare de la 1
                for (int i = 1; i <= n; i++) {
                    v[i] = sc.nextInt();
                }

                w = new int[m + 1]; // Adaugare element fictiv - indexare de la 1
                for (int i = 1; i <= m; i++) {
                    w[i] = sc.nextInt();
                }
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(Result result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", result.len);
                for (Integer x : result.subsequence) {
                    pw.printf("%d ", x);
                }
                pw.printf("\n");
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private Result getResult() {
            Result result = new Result();

            // TODO: Aflati cel mai lung subsir comun intre v (de lungime n)
            // si w (de lungime m).
            // Se puncteaza separat urmatoarele 2 cerinte:
            // 2.1. Lungimea CMLSC. Rezultatul pentru cerinta aceasta se va pune
            // in ``result.len``.
            // 2.2. Reconstructia CMLSC. Se puncteaza orice subsir comun maximal
            // valid. Solutia pentru aceasta cerinta se va pune in
            // ``result.subsequence``.

            int[][] dp = new int[n + 1][m + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (v[i] == w[j]) {
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }

            result.len = dp[n][m];

            if (result.len == 0) {
                return result;
            }

            int i = n, j = m;
            int crt = result.len - 1; // crt va tine pozitia pe care urmeaza sa punem o valoare in solutie
            while (i > 0 && j > 0) {
                if (v[i] == w[j]) { // v[i], w[j] a extins candva o solutie
                    result.subsequence.add(v[i]); // adaugam elementul v[i] la solutie
                    crt--;

                    --i; // i,j a extins i-1, j-1 (din recurenta)
                    --j;
                    continue;
                }

                if (dp[i][j] == dp[i - 1][j]) {
                    --i; // ori w[j] a extins singur o solutie
                } else {
                    --j; // ori v[i] a extins singur o solutie
                }
            }

            Collections.reverse(result.subsequence);

            return result;
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
