package editor;

import java.util.HashMap;

import view.FirstPersonRoom;

import model.Room;

public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EditorMouseListener mouseListener = new EditorMouseListener();
		
		EditorView editor = new EditorView("Zuul Level Editor",5,5,mouseListener);
		mouseListener.addObserver(editor);
		
		String[][] roomsArray = new String[5][5];
		HashMap<String, Room> rooms = new HashMap<String, Room>();
		
		FirstPersonRoom room = new FirstPersonRoom("Test");
		roomsArray[1][1] = "Test";
		rooms.put("Test", room);
		
		EditorUpdateObject test = new EditorUpdateObject(roomsArray, rooms, 0, 0);
		
		editor.update(null, test);
		
		editor.show();
	}

}
