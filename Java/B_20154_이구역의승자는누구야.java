import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class B_20154_이구역의승자는누구야 {
	
	static int[] num = {3, 2, 1, 2, 3, 3, 3, 3, 1, 1, 3, 1, 3, 3, 1, 2, 2, 2, 1, 2, 1, 1, 2, 2, 2, 1};
	static HashMap<Character, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Character start = 'A';
		for(int i = 0; i < 26; i++) {
			map.put((char)(start + i), num[i]);
		}
		String text = br.readLine();
		int sum = 0;
		for(int i = 0; i < text.length(); i++) {
			sum += map.get(text.charAt(i));
		}
		System.out.println(sum % 2 == 1 ? "I'm a winner!" : "You're the winner?");
	}
}
