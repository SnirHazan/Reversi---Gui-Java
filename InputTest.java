package myapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public  final class InputTest {

	private InputTest() { // private constructor
	}
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static int get_number_from_user() {
		int num;
		while(true){
			try{
				num = Integer.parseInt(br.readLine());
				break;
			}catch (NumberFormatException e) {
				System.out.println("Please enter numbers only.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return num;
	}
	public static void press_any_key_to_continue() {
		System.out.println("Press Enter key to continue...");
		try
		{
			br.readLine();
		}  
		catch(Exception e) {
			System.out.println("some problem");
		}  

	}
	public static int get_size_from_user(){
		int size = 0;
		boolean flag = false;

		do{
			if ( flag == true) {
				System.out.println("not good - try again.");
			}
			System.out.println("choose a board size (2 < size < 14 and even):");

			size = get_number_from_user();
			flag = true;
		}
		while (size <4 || size >12 || size%2 !=0);

		return size;
	}
	public static int get_index_from_user(int max_index) {
		int index = 0;
		boolean flag = false;

		do{
			if (flag == true) {
				System.out.println("not good - out of boundary");
			}
			System.out.println("(1<= index <=" + max_index + "): ");
			index = get_number_from_user();
			flag = true;
		}
		while (index <1 || index > max_index );

		return index;
	}


}
