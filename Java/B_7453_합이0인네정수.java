import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_7453_합이0인네정수 {
    
    static int n;
    static int[] A, B, C, D, AB, CD;
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        A = new int[n];
        B = new int[n];
        C = new int[n];
        D = new int[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }
        AB = new int[n * n];
        CD = new int[n * n];
        getSum();
        long result = 0L;
        Arrays.sort(AB);
        Arrays.sort(CD);
        int ABstart = 0, CDstart = (n * n) - 1;
        while(ABstart < n * n && CDstart > -1){
            int num = AB[ABstart] + CD[CDstart];
            if(num < 0){
                while(++ABstart < n * n && AB[ABstart] == AB[ABstart - 1]){

                }
            }else if(num > 0){
                while(--CDstart > -1 && CD[CDstart] == CD[CDstart + 1]){

                }
            }else{
                int ABcount = 1, CDcount = 1;
                while(ABstart + ABcount < n * n && AB[ABstart] == AB[ABstart + ABcount]){
                    ABcount++;
                }
                while(CDstart - CDcount > -1 && CD[CDstart] == CD[CDstart - CDcount]){
                    CDcount++;
                }
                result += (long)ABcount * (long)CDcount;
                ABstart += ABcount;
                CDcount -= CDcount;
            }
        }
        System.out.println(result);
    }

    static void getSum(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                AB[i * n + j] = A[i] + B[j];
                CD[i * n + j] = C[i] + D[j];
            }
        }
    }
}