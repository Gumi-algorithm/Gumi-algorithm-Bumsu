import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_17825_주사위윷놀이 {

	static class Horse {
		int y, x;

		public Horse(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int score;
	static int[] dice;
	static Horse[] horse;
	static int[][] arr;
	static boolean[][] v;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		score = 0;
		dice = new int[10];
		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		horse = new Horse[4];
		for (int i = 0; i < 4; i++) {
			horse[i] = new Horse(0, 0);
		}
		arr = new int[5][];
		arr[0] = new int[20]; // 0~ 38
		for (int i = 1; i < 20; i++) {
			arr[0][i] = i * 2;
		}
		arr[1] = new int[] { 10, 13, 16, 19 }; // 10으로 들어온 경우
		arr[2] = new int[] { 20, 22, 24 }; // 20으로 들어온 경우
		arr[3] = new int[] { 30, 28, 27, 26 }; // 30으로 들어온 경우
		arr[4] = new int[] { 25, 30, 35, 40, 0 }; // 중간 25 ~ 마지막까지
		v = new boolean[5][];
		for (int i = 0; i < 5; i++) {
			v[i] = new boolean[arr[i].length];
		}
		dfs(0, 0);
		System.out.println(score);
	}

	static void dfs(int index, int cnt) {
		if (index == 10) {
			score = Math.max(score, cnt);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int y = horse[i].y, x = horse[i].x; // 옮기기 전 말의 위치
			if (y == 4 && x == 4) { // 이미 도착지점 간 말
				continue;
			}
			int[] move = moveHorse(y, x, index); // 이동 후의 말 위치
			if (v[move[0]][move[1]]) // 이미 말이 있는 위치
				continue;

			if (move[0] == 4 && move[1] == 4) { // 말을 도착지로 이동한 경우
				v[y][x] = false;
				horse[i].y = 4;
				horse[i].x = 4;
			} else { // 말을 이동
				horse[i].y = move[0];
				horse[i].x = move[1];
				v[y][x] = false; // 원래 위치는 false
				v[move[0]][move[1]] = true; // 이동 한 곳은 true
			}
			dfs(index + 1, cnt + arr[move[0]][move[1]]); // 재귀
			horse[i].y = y; // 원상복구
			horse[i].x = x;
			v[move[0]][move[1]] = false;
			v[y][x] = true;

		}
	}

	static int[] moveHorse(int y, int x, int index) {
		x += dice[index];
		boolean flag = false;
		// 40을 가는 방법이 여러가지이고 25~ 35는 겹친 동선이 많음
		while (y != 4 && x >= arr[y].length) { // y가 4인 경우는 도착뿐
			x -= arr[y].length; // length만큼 x를 빼줌
			if (y != 0) { // 0이 아니면 4로 이동
				y = 4;
			}
			flag = true;
		}
		if (flag) {  
			if (y == 0) { // 0이 배열을 넘어간다 파란색 화살표로 움직이지 않고 38을 넘어간 상황
				y = 4; // 판을 4로 이동
				x += 3; // 처음시작인 40으로 설정
			} else { // 나머진 25부터 시작하도록
				y = 4;
			}
		}
		if (x >= arr[y].length - 1) { // 그렇게 하고도 말판크기를 넘어가면 가장 맨끝점(도착지)
			x = arr[y].length - 1;
		} else { // 10, 20, 30인 경우는 판을 각각 1, 2, 3으로 이동
			if (y == 0 && arr[y][x] % 10 == 0 && arr[y][x] < 40) {
				y = arr[y][x] / 10;
				x = 0;
			}
		}
		return new int[] { y, x };
	}
}
