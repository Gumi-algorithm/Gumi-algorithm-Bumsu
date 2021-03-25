import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_12738_가장긴증가하는부분수열3 {

	static int N, size;
	static int[] arr;
	static ArrayList<Integer> list;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		size = 1;
		list = new ArrayList<>();
		list.add(arr[0]);
		for(int i = 1; i < N; i++) {
			if(list.get(size - 1) < arr[i]) { // 맨 끝 값보다 크면 추가
				list.add(arr[i]);
				size += 1;
			}else { // 작으면 교체
				int idx = binarySearch(arr[i]);
				list.set(idx, arr[i]);
			}
		}
		System.out.println(size);
	}
	
	static int binarySearch(int x) { // 보다 큰 값 찾기 위한 BinarySearch
		int start = 0, end = size - 1;
		int res = 0;
		while(start <= end) {
			int mid = (start + end) / 2;
			int num = list.get(mid);
			if(num < x) {
				start = mid + 1;
			}else{
				end = mid - 1;
				res = mid;
			}
		}
		return res;
	}
}
