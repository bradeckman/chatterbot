import java.util.*;

/**
 * Represents an interactive Bot. A ChatterBot can process input and converse with
 * another agent (a user, another ChatterBot, etc.). The ChatterBot only 'understands'
 * statements starting with REQUEST_PREFIX and therefore replies positively to them,
 * whereas any other statement is not understandable.
 */
class ChatterBot {

	/**
	 * The prefix of a legal statement.
	 */
	static final String REQUEST_PREFIX = "say ";
	/**
	 * Placeholders for ChatterBot reply templates.
	 */
	static final String REQUESTED_PHRASE_PLACEHOLDER = "<phrase>";
	static final String ILLEGAL_REQUEST_PLACEHOLDER = "<request>";

	/* Members */
	Random rand = new Random();
	String name;
	String[] repliesToLegalRequest;
	String[] repliesToIllegalRequest;

	/**
	 * Constructor.
	 * @param name The name of the ChatterBot.
	 * @param repliesToLegalRequest A list of legal reply templates (used for statements starting with
	 *                              REQUEST_PREFIX).
	 * @param repliesToIllegalRequest A list of legal reply templates (used for statements starting without
	 * 	 *                            REQUEST_PREFIX).
	 */
	ChatterBot(String name, String[] repliesToLegalRequest, String[] repliesToIllegalRequest) {
		this.name = name;
		this.repliesToLegalRequest = new String[repliesToLegalRequest.length];
		this.repliesToIllegalRequest = new String[repliesToIllegalRequest.length];

		/* Deep copy lists */
		for(int i = 0 ; i < repliesToIllegalRequest.length ; i = i+1) {
			this.repliesToIllegalRequest[i] = repliesToIllegalRequest[i];
		}
		for(int i = 0 ; i < repliesToLegalRequest.length ; i = i+1) {
			this.repliesToLegalRequest[i] = repliesToLegalRequest[i];
		}
	}

	/**
	 * Returns the name of the ChatterBot.
	 * @return The ChatterBot's name.
	 */
	String getName() {
		return this.name;
	}

	/**
	 * Generates a reply to a given statement.
	 * @param statement The statement being said towards the ChatterBot.
	 * @return A String representing the ChatterBot's reply to the statement.
	 */
	String replyTo(String statement) {
		if(statement.startsWith(REQUEST_PREFIX)) {
			// we don’t repeat the request prefix, so delete it from the reply
			statement = statement.replaceFirst(REQUEST_PREFIX, "");
			return replacePlaceholderInARandomPattern(repliesToLegalRequest, REQUESTED_PHRASE_PLACEHOLDER,
					statement);
		}
		return replacePlaceholderInARandomPattern(repliesToIllegalRequest, ILLEGAL_REQUEST_PLACEHOLDER,
				statement);
	}

	/**
	 * Selects a random reply template and formats it with the given statement.
	 * @param replies A list of possible reply templates to select from at random.
	 * @param placeholder The String used in replies as a placeholder for statement.
	 * @param statement The statement to format into the reply template.
	 * @return A formatted reply template using the supplied statement.
	 */
	String replacePlaceholderInARandomPattern(String[] replies, String placeholder, String statement) {
		int randomIndex = this.rand.nextInt(replies.length);
		String phrase = replies[randomIndex];
		phrase = phrase.replaceAll(placeholder, statement);
		return phrase;
	}
}
