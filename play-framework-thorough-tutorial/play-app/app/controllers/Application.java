package controllers;

import java.util.List;

import play.*;
import play.data.*;
import static play.data.Form.*;
import play.mvc.*;

import models.Message;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        List<Message> data = Message.find.all();
        return ok(index.render("データベースのサンプル", data));
    }
}
