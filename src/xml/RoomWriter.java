package xml;

import java.io.PrintWriter;
import java.util.Set;

import model.Room;

public class RoomWriter {
	private static final String START_ROOM = "<Room>";
	private static final String END_ROOM = "</Room>";
	private static final String START_NAME = "<Name>";
	private static final String END_NAME = "</Name>";

	public RoomWriter(Room room, PrintWriter out) {
		write(room, out);
	}

	@SuppressWarnings("unused")
	private void write(Room room, PrintWriter out) {
		out.println(START_ROOM);
		out.println(START_NAME + room.getDescription() + END_NAME);
		Set<String> keys = room.getWalls().keySet();
		for (String direction : keys) {
			if (room.getWall(direction).getExit() != null) {
				ExitWriter temp = new ExitWriter(room.getWall(direction)
						.getExit(), direction, out);
			}
			if (room.getWall(direction).getMonster() != null) {
				MonsterWriter temp = new MonsterWriter(room.getWall(direction)
						.getMonster(), direction, out);
			}
			if (room.getWall(direction).getItem() != null) {
				ItemWriter temp = new ItemWriter(room.getWall(direction)
						.getItem(), direction, out);
			}
		}
		out.println(END_ROOM);
	}
}
