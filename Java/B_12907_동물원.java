import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_12907_동물원 {

	static int N;
	static int[] H;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		H = new int[41]; // 모든 동물의 대답을 담을 수 있는 배열
		for(int i = 0; i < N; i++) { 
			 H[Integer.parseInt(st.nextToken())] += 1; // 대답 갯수만큼 더해줌
		}
		int cnt = 1;
		boolean flag = true, check = true; // flag : 조합 가능 여부, check : 1로 끝나는가 2로 끝나는가
		for(int i = 0; i < 41; i++) { // 할 수 있는 모든 대답을 다 반복
			if(H[i] > 2) { // 만약 대답 횟수가 2가 넘으면 반복 종료
				flag = false;
				break;
			}else if(H[i] == 2){ // 대답 횟수가 2라면
				check = true; // 대답이 2면 true
				for(int j = 0; j < i; j++) { // 자기 자신보다 앞에 대답은 2여야 됨
					if(H[j] < 2) {
						flag = false;
						break;
					}
				}
				if(flag) { // 모든 대답 횟수가 2면 횟수 *2
					cnt *= 2;
				}else {
					break;
				}
			}else if(H[i] == 0){ // 대답 횟수가 0이면
				for(int j = i + 1; j < 41; j++) { // 그 대답 뒤에는 모두 0이여야 됨
					if(H[j] > 0) {
						flag = false;
						break;
					}
				}
				break;
			}else if(H[i] == 1) check = false; // 대답이 1이면 check를 false
		}
		if(flag) { // 조합이 가능하면
			if(check) { // 대답이 2로 끝나면
				System.out.println(cnt); // 조합이 모두 끝난 상태
			}else {
				System.out.println(cnt * 2); // 토끼와 고양이를 바꿔서 조합하면 *2 해야됨
			}
		}else {
			System.out.println(0);
		}
	}
}
