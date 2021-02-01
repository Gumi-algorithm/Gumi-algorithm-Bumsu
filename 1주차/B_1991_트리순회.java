import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class B_1991_트리순회{
	
	static HashMap<String, ArrayList<String>> tree = new HashMap<>();
	
	private static void preorder(String v) { // 전위
		
		if (v.equals(".")) {
			return ;
		}
		System.out.print(v);
		preorder(tree.get(v).get(0));
		preorder(tree.get(v).get(1));
	}
	
	private static void inorder(String v) { // 중위
		
		if (v.equals(".")) {
			return ;
		}
		inorder(tree.get(v).get(0));
		System.out.print(v);
		inorder(tree.get(v).get(1));
	}
	
	private static void postorder(String v) { // 후위
		
		if (v.equals(".")) {
			return ;
		}
		postorder(tree.get(v).get(0));
		postorder(tree.get(v).get(1));
		System.out.print(v);
	}
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			String[] treeSet = br.readLine().split(" ");
			ArrayList<String> list = new ArrayList<>();
			list.add(treeSet[1]);
			list.add(treeSet[2]);
			tree.put(treeSet[0], list);
		}
		preorder("A");
		System.out.println();
		inorder("A");
		System.out.println();
		postorder("A");
	}
}