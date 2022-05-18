import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static boolean DFS(int source, int[] color, ArrayList<Integer> adj[], ArrayList<Integer> toposort) {
        if (color[source] == 1) {
            return true;
        }

        if (color[source] == 2) {
            return false;
        }

        color[source] = 1;
        for (Integer neigh : adj[source]) {
            // if(color[neigh] == 1){
            // return true;
            // }

            if (DFS(neigh, color, adj, toposort) == true) {
                return true;
            }
        }

        toposort.add(source);
        color[source] = 2;
        return false;
    }

    public static void dfsComp(int source, boolean[] visited, ArrayList<Integer> adj[], ArrayList<Integer> comp) {

        visited[source] = true;
        for (Integer neigh : adj[source]) {
            if (!visited[neigh]) {
                dfsComp(neigh, visited, adj, comp);
            }
        }

        comp.add(source);
    }

    public static void main(String[] args) {
        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
         * class should be named Solution.
         */
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();

        int mandatorySize = scanner.nextInt();
        Set<Integer> mandatory = new HashSet<>();
        for (int i = 0; i < mandatorySize; i++) {
            mandatory.add(scanner.nextInt());
        }

        ArrayList<Integer> adj[] = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            int dependecySize = scanner.nextInt(); // numarul de dependinte

            for (int j = 0; j < dependecySize; j++) {
                int dep = scanner.nextInt();
                adj[dep].add(i);
            }
        }

        // for(int i = 1; i <= n; i++){
        // for(int j = 0; j < adj[i].size(); j++){
        // System.out.println("node: " + i + " " + adj[i].get(j));
        // }
        // }

        int[] color = new int[n + 1];
        // boolean[] visited = new boolean[n + 1];
        // Arrays.fill(visited, false);

        ArrayList<Integer> toposort = new ArrayList<>();
        ArrayList<ArrayList<Integer>> allToposorts = new ArrayList<>();

        boolean foundCycle = false;
        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                toposort.clear();
                boolean cycle = DFS(i, color, adj, toposort);
                if (cycle) {
                    foundCycle = true;
                    System.out.println("-1");
                    break;
                } else {
                    System.out.println(toposort);
                    allToposorts.add(toposort);
                }
            }
        }

        // ArrayList<ArrayList<Integer>> components = new ArrayList<>();
        // for(int i = 1; i <= n; i++){
        // if(!visited[i]){

        // ArrayList<Integer> newComp = new ArrayList<>();
        // dfsComp(i, visited, adj, newComp);
        // components.add(newComp);
        // }
        // }

        // System.out.println(components);

        // if(!foundCycle) {
        // ArrayList<Integer> result = new ArrayList<>();
        // for(ArrayList<Integer> topo : allToposorts){
        // int lastPos = -1;
        // for(int i = 0; i < topo.size(); i++) {
        // if(mandatory.contains(topo.get(i))){
        // lastPos = i;
        // break;
        // }
        // }

        // if(lastPos != -1){
        // for(int i = topo.size() - 1; i >= lastPos; i--){
        // result.add(topo.get(i));
        // }
        // }
        // }

        // // print result
        // System.out.println(result.size());
        // for(Integer e : result){
        // System.out.print(e + " ");
        // }
        // }

    }
}

class MyScanner {
    BufferedReader br;
    StringTokenizer st;

    public MyScanner() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}