import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateDom {
    public static void main(String[] args) throws Exception {
           
           DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
           DocumentBuilder builder = factory.newDocumentBuilder();
           DOMImplementation domImpl = builder.getDOMImplementation();

           Document doc = domImpl.createDocument("", "Products", null);
           Element products = doc.getDocumentElement();

           Element product = doc.createElement("Product");
           product.setAttribute("type", "regular");

           Element name = doc.createElement("Name");
           name.appendChild(doc.createTextNode("Instant Noodle"));
           product.appendChild(name);

           Element price = doc.createElement("Price");
           price.appendChild(doc.createTextNode("147"));
           product.appendChild(price);

           products.appendChild(product);

           TransformerFactory transFactory = TransformerFactory.newInstance();
           Transformer transformer = transFactory.newTransformer();

           DOMSource source = new DOMSource(doc);
           // StreamResult result = new StreamResult(System.out);
           File newXML = new File("newXML.xml");
           FileOutputStream os = new FileOutputStream(newXML);
           StreamResult result = new StreamResult(os);
           transformer.transform(source, result);
    }
}
