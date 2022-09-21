package ie.atu.sw;

public class Runner {
	
	// The entire program branches from this main function as
	// it creates an instance of menu and calls m.start.
	// Exceptions thrown to this method will print an application crashed message.
	public static void main(String[] args) throws Exception {
		try {
			Menu m = new Menu();
			m.start();
		} catch (Exception e) {
			System.out.println("ERROR: Application Failed");
			e.printStackTrace();
		}
	}
}