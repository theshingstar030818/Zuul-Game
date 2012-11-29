package XML;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;


import javax.xml.parsers.*;

import org.w3c.dom.*;


public class XMLParser {
	Map<String, Method> methodMap;
	
	public XMLParser()
	{
		methodMap = new HashMap<String, Method>();
		initMethodMap();
	}
	
	private void initMethodMap()
	{
		
	}
	
	public static void readDOM(File f) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder d = factory.newDocumentBuilder();
		Document doc = d.parse(f);
		System.out.println ("Root: " + doc.getDocumentElement().getNodeName());
		NodeList lst = doc.getDocumentElement().getChildNodes();
		
		for(int ii=0; ii<lst.getLength();ii++){
			Node n = lst.item(ii);
			System.out.println("Child: " + n.getNodeName() + " --> " +
			n.getTextContent());
		}
	}

}
