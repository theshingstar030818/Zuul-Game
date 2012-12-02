package xml;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;


import javax.xml.parsers.*;

import model.Game;
import model.Room;

import org.w3c.dom.*;


public class XMLReader {
	private static final String MAP = "Map";
	
	private String file;
	private TopLevelParser topLevel;
	
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
