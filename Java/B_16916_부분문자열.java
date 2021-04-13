import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_16916_부분문자열 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String t = br.readLine();
		String p = br.readLine();
		char[] text = t.toCharArray();
		char[] pat = p.toCharArray();
		int[] fail = makeFail(pat);
		
		int res = 0, j = 0;
		for (int i = 0; i < text.length; i++) {
			while (j > 0 && text[i] != pat[j]) {
				j = fail[j - 1];
			}
			if (text[i] == pat[j]) {
				if (j == pat.length - 1) {
					res = 1;
					break;
				} else {
					j += 1;
				}
			}
		}
		System.out.println(res);
	}

	static int[] makeFail(char[] p) {
		int size = p.length;
		int j = 0;
		int[] fail = new int[size];

		for (int i = 1; i < size; i++) {
			while (j > 0 && p[j] != p[i]) {
				j = fail[j - 1];
			}
			if (p[i] == p[j]) {
				j += 1;
				fail[i] = j;
			}
		}
		return fail;
	}
}
