package testSuite;

import junit.framework.TestCase;
import model.command.*;

public class CommandStackTest extends TestCase {
	private final static String FIRST_WORD_REVERSABLE = "pick";
	private final static String FIRST_WORD_REVERSE = "drop";
	private final static String FIRST_WORD_GO = "go";
	private final static String FIRST_WORD_NONREVERSIBLE = "help";
	private final static String SECOND_WORD = "north";
	private final static String SECOND_WORD_REVERSE = "south";
	
	private CommandStack cs;
	private Command go;
	private Command pick;
	private Command help;
	private Command bad;
	
	protected void setUp() throws Exception {
		super.setUp();
		cs = new CommandStack();
		go = new Command(FIRST_WORD_GO,SECOND_WORD);
		pick = new Command(FIRST_WORD_REVERSABLE, null);
		help = new Command(FIRST_WORD_NONREVERSIBLE, null);
		bad = new Command(null,null);
	}

	public void testCommandStack() {
		Command temp;
		assertEquals(null,cs.pop());
		cs.add(go);
		temp = cs.pop();
		assertEquals(FIRST_WORD_GO, temp.getCommandWord());
		assertEquals(SECOND_WORD_REVERSE,temp.getSecondWord());
		
		cs.add(help);
		assertEquals(null,cs.pop());
		cs.add(pick);
		
		temp = cs.pop();
		assertEquals(FIRST_WORD_REVERSE, temp.getCommandWord());
		assertEquals(null,temp.getSecondWord());
		
		cs.add(pick);
		cs.add(go);
		cs.empty();
		assertEquals(null,cs.pop());
		cs.add(bad);
		assertEquals(null,cs.pop());
		cs.add(pick);
		cs.add(go);
		temp = cs.pop();
		assertEquals(FIRST_WORD_GO, temp.getCommandWord());
		assertEquals(SECOND_WORD_REVERSE,temp.getSecondWord());
		temp = cs.pop();
		assertEquals(FIRST_WORD_REVERSE, temp.getCommandWord());
		assertEquals(null,temp.getSecondWord());
		assertEquals(null,cs.pop());
	}

}
