import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Shark{
	
	int cur, y, x;
	boolean live = true;
	int[][] dir = new int[5][4];
	
	public Shark(int y, int x) {
		this.y = y;
		this.x = x;
	}

}

public class B_19237_어른상어 {

	static int N, M, K, res, time = 0;
	static int[] dy = {0, -1, 1, 0, 0}, dx = {0, 0, 0, -1, 1}; //	1, 2, 3, 4 -> 북 남 왼 오
	static Shark[] shark;
	static int[][] arr;
	static int[][][] smell;
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		res = M;
		arr = new int[N][N];
		smell = new int[N][N][2]; // 0은 상어 index, 1은 냄새 남은 량
		shark = new Shark[M + 1];
		for(int i = 0; i < N; i++) { 
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] != 0) {
					smell[i][j] = new int[] {arr[i][j], K};
					shark[arr[i][j]] = new Shark(i, j);
				}
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < M; i++) { // 상어 현재 방향
			shark[i + 1].cur = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < M; i++) { // 방향 저장
			for(int j = 1; j < 5; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				shark[i + 1].dir[j] = new int[] {Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken())};
			}
		}
		for(int i = 0; i < 1001; i++) {
			time += 1;
			moveShark();
			if(res == 1) { // 상어 한마리 남으면 break
				break;
			}
		}
		if(time > 1000) { // 다돌고 나온 거면 -1출력
			time = - 1;
		}
		System.out.println(time);
	}
	static int[] getSmellCnt(int y, int x, int v, int s) { // 상어 4방향 검사하여 가능한 갯수 세기
		int[] cnt = new int[] {0, 0};
		for(int j = 1; j < 5; j++) {
			int ny = y + dy[j];
			int nx = x + dx[j];
			if(0 <= ny && ny < N && 0 <= nx && nx < N && smell[ny][nx][v] == s) {
				cnt[0] += 1;
				cnt[1] = j;
			}
		}
		return cnt; 
	}
	
	static void getDirection(int index, int d, int v, int s) { // 우선순위로 방향 정하기
		for(int i = 0; i < 4; i++) {
			int dir = shark[index].dir[d][i];
			int ny = shark[index].y + dy[dir];
			int nx = shark[index].x + dx[dir];
			if(0 <= ny && ny < N && 0 <= nx && nx < N && smell[ny][nx][v] == s) {
				if(arr[ny][nx] == 0) {
					arr[shark[index].y][shark[index].x] = 0;
					arr[ny][nx] = index;
					shark[index].y = ny;
					shark[index].x = nx;
					shark[index].cur = dir;
					break;
				}else {
					shark[index].live = false;
					res -= 1;
					arr[shark[index].y][shark[index].x] = 0;
					break;
				}
			}
		}
	}
	
	static void moveShark() {
		for(int i = 1; i < M + 1; i++) {
			if(shark[i].live) {
				int[] cnt = getSmellCnt(shark[i].y, shark[i].x, 1, 0);
				if(cnt[0] == 0) { // 냄새 없는 구역이 0
					int[] smellcnt = getSmellCnt(shark[i].y, shark[i].x, 0, i);
					if(smellcnt[0] == 1) { // 자신의 냄새 구역 1개면
						arr[shark[i].y][shark[i].x] = 0;
						shark[i].y += dy[smellcnt[1]];
						shark[i].x += dx[smellcnt[1]];
						shark[i].cur = smellcnt[1];
						arr[shark[i].y][shark[i].x] = i;
					}else if(smellcnt[0] > 1){ // 자신의 냄새 구역 2개 이상
						getDirection(i, shark[i].cur, 0, i);
					}
				}else if(cnt[0] == 1){ // 구역 1
					if(arr[shark[i].y + dy[cnt[1]]][shark[i].x + dx[cnt[1]]] == 0) { // 상어가 없으면
						arr[shark[i].y][shark[i].x] = 0;
						shark[i].y += dy[cnt[1]];
						shark[i].x += dx[cnt[1]];
						shark[i].cur = cnt[1];
						arr[shark[i].y][shark[i].x] = i;
					}else { // 가려는 곳에 상어가 있으면
						shark[i].live = false;
						res -= 1;
						arr[shark[i].y][shark[i].x] = 0;
					}
				}else { // 구역 2, 3, 4
					getDirection(i, shark[i].cur, 1, 0);
					
				}
			}
		}
		smellChange();
	}
	
	static void smellChange() { // 냄새 1씩 감소
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(smell[i][j][1] != 0) {
					smell[i][j][1] -= 1;
					if(smell[i][j][1] == 0) {
						smell[i][j][0] = 0;
					}
				}
			}
		}
		for(int i = 1; i < M + 1; i++) { // 상어 새로운 위치에 K 추가
			if(shark[i].live) {
				smell[shark[i].y][shark[i].x] = new int[] {i, K};
			}
		}
	}
}
