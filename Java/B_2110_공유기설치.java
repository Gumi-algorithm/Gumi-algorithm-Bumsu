import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_2110_공유기설치 {

	static int N, C;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		int start = 1, end = arr[N - 1] - arr[0], res = 0;
		// start는 최소거리는 1임(0으로 해서 틀림)
		// end는 최대 거리 (arr의 가장 큰 값 - arr의 가장 작은 값)
		while(start <= end) {
			int mid = (start + end) / 2;
			
			int cnt = 1, last = arr[0]; // 맨 앞에 설치
			for(int i = 1; i < N; i++) {
				if(arr[i] - last >= mid) { // 공유기 설치한 집보다 하려는 간격이 멀면 설치
					cnt += 1;
					last = arr[i];
				}
			}
			if(cnt < C) { // 설치 갯수가 C보다 작으면 값을 줄여서 확인
				end = mid - 1;
			}else { // 더 많이 설치되거나 같으면 값 저장
				res = mid;
				start = mid + 1;
			}
		}
		System.out.println(res);
	}
}
