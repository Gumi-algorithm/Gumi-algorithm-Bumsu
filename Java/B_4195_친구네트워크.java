import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Name{
	int cnt = 1;
	String parent;
	
	public Name(String parent) {
		this.parent = parent;
	}
}

public class B_4195_친구네트워크 {

	static int F;
	static HashMap<String, Name> map;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			F = Integer.parseInt(br.readLine());
			map = new HashMap<>();
			for(int i = 0; i < F; i++) {
				String[] name = br.readLine().split(" ");
				for(int j = 0; j < 2; j++) {
					if(!map.containsKey(name[j])) {
						map.put(name[j], new Name(name[j]));
					}
				}
				union(name[0], name[1]);
				sb.append(check(name[0], name[1]) + "\n");
			}
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static String find(String name) {
		if(map.get(name).parent.equals(name)) return name;
		return map.get(name).parent = find(map.get(name).parent);
	}
	
	static void union(String fir, String sec) {
		fir = find(fir);
		sec = find(sec);
		if(fir.equals(sec)){
			return ;
		}
		map.get(sec).cnt += map.get(fir).cnt; // 대표자에게 총 집합 수를 더해줌
		map.get(fir).parent = map.get(sec).parent;
	}
	
	static int check(String fir, String sec) {
		return Math.max(map.get(find(fir)).cnt, map.get(find(sec)).cnt); 
		
	}
}
