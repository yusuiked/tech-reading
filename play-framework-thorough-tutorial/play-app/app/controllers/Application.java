package controllers;

import play.*;
import play.data.*;
import play.mvc.*;

import models.*;
import views.html.*;

import java.util.*;
import scala.*;

public class Application extends Controller {

    public static Result index() {
        // Form<SampleForm> form = new Form(SampleForm.class);
        List<Message> msgs = Message.find.all();
        return ok(index.render("please set form.", msgs));
    }

    public static Result add() {
        Form<Message> f = new Form(Message.class);
        List<Member> mems = Member.find.select("name").findList();
        List<Tuple2<String, String>> opts = new ArrayList<Tuple2<String, String>>();
        for (Member mem : mems) {
            opts.add(new Tuple2(mem.name, mem.name));
        }
        return ok(add.render("投稿フォーム" , f, opts));
    }

    public static Result create() {
        return redirect("/");
    }

}
