import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_17472_다리만들기2 {
	
	static class Position{ // y, x 좌표 저장용
		int y, x;

		public Position(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static class Island{ // 섬들의 정보 저장
		int cnt = 0;
		ArrayList<Position> list = new ArrayList<>();
	}
	
	static class Node implements Comparable<Node>{
		int to, w;

		public Node(int to, int w) {
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
		
	}
	
	static int N, M, count; // 세로, 가로, 섬 갯수
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static int[][] arr;
	static ArrayList<Position> list; // 모든 섬의 위치 저장
	static Island[] island; // 섬의 배열
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 1) {
					list.add(new Position(i, j)); // 모든 섬의 위치 저장
				}
			}
		}
		island = new Island[7]; // 섬은 최대 6개이기 때문에 7로 선언
		divide();
		makeBridge();
	}
	
	static void divide() { // 섬 구분하기
		boolean[][] v = new boolean[N][M];
		int cnt = 1;
		for(int i = 0; i < list.size(); i++) { // 모든 섬 위치 반복
			Position now = list.get(i);
			if(!v[now.y][now.x]) { // 새로운 땅이 들어올 때 마다
				island[cnt] = new Island(); // 섬 배열 생성
				Queue<Position> q = new LinkedList<>();
				q.offer(new Position(now.y, now.x));
				arr[now.y][now.x] = cnt; 
				v[now.y][now.x] = true;
				while(!q.isEmpty()) { // BFS로 같은 땅은 찾기
					now = q.poll();
					island[cnt].cnt += 1; // 각각의 섬 배열에 섬 위치 저장
					island[cnt].list.add(new Position(now.y, now.x));
					for(int j = 0; j < 4; j++) {
						int ny = now.y + dy[j];
						int nx = now.x + dx[j];
						if(0 <= ny && ny < N && 0 <= nx && nx < M && !v[ny][nx]) {
							if(arr[ny][nx] == 1) {
								arr[ny][nx] = cnt; // 섬 번호 입력
								v[ny][nx] = true;
								q.offer(new Position(ny, nx));
							}
						}
					}
				}
				cnt += 1;
			}
		}
		count = cnt;
	}
	
	static void makeBridge() { // 다리 만들기
		int[][] len = new int[count][count]; // 섬 갯수 만큼 생성
		for(int i = 0; i < count; i++) { // 모든 섬끼리 위치를 최대 N * M으로 저장
			Arrays.fill(len[i], N * M);
		}
		for(int i = 1; i < count; i++) { // 1번 섬부터  모든 섬 탐색
			for(Position now : island[i].list) { // 1번 섬의 모든 위치 반복
				for(int j = 0; j < 4; j++) {
					int cnt = 0;
					int y = now.y + dy[j];
					int x = now.x + dx[j];
					while(0 <= y && y < N && 0 <= x && x < M) { // 모든 방향으로 한 번에 쭉 검사
						int idx = arr[y][x];
						if(idx > 0) {
							if(idx == i) { // 만약 섬 번호가 나와 같으면 break
								break;
							}else {
								if(cnt > 1) { // 거리가 1보다 큰 경우 그 중 최솟값 입력
									len[i][idx] = Math.min(len[i][idx], cnt);
									break;
								}else { // 2보다 작으면 break;
									break;
								}
							}
						}
						y += dy[j];
						x += dx[j];
						cnt += 1; // 섬과의 거리 1증가
					}
				}
			}
		}
		getLength(len);
	}
	
	static void getLength(int[][] len) { // Prim으로 MST 구하기
		boolean flag = false;
		int[] distance = new int[count];
		boolean[] v = new boolean[count];
		Arrays.fill(distance, N * M); // distance를 최대(아까 len 구할때 N*M을 사용)
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));
		int res = 0, cnt = 0;
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(v[now.to]) continue; // 거리가 짧아도 이미 간 곳은 계산 안함
			v[now.to] = true; // 안 온 곳이면 방문
			
			res += now.w; // 길이를 추가
			if(++cnt == count - 1) { // 이어진 섬 갯수가 총 갯수와 같으면
				flag = true; // 가능하다는 표시
				break; 
			}
			for(int i = 1; i < count; i++) {
				if(!v[i] && distance[i] > len[now.to][i]) { // 방문하지 않았고, 현재알고 있는 거리 보다 짧으면
					distance[i] = len[now.to][i]; // 거리 변경
					pq.offer(new Node(i, len[now.to][i]));
				}
			}
		}
		if(!flag) { // 불가능하면 -1
			res = -1;
		}
		System.out.println(res);
	}
}
