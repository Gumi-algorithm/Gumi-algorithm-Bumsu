import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_17471_게리맨더링 {

	static int N, tot, cnt, min = Integer.MAX_VALUE;
	static int[] person;
	static boolean[] check;
	static int[][] link;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		person = new int[N];
		check = new boolean[N];
		link = new int[N][];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			person[i] = Integer.parseInt(st.nextToken());
			tot += person[i];
		}
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			link[i] = new int[n];
			for(int j = 0; j < n; j++) {
				link[i][j] = Integer.parseInt(st.nextToken()) - 1;
			}
		}
		bitMask();
		if(min == Integer.MAX_VALUE) {
			min = -1;
		}
		System.out.println(min);
	}
	static void bitMask() {
		for(int i = 1; i < (1 << N) - 1; i++){ // 할 수 있는 모든 구역
			int per = 0;
			cnt = 0;
			check = new boolean[N];
//			1은 모두 A그룹, 0은 모두 B그룹
			int[] groupA = new int[Integer.bitCount(i)];
			int[] groupB = new int[N - Integer.bitCount(i)];
			int idx = 0, jdx = 0;
			for(int j = 0; j < N; j++) {
				if((i & 1 << j) != 0) {
					per += person[j];
					groupA[idx++] = j;
				}else {
					groupB[jdx++] = j;
				}
			}
			isPossible(groupB[0], groupB);
			isPossible(groupA[0], groupA);
			if(cnt == N) { // 연결된 수와 총 갯수가 같아야됨
				min = Math.min(min, Math.abs(tot - (2 * per)));
			}
		}
	}
	
	static void isPossible(int index, int[] group) { // 가능한 지 검사
		cnt += 1;
		check[index] = true;
		for(int i = 0; i < link[index].length; i++) {
			if(!check[link[index][i]]) {
				for(int j = 0; j < group.length; j++) { // 그룹 내에 있는 인덱스면
					if(group[j] == link[index][i]) {
						isPossible(link[index][i], group);
					}
				}
			}
		}
	}
}
