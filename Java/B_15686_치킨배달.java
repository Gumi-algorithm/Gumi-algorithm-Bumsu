import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_15686_치킨배달 {

	static int N, M, min = Integer.MAX_VALUE;
	static int[][] map;
	static ArrayList<int[]> house, store;
	static ArrayList<Integer> distance;
	static int[][] result;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		house = new ArrayList<>();
		store = new ArrayList<>();
		distance = new ArrayList<>();
		result = new int[M][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					store.add(new int[]{i, j});
				}else if(map[i][j] == 1) {
					house.add(new int[]{i, j});
				}
			}
		}
		combination(0, 0);
		System.out.println(min);
	}
	static int getDistance() {
		int res = 0;
		for(int i = 0; i < house.size(); i++) {
			int minDis = Integer.MAX_VALUE;
			for(int j = 0; j < M; j++) {
				int abs = Math.abs(house.get(i)[0] - result[j][0]) 
						+ Math.abs(house.get(i)[1] - result[j][1]); 
				minDis = Math.min(minDis, abs);
			}
			res += minDis;
		}
		return res;
	}
	static void combination(int index, int start) {
		if(index == M) {
			min = Math.min(min, getDistance());
			return;
		}
		for(int i = start; i < store.size(); i++) {
			result[index] = store.get(i);
			combination(index + 1, i + 1);
		}
	}
}