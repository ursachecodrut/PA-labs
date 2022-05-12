import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static class Obj {
        public int weight;
        public int price;
        public double ratio;

        public Obj() {
            weight = 0;
            price = 0;
        }
    };

    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n, w;
        Obj[] objs;

        public void solve() {
            readInput();
            writeOutput(getResult());
        }

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                w = sc.nextInt();
                objs = new Obj[n];
                for (int i = 0; i < n; i++) {
                    objs[i] = new Obj();
                    objs[i].weight = sc.nextInt();
                    objs[i].price = sc.nextInt();
                }
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(double result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%.4f\n", result);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private double getResult() {
            for (int i = 0; i < n; i++) {
                objs[i].ratio = ((double) objs[i].price) / ((double) objs[i].weight);
            }

            Arrays.sort(objs, new Comparator<Obj>() {
                public int compare(Obj a, Obj b) {
                    return b.ratio < a.ratio ? -1 : 1;
                }
            });

            double maxPrice = 0;
            for (Obj obj : objs) {
                if (obj.weight <= w) {
                    w -= obj.weight;
                    maxPrice += (double) obj.price;
                } else {
                    maxPrice += (double) obj.price * ((double) w / (double) obj.weight);
                    break;
                }
            }

            return maxPrice;
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
