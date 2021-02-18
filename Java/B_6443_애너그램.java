import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B_6443_애너그램 {

	static int N, end;
	static char[] words, res;
	static boolean[] check;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			String word = br.readLine();
			end = word.length();
			words = new char[end];
			res = new char[end];
			check = new boolean[end];
			for(int j = 0; j < word.length(); j++) {
				words[j] = word.charAt(j);
			}
			Arrays.sort(words);
			dfs(0);
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
		
	}
	
	static void dfs(int index) {
		if(index == end) {
			for(int i = 0; i < end; i++) {
				sb.append(res[i]);
			}
			sb.append("\n");
			return ;
		}
		for(int i = 0; i < end; i++) {
			for(int j = index + 1; j < end; j++) {
				res[j] = 0;
			}
			if(!check[i] && res[index] != words[i]) {
				res[index] = words[i];
				check[i] = true;
				dfs(index + 1);
				check[i] = false;
			}
		}
	}
}