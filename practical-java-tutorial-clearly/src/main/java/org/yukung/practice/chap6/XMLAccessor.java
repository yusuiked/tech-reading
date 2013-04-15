package org.yukung.practice.chap6;

import java.io.File;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;


public class XMLAccessor {

	public void read(File xml) {
		try {
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xml);
			Element root = document.getDocumentElement();
			NodeList itemList = root.getElementsByTagName("item");
			for (int i = 0; i < itemList.getLength(); i++) {
				Node item = itemList.item(i);
				NodeList children = item.getChildNodes();
				String name = null;
				String price = null;
				for (int j = 0; j < children.getLength(); j++) {
					Node child = children.item(j);
					if (child.getNodeName().equals("name")) {
						name = child.getFirstChild().getNodeValue().trim();
					} else if (child.getNodeName().equals("price")) {
						price = child.getFirstChild().getNodeValue().trim();
					}
				}
				System.out.println(name + ":" + price + "円");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void write() {
		try {
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element foo = document.createElement("foo");
			Element bar = document.createElement("bar");
			Text baz = document.createTextNode("baz");
			bar.appendChild(baz);
			foo.appendChild(bar);
			document.appendChild(foo);

			StringWriter sw = new StringWriter();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(sw);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();

			transformer.transform(source, result);

			System.out.println(sw.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new XMLAccessor().read(new File("src/main/resources/org/yukung/practice/chap6/list.xml"));
		new XMLAccessor().write();
	}
}
