package controllers;

import play.*;
import play.data.*;
import play.mvc.*;

import models.*;
import views.html.*;

import java.util.*;

public class Application extends Controller {

    public static class SampleForm {
        public List<String> inputs;
    }

    public static Result index() {
        // Form<SampleForm> form = new Form(SampleForm.class);
        List<Message> msgs = Message.find.all();
        return ok(index.render("please set form.", msgs));
    }

}
