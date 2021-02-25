import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2477_참외밭 {

	static int[] dy = {0, 0, 0, 1, -1};
	static int[] dx = {0, 1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int k = Integer.parseInt(br.readLine());
		int[] lst = new int[6];
		int[] y = new int[2], x = new int[2];
		
		for(int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			if(d < 3) {
				if(x[0] < s) {
					x[0] = s;
					x[1] = i;
				}
			}else {
				if(y[0] < s) {
					y[0] = s;
					y[1] = i;
				}
			}
			lst[i] = s;
		}
		int miny = lst[(x[1] + 3) % 6], minx = lst[(y[1] + 3) % 6];
		System.out.println(((y[0] * x[0]) - (miny * minx)) * k);
	}
}