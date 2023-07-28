import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.util.Comparator;
import java.io.InputStream;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Q3 {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(in, out);
        out.close();
    }
 
    static class TaskA {

        public boolean matrixConstruction(int[] R, int[] C) {
            int n = R.length;
            int[][] M = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (R[i] > 0 && C[j] > 0) {
                        M[i][j] = 1;
                        R[i]--;
                        C[j]--;
                    } else {
                        M[i][j] = 0;
                    }
                }
            }

            
            for (int i = 0; i < n; i++) {
                if (R[i] != 0 || C[i] != 0) {
                    return false;
                }
            }

            
            System.out.println("The 1's in the matrix are at the following row and column numbers:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (M[i][j] == 1) {
                        System.out.println("(" + (i + 1) + ", " + (j + 1) + ")");
                    }
                }
            }

            return true;
        }

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int r[] = new int[n];
            int c[] = new int[n];
            for (int i = 0 ; i < n ; i++) 
                r[i] = in.nextInt();
            for (int i = 0 ; i < n ; i++)
                c[i] = in.nextInt();
	        int [][]result = matrixConstruction(r,c);
            for (int i = 0 ; i < result.length ; i++) {
                for (int j = 0 ; j < result[i].length ; j++) {
                    System.out.print(result[i][j] + " ");
                }
                System.out.print("\n");
            }
        }

    }
 
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
 
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

 
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
        public long nextLong() {
            return Long.parseLong(next());
        }
 

    }
}