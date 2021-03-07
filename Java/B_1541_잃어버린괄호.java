import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B_1541_잃어버린괄호 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String expr = br.readLine();
		ArrayList<Integer> num = new ArrayList<>();
		ArrayList<Character> oper = new ArrayList<>();
		int size = expr.length();
		int number = 0;
		for(int i = 0; i < size; i++) {
			if(expr.charAt(i) != '+' && expr.charAt(i) != '-' ){
				number *= 10;
				number += expr.charAt(i) - '0';
			}else {
				oper.add(expr.charAt(i));
				num.add(number);
				number = 0;
			}
		}
		num.add(number);
		int res = num.get(0);
		boolean flag = false;
		for(int i = 0; i < oper.size(); i++) {
			if(!flag) {
				if(oper.get(i) == '+') {
					res += num.get(i + 1);
				}else {
					flag = true;
					res -= num.get(i + 1);
				}
			}else {
				res -= num.get(i + 1);
			}
		}
		System.out.println(res);
	}
}
