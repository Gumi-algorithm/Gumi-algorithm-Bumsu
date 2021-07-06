import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_1212_8진수2진수 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String N = br.readLine();
		String res = "";
		for (int i = 0; i < N.length(); i++) {
			res = Integer.toBinaryString(N.charAt(i) - '0');
			if (i > 0) {
				for (int j = res.length(); j < 3; j++) {
					sb.append("0");
				}
			}
			sb.append(Integer.toBinaryString(N.charAt(i) - '0'));
		}
		System.out.println(sb);
	}
}
