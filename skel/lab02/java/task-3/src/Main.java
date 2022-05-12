import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static class Homework {
        public int deadline;
        public int score;

        public Homework() {
            deadline = 0;
            score = 0;
        }
    }

    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n;
        Homework[] hws;

        public void solve() {
            readInput();
            writeOutput(getResult());
        }

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                hws = new Homework[n];
                for (int i = 0; i < n; i++) {
                    hws[i] = new Homework();
                    hws[i].deadline = sc.nextInt();
                    hws[i].score = sc.nextInt();
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
            // TODO: Aflati punctajul maxim pe care il puteti obtine
            // planificand optim temele.

            // descrescator dupe scorul temelor
            Arrays.sort(hws, new Comparator<Homework>() {
                public int compare(Homework a, Homework b) {
                    return (b.score < a.score) ? -1 : 1;
                }
            });

            int max_deadline = 0;
            for (Homework homework : hws) {
                max_deadline = Math.max(max_deadline, homework.deadline);
            }

            boolean[] used = new boolean[max_deadline + 1];
            Arrays.fill(used, false);

            int maxScore = 0;
            for (Homework homework : hws) {
                for (int t = homework.deadline; t > 0; t--) {
                    if (!used[t]) {
                        used[t] = true;
                        maxScore += homework.score;
                        break;
                    }
                }
            }
            return maxScore;
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
