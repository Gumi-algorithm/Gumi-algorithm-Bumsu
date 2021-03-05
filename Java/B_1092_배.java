import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_10921_배 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Integer[] crain = new Integer[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			crain[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		Integer[] box = new Integer[M];
		for(int i = 0; i < M; i++) {
			box[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(crain, (o1, o2) -> o2 - o1);
		Arrays.sort(box, (o1, o2) -> o2 - o1); // 정렬
		int time = 0;
		int cnt = 0;
		if(box[0] > crain[0]) { // 가장 큰 박스가 가장 큰 크레인으로 보다 크면 -1 출력
			time = -1;
		}
		else {
			boolean[] crainCheck = new boolean[N];
			boolean[] boxCheck = new boolean[M];
			while(true) {
				for(int i = 0; i < N; i++) {
					if(!crainCheck[i]) { // 해당 크레인으로 옮길 수 있는 박스가 있으면
						for(int j = 0; j < M; j++) { 
							if(!boxCheck[j] && crain[i] >= box[j]) { // 박스 중 가장 큰 거 이동
								cnt += 1;
								boxCheck[j] = true;
								break;
							}
							if(j == M - 1) { //맨 마지막까지 검사했는데 없으면 크레인 사용 X
								crainCheck[i] = true;
							}
						}
						
					}
				}
				time += 1;
				if(cnt == M) { // 옮긴 박스 수랑 총 박스 수가 같으면 탈출
					break;
				}
			}
		}
		System.out.println(time);
	}
}