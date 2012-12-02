package xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import model.Game;

import org.w3c.dom.Document;
import org.w3c.dom.Node;


public class XMLReader {
	private static final String MAP = "Map";
	
	private String file;
//	private TopLevelParser topLevel;
	
	public XMLReader(String file, Game g)
	{
		this.file = file;
		try {
			readDOM(new File(this.file), g);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void readDOM(File f, Game g) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder d = factory.newDocumentBuilder();
		Document doc = d.parse(f);
		//topLevel = new TopLevelParser(doc);
		
		if(doc.getDocumentElement().getNodeName()==MAP)
		{
			Node n = doc.getDocumentElement();
			TopLevelParser t = new TopLevelParser(n, g);
		}

	}

}
