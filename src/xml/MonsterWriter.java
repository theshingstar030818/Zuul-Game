package xml;

import java.io.PrintWriter;

import model.object.Monster;
import view.FirstPersonMonster;

public class MonsterWriter {
	private static final String START_MONSTER = "<Monster>";
	private static final String END_MONSTER = "</Monster>";
	private static final String START_NAME = "<Name>";
	private static final String END_NAME = "</Name>";
	private static final String START_HEALTH = "<Health>";
	private static final String END_HEALTH = "</Health>";
	private static final String START_IMG = "<Img>";
	private static final String END_IMG = "</Img>";
	private static final String START_WALL = "<Wall>";
	private static final String END_WALL = "</Wall>";

	public MonsterWriter(Monster m,String direction, PrintWriter out) {
		write(m,direction, out);
	}

	private void write(Monster m,String d, PrintWriter out) {
		out.println(START_MONSTER);
		out.println(START_NAME+m.getName()+END_NAME);
		out.println(START_HEALTH+m.getHealth()+END_HEALTH);
		out.println(START_IMG+((FirstPersonMonster)m).getImage()+END_IMG);
		out.println(START_WALL+d+END_WALL);
		out.println(END_MONSTER);
	}
	
}
