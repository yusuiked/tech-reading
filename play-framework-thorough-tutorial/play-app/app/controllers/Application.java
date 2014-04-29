package controllers;

// import org.codehaus.jackson.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.*;
import play.data.*;
import play.libs.Json;
import play.mvc.*;

import models.*;
import views.html.*;

import java.util.*;
import scala.*;

public class Application extends Controller {

    public static Result index() {
        List<Message> msgs = Message.find.all();
        return ok(index.render("please set form.", msgs));
    }

    public static Result ajax() {
        String input = request().body().asFormUrlEncoded().get("input")[0];
        ObjectNode result = Json.newObject();
        if (input == null) {
            result.put("status", "BAD");
            result.put("message", "Can't get sending data...");
            return badRequest(result);
        } else {
            result.put("status", "OK");
            result.put("message", input);
            return ok(result);
        }
    }
}
