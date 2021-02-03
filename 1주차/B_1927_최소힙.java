import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class B_1927_최소힙 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int n; 
		
		for(int i = 0; i < T; i++) {
			n = Integer.parseInt(br.readLine());
			if (n > 0) {
				heap.offer(n);
			} else if (n == 0) {
				if(heap.size() != 0) {
					sb.append(heap.poll() + "\n");
				} else {
					sb.append("0\n");
				}
			}
		}
		System.out.println(sb);
	}

}