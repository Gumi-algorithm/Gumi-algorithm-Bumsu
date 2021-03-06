import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14888_연산자끼워넣기 {

	static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	static int[] num, oper;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		oper = new int[4];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < 4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}
		dfs(1, num[0]);
		System.out.println(max);
		System.out.println(min);
	}
	static int cal(int i, int num, int calNum) {
		if(i == 0) { //  0 -> +
			calNum += num;
		}else if(i == 1){ // 1 -> -
			calNum -= num;
		}else if(i == 2){ // 2 -> *
			calNum *= num;
		}else { // 3 -> /
			calNum /= num;
		}
		return calNum;
	}
	static void dfs(int index, int calNum) {
		if(index == N) {
			min = Math.min(calNum, min);
			max = Math.max(calNum, max);
			return ;
		}
		for(int i = 0; i < 4; i++) {
			if(oper[i] != 0) {
				oper[i] -= 1;
				calNum = cal(i, num[index], calNum);
				dfs(index + 1, calNum);
				if(i % 2 == 0) { // i가 짝수면 1더하면 반대 연산자가 됨
					calNum = cal(i + 1, num[index], calNum);
				}else { // i가 홀수면 1을 빼면 반대 연산자가 됨
					calNum = cal(i - 1, num[index], calNum);
				}
				oper[i] += 1;
			}
		}
	}
}