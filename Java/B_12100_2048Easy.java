import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_12100_2048Easy {

	static int N, max = 0;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		// 다시 봐야할 듯
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0);
		System.out.println(max);
	}
	static int[][] copy(int[][] a) { // 배열을 계속 돌리기 때문에 복사
		int[][] board = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				board[i][j] = a[i][j];
			}
		}
		return board;
	}
	static void dfs(int index) { 
		if(index == 5) { // 최대 5번이기 때문에
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					max = Math.max(arr[i][j], max); // 최대값 구하기
				}
			}
			return ;
		}
		int[][] board = copy(arr); // 복사
		for(int i = 0; i < 4; i++) {
			move(i); // 배열 움직이기
			dfs(index + 1);
			arr = copy(board); // 다시 돌려 놓기
		}
	}
	// 파이썬으로 안에 arr[j][i]를 if안에 넣으면 값이 다를 때 idx == j인 경우는 0으로 초기화 되버림
	static void move(int d) {
		if(d == 0) {
			for(int i = 0; i < N; i++) { // 상
				int idx = 0;
				for(int j = 1; j < N; j++) {
					if(arr[j][i] != 0) { // 0이면 냅두면 됨
						int temp = arr[j][i]; 
						arr[j][i] = 0;
						if(arr[idx][i] == 0) { // 해당하는 곳이 0이면 끌고 데려오기
							arr[idx][i] = temp;
						}else if(arr[idx][i] == temp) { // 같으면 합치기
							arr[idx][i] *= 2;
							idx += 1;
						}else { // 다르면 한 칸 옮기고 값 넣기
							idx += 1;
							arr[idx][i] = temp;
						}
					}
				}
			}
		}
		else if(d == 1) {
			for(int i = 0; i < N; i++) { // 하
				int idx = N - 1;
				for(int j = N - 2; j > -1; j--) {
					if(arr[j][i] != 0) {
						int temp = arr[j][i];
						arr[j][i] = 0;
						if(arr[idx][i] == 0) {
							arr[idx][i] = temp;
						}else if(arr[idx][i] == temp) {
							arr[idx][i] *= 2;
							idx -= 1;
						}else {
							idx -= 1;
							arr[idx][i] = temp;
						}
					}
				}
			}
		}
		else if(d == 2) {
			for(int i = 0; i < N; i++) { // 좌
				int idx = 0;
				for(int j = 1; j < N; j++) {
					if(arr[i][j] != 0) {
						int temp = arr[i][j];
						arr[i][j] = 0;
						if(arr[i][idx] == 0) {
							arr[i][idx] = temp;
						}else if(arr[i][idx] == temp) {
							arr[i][idx] *= 2;
							idx += 1;
						}else {
							idx += 1;
							arr[i][idx] = temp;
						}
					}
				}
			}
		}
		else if(d == 3) {
			for(int i = 0; i < N; i++) { // 우
				int idx = N - 1;
				for(int j = N - 2; j > -1; j--) {
					if(arr[i][j] != 0) {
						int temp = arr[i][j];
						arr[i][j] = 0;
						if(arr[i][idx] == 0) {
							arr[i][idx] = temp;
						}else if(arr[i][idx] == temp) {
							arr[i][idx] *= 2;
							idx -= 1;
						}else {
							idx -= 1;
							arr[i][idx] = temp;
						}
					}
				}
			}
		}
	}
}
