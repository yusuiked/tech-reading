package controllers;

import com.avaje.ebean.ExpressionList;

import play.*;
import play.data.*;
import play.data.validation.Constraints.Required;
import play.db.ebean.*;
import play.mvc.*;

import models.*;
import views.html.*;

import java.util.*;

public class Application extends Controller {

    public static Result index() {
        List<Message> messages = Message.find.all();
        List<Member> members = Member.find.all();
        return ok(index.render("データベースのサンプル", messages, members));
    }

    public static Result addMessage() {
        Form<Message> f = new Form(Message.class);
        return ok(addMessage.render("投稿フォーム", f));
    }

    public static Result createMessage() {
        Form<Message> f = new Form(Message.class).bindFromRequest();
        if (!f.hasErrors()) {
            Message message = f.get();
            String[] names = message.name.split(",");
            for (String name : names) {
                Member m = Member.findByName(name);
                message.members.add(m);
                m.messages.add(message);
            }
            message.save();
            return redirect("/");
        } else {
            return badRequest(addMessage.render("ERROR", f));
        }
    }

    public static Result addMember() {
        Form<Member> f = new Form(Member.class);
        return ok(addMember.render("投稿フォーム", f));
    }

    public static Result createMember() {
        Form<Member> f = new Form(Member.class).bindFromRequest();
        if (!f.hasErrors()) {
            Member member = f.get();
            member.save();
            return redirect("/");
        } else {
            return badRequest(addMember.render("ERROR", f));
        }
    }

}
