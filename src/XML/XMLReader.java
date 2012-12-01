package XML;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;


import javax.xml.parsers.*;

import model.Room;

import org.w3c.dom.*;


public class XMLReader {
	private String file;
	private TopLevelParser topLevel;
	
	public XMLReader(String file)
	{
		this.file = file;
		try {
			readDOM(new File(this.file));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void readDOM(File f) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder d = factory.newDocumentBuilder();
		Document doc = d.parse(f);
		topLevel = new TopLevelParser(doc);
	}

}
