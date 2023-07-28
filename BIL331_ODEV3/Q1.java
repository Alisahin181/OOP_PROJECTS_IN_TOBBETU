import java.util.Comparator;
import java.util.PriorityQueue;
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
public class Q1 {

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
    	

    	
        public boolean isFeasible(int[][] l, int n, int m, int maxMakespan) {
            int remainingJobs = m;

            for (int i = 0; i < n; i++) {
                int jobsAssigned = 0;

                for (int j = 0; j < m; j++) {
                    if (l[i][j] <= maxMakespan) {
                        jobsAssigned++;
                    } else {
                        break;
                    }
                }

                remainingJobs -= jobsAssigned;
                if (remainingJobs <= 0) {
                    break;
                }
            }

            return remainingJobs <= 0;
        }

        public int minimizeMakespan(int[][] l, int n) {
            int m = l[0].length;
            int lowerBound = 0;
            int upperBound = l[n - 1][m - 1];

            while (lowerBound < upperBound) {
                int mid = (lowerBound + upperBound) / 2;
                if (isFeasible(l, n, m, mid)) {
                    upperBound = mid;
                } else {
                    lowerBound = mid + 1;
                }
            }

            return lowerBound;
        }



        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int l[][] = new int[n][m];
            for (int i = 0 ; i < n ; i++) {
		        for (int j = 0; j < m ; j++)
	        		l[i][j] = in.nextInt();
	    	}
	        int result = minimizeMakespan(l,n);
	        System.out.println(result);
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





