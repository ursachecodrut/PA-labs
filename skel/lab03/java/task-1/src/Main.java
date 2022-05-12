import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n, S;
        int[] v;

        public static final int INF = (int) 1e9;

        public void solve() {
            readInput();
            writeOutput(getResult());
        }

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                S = sc.nextInt();
                v = new int[n + 1];
                for (int i = 1; i <= n; i++) {
                    v[i] = sc.nextInt();
                }
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(int result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", result);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private int getResult() {
            // TODO: Aflati numarul minim de monede ce poate fi folosit pentru a
            // obtine suma S. Tipurile monedelor sunt stocate in vectorul v, de
            // dimensiune n.

            int[] dp = new int[S + 1];

            dp[0] = 0;
            for (int s = 1; s <= S; s++) {
                dp[s] = INF;
                for (int coinValue : v) {
                    if (coinValue <= s) {
                        dp[s] = Math.min(dp[s], 1 + dp[s - coinValue]);
                    }
                }
            }
            return (dp[S] != INF ? dp[S] : -1);
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
