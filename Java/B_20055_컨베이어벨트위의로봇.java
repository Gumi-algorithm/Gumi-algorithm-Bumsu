import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class position{
	int n; // 벨트 내구도
	boolean p; // 벨트 로봇 여부
	
	public position(int n, boolean p) {
		this.n = n;
		this.p = p;
	}
}

public class B_20055_컨베이어벨트위의로봇 {

	static int N, K, cnt = 0, time = 0;
	static position[][] arr;
	static boolean[][] robot;
	static ArrayList<int[]> list = new ArrayList<>();
	static int[] dy = {0, 1, 0, -1}, dx = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new position[2][N];
		robot = new boolean[2][N];
		int index = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < N; j++) {
				arr[i][index] = new position(Integer.parseInt(st.nextToken()), false);
				if(arr[i][index].n == 0) {
					cnt += 1;
				}
				if(i == 0 && j != N - 1) {
					index += 1;
				}else if(i == 1) {
					index -= 1;
				}
			}
		}
		while(true) {
			time += 1;
			move();
			moveRobot();
			if(arr[0][0].n != 0) {
				arr[0][0].p = true;
				arr[0][0].n -= 1;
				if(arr[0][0].n == 0) {
					cnt += 1;
				}
			}
			if(cnt >= K) {
				break;
			}
		}
		System.out.println(time);
		
	}
	
	static void move() { // 벨트 이동
		int[] pattern = new int[] {N-1, 1, N-1, 1};
		int y = 0, x = 0;
		position cur = arr[0][0];
		for(int i = 0; i < 4; i++) {
			if(i == 1) {
				arr[y][x].p = false;
			}
			for(int j = 0; j < pattern[i]; j++) {
				y += dy[i];
				x += dx[i];
				position next = arr[y][x];
				arr[y][x] = cur;
				cur = next;
				
			}
		}
	}
	
	static void moveRobot() { // 로봇이동
		for(int i = N - 1; i > 0; i--) {
			if(!arr[0][i].p && arr[0][i - 1].p && arr[0][i].n > 0) {
				arr[0][i].p = true;
				arr[0][i].n -= 1;
				arr[0][i - 1].p = false;
				if(arr[0][i].n == 0) {
					cnt += 1;
				}
				if(i == N - 1) {
					arr[0][i].p = false;
				}
			}
		}
	}
	
}
