package testSuite;

import junit.framework.TestCase;
import model.command.Command;

public class TestCommand extends TestCase {
	private static final String GOOD_FIRST_WORD = "go";
	private static final String BAD_FIRST_WORD = "asdjk";
	private static final String GOOD_SECOND_WORD = "north";
	private static final String NO_SECOND_WORD = null;

	private Command goodFirst;
	private Command goodSecond;
	private Command badFirst;

	protected void setUp() throws Exception {
		super.setUp();
		goodFirst = new Command(GOOD_FIRST_WORD, NO_SECOND_WORD);
		goodSecond = new Command(GOOD_FIRST_WORD, GOOD_SECOND_WORD);
		badFirst = new Command(BAD_FIRST_WORD, NO_SECOND_WORD);
	}

	public void testGetCommandWord() {
		assertEquals(GOOD_FIRST_WORD, goodFirst.getCommandWord());
		assertEquals(BAD_FIRST_WORD, badFirst.getCommandWord());
	}

	public void testGetSecondWord() {
		assertEquals(NO_SECOND_WORD, goodFirst.getSecondWord());
		assertEquals(GOOD_SECOND_WORD, goodSecond.getSecondWord());
	}

	public void testHasSecondWord() {
		assertEquals(false, goodFirst.hasSecondWord());
		assertEquals(true, goodSecond.hasSecondWord());
	}

}
