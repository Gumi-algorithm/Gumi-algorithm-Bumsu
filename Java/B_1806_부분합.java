import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1806_부분합 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int start = 0, end = 0, val = arr[0], len = N + 1;
		while(start < N) {
			if(val >= S) { // 현재 값이 S보다 큰 경우
				len = Math.min(len, end - start + 1); // 길이를 저장
				val -= arr[start]; // start를 한 칸 뒤로
				start += 1; 
			}else { // 값이 S보다 작은 경우
				if(end == N - 1) { // end가 맨 끝이면 종료
					break;
				}else { 
					end += 1; // end를 한 칸 뒤로
					val += arr[end];
				}
			}
		}
        if(len == N + 1){ // 만족하는 값이 하나도 없을 경우
            len = 0; // 0으로 설정
        }
		System.out.println(len);
	}
}

