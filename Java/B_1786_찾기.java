import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B_1786_찾기 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String t = br.readLine();
		String w = br.readLine();
		ArrayList<Integer> list = new ArrayList<>();
		
		int j = 0, lenWord = w.length(), cnt = 0;
		char[] text = t.toCharArray();
		char[] word = w.toCharArray();
		int[] fail = makeFail(word);
		for(int i = 0; i < text.length; i++) {
			
			while(j > 0 && text[i] != word[j]) { // j가 양수고, 글자가 다르면
				j = fail[j - 1]; // j를 실패함수로 변경
			}
			if(text[i] == word[j]) { // 같으면
				if(j == lenWord - 1) { // 찾는 단어가 끝이면
					cnt += 1; // 총 갯수 + 1
					list.add(i - j + 1); // 위치 저장
					j = fail[j]; // j를 fail함수의 위치로 보냄
				}else { // 찾는 단어의 끝이 아니면 j + 1
					j += 1;
				}
			}
		}
		sb.append(cnt + "\n");
		for(int x : list) sb.append(x + " ");
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static int[] makeFail(char[] word) { // 실패함수 만들기
		int size = word.length;
		int j = 0;
		int[] fail = new int[size];
		
		for(int i = 1; i < size; i++) {
			while(j > 0 && word[i] != word[j]) {
				j = fail[j - 1];
			}
			if(word[i] == word[j]) {
				j += 1;
				fail[i] = j;
			}
		}
		return fail;
	}
}
