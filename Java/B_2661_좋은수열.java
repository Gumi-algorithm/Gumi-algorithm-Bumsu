import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_2661_좋은수열 {
	
	static int N;
	static boolean p = true;
	static int[] number;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		number = new int[N];
		dfs(0);
		System.out.println(sb);
	}
	
	static void dfs(int index) {
		
		if(index == N) {
			for(int x : number) sb.append(x);
			p = false; // 가장 작은 수를 만들면 이제 그만 만들기
			return;
		}
		for(int i = 1; i < 4; i++) {
			number[index] = i;
			if(isPossible(number, index) && p) {
				dfs(index + 1);
			}
		}
	}
	
	static boolean isPossible(int[] num, int index) {
		int len = (index + 1) / 2; // 적은 수열의 마지막의 반(반복이 되려면 반만 까지만 검사하면 됨)
		int last = index; // 수열의 길이
		for(int i = 1; i < len + 1; i++) { // 1글자 ~ 반글자만 검사
			int cnt = 0;
			for(int j = 0; j < i; j++) { // 글자를 검사
				if(num[last - j] != num[last - j - i]) { // 다르면 종료 후 글자 추가해서 검사
					break;
				}else { // 같은 글자면 cnt를 1더함
					cnt += 1;
				}
			}
			if(cnt == i) { // cnt와 글자 검사 갯수가 같으면 같은 수열이 존재
				return false;
			}
		}
		return true;
	}
}
