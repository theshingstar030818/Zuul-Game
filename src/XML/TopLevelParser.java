package XML;

import java.lang.reflect.Method;
import java.util.*;

import org.w3c.dom.Document;

public class TopLevelParser implements XMLParser{
	private List<XMLRoom> rooms;
	private Document doc;
	private Map<String, Method> methodMap;
	
	public TopLevelParser(Document doc) {
		this.doc = doc;
		rooms = new ArrayList<XMLRoom>();
		methodMap = new HashMap<String, Method>();
		initMethods();
		parse();
	}

	public void initMethods()
	{
		try {
			methodMap.put("Map", XMLParser.class.getMethod("parseMap"));
			methodMap.put("Room",XMLParser.class.getMethod("parseRoom"));
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void parseMap()
	{
		
	}
	
	public void parseRoom()
	{
		
	}

	@Override
	public void parse() {
		//doc.
	}
}
