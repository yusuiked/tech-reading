package controllers;

import play.*;
import play.data.*;
import play.libs.Json;
import play.mvc.*;
import play.mvc.Result;

import models.*;
import views.html.*;

import java.io.*;
import java.util.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class Application extends Controller {

    public static Result index() {
        Member m = new Member();
        m.name = "hoge";
        m.mail = "hoge@hoge.com";
        m.save();
        List<Message> msgs = Message.find.all();
        return ok(index.render("please set form.", msgs));
    }

    public static Result ajax() {
        String input = request().body().asFormUrlEncoded().get("input")[0];
        Member mem = Member.findByName(input);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String str = "<xml><root><err>ERROR!</err></root>";
        Document doc = null;
        try {
            doc = factory.newDocumentBuilder().newDocument();
            Element root = doc.createElement("data");
            Element el = doc.createElement("name");
            el.appendChild(doc.createTextNode(mem.name));
            root.appendChild(el);
            el = doc.createElement("mail");
            el.appendChild(doc.createTextNode(mem.mail));
            root.appendChild(el);
            el = doc.createElement("tel");
            el.appendChild(doc.createTextNode(mem.tel));
            root.appendChild(el);
            doc.appendChild(root);
            TransformerFactory tfactory = TransformerFactory.newInstance();
            StringWriter writer = new StringWriter();
            StreamResult stream = new StreamResult(writer);
            Transformer trans = tfactory.newTransformer();
            trans.transform(new DOMSource(doc.getDocumentElement()), stream);
            str = stream.getWriter().toString();
        } catch (ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        if (doc == null) {
            return badRequest(str);
        } else {
            return ok(str);
        }
    }
}
