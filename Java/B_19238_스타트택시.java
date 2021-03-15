import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Position{ // 가장 가까운 승객 정보 담기용
	int y, x, cnt, index;

	public Position(int y, int x, int cnt, int index) {
		this.y = y;
		this.x = x;
		this.cnt = cnt;
		this.index = index;
	}

}

class Person{ // 승객 정보
	boolean p = true;
	int y, x, ny, nx;
	
	public Person(int y, int x, int ny, int nx) {
		this.y = y;
		this.x = x;
		this.ny = ny;
		this.nx = nx;
	}
}

public class B_19238_스타트택시 {
	
	static int N, M, F, starty, startx, endy, endx, cnt = 0;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static int[][] arr;
	static Person[] person;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		starty = Integer.parseInt(st.nextToken()) - 1;
		startx = Integer.parseInt(st.nextToken()) - 1;
		person = new Person[M];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			person[i] = new Person(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1
					,Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		}
		for(int i = 0; i < M; i++) { // 결국 M명 태우는거라 M번만 돌리면 됨
			if(F == -1) { // 연료 부족한 경우 or 목적지 못가는 경우
				break;
			}
			movePerson();
		}
		System.out.println(F);
	}
	
	static boolean findPerson() {
		int[][] board = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				board[i][j] = arr[i][j];
			}
		}
		for(int i = 0; i < M; i++) { // 승객이 있는 곳은 2 + i로 표시
			if(person[i].p) {
				Person p = person[i];
				board[p.y][p.x] = 2 + i; 
			}
		}
		Queue<Position> queue = new LinkedList<>();
		ArrayList<Position> list = new ArrayList<>();
		queue.offer(new Position(starty, startx, 0, 0));
		if(board[starty][startx] >= 2) { // 현재 위치와 승객 위치가 같으면 그 사람만 추가
			list.add(new Position(starty, startx, 0, board[starty][startx] - 2));
		}else { // 현재 위치와 승객 위치가 다른 경우
			board[starty][startx] = -1;
			while(!queue.isEmpty()) {
				Position cur = queue.poll();
				if(list.size() > 0 && list.get(list.size() - 1).cnt <= cur.cnt) { 
					continue; // 한명이라도 이미 담았으면 거리가 먼 곳은 전부 continue
				}
				for(int i = 0; i < 4; i++) {
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];
					if(0 <= ny && ny < N && 0 <= nx && nx < N) {
						if(board[ny][nx] == 0) { // 승객은 없고 갈 수 있는 곳
							queue.offer(new Position(ny, nx, cur.cnt + 1, 0));
							board[ny][nx] = -1;
						}else if(board[ny][nx] >= 2) { // 승객이 있는 곳
							list.add(new Position(ny, nx, cur.cnt + 1, board[ny][nx] - 2));
							board[ny][nx] = -1;
						}
					}
				}
			}
		}
		if(list.size() > 0) { // 데려갈 승객이 있는 경우
			Collections.sort(list, new Comparator<Position>() { // 거리, y, x 순으로 정렬
				@Override
				public int compare(Position o1, Position o2) {
					if(o1.cnt == o2.cnt) {
						if(o1.y == o2.y) {
							return Integer.compare(o1.x, o2.x);
						}
						return Integer.compare(o1.y, o2.y);
					}
					return Integer.compare(o1.cnt, o2.cnt);
				}
			});
			F -= list.get(0).cnt;
			starty = list.get(0).y; // 시작점을 승객 위치로 변경
			startx = list.get(0).x;
			person[list.get(0).index].p = false; // 태운 승객은 제외
			endy = person[list.get(0).index].ny; // 도착 위치를 승객의 목적지
			endx = person[list.get(0).index].nx;
			if(F < 1) { // 승객까지 가진 연료로 갈 수 없는 경우
				F = -1;
				return false;
			}else {  // 승객까지 갈 수 있는 경우
				return true;
			}
		}else { // 데려갈 수 있는 승객이 없는 경우
			F = -1;
			return false;
		}
	}
	
	static boolean movePerson() {
		if(findPerson()) { // 승객이 탑승한 경우
			int[][] board = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					board[i][j] = arr[i][j];
				}
			}
			board[starty][startx] = -1;
			Queue<int[]> queue = new LinkedList<>();
			queue.offer(new int[] {starty, startx, 0});
			while(!queue.isEmpty()) {
				int[] cur = queue.poll();
				for(int i = 0; i < 4; i++) {
					int ny = cur[0] + dy[i];
					int nx = cur[1] + dx[i];
					if(ny == endy && nx == endx) { // 목적지 도착한 경우
						cur[2] += 1; // 거리 1추가
						F -= cur[2]; // 일단 가진 연료로 갈 수 있는지 검사
						if(F < 0) { // 못 가는 경우
							F = - 1;
							return false;
						}else { // 갈 수 있는 경우 연료 추가
							F += 2 * cur[2];
							starty = endy;
							startx = endx;
							return true;
						}
					}
					if(0 <= ny && ny < N && 0 <= nx && nx < N && board[ny][nx] == 0) {
						queue.offer(new int[] {ny, nx, cur[2] + 1});
						board[ny][nx] = -1;
					}
				}
			}
		}
		F = - 1; // 승객이 남아있지만 태울 수 없는 경우 or 목적지까지 BFS를 하였지만 가지 못하는 경우
		return false;
	}
}	
