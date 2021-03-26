public class KAKAO합승택시요금 {

	public int solution(int n, int s, int a, int b, int[][] fares) {
		final int INF = 1000001;
        int answer = Integer.MAX_VALUE;
        
        int[][] arr = new int[n][n];
        for(int i = 0; i < n; i++) {
        	for(int j = 0; j < n; j++) {
        		if(i != j) {
        			arr[i][j] = INF;
        		}
        	}
        }
        for(int i = 0; i < fares.length; i++) { // 양방향이기 때문에 모두 표시
        	arr[fares[i][0] - 1][fares[i][1] - 1] = fares[i][2];
        	arr[fares[i][1] - 1][fares[i][0] - 1] = fares[i][2];
        }
        
        for(int i = 0; i < n; i++) { // 플로이드-와샬 알고리즘 사용
        	for(int j = 0; j < n; j++) {
        		for(int k = 0; k < n; k++) {
        			arr[j][k] = Math.min(arr[j][k], arr[j][i] + arr[i][k]);
        		}
        	}
        }
        for(int i = 0; i < n; i++) { // 모든 지역 검사
    		int val = arr[s - 1][i] + arr[i][a - 1] + arr[i][b - 1];
    		answer = Math.min(answer, val);
        }
        return answer;
    }
}
