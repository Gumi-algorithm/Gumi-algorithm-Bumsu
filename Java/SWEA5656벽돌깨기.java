import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA5656벽돌깨기 {

	static class Position { // y, x, power 저장
		int y, x, pw;

		public Position(int y, int x, int pw) {
			this.y = y;
			this.x = x;
			this.pw = pw;
		}
	}

	static int N, W, H, min;
	static int[] num, dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
	static int[][] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			min = W * H;
			arr = new int[H][W];
			num = new int[N];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			getNumber(0);
			sb.append("#" + t + " " + min + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

	static void getNumber(int index) { // 조합으로 구슬 던지는 위치 선정
		if (index == N) {
			simulation();
			return;
		}
		for (int i = 0; i < W; i++) {
			num[index] = i;
			getNumber(index + 1);
		}
	}

	static int throwing(int[][] map, int num) { // 구슬을 던짐
		for (int i = 0; i < H; i++) { // 정해진 x열에서 위에서 부터 벽돌 검사
			if (map[i][num] != 0) { // 벽돌이 있으면 높이 리턴
				return i;
			}
		}
		return -1; // 없으면 -1을 리턴
	}

	static void downMap(int[][] map) { // 빈 공간이 있으면 벽돌 이동
		for (int i = 0; i < W; i++) {
			for (int j = H - 2; j >= 0; j--) { // 맨 밑에 한 칸위 부터 검사
				if (map[j][i] != 0) { // 벽돌이라면
					int y = j + 1;
					while (y < H) { // 맨 밑바닥까지 검사
						if (map[y][i] == 0) { // 빈 공간이면
							map[y][i] = map[y - 1][i]; // 한칸 내림
							map[y - 1][i] = 0;
							y += 1;
						} else { // 빈 공간이 아니면 종료
							break;
						}
					}

				}
			}
		}
	}

	static void simulation() { 
		int[][] map = new int[H][W]; // 모든 경우 해봐야하기 때문에 원래 배열을 복사
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = arr[i][j];
			}
		}
		for (int i = 0; i < N; i++) { // N번 투하
			int idx = throwing(map, num[i]); // 던져지는 위치 찾기
			if (idx == -1) { // 던지는 행에 벽돌이 없으면 넘어감
				continue;
			}
			Queue<Position> q = new LinkedList<>();
			q.offer(new Position(idx, num[i], map[idx][num[i]])); // 구슬을 맞는 벽돌의 y, x, pw를 저장 
			while (!q.isEmpty()) {
				Position now = q.poll();
				map[now.y][now.x] = 0; // 맞는 부분 0으로 표시
				for (int j = 0; j < 4; j++) { // 4방향 탐색
					for (int k = 1; k < now.pw; k++) { // pw만큼 검사
						int ny = now.y + (k * dy[j]);
						int nx = now.x + (k * dx[j]);
						if (0 <= ny && ny < H && 0 <= nx && nx < W && map[ny][nx] > 0) { // 빈공간이 아닌 경우만
							if (map[ny][nx] > 1) { // 1보다 큰 경우에는 Queue넣기
								q.offer(new Position(ny, nx, map[ny][nx]));
							}
							map[ny][nx] = 0; // 모두 0으로 표시
						}
					}
				}
			}
			downMap(map);
		}
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] > 0)
					cnt += 1;
			}
		}
		min = Math.min(min, cnt); // 남은 벽돌의 최솟값 저장
	}
}
