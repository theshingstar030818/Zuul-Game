package xml;

import java.io.PrintWriter;

import model.Room;

public class ExitWriter {
	private static final String START_EXIT = "<Exit>";
	private static final String END_EXIT = "</Exit>";
	private static final String START_DIRECTION = "<Direction>";
	private static final String END_DIRECTION = "</Direction>";
	private static final String START_ROOM = "<Room>";
	private static final String END_ROOM = "</Room>";

	public ExitWriter(Room exit,String direction, PrintWriter out) {
		write(exit, direction, out);
	}

	private void write(Room exit, String direction, PrintWriter out) {
		out.println(START_EXIT);
		out.println(START_DIRECTION+direction+END_DIRECTION);
		out.println(START_ROOM+exit.getDescription()+END_ROOM);
		out.println(END_EXIT);
	}

}
