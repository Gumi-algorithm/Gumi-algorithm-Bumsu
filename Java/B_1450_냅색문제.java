import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class B_1450_냅색문제 {
	
	static int N, C;
	static int[] A, B;
	static ArrayList<Long> Alist, Blist;
	
	public static void main(String[] args) throws IOException {
//		Meet in the middle알고리즘 (부분집합을 반 쪼개어 계산)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		A = new int[N / 2];
		B = new int[N - A.length];
		Alist = new ArrayList<>();
		Blist = new ArrayList<>();
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			if(i < N / 2) {	
				A[i] = Integer.parseInt(st.nextToken());
			}else {
				B[i - (N / 2)] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0;
		dfs(0, A, 0, true);
		dfs(0, B, 0, false);
		Collections.sort(Blist);
		int end = Blist.size(); // Blist의 갯수
		Long max = Blist.get(end - 1); // Blist의 최댓값
		for(int i = 0; i < Alist.size(); i++) {
			long target = C - Alist.get(i); // 할 수 있는 무게
			if(target >= 0 && target < max ) {
				cnt += upperBound(end - 1, target); // 가능한 무게의 갯수
			}else if(target >= max) { // Blist의 최댓값보다 여유분이 큰 경우
				cnt += end;  // 모두 넣음
			}
		}
		System.out.println(cnt);
		
	}
	static int upperBound(int end, long target) { // UpperBound (다시 봐야할 듯..)
		int start = 0, mid = 0;
		while(start < end) {
			
			mid = (start + end) / 2;
			if(Blist.get(mid) > target) {
				end = mid;
			}else {
				start = mid + 1;
			}
		}
		return end; // target을 초과하기 바로 직전 idx 반환
	}
	
	static void dfs(int index, int[] group, long sum, boolean flag) {
		if(index == group.length) {
			if(sum <= C) {
				if(flag) Alist.add(sum);
				else Blist.add(sum);
			}
			return;
		}
		dfs(index + 1, group, sum, flag);
		dfs(index + 1, group, sum + group[index], flag);
	}
}
