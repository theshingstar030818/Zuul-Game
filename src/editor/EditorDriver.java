package editor;

import editor.controller.EditorMouseListener;
import editor.model.LevelEditor;
import editor.view.EditorView;


public class EditorDriver {
	
	private static int maxX = 5;
	private static int maxY = 5;
	
	private static int health = 20;
	private static int weight = 20;
	
	private static EditorMouseListener mouseListener;
	private static EditorView view;
	private static LevelEditor model;
	
	public EditorDriver() {
		mouseListener = new EditorMouseListener();
		view = new EditorView("Zuul Level Editor",maxX,maxY,mouseListener);
		model = new LevelEditor(maxX, maxY, health, weight);
		
		mouseListener.addObserver(model);
		model.addObserver(view);
		view.addObserver(model);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EditorDriver driver = new EditorDriver();
		driver.show();
	}
	
	public void show() {
		view.show();
	}

}
