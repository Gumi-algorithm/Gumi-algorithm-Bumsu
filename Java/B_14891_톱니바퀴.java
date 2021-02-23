import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14891_톱니바퀴 {

	static int[] cmd; 
	static boolean[] check;
	static int[][] gear = new int[4][8];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int res = 0;
		for(int i = 0; i < 4; i++) { // 톱니바퀴 정보 입력
			String data = br.readLine();
			for(int j = 0; j < 8; j++) {
				gear[i][j] = data.charAt(j) - '0';
			}
		}
		
		int k = Integer.parseInt(br.readLine());
		for(int i = 0; i < k; i++) {
			cmd = new int[4]; // 톱니바퀴별 명령 저장용
			check = new boolean[4]; // 회전방향을 정했는지 확인용
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken()) - 1;
			int t = Integer.parseInt(st.nextToken());
			cmd[n] = t; // 입력받은 톱니바퀴 명령 저장
			makeCmd(n, t); 
			for(int j = 0; j < 4; j++) {
				if(cmd[j] == 1) { // 1이면 시계 방향
					clock(j); 
				}else if(cmd[j] == - 1) { // -1이면 반시계 방향
					antiClock(j);
				}
			}
		}
		for(int i = 0; i < 4; i++) {
			if(gear[i][0] == 1) res += Math.pow(2, i);
		}
		System.out.println(res);
	}

	static void makeCmd(int n, int t) {
		if(check[n]) return;
		int next;
		check[n] = true;
		if(n == 0) { // 첫 번째 톱니 바퀴는 오른쪽만 확인
			next = right(n, t);
			if(next != - 1) makeCmd(next, cmd[next]);
		}else if(n == 3) { // 네 번째 톱니 바퀴는 왼쪽만 확인
			next = left(n, t);
			if(next != - 1) makeCmd(next, cmd[next]);
		}else { // 그 외 2개는 오른쪽 왼쪽 둘다 확인
			next = left(n, t);
			if(next != - 1) makeCmd(next, cmd[next]);
			next = right(n, t);
			if(next != - 1) makeCmd(next, cmd[next]);
		}
	}
	
	static void clock(int num) { // 시계 방향 회전
		int tmp = gear[num][7];
		for(int i = 6; i >= 0; i--) {
			gear[num][i + 1] = gear[num][i];
		}
		gear[num][0] = tmp;
	}
	
	static void antiClock(int num) { // 반시계 방향 회전
		int tmp = gear[num][0];
		for(int i = 1; i < 8; i++) {
			gear[num][i - 1] = gear[num][i];
		}
		gear[num][7] = tmp;
	}
	
	static int left(int n, int t) { // 왼쪽 톱니바퀴와 일치 여부 확인
		if(check[n - 1]) return - 1;
		if(gear[n][6] != gear[n - 1][2]) { // 다르면 -t로 반대 방향입력
			cmd[n - 1] = -t;
			return n - 1;
		}
		check[n - 1] = true;
		return - 1;
	}
	static int right(int n, int t) { // 오른쪽 톱니바퀴와 일치 여부 확인
		if(check[n + 1]) return -1;
		if(gear[n][2] != gear[n + 1][6]) { // 다르면 -t로 반대 방향입력
			cmd[n + 1] = -t;
			return n + 1;
		}
		check[n + 1] = true;
		return -1;
	}
}