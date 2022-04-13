import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) {	
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for(int tc = 0; tc < T; tc++) {
			
			//input
			int n = fs.nextInt();
			int[] a = fs.readArray(n);
			
			//extra variable and boolean array (for collecting positive elements
			//and marking the visited cells respectively)
			long positive = 0;
			boolean[] visited = new boolean[n];
			Arrays.fill(visited, false);
			
			//there will two iterations:
			//1. from 0 to (N - 1)
			//2. from (N - 1) to 0
			
			//the first iteration will be free since i < j, so the process works
			//by collecting all the positive numbers and decreasing them if the
			//current element being iterated upon is a negative number
			//note: don't forget to update the value of the negative number
			//and mark all the positive elements that were visited as well
			
			for(int i = 0; i < n; i++) {
				if(a[i] > 0) {
					visited[i] = true;
					positive += a[i];
					a[i] = 0;
				}
				else if(positive >= Math.abs(a[i])) {
					positive -= Math.abs(a[i]);
					a[i] = 0;
				}
				else {
					a[i] += positive;
					positive = 0;
				}
			}
			
			//the second operation contains our final answer since is i > j and
			//the process works by doing the same thing as the first iteration
			//but this time, there is a cost and we need skip the positive elements
			//that were visited in the first iteration
			
			long ans = 0;
			for(int i = 0; i < n; i++) {
				if(!visited[i]) {
					int newNum = Math.abs(a[i]);
					positive -= newNum;
					ans += newNum;
					a[i] = 0;
				}
			}
			
			//output
			out.println(ans);
		}
		out.close();
	}
	
	static void sort(int[] a) {
		ArrayList<Integer> arr = new ArrayList<>();
		for(int x : a) {
			arr.add(x);
		}
		Collections.sort(arr);
		for(int i = 0; i < a.length; i++) {
			a[i] = arr.get(i);
		}
	}
		
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while(!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for(int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
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
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
