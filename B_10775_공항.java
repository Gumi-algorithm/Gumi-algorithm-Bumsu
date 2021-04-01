import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_10775_공항 {

	static int G, P;
	static boolean[] v;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		// 분리집합 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		parent = new int[G + 1];
		for(int i = 0; i < G + 1; i++) {
			parent[i] = i;
		}
		int cnt = 0;
		for(int i = 0; i < P; i++) {
			int n = Integer.parseInt(br.readLine());
			n = find(n);
			if(n != 0) {
				union(n, n - 1); // 자기 자신보다 1낮은 숫자를 부모로 연결
				cnt += 1;
			}else {
				break;
			}
		}
		System.out.println(cnt);
		
	}
	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		parent[x] = parent[y];
	}
	
	static void bruteforce() throws NumberFormatException, IOException {
		// 약간 전부 탐색한 느낌..
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		v = new boolean[G + 1];
		int cnt = 0;
		for(int i = 0; i < P; i++) { 
			int n = Integer.parseInt(br.readLine());
			for(int j = n; j > 0; j--) { // n ~ 1까지 도킹이 가능한 숫자 검사
				if(!v[j]) {
					cnt += 1;
					v[j] = true;
					break;
				}
			}
			if(cnt == i) { // 도킹에 실패한 경우
				break;
			}
		}
		System.out.println(cnt);
	}
}
