import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_6987_¿ùµåÄÅ {

	static int check;
	static int[][] arr, res, team;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		arr = new int[6][3];
		res = new int[6][3];
		team = new int[15][2];
		makeTeam();
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			check = 0;
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 3; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			dfs(0);
			sb.append(check + " ");
		}
		System.out.println(sb);
	}

	static void makeTeam() {
		int index = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = i + 1; j < 6; j++) {
				team[index][0] = i;
				team[index][1] = j;
				index += 1;
			}
		}
	}

	static void dfs(int index) {
		if (index == 15) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					if (arr[i][j] != res[i][j]) {
						return;
					}
				}
			}
			check = 1;
			return;
		}
		int me = team[index][0];
		int you = team[index][1];
		// ½Â
		res[me][0] += 1;
		res[you][2] += 1;
		dfs(index + 1);
		res[me][0] -= 1;
		res[you][2] -= 1;
		// ÆÐ
		res[me][2] += 1;
		res[you][0] += 1;
		dfs(index + 1);
		res[me][2] -= 1;
		res[you][0] -= 1;
		// ¹«
		res[me][1] += 1;
		res[you][1] += 1;
		dfs(index + 1);
		res[me][1] -= 1;
		res[you][1] -= 1;
	}
}
