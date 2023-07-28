
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Q2 {
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
        public int switchCount(int[] a, int[] b) {
            int n = a.length;
            ArrayList<Pair> times = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                times.add(new Pair(a[i], true));
                times.add(new Pair(b[i], false));
            }

            Collections.sort(times, Comparator.comparingInt(p -> p.time));

            int lightSwitchCount = 0;
            int peopleInRoom = 0;

            for (Pair p : times) {
                if (p.isEnter) {
                    if (peopleInRoom == 0) {
                        lightSwitchCount++;
                    }
                    peopleInRoom++;
                } else {
                    peopleInRoom--;
                }
            }

            return lightSwitchCount;
        }

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                b[i] = in.nextInt();
            }
            int result = switchCount(a, b);
            System.out.println(result);
        }
    }

    static class Pair {
        int time;
        boolean isEnter;

        Pair(int time, boolean isEnter) {
            this.time = time;
            this.isEnter = isEnter;
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