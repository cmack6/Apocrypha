public class User extends Actor{

	private String password;
	private String username;
	/* public statistic score */
	
	public String select() {
		Scanner input = new Scanner();
		String select = input.next();
		input.close();
		return select;
	}
	
}