package Client;

public class MattClientProtocol {
	private boolean toRequest = true;
	private String programId;

	public MattClientProtocol(String programId) {
		this.programId = programId;
	}

	public boolean canRequest() {
		return toRequest;
	}

	public String sendIWannaDance() {
		toRequest = false;
		return "I want to dance";
	}

	public void allowRequest() {
		toRequest = true;
	}

	public String handleMessages(String input) {
		if (input.charAt(0) == 'D' && input.equals("Dancer: " + programId)) {
			toRequest = true;
			return "I'm dancing centerstage!!";
		} else if (input.charAt(0) == 'D')
			return input + " is currently dancing!!";
		else
			return "YAA WHEN WILL IT BE MY TURN?!?!?!";
	}
}
