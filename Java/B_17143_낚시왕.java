import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Shark{
	
	boolean l = true; // 상어 죽은 여부
	int r, c, s, d, z;
	
	public Shark(int r, int c, int s, int d, int z) {
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
	}
}

public class B_17143_낚시왕 {

	static int R, C, M, cnt = 0;
	static int[] dy = {0, -1, 1, 0, 0}, dx = {0, 0, 0, 1, -1};
	static int[][] arr;
	static Shark[] shark;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[R + 1][C + 1];
		shark = new Shark[M];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r, c, s, d, z;
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			if(d == 1 || d == 2) {
				s %= 2 * (R - 1);
			}else {
				s %= 2 * (C - 1);
			}
			shark[i] = new Shark(r, c, s, d, z);
			arr[r][c] = i + 1;
		}
		
		for(int t = 1; t < C + 1; t++) {
			fishing(t);
			moveShark();
		}
		System.out.println(cnt);
	}
	
	static void fishing(int t) {
		for(int i = 1; i < R + 1; i++) {
			if(arr[i][t] != 0) {
				cnt += shark[arr[i][t] - 1].z;
				shark[arr[i][t] - 1].l = false;
				arr[i][t] = 0;
				break;
			}
		}
	}
	
	static void moveShark() {
		for(int i = 0; i < M; i++) {
			if(shark[i].l) {
				int d = shark[i].d;
				int s = shark[i].s;
				int nr = shark[i].r + dy[d] * s;
				int nc = shark[i].c + dx[d] * s;
				while(true) { 
//				1까지 도착했을 경우에 2 - nR
//				끝에 도착했을 땐 R - (nr - R)
					boolean p = true;
					if(d == 1 || d == 2) {
						if(nr < 1) {
							nr = 2 - nr;
						}else if(nr > R) {
							nr = R - (nr - R);
						}else {
							p = false;
						}
					}else {
						if(nc < 1) {
							nc = 2 - nc;
						}else if(nc > C) {
							nc = C - (nc - C);
						}else {
							p = false;
						}
					}
					if(p) {
						if(d % 2 == 0) {
							d -= 1;
						}else {
							d += 1;
						}
					}else {
						break;
					}
				}
				arr[shark[i].r][shark[i].c] = 0; // arr을 0으로 초기화
				shark[i].r = nr;
				shark[i].c = nc;
				shark[i].d = d;
			}
		}
		for(int i = 0; i < M; i++) {
			if(shark[i].l) {
				int y = shark[i].r;
				int x = shark[i].c;
				if(arr[y][x] != 0) {
					if(shark[arr[y][x] - 1].z < shark[i].z) { // 새로온 상어가 큰경우
						shark[arr[y][x] - 1].l = false; // 상어를 죽임
						arr[y][x] = i + 1; // 상어 변경
					}else {
						shark[i].l = false;
					}
				}else { // 빈 공간인 경우
					arr[y][x] = i + 1;
				}
			}
		}
	}
}
