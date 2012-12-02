package xml;

import java.io.*;
import java.util.HashMap;
import java.util.Set;

import view.FirstPersonRoom;

public class XMLWriter {
	private final static String START_MAP = "<Map>";
	private final static String END_MAP = "</Map>";
	private final static String START_DEFAULT_ROOM = "<Start>";
	private final static String END_DEFAULT_ROOM = "</Start>";
	private final static String START_MAP_DIR = "<MapDir>";
	private final static String END_MAP_DIR = "</MapDir>";
	
	private String fileName;
	
	public XMLWriter(HashMap<String,FirstPersonRoom> rooms, String fileName, String start)
	{
		this.fileName = "src/xml/maps/"+fileName+".xml";
		writeMap(rooms,start);
	}
	
	private void writeMap(HashMap<String, FirstPersonRoom> rooms, String start)
	{
		try {
			FileWriter outFile = new FileWriter(fileName);
			PrintWriter out = new PrintWriter(outFile);
	        out.write(START_MAP);
	        
	        Set<String> keys = rooms.keySet();
			for (String room : keys) {
				RoomWriter rw = new RoomWriter(rooms.get(room), out);
			}
			out.write(START_DEFAULT_ROOM+start+END_DEFAULT_ROOM);
	        out.write(END_MAP);
	        out.close();
	    } catch (IOException e) {
	    	System.out.println("WriterError");
	    }
	}
}
