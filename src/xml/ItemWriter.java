package xml;

import java.io.PrintWriter;

import model.object.Item;
import view.FirstPersonItem;


public class ItemWriter {
	private static final String START_ITEM = "<Item>";
	private static final String END_ITEM = "</Item>";
	private static final String START_NAME = "<Name>";
	private static final String END_NAME = "</Name>";
	private static final String START_WEIGHT = "<Weight>";
	private static final String END_WEIGHT = "</Weight>";
	private static final String START_IMG = "<Img>";
	private static final String END_IMG = "</Img>";
	private static final String START_WALL = "<Wall>";
	private static final String END_WALL = "</Wall>";


	public ItemWriter(Item i,String direction, PrintWriter out) {
		write(i,direction, out);
	}

	private void write(Item i,String d, PrintWriter out) {
		out.println(START_ITEM);
		out.println(START_NAME+i.getItemName()+END_NAME);
		out.println(START_WEIGHT+i.getItemWeight()+END_WEIGHT);
		out.println(START_IMG+((FirstPersonItem)i).getImage()+END_IMG);
		out.println(START_WALL+d+END_WALL);
		out.println(END_ITEM);
	}
}
