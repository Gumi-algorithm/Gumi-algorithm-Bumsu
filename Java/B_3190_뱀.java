import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class B_3190_뱀 {
	
	static class Position{ // 위치 정보
		int y, x;
		
		public Position(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static class Time{ // 시간, 방향 저장
		int time;
		String cmd;
		
		public Time(int time, String cmd) {
			this.time = time;
			this.cmd = cmd;
		}
	}

	static int N, K, L, dir;
	static int[][] arr;
	static Time[] times;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1}; // 상하좌우
	static Deque<Position> dq;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];
		K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			arr[y][x] = 1;
		}
		L = Integer.parseInt(br.readLine());
		times = new Time[L];
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int time = Integer.parseInt(st.nextToken());
			String d = st.nextToken();
			times[i] = new Time(time, d);
		}
		
		dir = 3; // 처음은 오른쪽
		int res = 0, idx = 0;
		dq = new ArrayDeque<>();
		dq.offer(new Position(1, 1));
		while(true) {
			res += 1;
			int ny = dq.peek().y + dy[dir];
			int nx = dq.peek().x + dx[dir];
			if(0 < ny && ny < N + 1 && 0 < nx && nx < N + 1 && check(ny, nx)) {
				dq.offerFirst(new Position(ny, nx)); // 뱀의 머리는 dq 맨 앞에 추가
				if(arr[ny][nx] == 1) { // 사과면 사과만 0으로 표시
					arr[ny][nx] = 0;
				}else { // 0이면 꼬리 제거
					dq.pollLast(); 
				}
			}else { // 범위 밖이거나 check로 뱀의 몸에  부딪히면 break
				break;
			}
			if(idx < L && res == times[idx].time) { // 시간이 되면 다음 방향으로 돌기
				DiretionChange(times[idx].cmd);
				idx += 1;
			}
		}
		System.out.println(res);
	}
	
	static void DiretionChange(String cmd) {
		if(cmd.equals("L")) { // L 일때
			if(dir < 2) { // 상, 하 => 좌, 우 (0, 1 => 2, 3)
				dir += 2;
			}else {
				dir = -dir + 3; // 좌, 우 => 하, 상(2, 3 => 1, 0)
			}
		}else { // D 일때
			if(dir < 2) { // 상, 하 => 우, 좌(0, 1 => 3, 2)
				dir = -dir + 3;
			}else { // 좌, 우 => 상, 하(2, 3 => 0, 1)
				dir -= 2;
			}
		}
	}
	static boolean check(int ny, int nx) {
		for(Position position : dq) { // 뱀의 머리가 몸통이랑 부딪힌 곳이 있는 지 검사
			if(ny == position.y && nx == position.x) {
				return false;
			}
		}
		return true;
	}
}
