package editor;


public class Driver {
	
	private static int maxX = 5;
	private static int maxY = 5;
	
	private static int health = 20;
	private static int weight = 20;
	
	private static EditorMouseListener mouseListener;
	private static EditorView view;
	private static LevelEditor model;
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		mouseListener = new EditorMouseListener();
		view = new EditorView("Zuul Level Editor",maxX,maxY,mouseListener);
		model = new LevelEditor(maxX, maxY, health, weight);
		
		mouseListener.addObserver(model);
		model.addObserver(view);
		view.addObserver(model);
		
		view.show();
	}

}
