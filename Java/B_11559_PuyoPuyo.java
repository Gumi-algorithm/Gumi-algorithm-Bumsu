import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class location{ //위치 정보 저장용
	int y;
	int x;
	public location(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class B_11559_PuyoPuyo {
	
	static int result = 0; // 최종 연쇄 횟수
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static char[][] board = new char[12][6];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 12; i++) {
			String data = br.readLine();
			for(int j = 0; j < 6; j++) {
				board[i][j] = data.charAt(j);
			}
		}
		while(true) {
			int possible = 0; // 삭제가 가능한지 검사용
			for(int i = 11; i > 0; i--) { //모든 좌표 확인하여 삭제 가능한 곳 삭제
				for(int j = 0; j < 6; j++) {
					if(board[i][j] != '.') {
						possible += bfs(new location(i, j)); 
					}
				}
			}
			changeBoard(); // 중력에 따라 뿌요 이동
			if(possible == 0) { // 삭제 가능한 곳이 없다
				break; // 종료
			}else { // 있으면 연쇄 1 추가
				result += 1;
			}
		}
		System.out.println(result);
	}
	
	static void changeBoard() { 
		for(int i = 10; i >= 0; i--) { // 중력이기 때문에 밑에서 위로 검사
			for(int j = 0; j < 6; j++) {
				if(board[i + 1][j] == '.' && board[i][j] != '.') { 
					int y = i; // 한 칸 아래가 공백이고, 자기 자신이 공백이 아닐 때
					while(y < 11 && board[y + 1][j] == '.') { //뿌요 만날 때까지 반복
						y += 1;
					}
					board[y][j] = board[i][j]; // 자기 자신과 빈칸과 교체
					board[i][j] = '.';
				}
			}
		}
	}
	
	static int bfs(location start) {
		int[][] check = new int[12][6]; // 중복거르기 위한 배열
		int count = 1; // 뿌요가 4개 이상인지 확인용
		ArrayList<location> removeList = new ArrayList<>(); // 삭제한 위치 저장용
		char color = board[start.y][start.x]; // 색깔과 같아야 함으로 확인용
		removeList.add(start); // start를 추가
		Queue<location> queue = new LinkedList<>();
		check[start.y][start.x] = 1; 
		queue.offer(start);
		while(!queue.isEmpty()) {
			location next = queue.poll();
			for(int i = 0; i < 4; i++) {
				int ny = next.y + dy[i];
				int nx = next.x + dx[i];
				if(0 <= ny && ny < 12 && 0 <= nx && nx < 6 && check[ny][nx] == 0) {
					// board 범위 안이고, 갔던 곳이 아니고
					if(board[ny][nx] == color) { //색이 같으면
						queue.offer(new location(ny, nx));
						removeList.add(new location(ny, nx)); //삭제할 위치 저장
						check[ny][nx] = 1; // 갔던 곳으로 표시
						count += 1; // 갯수 추가
					}
				}
			}
		}
		if (count >= 4) { // 뿌요 4개 이상일 경우 삭제
			for(int i = 0; i < removeList.size(); i++) {
				board[removeList.get(i).y][removeList.get(i).x] = '.';
			}
			return 1;
		}return 0;
	}
}
