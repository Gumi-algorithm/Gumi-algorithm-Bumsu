import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_15961_회전초밥 {

	static int N, d, k, c;
	static int[] arr;
	static boolean[] v;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); //접시 수
		d = Integer.parseInt(st.nextToken()); //초밥 가짓 수
		k = Integer.parseInt(st.nextToken()); //연속해서 먹는 접시 수
		c = Integer.parseInt(st.nextToken()); //쿠폰 번호
		arr = new int[N + k]; //맨 끝을 선택했을 때 앞에 번호들이 필요(회전이기 때문에)
		int[] v = new int[d + 1];
		v[c] = 1; // 최대로 먹으려면 무조건 쿠폰은 먹어야 됨
		int max = 0, cnt = 1;
		for (int i = 0; i < N; i++) {//접시 입력
			arr[i] = Integer.parseInt(br.readLine());
			if (i < k) { // 맨 뒤에 k - 1 추가로 입력
				arr[i + N] = arr[i];
				if (v[arr[i]] == 0) { // 처음에 k보다 작은 경우의 갯수를 먼저 생성
					cnt += 1;
				}
				v[arr[i]] += 1; //하나먹었다는 표시용
			}
		}
		for (int i = k; i < N + k - 1; i++) { // 0~k-1로 입력받으면서 구해놨기 때문에 k부터 검사
			if (v[arr[i]] == 0) { // 새로운 접시의 초밥의 번호가 안먹은 번호면 cnt + 1
				cnt += 1;
			}
			v[arr[i]] += 1; // 그리고 먹었다는 표시로 v에 1더해서 표시
			v[arr[i - k]] -= 1; // 새로운 접시 먹었으므로 가장 옛날 접시를 1개 안먹은 걸로 함
			if (v[arr[i - k]] == 0) { // 안먹었다는 표시했는데 0이면 내가 선택한 구간에 해당 번호의 초밥이 없음
				cnt -= 1; // 해당 번호의 초밥 먹은 적이 없으므로 cnt - 1
			}
			max = Math.max(max, cnt); // 모든 경우의 수 중 가장 큰 갯수로 변경
		}
		System.out.println(max); // 출력
	}
}
