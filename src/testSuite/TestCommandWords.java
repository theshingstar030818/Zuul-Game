package testSuite;

import model.command.Command;
import model.command.CommandWords;
import junit.framework.TestCase;

public class TestCommandWords extends TestCase {
	private static final String NON_REVERSIBLE = "help";
	private static final String REVERSIBLE = "pick";
	private static final String REVERSE = "drop";
	private static final String BAD = "fly";
	
	private CommandWords cw;
	
	protected void setUp() throws Exception {
		super.setUp();
		cw = new CommandWords();
	}

	public void testIsCommand() {
		assertEquals(true,cw.isCommand(NON_REVERSIBLE));
		assertEquals(true,cw.isCommand(REVERSIBLE));
		assertEquals(true,cw.isCommand(REVERSE));
		assertEquals(false,cw.isCommand(BAD));
	}

	public void testIsReversible() {
		assertEquals(false,cw.isReversible(NON_REVERSIBLE));
		assertEquals(true,cw.isReversible(REVERSIBLE));
		assertEquals(true,cw.isReversible(REVERSE));
		assertEquals(false,cw.isReversible(BAD));
	}

	public void testGetReverse() {
		assertEquals(null,cw.getReverse(new Command(NON_REVERSIBLE,null)));
		assertEquals(new Command(REVERSE,null).getCommandWord(),cw.getReverse(new Command(REVERSIBLE,null)).getCommandWord());
		assertEquals(new Command(REVERSIBLE,null).getCommandWord(),cw.getReverse(new Command(REVERSE,null)).getCommandWord());
		assertEquals(null,cw.getReverse(new Command(BAD,null)));
	}

}
