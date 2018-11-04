package hubbert;

public class Afinal {
	
	public static void recMeth(int arg){
		if(arg < 40){
			System.out.println( arg + " ");
			return;
		} else{
			recMeth((arg / 2) + 10);
			System.out.println( arg + " ");
			return;
		}
	}

	public static void main(String[] args) {
//		recMeth(110);
		
		System.out.println((4444 % 137) + 1);

	}

}
